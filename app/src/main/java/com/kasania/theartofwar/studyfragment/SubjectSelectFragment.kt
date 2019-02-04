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

//        val chapter1Text = "${getText(R.string.subject_1_name)}\n${getText(R.string.chapter_prefix_name)} ${getText(R.string.subject_1_chapter)} ${getText(R.string.chapter_postfix_name)}"
//        view.btn_subject_1.text = chapter1Text
//        val chapter2Text = "${getText(R.string.subject_2_name)}\n${getText(R.string.chapter_prefix_name)} ${getText(R.string.subject_2_chapter)} ${getText(R.string.chapter_postfix_name)}"
//        view.btn_subject_2.text = chapter2Text
//        val chapter3Text = "${getText(R.string.subject_3_name)}\n${getText(R.string.chapter_prefix_name)} ${getText(R.string.subject_3_chapter)} ${getText(R.string.chapter_postfix_name)}"
//        view.btn_subject_3.text = chapter3Text
//        val chapter4Text = "${getText(R.string.subject_4_name)}\n${getText(R.string.chapter_prefix_name)} ${getText(R.string.subject_4_chapter)} ${getText(R.string.chapter_postfix_name)}"
//        view.btn_subject_4.text = chapter4Text
//        val chapter5Text = "${getText(R.string.subject_5_name)}\n${getText(R.string.chapter_prefix_name)} ${getText(R.string.subject_5_chapter)} ${getText(R.string.chapter_postfix_name)}"
//        view.btn_subject_5.text = chapter5Text
//        val chapter6Text = "${getText(R.string.subject_6_name)}\n${getText(R.string.chapter_prefix_name)} ${getText(R.string.subject_6_chapter)} ${getText(R.string.chapter_postfix_name)}"
//        view.btn_subject_6.text = chapter6Text
//        val chapter7Text = "${getText(R.string.subject_7_name)}\n${getText(R.string.chapter_prefix_name)} ${getText(R.string.subject_7_chapter)} ${getText(R.string.chapter_postfix_name)}"
//        view.btn_subject_7.text = chapter7Text
//        initClick(view)


        view.contents_subject_1

        initClick(view)

        return view
    }

    private fun initClick(view: View) {

        view.contents_subject_1.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.contents_panel_main,StudyMainFragment().newInstance(1))?.commit()
        }


        view.contents_subject_2.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.contents_panel_main,ChapterSelection2ItemFragment())?.commit()
        }

        view.contents_subject_3.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.contents_panel_main,StudyMainFragment().newInstance(4))?.commit()
        }

        view.contents_subject_4.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.contents_panel_main,ChapterSelection3ItemFragment())?.commit()
        }

        view.contents_subject_5.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.contents_panel_main,ChapterSelection4ItemFragment())?.commit()
        }

        view.contents_subject_6.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.contents_panel_main,StudyMainFragment().newInstance(12))?.commit()
        }

        view.contents_subject_7.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.contents_panel_main,StudyMainFragment().newInstance(13))?.commit()
        }

    }

}