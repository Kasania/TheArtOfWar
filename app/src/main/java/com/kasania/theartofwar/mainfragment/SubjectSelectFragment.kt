package com.kasania.theartofwar.mainfragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kasania.theartofwar.R
import com.kasania.theartofwar.enableAnimation
import com.kasania.theartofwar.studyfragment.ChapterSelection2ItemFragment
import com.kasania.theartofwar.studyfragment.ChapterSelection3ItemFragment
import com.kasania.theartofwar.studyfragment.ChapterSelection4ItemFragment
import com.kasania.theartofwar.studyfragment.StudyMainFragment
import kotlinx.android.synthetic.main.fragment_study_subject_select.view.*

class SubjectSelectFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater.inflate(R.layout.fragment_study_subject_select, container, false)
        initClick(view)

        return view
    }

    private fun initClick(view: View) {

        view.btn_subject_1.setOnClickListener {
            StudyMainFragment.createStudyContents(fragmentManager, 1)
        }

        view.btn_subject_2.setOnClickListener {
            StudyMainFragment.createStudyContents(fragmentManager, 2)
        }

        view.btn_subject_3.setOnClickListener {
            StudyMainFragment.createStudyContents(fragmentManager, 3)
        }

        view.btn_subject_4.setOnClickListener {
            StudyMainFragment.createStudyContents(fragmentManager, 4)
        }

        view.btn_subject_5.setOnClickListener {
            StudyMainFragment.createStudyContents(fragmentManager, 5)
        }

        view.btn_subject_6.setOnClickListener {
            StudyMainFragment.createStudyContents(fragmentManager, 6)
        }

        view.btn_subject_7.setOnClickListener {
            StudyMainFragment.createStudyContents(fragmentManager, 7)
        }

        view.btn_subject_8.setOnClickListener {
            StudyMainFragment.createStudyContents(fragmentManager, 8)
        }

        view.btn_subject_9.setOnClickListener {
            StudyMainFragment.createStudyContents(fragmentManager, 9)
        }

        view.btn_subject_10.setOnClickListener {
            StudyMainFragment.createStudyContents(fragmentManager, 10)
        }

        view.btn_subject_11.setOnClickListener {
            StudyMainFragment.createStudyContents(fragmentManager, 11)
        }

        view.btn_subject_12.setOnClickListener {
            StudyMainFragment.createStudyContents(fragmentManager, 12)
        }

        view.btn_subject_13.setOnClickListener {
            StudyMainFragment.createStudyContents(fragmentManager, 13)
        }
    /*
        view.btn_subject_2.setOnClickListener {
            val ft = fragmentManager!!.beginTransaction()
            if(enableAnimation){
                ft.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left,R.anim.slide_in_left,R.anim.slide_out_right)
            }
            ft.replace(R.id.contents_panel_main, ChapterSelection2ItemFragment()).
            addToBackStack("Subject2").
            commit()
        }

        view.btn_subject_3.setOnClickListener {
            StudyMainFragment.createStudyContents(fragmentManager, 4)
        }

        view.btn_subject_4.setOnClickListener {
            val ft = fragmentManager!!.beginTransaction()
            if(enableAnimation){
                ft.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left,R.anim.slide_in_left,R.anim.slide_out_right)
            }
            ft.replace(R.id.contents_panel_main, ChapterSelection3ItemFragment()).
                addToBackStack("Subject4").
                commit()
        }

        view.btn_subject_5.setOnClickListener {
            val ft = fragmentManager!!.beginTransaction()
            if(enableAnimation){
                ft.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left,R.anim.slide_in_left,R.anim.slide_out_right)
            }
            ft.replace(R.id.contents_panel_main, ChapterSelection4ItemFragment()).
                addToBackStack("Subject5").
                commit()
        }

        view.btn_subject_6.setOnClickListener {
            StudyMainFragment.createStudyContents(fragmentManager, 12)
        }

        view.btn_subject_7.setOnClickListener {
            StudyMainFragment.createStudyContents(fragmentManager, 13)
        }
    */
    }

    override fun onDestroyView() {
        Log.d("SubjectSelect","DestroyView")
        super.onDestroyView()
    }

}