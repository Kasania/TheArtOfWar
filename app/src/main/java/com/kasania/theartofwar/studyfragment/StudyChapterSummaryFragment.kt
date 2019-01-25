package com.kasania.theartofwar.studyfragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kasania.theartofwar.R
import com.kasania.theartofwar.studyfragment.StudyMainFragment.Companion.currentChapter
import kotlinx.android.synthetic.main.fragment_study_chapter_summary.view.*

class StudyChapterSummaryFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_study_chapter_summary, container, false)

        view.tv_study_chapter_summary.text = "$currentChapter 편 총괄"


        return view
    }
}