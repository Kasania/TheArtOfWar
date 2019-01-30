package com.kasania.theartofwar.studyfragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.kasania.theartofwar.R
import kotlinx.android.synthetic.main.fragment_study_chapter_summary.view.*

class StudyChapterSummaryFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_study_chapter_summary, container, false)

        val id = resources.getIdentifier("@string/chapterSummary${StudyMainFragment.currentChapter}","String",context?.packageName)

        val summaryValue = getText(id).split("/")

        for (s in summaryValue){
            val textView = TextView(context)
            textView.text = s

            view.sv_chapter_summary.addView(textView)
        }
        return view
    }
}