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
import kotlinx.android.synthetic.main.fragment_study_chapter_summary.view.*
import kotlinx.android.synthetic.main.fragment_study_menu.*
import kotlinx.android.synthetic.main.fragment_study_menu.view.*
import java.util.*

class StudyChapterSummaryFragment : Fragment() {
    lateinit var tts : TextToSpeech

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_study_chapter_summary, container, false)




//        initTTS()


        /*val id = resources.getIdentifier("@string/chapterSummary${StudyMainFragment.currentChapter}","String",context?.packageName)

        val summaryValue = getText(id).split("//")

        for (s in summaryValue){
            val textView = TextView(context)
            textView.text = StudyMainFragment.convertColoredText(s)
            textView.textSize = 20f
            textView.setTextColor(ContextCompat.getColor(context!!,R.color.Black))
            val commentViewLayout = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            commentViewLayout.setMargins(10,10,10,10)
            textView.layoutParams = commentViewLayout

            textView.setOnClickListener {
//                speech(s)
            }
            //view.sv_chapter_summary.addView(textView)
        }*/
        return view
    }

//    private fun initTTS(){
//        tts = TextToSpeech(context, TextToSpeech.OnInitListener { status ->
//            if (status == TextToSpeech.SUCCESS) {
//                val result = tts.setLanguage(Locale.KOREAN)
//                if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
//                    Toast.makeText(context,"TTS가 지원되지 않는 언어입니다.", Toast.LENGTH_SHORT).show()
//                }
//                else{
//                    tts.setPitch(1.0f)
//                    tts.setSpeechRate(1.0f)
//                }
//            }
//        })
//    }
//
//    private fun speech(text:String){
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
//            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
//        // API 20
//        else
//            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
//    }
}