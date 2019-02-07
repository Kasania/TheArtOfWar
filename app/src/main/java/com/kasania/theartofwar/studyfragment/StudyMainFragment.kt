package com.kasania.theartofwar.studyfragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.kasania.theartofwar.*
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
                0-> ft.setCustomAnimations(R.anim.slide_in_bottom,R.anim.slide_out_top,R.anim.slide_in_top,R.anim.slide_out_bottom)
                    .replace(R.id.contents_root,StudyMainFragment().newInstance())
                    .addToBackStack("StudyMain")
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

        val view = inflater.inflate(R.layout.fragment_study_main, container, false)

        setChapterTextView(view)

        view.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_UP) {
                fragmentManager!!.popBackStack("StudyMain", FragmentManager.POP_BACK_STACK_INCLUSIVE)
                return@OnKeyListener true
            }
            false
        })

        view.tv_study_current_chapter.setOnClickListener {
            MainActivity.toggleBookMark(currentChapter, currentPhrase)
            if(MainActivity.isCheckedBookMark(currentChapter, currentPhrase)){
                Toast.makeText(context,"Checked!",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context,"UnChecked!",Toast.LENGTH_SHORT).show()
            }
        }

        view.btn_study_back_to_chapter.setOnClickListener {
            fragmentManager!!.popBackStack("StudyMain", FragmentManager.POP_BACK_STACK_INCLUSIVE)
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