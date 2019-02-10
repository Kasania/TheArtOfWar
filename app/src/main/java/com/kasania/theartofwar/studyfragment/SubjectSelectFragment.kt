package com.kasania.theartofwar.studyfragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kasania.theartofwar.R
import com.kasania.theartofwar.enableAnimation
import kotlinx.android.synthetic.main.fragment_study_subject_select.view.*

class SubjectSelectFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater.inflate(R.layout.fragment_study_subject_select, container, false)
        initClick(view)

        return view
    }

    private fun initClick(view: View) {

        view.contents_subject_1.setOnClickListener {
            StudyMainFragment.createStudyContents(fragmentManager,1)
        }

        view.contents_subject_2.setOnClickListener {
            val ft = fragmentManager!!.beginTransaction()
            if(enableAnimation){
                ft.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left,R.anim.slide_in_left,R.anim.slide_out_right)
            }
            ft.replace(R.id.contents_panel_main,ChapterSelection2ItemFragment()).
            addToBackStack("Subject2").
            commit()
        }

        view.contents_subject_3.setOnClickListener {
            StudyMainFragment.createStudyContents(fragmentManager,4)
        }

        view.contents_subject_4.setOnClickListener {
            val ft = fragmentManager!!.beginTransaction()
            if(enableAnimation){
                ft.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left,R.anim.slide_in_left,R.anim.slide_out_right)
            }
            ft.replace(R.id.contents_panel_main,ChapterSelection3ItemFragment()).
                addToBackStack("Subject4").
                commit()
        }

        view.contents_subject_5.setOnClickListener {
            val ft = fragmentManager!!.beginTransaction()
            if(enableAnimation){
                ft.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left,R.anim.slide_in_left,R.anim.slide_out_right)
            }
            ft.replace(R.id.contents_panel_main,ChapterSelection4ItemFragment()).
                addToBackStack("Subject5").
                commit()
        }

        view.contents_subject_6.setOnClickListener {
            StudyMainFragment.createStudyContents(fragmentManager,12)
        }

        view.contents_subject_7.setOnClickListener {
            StudyMainFragment.createStudyContents(fragmentManager,13)
        }

    }

}