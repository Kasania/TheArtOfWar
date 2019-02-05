package com.kasania.theartofwar.studyfragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kasania.theartofwar.R
import kotlinx.android.synthetic.main.fragment_study_subject_select.view.*

class SubjectSelectFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater.inflate(R.layout.fragment_study_subject_select, container, false)
        view.contents_subject_1

        initClick(view)

        return view
    }

    private fun initClick(view: View) {

        view.contents_subject_1.setOnClickListener {
            StudyMainFragment.changeStudyContents(fragmentManager,1)
        }


        view.contents_subject_2.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.contents_panel_main,ChapterSelection2ItemFragment())?.commit()
        }

        view.contents_subject_3.setOnClickListener {
            StudyMainFragment.changeStudyContents(fragmentManager,4)
        }

        view.contents_subject_4.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.contents_panel_main,ChapterSelection3ItemFragment())?.commit()
        }

        view.contents_subject_5.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.contents_panel_main,ChapterSelection4ItemFragment())?.commit()
        }

        view.contents_subject_6.setOnClickListener {
            StudyMainFragment.changeStudyContents(fragmentManager,12)
        }

        view.contents_subject_7.setOnClickListener {
            StudyMainFragment.changeStudyContents(fragmentManager,13)
        }

    }

}