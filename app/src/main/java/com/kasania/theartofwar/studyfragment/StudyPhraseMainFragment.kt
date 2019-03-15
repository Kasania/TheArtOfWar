package com.kasania.theartofwar.studyfragment

import android.os.Build
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.kasania.theartofwar.MainActivity
import com.kasania.theartofwar.R
import com.kasania.theartofwar.TTS
import kotlinx.android.synthetic.main.fragment_study_main.view.*
import kotlinx.android.synthetic.main.fragment_study_phrase_main.view.*
import java.util.*


class StudyPhraseMainFragment : Fragment() {

    lateinit var tts : TTS

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater.inflate(R.layout.fragment_study_phrase_main, container, false)
        tts = TTS()
        tts.initialize(context!!)

        fragmentManager?.beginTransaction()?.replace(R.id.contents_panel_study_phrase,StudyPhraseInterpretFragment().addTTS(tts))?.commit()



        return view
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.d("phraseMain","DestroyView")
        super.onSaveInstanceState(outState)
    }

}