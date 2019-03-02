package com.kasania.theartofwar.studyfragment

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kasania.theartofwar.R
import kotlinx.android.synthetic.main.fragment_study_phrase_main.view.*
import java.util.*


class StudyPhraseMainFragment : Fragment() {

    lateinit var tts : TextToSpeech

    var ttsSupport = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater.inflate(R.layout.fragment_study_phrase_main, container, false)
        initTTS()

        fragmentManager?.beginTransaction()?.replace(R.id.contents_panel_study_phrase,StudyPhraseInterpretFragment().addTTS(tts,ttsSupport))?.commit()


        view.tabbar_study_contents.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {

                when(view.tabbar_study_contents.selectedTabPosition){
                    0 -> fragmentManager?.beginTransaction()?.replace(R.id.contents_panel_study_phrase,StudyPhraseInterpretFragment().addTTS(tts,ttsSupport))?.commit()
//                    1 -> fragmentManager?.beginTransaction()?.replace(R.id.contents_panel_study_phrase,StudyPhraseVocaFragment())?.commit()
                    1 -> fragmentManager?.beginTransaction()?.replace(R.id.contents_panel_study_phrase,StudyPhraseCommentFragment().addTTS(tts,ttsSupport))?.commit()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })



        return view
    }

    private fun initTTS(){
        tts = TextToSpeech(context, TextToSpeech.OnInitListener { status ->
            if (status == TextToSpeech.SUCCESS) {
                val result = tts.setLanguage(Locale.KOREAN)
                ttsSupport = result
                when (result) {
                    TextToSpeech.LANG_MISSING_DATA -> false
                    TextToSpeech.LANG_NOT_SUPPORTED -> false
                    else -> {
                        tts.setPitch(1.0f)
                        tts.setSpeechRate(1.0f)
                        ttsSupport = result
                    }
                }
            }
        })
    }


    override fun onStop() {
        if(tts != null){
            tts.stop()
            tts.shutdown()
        }
        super.onStop()
    }

    fun MakeContents(){

    }
}