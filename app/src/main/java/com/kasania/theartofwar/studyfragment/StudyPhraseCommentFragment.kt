package com.kasania.theartofwar.studyfragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.kasania.theartofwar.R
import kotlinx.android.synthetic.main.fragment_study_phrase_comment.view.*

class StudyPhraseCommentFragment :Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_study_phrase_comment, container, false)

        val commentId = resources.getIdentifier("@string/comment_${StudyMainFragment.currentChapter}_${StudyMainFragment.currentPhrase}","String",context?.packageName)
        val commentValue = getText(commentId).split("/")

        for (s in commentValue){
            val commentView = TextView(context)
            commentView.text = s
            commentView.textSize = 20f
            commentView.setTextColor(ContextCompat.getColor(context!!,R.color.Black))
            val commentViewLayout = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            commentViewLayout.setMargins(10,20,10,15)
            commentView.layoutParams = commentViewLayout
            view.sv_phrase_comment.addView(commentView)
        }

        return view
    }
}