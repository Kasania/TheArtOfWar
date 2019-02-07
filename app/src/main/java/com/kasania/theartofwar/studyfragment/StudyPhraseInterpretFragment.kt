package com.kasania.theartofwar.studyfragment

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.ERROR
import android.support.v4.app.Fragment
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.kasania.theartofwar.R
import kotlinx.android.synthetic.main.fragment_study_phrase_interpret.view.*
import java.util.*


class StudyPhraseInterpretFragment : Fragment() {

    lateinit var tts : TextToSpeech

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_study_phrase_interpret, container, false)

        val hanjaId = resources.getIdentifier("@string/hanja_${StudyMainFragment.currentChapter}_${StudyMainFragment.currentPhrase}","String",context?.packageName)
        val soundId = resources.getIdentifier("@string/sound_${StudyMainFragment.currentChapter}_${StudyMainFragment.currentPhrase}","String",context?.packageName)
        val interpretId = resources.getIdentifier("@string/interpret_${StudyMainFragment.currentChapter}_${StudyMainFragment.currentPhrase}","String",context?.packageName)

        val hanjaValue = getText(hanjaId).split("/")
        val soundValue = getText(soundId).split("/")
        val interpretValue = getText(interpretId).split("/")

        tts = TextToSpeech(context, TextToSpeech.OnInitListener { status ->
            if (status != ERROR) {
                tts.language = Locale.KOREAN

            }
        })



        for (i in 0.. hanjaValue.lastIndex){
            val interpretSet = LinearLayout(context)
            interpretSet.orientation = LinearLayout.HORIZONTAL
            interpretSet.gravity = Gravity.LEFT

            interpretSet.setOnClickListener{

            }

            val hanjaSet = LinearLayout(context)
            hanjaSet.orientation = LinearLayout.VERTICAL
            hanjaSet.layoutParams = LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT,0.45f)
            hanjaSet.gravity = Gravity.CENTER

            val hanjaView = TextView(context)
            hanjaView.text = hanjaValue[i]
            hanjaView.textSize=24.0f
            hanjaView.gravity = Gravity.CENTER




            val soundView = TextView(context)
            soundView.text = soundValue[i]
            soundView.textSize=24.0f
            soundView.gravity = Gravity.CENTER

            val interpretView = TextView(context)
            interpretView.text = interpretValue[i]
            interpretView.textSize=24.0f
            interpretView.gravity = Gravity.CENTER
            interpretView.layoutParams = LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT,0.55f)


            hanjaSet.addView(hanjaView)
            hanjaSet.addView(soundView)

            interpretSet.addView(hanjaSet)
            interpretSet.addView(interpretView)
            view.sv_phrase_interpret.addView(interpretSet)
        }


        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        if(tts != null){
            tts.stop()
            tts.shutdown()
        }
    }
}