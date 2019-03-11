package com.kasania.theartofwar.studyfragment

import android.os.Build
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.text.Html
import android.text.util.Linkify
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.kasania.theartofwar.R
import com.kasania.theartofwar.TTS
import kotlinx.android.synthetic.main.fragment_study_phrase_interpret.view.*
import java.util.regex.Pattern


class StudyPhraseInterpretFragment : Fragment() {

    private lateinit var tts : TTS

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_study_phrase_interpret, container, false)

        createHanjaSet(view)

        createVocaSet(view)

        createInterpretSet(view)

        return view
    }

    private fun createHanjaSet(view : View){
        val hanjaId = resources.getIdentifier("@string/hanja_${StudyMainFragment.currentChapter}_${StudyMainFragment.currentPhrase}","String",context?.packageName)
        val soundId = resources.getIdentifier("@string/sound_${StudyMainFragment.currentChapter}_${StudyMainFragment.currentPhrase}","String",context?.packageName)


        val hanjaValue = getText(hanjaId).split("//")
        val soundValue = getText(soundId).split("//")
        var ttsSound = ""
        val hanjaLayout = LinearLayout(context)
        hanjaLayout.orientation = LinearLayout.VERTICAL

        for (i in 0.. hanjaValue.lastIndex){
            val hanjaSet = LinearLayout(context)
            hanjaSet.orientation = LinearLayout.VERTICAL
            hanjaSet.gravity = Gravity.CENTER
            val hanjaSetLayout = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            hanjaSetLayout.setMargins(0,0,0,15)
            hanjaSet.layoutParams = hanjaSetLayout

            val hanjaView = TextView(context)
            hanjaView.text = StudyMainFragment.convertColoredText(hanjaValue[i])
            hanjaView.textSize=22.0f
            hanjaView.gravity = Gravity.CENTER
            hanjaView.setTextColor(ContextCompat.getColor(context!!,R.color.Black))

            val soundView = TextView(context)
            soundView.text = StudyMainFragment.convertColoredText(soundValue[i])
            soundView.textSize=22.0f
            soundView.gravity = Gravity.CENTER
            soundView.setTextColor(ContextCompat.getColor(context!!,R.color.Black))

//            hanjaSet.setOnClickListener {
//                speech(soundValue[i])
//            }

            ttsSound += soundView.text.toString() + "\n"
            hanjaSet.addView(hanjaView)
            hanjaSet.addView(soundView)
            hanjaLayout.addView(hanjaSet)
        }
        hanjaLayout.setOnClickListener {
            tts.speech(ttsSound)
        }

        view.sv_phrase_interpret.addView(hanjaLayout)

    }

    private fun createVocaSet(view:View){
        val id = resources.getIdentifier("@string/voca_${StudyMainFragment.currentChapter}_${StudyMainFragment.currentPhrase}","String",context?.packageName)

        val vocaLines = getText(id).split("//")
        if (vocaLines[0] != ""){
            val vocaLayout = LinearLayout(context)
            vocaLayout.orientation = LinearLayout.VERTICAL

            for (s in vocaLines){

                val vocaValue = s.split("|")
                val vocaContainer = LinearLayout(context)

                val vocaChar = TextView(context)
                val vocaInterpret = TextView(context)

                vocaChar.text = "• ${StudyMainFragment.convertColoredText(vocaValue[0])} : "
                vocaChar.textSize = 24f
                vocaChar.setTextColor(ContextCompat.getColor(context!!,R.color.Black))

                vocaContainer.addView(vocaChar)

                if(vocaValue.lastIndex > 0){
                    vocaContainer.addView(vocaInterpret)
                    vocaInterpret.text = vocaValue[1]
                    vocaInterpret.textSize = 18f
                    vocaInterpret.setTextColor(ContextCompat.getColor(context!!,R.color.Black))
                    vocaInterpret.gravity = Gravity.CENTER_VERTICAL
                    val layout = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT)
                    layout.setMargins(10,0,0,0)
                    vocaInterpret.layoutParams = layout
                }

                val layout = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT)
                layout.setMargins(10,20,10,10)
                vocaContainer.layoutParams = layout
                vocaLayout.addView(vocaContainer)

            }
            vocaLayout.background = ContextCompat.getDrawable(context!!,R.drawable.img_line_contants)
            view.sv_phrase_interpret.addView(vocaLayout)
        }

    }

    private fun createInterpretSet(view : View){
        val interpretId = resources.getIdentifier("@string/interpret_${StudyMainFragment.currentChapter}_${StudyMainFragment.currentPhrase}","String",context?.packageName)
        val interpretValue = getText(interpretId)/*.split("/")*/
        if(interpretValue != ""){
            val interpretView = TextView(context)
            interpretView.text = StudyMainFragment.convertColoredText(interpretValue.toString())
            interpretView.textSize=22.0f
            interpretView.gravity = Gravity.START
            interpretView.setTextColor(ContextCompat.getColor(context!!,R.color.Black))
            interpretView.background = ContextCompat.getDrawable(context!!,R.drawable.img_line_contants)
            interpretView.setOnClickListener {
                tts.speech(interpretView.text.toString())
            }

            val transform = Linkify.TransformFilter { _, _ ->  ""}

            val sunjaURL = Pattern.compile("손자")
            Linkify.addLinks(interpretView,sunjaURL,"https://www.google.com/search?q=손무",null,transform)

//
            val interpretLayout = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT)
            interpretLayout.setMargins(10,20,10,10)
            interpretView.layoutParams = interpretLayout

            view.sv_phrase_interpret.addView(interpretView)
        }
    }

    fun addTTS(tts:TTS) : Fragment{
        this.tts = tts
        return this
    }




}