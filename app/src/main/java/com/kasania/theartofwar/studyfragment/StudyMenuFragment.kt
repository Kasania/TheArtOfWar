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
import android.graphics.Color.parseColor
import android.R.attr.button
import android.view.View.OnTouchListener
import android.view.View.VISIBLE
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.fragment_study_chapter_summary.*


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
        if(StudyMainFragment.currentPhrase == 0) view.fa_fav.setImageResource(R.drawable.bmark_final_white)
        else {
            if (MainActivity.isCheckedBookMark(StudyMainFragment.currentChapter, StudyMainFragment.currentPhrase))
                view.fa_fav.setImageResource(R.drawable.bmark_final_dark)
            else
                view.fa_fav.setImageResource(R.drawable.bmark_final_white)
        }

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
            if(StudyMainFragment.currentPhrase == 0){
                summary_review.startAnimation(fab_animation_close)
            }
            //    when(StudyMainFragment.currentChapter) {
            //        1 -> view.setBackgroundResource(R.drawable.summary__1)
            //    }
            //}
            //else
            else fragmentManager?.beginTransaction()?.replace(R.id.contents_panel_study_phrase,StudyPhraseInterpretFragment())?.commit()

            view.fa_comment.isClickable = true
            view.fa_interpret.isClickable = false
        }

        view.fa_comment.setOnClickListener {
            if(StudyMainFragment.currentPhrase == 0){
                summary_review.startAnimation(fab_animation_open)
            }
            //    when(StudyMainFragment.currentChapter) {
            //        1 -> view.setBackgroundResource(R.drawable.summary_1)
            //    }
            //}
            //else
            else fragmentManager?.beginTransaction()?.replace(R.id.contents_panel_study_phrase,StudyPhraseCommentFragment())?.commit()
            view.fa_comment.isClickable = false
            view.fa_interpret.isClickable = true
        }

        view.fa_fav.setOnClickListener {

            if(StudyMainFragment.currentPhrase == 0){
                Toast.makeText(context, "총괄은 설정이 불가능 합니다.", Toast.LENGTH_SHORT).show()
            }
            else {
                MainActivity.toggleBookMark(StudyMainFragment.currentChapter, StudyMainFragment.currentPhrase)

                    if (MainActivity.isCheckedBookMark(StudyMainFragment.currentChapter, StudyMainFragment.currentPhrase)) {
                        Toast.makeText(context, "즐겨찾기 설정", Toast.LENGTH_SHORT).show()
                        view.fa_fav.setImageResource(R.drawable.bmark_final_dark)
                    } else {
                        Toast.makeText(context, "즐겨찾기 해제", Toast.LENGTH_SHORT).show()
                        view.fa_fav.setImageResource(R.drawable.bmark_final_white)
                    }
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

        fa_comment.isClickable = true
        fa_interpret.isClickable = false

        if(StudyMainFragment.currentPhrase == 0) fa_fav.setImageResource(R.drawable.bmark_final_white)
        else {
            if (MainActivity.isCheckedBookMark(StudyMainFragment.currentChapter, StudyMainFragment.currentPhrase))
                fa_fav.setImageResource(R.drawable.bmark_final_dark)
            else
                fa_fav.setImageResource(R.drawable.bmark_final_white)
        }
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
            fa_next.isClickable = false
            fa_fav.isClickable = false
            fa_prev.isClickable = false
            fa_qmove.isClickable = false
            fa_interpret.isClickable = false
            fa_comment.isClickable = false
            fa_main.setImageResource(R.drawable.plus)
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
            fa_next.isClickable = true
            fa_fav.isClickable = true
            fa_prev.isClickable = true
            fa_qmove.isClickable = true
            fa_interpret.isClickable = true
            fa_comment.isClickable = true
            fa_main.setImageResource(R.drawable.x)
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






