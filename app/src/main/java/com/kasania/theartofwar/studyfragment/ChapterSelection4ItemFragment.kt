package com.kasania.theartofwar.studyfragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kasania.theartofwar.R
import kotlinx.android.synthetic.main.fragment_study_chapter_selection_4.view.*

class ChapterSelection4ItemFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater.inflate(R.layout.fragment_study_chapter_selection_4, container, false)
        view.btn_backPage.setOnClickListener { v->
            val fm = fragmentManager
            val ft = fm!!.beginTransaction()
            ft!!.replace(R.id.contents_panel_main,SubjectSelectFragment())
            ft!!.commit()
        }
        view.btn_study_gochapter8.setOnClickListener { v->
            fragmentManager?.beginTransaction()?.replace(R.id.contents_panel_main,StudyMainFragment().newInstance(8))?.commit()
        }
        view.btn_study_gochapter9.setOnClickListener { v->
            fragmentManager?.beginTransaction()?.replace(R.id.contents_panel_main,StudyMainFragment().newInstance(9))?.commit()
        }
        view.btn_study_gochapter10.setOnClickListener { v->
            fragmentManager?.beginTransaction()?.replace(R.id.contents_panel_main,StudyMainFragment().newInstance(10))?.commit()
        }
        view.btn_study_gochapter11.setOnClickListener { v->
            fragmentManager?.beginTransaction()?.replace(R.id.contents_panel_main,StudyMainFragment().newInstance(11))?.commit()
        }
        return view
    }
}