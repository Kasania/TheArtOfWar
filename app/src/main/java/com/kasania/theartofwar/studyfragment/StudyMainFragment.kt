package com.kasania.theartofwar.studyfragment

import android.app.AlertDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.kasania.theartofwar.R
import kotlinx.android.synthetic.main.dialog_study_quick_chepter_select.*
import kotlinx.android.synthetic.main.fragment_study_main.view.*

class StudyMainFragment : Fragment(){

    companion object {

        var chapter: Int? = null
        var phrase: Int? = null
        val phraseNum = intArrayOf(0,12,12,11,10,10,17,15,7,19,16,26,26,12)
    }
//
//
//
    fun newInstance(chapter:Int,phrase:Int = 0): StudyMainFragment
    {
        val args = Bundle()
        val frag = StudyMainFragment()
        StudyMainFragment.chapter = chapter
        StudyMainFragment.phrase = phrase
        return frag
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {

        val view = inflater.inflate(R.layout.fragment_study_main, container, false)
        val currentChapterText = "${resources.getString(R.string.chapter_prefix_name)} $chapter ${resources.getString(R.string.chapter_postfix_name)}"

        view.tv_study_current_chapter.text = currentChapterText

        if(phrase == 0) {
            fragmentManager?.beginTransaction()?.replace(R.id.contents_panel_study_main,StudyChapterSummaryFragment())?.commit()
        }else{
            fragmentManager?.beginTransaction()?.replace(R.id.contents_panel_study_main,StudyPhraseMainFragment()/*.newInstance(chapter!!,phrase!!)*/)?.commit()
        }
        view.btn_study_back_to_chapter.setOnClickListener { v->
            fragmentManager!!.beginTransaction().replace(R.id.contents_panel_main,SubjectSelectFragment()).commit()
        }

        view.btn_study_quick_select.setOnClickListener { v->
            showQuickChapterSelectionDialog()

        }
        return view
    }

    fun showQuickChapterSelectionDialog(){

        QuickChapterSelectDialog().newInstance().show(fragmentManager,"Quick Select")


//        val dialog = AlertDialog.Builder(context).setView(layoutInflater.inflate(R.layout.dialog_study_quick_chepter_select,null)).create()
//
//
//        dialog.np_quick_chapter_chapter.minValue = 0
//        dialog.np_quick_chapter_chapter.maxValue = 13
//        dialog.np_quick_chapter_chapter.value = chapter!!
//        dialog.np_quick_chapter_chapter.wrapSelectorWheel = false
//
//        dialog.np_quick_chapter_phrase.minValue = 0
//        dialog.np_quick_chapter_phrase.maxValue = phraseNum[chapter!!]
//        dialog.np_quick_chapter_phrase.value = phrase!!
//        dialog.np_quick_chapter_phrase.wrapSelectorWheel = false
//
//
//
//        dialog.btn_quick_chapter_submit.setOnClickListener { v->
//
//            Toast.makeText(context,"${dialog.np_quick_chapter_chapter.value},${dialog.np_quick_chapter_phrase.value}",Toast.LENGTH_SHORT).show()
//            dialog.dismiss()
//        }
//
//        dialog.show()

    }



}