package com.kasania.theartofwar.studyfragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TableLayout
import com.kasania.theartofwar.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_study_main.view.*

class StudyMainFragment : Fragment(){

    companion object {
        var currentChapter: Int = 1
        var currentPhrase: Int = 0
    }
    fun newInstance(chapter:Int = 0,phrase:Int = 0): StudyMainFragment
    {
        currentChapter = chapter
        currentPhrase = phrase
        return newInstance()
    }

    private fun newInstance():StudyMainFragment{
        val fragment = StudyMainFragment()

        return fragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {

        val view = inflater.inflate(R.layout.fragment_study_main, container, false)

        activity?.contents_main_bottom_buttons?.visibility = LinearLayout.GONE

        val fm = fragmentManager
        fm?.addOnBackStackChangedListener {
            fragmentManager!!.beginTransaction().replace(R.id.contents_panel_main,MainActivity.subjectSelectFragment).commit()
        }

        val displayPhrase = when (currentPhrase){
            0-> "총괄"
            else -> "$currentPhrase 절"
        }

        val currentChapterText = "${resources.getString(R.string.chapter_prefix_name)} $currentChapter ${resources.getString(R.string.chapter_postfix_name)} $displayPhrase"


        view.tv_study_current_chapter.text = currentChapterText
        view.tv_study_current_chapter.setOnClickListener {
            MainActivity.toggleBookMark(currentChapter, currentPhrase)

            //----------------------------for Test
            if(MainActivity.isCheckedBookMark(currentChapter, currentPhrase)){
//                view.tv_study_current_chapter.setBackgroundColor(ContextCompat.getColor(context!!, R.color.darkRed))
            }else{
                view.tv_study_current_chapter.setBackgroundColor(ContextCompat.getColor(context!!, R.color.PrimaryMain))
            }
            //----------------------------for Test

        }

        //---------------------------for Test
        if(MainActivity.isCheckedBookMark(currentChapter, currentPhrase)){
//            view.tv_study_current_chapter.setBackgroundColor(ContextCompat.getColor(context!!, R.color.darkRed))
        }else{
            view.tv_study_current_chapter.setBackgroundColor(ContextCompat.getColor(context!!, R.color.PrimaryMain))
        }
        //----------------------------for Test

        if(currentPhrase == 0) {
            fragmentManager?.beginTransaction()?.replace(R.id.contents_panel_study_main,StudyChapterSummaryFragment())?.commit()
        }else{
            fragmentManager?.beginTransaction()?.replace(R.id.contents_panel_study_main,StudyPhraseMainFragment())?.commit()
        }

        view.btn_study_back_to_chapter.setOnClickListener {
            fragmentManager!!.beginTransaction().replace(R.id.contents_panel_main,MainActivity.subjectSelectFragment).commit()
        }

        view.btn_study_quick_select.setOnClickListener {
            QuickChapterSelectDialog().newInstance().show(fragmentManager,"Quick Select")
        }

        view.btn_study_phrase_prev.setOnClickListener {
            updateStudyFragmentWithOffset(-1)
        }


        view.btn_study_phrase_next.setOnClickListener {
            updateStudyFragmentWithOffset(+1)
        }

        return view
    }

    override fun onDestroy() {
        super.onDestroy()

        activity?.contents_main_bottom_buttons?.visibility = LinearLayout.VISIBLE
    }

    override fun onStop() {
        super.onStop()
        activity!!.getSharedPreferences(SharedPrefName, Context.MODE_PRIVATE).edit()
            .putInt(SharedPrefKeyLastChapter, currentChapter).putInt(
            SharedPrefKeyLastPhrase, currentPhrase).apply()

    }


    private fun updateStudyFragmentWithOffset(phraseOffset:Int){

        var phrase = currentPhrase + phraseOffset
        var chapter = currentChapter

        if(phrase< 0){
            //negative
            chapter--
            if (chapter < minChapterNum){
                chapter = maxChapterNum
            }
            phrase = maxPhraseNum[chapter] + (phrase + 1)

        }else if(phrase > maxPhraseNum[currentChapter]){
            //positive
            chapter++
            if (chapter > maxChapterNum){
                chapter = minChapterNum
            }
            phrase -= (maxPhraseNum[currentChapter] + 1)
        }

        currentChapter = chapter
        currentPhrase = phrase
        fragmentManager?.beginTransaction()?.replace(R.id.contents_panel_main,StudyMainFragment().newInstance())?.commit()
    }



}