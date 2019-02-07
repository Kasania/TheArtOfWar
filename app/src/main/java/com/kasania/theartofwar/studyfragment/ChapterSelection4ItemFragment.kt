package com.kasania.theartofwar.studyfragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kasania.theartofwar.MainActivity
import com.kasania.theartofwar.R
import kotlinx.android.synthetic.main.fragment_study_chapter_selection_4.view.*

class ChapterSelection4ItemFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater.inflate(R.layout.fragment_study_chapter_selection_4, container, false)
        view.btn_study_gochapter8.setOnClickListener {
            StudyMainFragment.changeStudyContents(fragmentManager,8)
        }
        view.btn_study_gochapter9.setOnClickListener {
            StudyMainFragment.changeStudyContents(fragmentManager,9)
        }
        view.btn_study_gochapter10.setOnClickListener {
            StudyMainFragment.changeStudyContents(fragmentManager,10)
        }
        view.btn_study_gochapter11.setOnClickListener {
            StudyMainFragment.changeStudyContents(fragmentManager,11)
        }
        return view
    }
}