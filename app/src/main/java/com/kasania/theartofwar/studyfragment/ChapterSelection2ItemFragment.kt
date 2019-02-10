package com.kasania.theartofwar.studyfragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kasania.theartofwar.R
import kotlinx.android.synthetic.main.fragment_study_chapter_selection_2.view.*

class ChapterSelection2ItemFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {

        val view = inflater.inflate(R.layout.fragment_study_chapter_selection_2, container, false)
        view.btn_study_gochapter2.setOnClickListener {
            StudyMainFragment.createStudyContents(fragmentManager,2)
        }
        view.btn_study_gochapter3.setOnClickListener {
            StudyMainFragment.createStudyContents(fragmentManager,3)
        }


        return view
    }

}