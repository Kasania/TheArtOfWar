package com.kasania.theartofwar.studyfragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kasania.theartofwar.R
import kotlinx.android.synthetic.main.fragment_study_chapter_selection_3.view.*

class ChapterSelection3ItemFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {

        val view = inflater.inflate(R.layout.fragment_study_chapter_selection_3, container, false)
        view.btn_backPage3.setOnClickListener { v->
            fragmentManager?.beginTransaction()?.replace(R.id.contents_panel_main,SubjectSelectFragment())?.commit()
        }
        view.btn_study_gochapter5.setOnClickListener { v->
            fragmentManager?.beginTransaction()?.replace(R.id.contents_panel_main,StudyMainFragment().newInstance(5))?.commit()
        }
        view.btn_study_gochapter6.setOnClickListener { v->
            fragmentManager?.beginTransaction()?.replace(R.id.contents_panel_main,StudyMainFragment().newInstance(6))?.commit()
        }
        view.btn_study_gochapter7.setOnClickListener { v->
            fragmentManager?.beginTransaction()?.replace(R.id.contents_panel_main,StudyMainFragment().newInstance(7))?.commit()
        }
        return view
    }
}