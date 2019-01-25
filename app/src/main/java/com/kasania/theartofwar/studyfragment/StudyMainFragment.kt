package com.kasania.theartofwar.studyfragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.kasania.theartofwar.R
import kotlinx.android.synthetic.main.fragment_study_main.view.*

class StudyMainFragment : Fragment(){

    companion object {


        const val minChapterNum = 1
        const val maxChapterNum = 13
        var currentChapter: Int? = null
        var currentPhrase: Int? = null
        val maxPhraseNum = intArrayOf(0,12,12,11,10,10,17,15,7,19,16,26,26,12)
        val displayPhraseName  = arrayOf("총괄","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26")
    }
//
//
//
    fun newInstance(chapter:Int = 0,phrase:Int = 0): StudyMainFragment
    {
        Companion.currentChapter = chapter
        Companion.currentPhrase = phrase
        return newInstance()
    }

    fun newInstance():StudyMainFragment{
        val args = Bundle()
        val frag = StudyMainFragment()
        return frag
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {

        val view = inflater.inflate(R.layout.fragment_study_main, container, false)
        val currentChapterText = "${resources.getString(R.string.chapter_prefix_name)} $currentChapter ${resources.getString(R.string.chapter_postfix_name)}"

        view.tv_study_current_chapter.text = currentChapterText

        if(currentPhrase == 0) {
            fragmentManager?.beginTransaction()?.replace(R.id.contents_panel_study_main,StudyChapterSummaryFragment())?.commit()
        }else{
            fragmentManager?.beginTransaction()?.replace(R.id.contents_panel_study_main,StudyPhraseMainFragment()/*.newInstance(currentChapter!!,currentPhrase!!)*/)?.commit()
        }

        view.btn_study_back_to_chapter.setOnClickListener { v->
            fragmentManager!!.beginTransaction().replace(R.id.contents_panel_main,SubjectSelectFragment()).commit()
        }

        view.btn_study_quick_select.setOnClickListener { v->
            QuickChapterSelectDialog().newInstance().show(fragmentManager,"Quick Select")
        }

        view.btn_study_phrase_prev.setOnClickListener { v->
            updatePhraseWithOffset(-1)
        }


        view.btn_study_phrase_next.setOnClickListener { v->
            updatePhraseWithOffset(+1)
        }

        return view
    }

    fun updatePhraseWithOffset(phraseOffset:Int){

        var phrase = currentPhrase!! + phraseOffset
        var chapter = currentChapter!!

        if(phrase< 0){
            //minus
            chapter--
            if (chapter < minChapterNum){
                chapter = maxChapterNum
            }
            phrase = maxPhraseNum[chapter] + (phrase + 1)

        }else if(phrase > maxPhraseNum[currentChapter!!]){
            //plus

            chapter++

            if (chapter > maxChapterNum){
                chapter = minChapterNum
            }

            phrase -= 1

        }

        currentChapter = chapter
        currentPhrase = phrase

        Toast.makeText(context,"$currentChapter,$currentPhrase",Toast.LENGTH_SHORT).show()

        fragmentManager?.beginTransaction()?.replace(R.id.contents_panel_main,StudyMainFragment().newInstance())?.commit()
    }


}