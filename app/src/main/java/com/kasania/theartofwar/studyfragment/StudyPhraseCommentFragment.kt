package com.kasania.theartofwar.studyfragment

import android.os.Build
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.kasania.theartofwar.R
import com.kasania.theartofwar.TTS
import kotlinx.android.synthetic.main.fragment_study_phrase_comment.view.*

class StudyPhraseCommentFragment :Fragment() {

    lateinit var tts : TTS

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_study_phrase_comment, container, false)
        addTTS()
        StudyMainFragment.tts_id = tts
        Log.i("TAG", "Found tts in comment: ${StudyMainFragment.tts_id}")
        view.sv_phrase_comment.setBackgroundResource(R.drawable.outline)
        val commentId = resources.getIdentifier("@string/comment_${StudyMainFragment.currentChapter}_${StudyMainFragment.currentPhrase}","String",context?.packageName)
        val commentValue = getText(commentId).split("//")

        for (s in commentValue){
            val commentView = TextView(context)
            commentView.setLineSpacing(20f,1f)
            commentView.text = StudyMainFragment.convertColoredText(s)
            commentView.textSize = 22f
            commentView.setTextColor(ContextCompat.getColor(context!!,R.color.Black))
            val commentViewLayout = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            commentViewLayout.setMargins(30,20,30,30)
            commentView.layoutParams = commentViewLayout
            commentView.setOnClickListener {
                tts.speech(commentView.text.toString())
            }
            view.sv_phrase_comment.addView(commentView)
        }

        return view
    }

    fun addTTS() : Fragment{
        this.tts = TTS()
        tts.initialize(context!!)
        return this
    }

}