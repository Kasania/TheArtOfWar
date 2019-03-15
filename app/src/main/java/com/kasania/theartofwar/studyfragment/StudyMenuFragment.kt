package com.kasania.theartofwar.studyfragment

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.kasania.theartofwar.*
import kotlinx.android.synthetic.main.fragment_study_main.view.*
import kotlinx.android.synthetic.main.fragment_study_menu.view.*
import kotlinx.android.synthetic.main.fragment_study_phrase_main.view.*

class StudyMenuFragment: Fragment() {

    lateinit var tts : TTS

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_study_menu, container, false)
        tts = TTS()
        tts.initialize(context!!)
        setChapterTextView(view)
        view.tv_study_current_chapter.setOnClickListener {
            QuickChapterSelectDialog().newInstance().show(fragmentManager,"Quick Select")
            setChapterTextView(view)
        }

        view.btn_study_phrase_prev2.setOnClickListener {
            updateStudyFragmentWithOffset(-1)
            setChapterTextView(view)
        }


        view.btn_study_phrase_next2.setOnClickListener {
            updateStudyFragmentWithOffset(+1)
            setChapterTextView(view)
        }

        view.tabbar_study_contents.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab) {


                when(view.tabbar_study_contents.selectedTabPosition){
                    0 -> fragmentManager?.beginTransaction()?.replace(R.id.contents_panel_study_phrase,StudyPhraseInterpretFragment().addTTS(tts))?.commit()
//                    1 -> fragmentManager?.beginTransaction()?.replace(R.id.contents_panel_study_phrase,StudyPhraseVocaFragment())?.commit()
                    1 -> fragmentManager?.beginTransaction()?.replace(R.id.contents_panel_study_phrase,StudyPhraseCommentFragment().addTTS(tts))?.commit()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

        view.btn_fav_select2.setOnClickListener {
            MainActivity.toggleBookMark(StudyMainFragment.currentChapter, StudyMainFragment.currentPhrase)
            if(MainActivity.isCheckedBookMark(StudyMainFragment.currentChapter, StudyMainFragment.currentPhrase)){
                Toast.makeText(context,"즐겨찾기 설정", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context,"즐겨찾기 해제", Toast.LENGTH_SHORT).show()
            }
        }

        return view

    }

    private fun updateStudyFragmentWithOffset(phraseOffset:Int){

        var phrase = StudyMainFragment.currentPhrase + phraseOffset
        var chapter = StudyMainFragment.currentChapter

        if(phrase< 0){
            //negative
            chapter--
            if (chapter < minChapterNum){
                chapter = maxChapterNum
            }
            phrase = maxPhraseNum[chapter] + (phrase + 1)

        }else if(phrase > maxPhraseNum[StudyMainFragment.currentChapter]){
            //positive
            chapter++
            if (chapter > maxChapterNum){
                chapter = minChapterNum
            }
            phrase -= (maxPhraseNum[StudyMainFragment.currentChapter] + 1)
        }
        //to negative 1 / positive 2
        val animationDir = when(phraseOffset){
            -1 -> 1
            1 -> 2
            else -> 1
        }

        StudyMainFragment.changeStudyContents(fragmentManager, chapter, phrase, animationDir)
    }

    private fun setChapterTextView(v:View){
        val displayPhrase = when (StudyMainFragment.currentPhrase){
            0-> "총괄"
            else -> "${StudyMainFragment.currentPhrase} 절"
        }
        val currentChapterText = "${resources.getString(R.string.chapter_prefix_name)} ${StudyMainFragment.currentChapter} ${resources.getString(R.string.chapter_postfix_name)} $displayPhrase"
        v.tv_study_current_chapter?.text = currentChapterText
    }


}