package com.kasania.theartofwar.studyfragment

import android.os.Build
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.ERROR
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
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
        val interpretValue = getText(interpretId)/*.split("/")*/

        initTTS()



        for (i in 0.. hanjaValue.lastIndex){
            val hanjaSet = LinearLayout(context)
            hanjaSet.orientation = LinearLayout.VERTICAL
//            hanjaSet.layoutParams = LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT,0.45f)
            hanjaSet.gravity = Gravity.CENTER

            val hanjaView = TextView(context)
            hanjaView.text = hanjaValue[i]
            hanjaView.textSize=24.0f
            hanjaView.gravity = Gravity.CENTER
            hanjaView.setTextColor(ContextCompat.getColor(context!!,R.color.Black))

            val soundView = TextView(context)
            soundView.text = soundValue[i]
            soundView.textSize=24.0f
            soundView.gravity = Gravity.CENTER
            soundView.setTextColor(ContextCompat.getColor(context!!,R.color.Black))

            hanjaSet.setOnClickListener {
                speech(soundValue[i])
            }

//
            hanjaSet.addView(hanjaView)
            hanjaSet.addView(soundView)

//            interpretSet.addView(hanjaSet)
//            interpretSet.addView(interpretView)
            view.sv_phrase_interpret.addView(hanjaSet)
        }

        val interpretView = TextView(context)
        interpretView.text = interpretValue
        interpretView.textSize=24.0f
        interpretView.gravity = Gravity.CENTER
        interpretView.setTextColor(ContextCompat.getColor(context!!,R.color.Black))
        interpretView.setOnClickListener {
            speech(interpretValue.toString())
        }
//
        val interpretLayout = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT)
        interpretLayout.setMargins(10,20,10,10)
        interpretView.layoutParams = interpretLayout

        view.sv_phrase_interpret.addView(interpretView)


        return view
    }

    private fun initTTS(){
        tts = TextToSpeech(context, TextToSpeech.OnInitListener { status ->
            if (status == TextToSpeech.SUCCESS) {
                val result = tts.setLanguage(Locale.KOREAN)
                if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                    Toast.makeText(context,"TTS가 지원되지 않는 언어입니다.", Toast.LENGTH_SHORT).show()
                }
                else{
                    tts.setPitch(1.0f)
                    tts.setSpeechRate(1.0f)
                }
            }
        })
    }

    private fun speech(text:String){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        // API 20
        else
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }



    override fun onStop() {
        if(tts != null){
            tts.stop()
            tts.shutdown()
        }
        super.onStop()
    }
}