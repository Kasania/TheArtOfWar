package com.kasania.theartofwar.studyfragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import com.kasania.theartofwar.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_study_main.view.*

class StudyMainFragment : Fragment(){

    companion object {
        var currentChapter: Int = 1
        var currentPhrase: Int = 0

        fun changeStudyContents(fm:FragmentManager?, chapter:Int = 1, phrase:Int = 0, action:Int = 0){
            currentChapter = chapter
            currentPhrase = phrase
            val ft = fm!!.beginTransaction()
            when (action){
                0-> ft.setCustomAnimations(R.anim.slide_in_bottom,R.anim.slide_out_top)
                    .replace(R.id.contents_panel_main,StudyMainFragment().newInstance())
                1 -> ft.setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right)
                2 -> ft.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left)
                3 -> ft.setCustomAnimations(R.anim.slide_in_bottom,R.anim.slide_out_top)
            }

            if(phrase == 0) {
                ft.replace(R.id.contents_panel_study_main,StudyChapterSummaryFragment())
            }else{
                ft.replace(R.id.contents_panel_study_main,StudyPhraseMainFragment())
            }

            ft.commit()
        }

    }
    private fun newInstance():StudyMainFragment{
        val fragment = StudyMainFragment()

        return fragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        if (activity?.contents_main_bottom_buttons?.visibility != LinearLayout.GONE){
            activity?.contents_main_bottom_buttons?.startAnimation(AnimationUtils.loadAnimation(context,R.anim.slide_out_bottom))
            activity?.contents_main_bottom_buttons?.visibility = LinearLayout.GONE
        }

        val view = inflater.inflate(R.layout.fragment_study_main, container, false)

        setChapterTextView(view)

        view.tv_study_current_chapter.setOnClickListener {
            MainActivity.toggleBookMark(currentChapter, currentPhrase)
        }

        view.btn_study_back_to_chapter.setOnClickListener {
            activity?.contents_main_bottom_buttons?.startAnimation(AnimationUtils.loadAnimation(context,R.anim.slide_in_bottom))
            activity?.contents_main_bottom_buttons?.visibility = LinearLayout.VISIBLE
            fragmentManager!!
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in_top,R.anim.slide_out_bottom)
                .replace(R.id.contents_panel_main,MainActivity.subjectSelectFragment)
                .commit()
        }

        view.btn_study_quick_select.setOnClickListener {
            QuickChapterSelectDialog().newInstance().show(fragmentManager,"Quick Select")
            setChapterTextView(view)
        }

        view.btn_study_phrase_prev.setOnClickListener {
            updateStudyFragmentWithOffset(-1)
            setChapterTextView(view)
        }


        view.btn_study_phrase_next.setOnClickListener {
            updateStudyFragmentWithOffset(+1)
            setChapterTextView(view)
        }

        return view
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
        //to negative 1 / positive 2
        val animationDir = when(phraseOffset){
            -1 -> 1
            1 -> 2
            else -> 1
        }

        changeStudyContents(fragmentManager,chapter,phrase,animationDir)
    }

    private fun setChapterTextView(v:View){
        val displayPhrase = when (currentPhrase){
            0-> "총괄"
            else -> "$currentPhrase 절"
        }
        val currentChapterText = "${resources.getString(R.string.chapter_prefix_name)} $currentChapter ${resources.getString(R.string.chapter_postfix_name)} $displayPhrase"
        v.tv_study_current_chapter?.text = currentChapterText
    }

}