package com.kasania.theartofwar.studyfragment

import android.os.Build
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.kasania.theartofwar.R
import com.kasania.theartofwar.TTS
import kotlinx.android.synthetic.main.fragment_study_chapter_summary.view.*
import kotlinx.android.synthetic.main.fragment_study_menu.*
import kotlinx.android.synthetic.main.fragment_study_menu.view.*
import java.util.*

class StudyChapterSummaryFragment : Fragment() {
    lateinit var tts : TTS

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_study_chapter_summary, container, false)
        var ttsSound = ""

        addTTS()
        StudyMainFragment.tts_id = tts
        when (StudyMainFragment.currentChapter) {
            1 -> ttsSound += "1장 TTS 테스트 \n"
            2 -> ttsSound += "2장 TTS 테스트 \n"
            3 -> ttsSound += "3장 TTS 테스트 \n"
            4 -> ttsSound += "4장 TTS 테스트 \n"
            5 -> ttsSound += "5장 TTS 테스트 \n"
            6 -> ttsSound += "6장 TTS 테스트 \n"
            7 -> ttsSound += "7장 TTS 테스트 \n"
            8 -> ttsSound += "8장 TTS 테스트 \n"
            9 -> ttsSound += "9장 TTS 테스트 \n"
            10 -> ttsSound += "10장 TTS 테스트 \n"
            11 -> ttsSound += "11장 TTS 테스트 \n"
            12 -> ttsSound += "12장 TTS 테스트 \n"
            13 -> ttsSound += "13장 TTS 테스트 \n"
        }


        view.setOnClickListener {
            tts.speech(ttsSound)
        }

        return view
    }

private fun addTTS() : Fragment{
    this.tts = TTS()
    tts.initialize(context!!)
    return this
}
}
