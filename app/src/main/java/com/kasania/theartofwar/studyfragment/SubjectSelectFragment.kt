package com.kasania.theartofwar.studyfragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kasania.theartofwar.R
import kotlinx.android.synthetic.main.fragment_study_select_subject.view.*

class SubjectSelectFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater.inflate(R.layout.fragment_study_select_subject, container, false)

        initClick(view)

        return view
    }

    private fun initClick(view: View) {
        view.btn_subject_1.setOnClickListener { v->
            fragmentManager?.beginTransaction()?.replace(R.id.contents_panel_main,StudyMainFragment().newInstance(1))?.commit()
        }
        view.btn_subject_2.setOnClickListener { v->
            fragmentManager?.beginTransaction()?.replace(R.id.contents_panel_main,ChapterSelection2ItemFragment())?.commit()
        }
        view.btn_subject_3.setOnClickListener { v->
            fragmentManager?.beginTransaction()?.replace(R.id.contents_panel_main,StudyMainFragment().newInstance(4))?.commit()
        }
        view.btn_subject_4.setOnClickListener { v->
            fragmentManager?.beginTransaction()?.replace(R.id.contents_panel_main,ChapterSelection3ItemFragment())?.commit()
        }
        view.btn_subject_5.setOnClickListener { v->
            fragmentManager?.beginTransaction()?.replace(R.id.contents_panel_main,ChapterSelection4ItemFragment())?.commit()
        }
        view.btn_subject_6.setOnClickListener { v->
            fragmentManager?.beginTransaction()?.replace(R.id.contents_panel_main,StudyMainFragment().newInstance(12))?.commit()
        }
        view.btn_subject_7.setOnClickListener { v->
            fragmentManager?.beginTransaction()?.replace(R.id.contents_panel_main,StudyMainFragment().newInstance(13))?.commit()
        }

    }

}