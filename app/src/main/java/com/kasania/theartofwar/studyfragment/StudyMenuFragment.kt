package com.kasania.theartofwar.studyfragment

import android.os.Bundle
import android.os.Message
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.kasania.theartofwar.*
import kotlinx.android.synthetic.main.fragment_study_main.view.*
import kotlinx.android.synthetic.main.fragment_study_menu.*
import kotlinx.android.synthetic.main.fragment_study_menu.view.*
import kotlinx.android.synthetic.main.fragment_study_phrase_interpret.*
import kotlinx.android.synthetic.main.fragment_study_phrase_interpret.view.*
import kotlinx.android.synthetic.main.fragment_study_phrase_main.view.*
import android.graphics.Color.parseColor
import android.R.attr.button
import android.view.View.OnTouchListener
import android.view.animation.Animation
import android.view.animation.AnimationUtils


class StudyMenuFragment: Fragment() {

    lateinit var tts : TTS
    private var isFabOpen = false
    lateinit var fab_animation_open : Animation
    lateinit var fab_animation_close : Animation
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_study_menu, container, false)
        fab_animation_open = AnimationUtils.loadAnimation(context!!,R.anim.fab_open)
        fab_animation_close = AnimationUtils.loadAnimation(context!!,R.anim.fab_close)
        tts = TTS()
        tts.initialize(context!!)
        setChapterTextView(view)

        view.fa_main.setOnClickListener {
            fab_ani()
        }
        view.fa_qmove.setOnClickListener {
            QuickChapterSelectDialog().newInstance().show(fragmentManager,"Quick Select")
            setChapterTextView(view)
        }

        view.fa_prev.setOnClickListener {
            updateStudyFragmentWithOffset(-1)
            setChapterTextView(view)
        }


        view.fa_next.setOnClickListener {
            updateStudyFragmentWithOffset(+1)
            setChapterTextView(view)
        }

        view.fa_interpret.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.contents_panel_study_phrase,StudyPhraseInterpretFragment().addTTS(tts))?.commit()
        }

        view.fa_comment.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.contents_panel_study_phrase,StudyPhraseCommentFragment().addTTS(tts))?.commit()
        }

        view.fa_fav.setOnClickListener {
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

    private fun fab_ani() {
        if(isFabOpen)
        {
            fa_next.startAnimation(fab_animation_close)
            fa_fav.startAnimation(fab_animation_close)
            fa_prev.startAnimation(fab_animation_close)
            fa_qmove.startAnimation(fab_animation_close)
            fa_interpret.startAnimation(fab_animation_close)
            fa_comment.startAnimation(fab_animation_close)
            isFabOpen = false
        }
        else
        {
            fa_next.startAnimation(fab_animation_open)
            fa_fav.startAnimation(fab_animation_open)
            fa_prev.startAnimation(fab_animation_open)
            fa_qmove.startAnimation(fab_animation_open)
            fa_interpret.startAnimation(fab_animation_open)
            fa_comment.startAnimation(fab_animation_open)
            isFabOpen = true
        }
    }

}






