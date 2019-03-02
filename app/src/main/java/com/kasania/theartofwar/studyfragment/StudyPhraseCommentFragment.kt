package com.kasania.theartofwar.studyfragment

import android.os.Build
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.kasania.theartofwar.R
import kotlinx.android.synthetic.main.fragment_study_phrase_comment.view.*

class StudyPhraseCommentFragment :Fragment() {

    lateinit var tts : TextToSpeech
    private var ttsSupport = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_study_phrase_comment, container, false)

        val commentId = resources.getIdentifier("@string/comment_${StudyMainFragment.currentChapter}_${StudyMainFragment.currentPhrase}","String",context?.packageName)
        val commentValue = getText(commentId).split("/")

        for (s in commentValue){
            val commentView = TextView(context)
            commentView.text = s
            commentView.textSize = 22f
            commentView.setTextColor(ContextCompat.getColor(context!!,R.color.Black))
            val commentViewLayout = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            commentViewLayout.setMargins(10,20,10,15)
            commentView.layoutParams = commentViewLayout
            commentView.setOnClickListener {
                speech(s)
            }
            view.sv_phrase_comment.addView(commentView)
        }

        return view
    }

    fun addTTS(tts:TextToSpeech, isSupport : Int) : Fragment{
        this.tts = tts
        this.ttsSupport = isSupport
        return this
    }

    private fun speech(text:String){
        when (ttsSupport) {
            TextToSpeech.LANG_MISSING_DATA -> Toast.makeText(context,"TTS에 필요한 음성팩이 없습니다.", Toast.LENGTH_SHORT).show()
            TextToSpeech.LANG_NOT_SUPPORTED -> Toast.makeText(context,"TTS가 지원되지 않는 언어입니다.", Toast.LENGTH_SHORT).show()
            else -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
                // API 20
                else
                    tts.speak(text, TextToSpeech.QUEUE_FLUSH, null)
            }
        }
    }

}