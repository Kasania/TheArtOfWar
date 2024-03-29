package com.kasania.theartofwar.studyfragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.content.res.AppCompatResources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.kasania.theartofwar.*
import kotlinx.android.synthetic.main.fragment_study_menu.*
import kotlinx.android.synthetic.main.fragment_study_menu.view.*
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils




class StudyMenuFragment: Fragment(), MainActivity.OnBackPressedListener {

    override fun onBack() {
        Log.e("Other", "onBack()")
        // 리스너를 설정하기 위해 Activity 를 받아옵니다.
        val activity = activity as MainActivity?
        // 한번 뒤로가기 버튼을 눌렀다면 Listener 를 null 로 해제해줍니다.
        //activity!!.setOnBackPressedListener()
        activity?.setOnBackPressedListener(null)
        // MainFragment 로 교체
        StudyMainFragment.tts_id.destroy()
        val ide = fragmentManager!!.getBackStackEntryAt(0).name
        fragmentManager!!.popBackStack(ide, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        Log.i("TAG", "Found fragment: $ide")

    }

    // Fragment 호출 시 반드시 호출되는 오버라이드 메소드입니다.
    override//                     혹시 Context 로 안되시는분은 Activity 로 바꿔보시기 바랍니다.
    fun onAttach(context: Context?) {
        super.onAttach(context)
        Log.e("Other", "onAttach()")
        (context as MainActivity).setOnBackPressedListener(this)
    }

    private var isFabOpen = false
    lateinit var fab_animation_open: Animation
    lateinit var fab_animation_close: Animation
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_study_menu, container, false)
        fab_animation_open = AnimationUtils.loadAnimation(context!!, R.anim.fab_open)
        fab_animation_close = AnimationUtils.loadAnimation(context!!, R.anim.fab_close)
        setChapterTextView(view)
        if (StudyMainFragment.currentPhrase == 0) view.fa_fav.setImageResource(R.drawable.bmark_final_white)
        else {
            if (MainActivity.isCheckedBookMark(StudyMainFragment.currentChapter, StudyMainFragment.currentPhrase))
                view.fa_fav.setImageResource(R.drawable.bmark_final_dark)
            else
                view.fa_fav.setImageResource(R.drawable.bmark_final_white)
        }
        // 총괄 배경화면 지정
        if (StudyMainFragment.currentPhrase == 0) when (StudyMainFragment.currentChapter) {
            1 ->{
                view.setBackgroundResource(R.drawable.summary_1_background)
                //view.summary_review.setBackgroundResource(R.drawable.summaryc_1)
                view.summary_review.setImageResource(R.drawable.summaryc_1)



            }
            2 -> {
                view.setBackgroundResource(R.drawable.summary_2_background)
                view.summary_review.setImageResource(R.drawable.summaryc_2)
            }
            3 -> {
                view.setBackgroundResource(R.drawable.summary_3_background)
                view.summary_review.setImageResource(R.drawable.summaryc_3)
            }
            4 -> {
                view.setBackgroundResource(R.drawable.summary_4_background)
                view.summary_review.setImageResource(R.drawable.summaryc_4)
            }
            5 -> {
                view.setBackgroundResource(R.drawable.summary_5_background)
                view.summary_review.setImageResource(R.drawable.summaryc_5)
            }
            6 -> {
                view.setBackgroundResource(R.drawable.summary_6_background)
                view.summary_review.setImageResource(R.drawable.summaryc_6)
            }
            7 -> {
                view.setBackgroundResource(R.drawable.summary_7_background)
                view.summary_review.setImageResource(R.drawable.summaryc_7)
            }
            8 -> {
                view.setBackgroundResource(R.drawable.summary_8_background)
                view.summary_review.setImageResource(R.drawable.summaryc_8)
            }
            9 -> {
                view.setBackgroundResource(R.drawable.summary_9_background)
                view.summary_review.setImageResource(R.drawable.summaryc_9)
            }
            10 -> {
                view.setBackgroundResource(R.drawable.summary_10_background)
                view.summary_review.setImageResource(R.drawable.summaryc_10)
            }
            11 -> {
                view.setBackgroundResource(R.drawable.summary_11_background)
                view.summary_review.setImageResource(R.drawable.summaryc_11)
            }
            12 -> {
                view.setBackgroundResource(R.drawable.summary_12_background)
                view.summary_review.setImageResource(R.drawable.summaryc_12)
            }
            13 -> {
                view.setBackgroundResource(R.drawable.summary_13_background)
                view.summary_review.setImageResource(R.drawable.summaryc_13)
            }
        }


        view.fa_main.setOnClickListener {
            fab_ani()
        }
        view.fa_qmove.setOnClickListener {
            QuickChapterSelectDialog().newInstance().show(fragmentManager, "Quick Select")
            setChapterTextView(view)
            if (StudyMainFragment.currentPhrase == 0) { // 총괄 배경화면 지정
                when (StudyMainFragment.currentChapter) {
                    1 ->{
                        view.setBackgroundResource(R.drawable.summary_1_background)
                        view.summary_review.setImageResource(R.drawable.summaryc_1)
                    }
                    2 -> {
                        view.setBackgroundResource(R.drawable.summary_2_background)
                        view.summary_review.setImageResource(R.drawable.summaryc_2)
                    }
                    3 -> {
                        view.setBackgroundResource(R.drawable.summary_3_background)
                        view.summary_review.setImageResource(R.drawable.summaryc_3)
                    }
                    4 -> {
                        view.setBackgroundResource(R.drawable.summary_4_background)
                        view.summary_review.setImageResource(R.drawable.summaryc_4)
                    }
                    5 -> {
                        view.setBackgroundResource(R.drawable.summary_5_background)
                        view.summary_review.setImageResource(R.drawable.summaryc_5)
                    }
                    6 -> {
                        view.setBackgroundResource(R.drawable.summary_6_background)
                        view.summary_review.setImageResource(R.drawable.summaryc_6)
                    }
                    7 -> {
                        view.setBackgroundResource(R.drawable.summary_7_background)
                        view.summary_review.setImageResource(R.drawable.summaryc_7)
                    }
                    8 -> {
                        view.setBackgroundResource(R.drawable.summary_8_background)
                        view.summary_review.setImageResource(R.drawable.summaryc_8)
                    }
                    9 -> {
                        view.setBackgroundResource(R.drawable.summary_9_background)
                        view.summary_review.setImageResource(R.drawable.summaryc_9)
                    }
                    10 -> {
                        view.setBackgroundResource(R.drawable.summary_10_background)
                        view.summary_review.setImageResource(R.drawable.summaryc_10)
                    }
                    11 -> {
                        view.setBackgroundResource(R.drawable.summary_11_background)
                        view.summary_review.setImageResource(R.drawable.summaryc_11)
                    }
                    12 -> {
                        view.setBackgroundResource(R.drawable.summary_12_background)
                        view.summary_review.setImageResource(R.drawable.summaryc_12)
                    }
                    13 -> {
                        view.setBackgroundResource(R.drawable.summary_13_background)
                        view.summary_review.setImageResource(R.drawable.summaryc_13)
                    }
                }
            }
            StudyMainFragment.tts_id.destroy()
            StudyMainFragment.tts_id.initialize(context!!)
        }

        view.fa_prev.setOnClickListener {
            updateStudyFragmentWithOffset(-1)
            setChapterTextView(view)

            if (StudyMainFragment.currentPhrase == 0) { // 총괄 배경화면 지정
                view.summary_review.setVisibility(View.GONE)
                when (StudyMainFragment.currentChapter) {
                    1 ->{
                        view.setBackgroundResource(R.drawable.summary_1_background)
                        view.summary_review.setImageResource(R.drawable.summaryc_1)
                    }
                    2 -> {
                        view.setBackgroundResource(R.drawable.summary_2_background)
                        view.summary_review.setImageResource(R.drawable.summaryc_2)
                    }
                    3 -> {
                        view.setBackgroundResource(R.drawable.summary_3_background)
                        view.summary_review.setImageResource(R.drawable.summaryc_3)
                    }
                    4 -> {
                        view.setBackgroundResource(R.drawable.summary_4_background)
                        view.summary_review.setImageResource(R.drawable.summaryc_4)
                    }
                    5 -> {
                        view.setBackgroundResource(R.drawable.summary_5_background)
                        view.summary_review.setImageResource(R.drawable.summaryc_5)
                    }
                    6 -> {
                        view.setBackgroundResource(R.drawable.summary_6_background)
                        view.summary_review.setImageResource(R.drawable.summaryc_6)
                    }
                    7 -> {
                        view.setBackgroundResource(R.drawable.summary_7_background)
                        view.summary_review.setImageResource(R.drawable.summaryc_7)
                    }
                    8 -> {
                        view.setBackgroundResource(R.drawable.summary_8_background)
                        view.summary_review.setImageResource(R.drawable.summaryc_8)
                    }
                    9 -> {
                        view.setBackgroundResource(R.drawable.summary_9_background)
                        view.summary_review.setImageResource(R.drawable.summaryc_9)
                    }
                    10 -> {
                        view.setBackgroundResource(R.drawable.summary_10_background)
                        view.summary_review.setImageResource(R.drawable.summaryc_10)
                    }
                    11 -> {
                        view.setBackgroundResource(R.drawable.summary_11_background)
                        view.summary_review.setImageResource(R.drawable.summaryc_11)
                    }
                    12 -> {
                        view.setBackgroundResource(R.drawable.summary_12_background)
                        view.summary_review.setImageResource(R.drawable.summaryc_12)
                    }
                    13 -> {
                        view.setBackgroundResource(R.drawable.summary_13_background)
                        view.summary_review.setImageResource(R.drawable.summaryc_13)
                    }
                }
            }
            Log.i("TAG", "Found tts in studymenu : ${StudyMainFragment.tts_id}")
            StudyMainFragment.tts_id.destroy()
            StudyMainFragment.tts_id.initialize(context!!)
        }


        view.fa_next.setOnClickListener {
            updateStudyFragmentWithOffset(+1)
            setChapterTextView(view)

            if (StudyMainFragment.currentPhrase == 0) { // 총괄 배경화면 지정
                view.summary_review.setVisibility(View.GONE)
                when (StudyMainFragment.currentChapter) {
                    1 ->{
                        view.setBackgroundResource(R.drawable.summary_1_background)
                        view.summary_review.setImageResource(R.drawable.summaryc_1)
                    }
                    2 -> {
                        view.setBackgroundResource(R.drawable.summary_2_background)
                        view.summary_review.setImageResource(R.drawable.summaryc_2)
                    }
                    3 -> {
                        view.setBackgroundResource(R.drawable.summary_3_background)
                        view.summary_review.setImageResource(R.drawable.summaryc_3)
                    }
                    4 -> {
                        view.setBackgroundResource(R.drawable.summary_4_background)
                        view.summary_review.setImageResource(R.drawable.summaryc_4)
                    }
                    5 -> {
                        view.setBackgroundResource(R.drawable.summary_5_background)
                        view.summary_review.setImageResource(R.drawable.summaryc_5)
                    }
                    6 -> {
                        view.setBackgroundResource(R.drawable.summary_6_background)
                        view.summary_review.setImageResource(R.drawable.summaryc_6)
                    }
                    7 -> {
                        view.setBackgroundResource(R.drawable.summary_7_background)
                        view.summary_review.setImageResource(R.drawable.summaryc_7)
                    }
                    8 -> {
                        view.setBackgroundResource(R.drawable.summary_8_background)
                        view.summary_review.setImageResource(R.drawable.summaryc_8)
                    }
                    9 -> {
                        view.setBackgroundResource(R.drawable.summary_9_background)
                        view.summary_review.setImageResource(R.drawable.summaryc_9)
                    }
                    10 -> {
                        view.setBackgroundResource(R.drawable.summary_10_background)
                        view.summary_review.setImageResource(R.drawable.summaryc_10)
                    }
                    11 -> {
                        view.setBackgroundResource(R.drawable.summary_11_background)
                        view.summary_review.setImageResource(R.drawable.summaryc_11)
                    }
                    12 -> {
                        view.setBackgroundResource(R.drawable.summary_12_background)
                        view.summary_review.setImageResource(R.drawable.summaryc_12)
                    }
                    13 -> {
                        view.setBackgroundResource(R.drawable.summary_13_background)
                        view.summary_review.setImageResource(R.drawable.summaryc_13)
                    }
                }
            }
            Log.i("TAG", "Found tts in studymenu : ${StudyMainFragment.tts_id}")
            StudyMainFragment.tts_id.destroy()
            StudyMainFragment.tts_id.initialize(context!!)

        }
        view.fa_interpret.setOnClickListener {
            if (StudyMainFragment.currentPhrase == 0) {
                //summary_review.startAnimation(fab_animation_close)
                view.summary_review.setVisibility(if (summary_review.getVisibility() == View.VISIBLE) View.GONE else View.VISIBLE) // 총괄 해석 투명도 설정
            }
            else fragmentManager?.beginTransaction()?.replace(
                R.id.contents_panel_study_phrase,
                StudyPhraseInterpretFragment()
            )?.commit()

            view.fa_comment.isClickable = true
            view.fa_interpret.isClickable = false
            val ides = StudyMainFragment.currentPhrase
            Log.i("TAG", "interpret Found fragment: $ides")

            if (StudyMainFragment.currentPhrase != 0) {
                StudyMainFragment.tts_id.destroy()
                StudyMainFragment.tts_id.initialize(context!!)
            }

        }

        view.fa_comment.setOnClickListener {
            if (StudyMainFragment.currentPhrase == 0) {
                view.summary_review.setVisibility(if (summary_review.getVisibility() == View.VISIBLE) View.GONE else View.VISIBLE) // 총괄 해석 투명도 설정
            }
            else fragmentManager?.beginTransaction()?.replace(
                R.id.contents_panel_study_phrase,
                StudyPhraseCommentFragment()
            )?.commit()
            view.fa_comment.isClickable = false
            view.fa_interpret.isClickable = true
            val ides = StudyMainFragment.currentPhrase
            Log.i("TAG", "comment Found fragment: $ides")

            if (StudyMainFragment.currentPhrase != 0) {
                StudyMainFragment.tts_id.destroy()
                StudyMainFragment.tts_id.initialize(context!!)
            }

        }

        view.fa_fav.setOnClickListener {

            if (StudyMainFragment.currentPhrase == 0) {
                Toast.makeText(context, "총괄은 설정이 불가능 합니다.", Toast.LENGTH_SHORT).show()
            } else {
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

    private fun updateStudyFragmentWithOffset(phraseOffset: Int) {
        var phrase = StudyMainFragment.currentPhrase + phraseOffset
        var chapter = StudyMainFragment.currentChapter

        if (phrase < 0) {
            //negative
            chapter--
            if (chapter < minChapterNum) {
                chapter = maxChapterNum
            }
            phrase = maxPhraseNum[chapter] + (phrase + 1)

        } else if (phrase > maxPhraseNum[StudyMainFragment.currentChapter]) {
            //positive
            chapter++
            if (chapter > maxChapterNum) {
                chapter = minChapterNum
            }
            phrase -= (maxPhraseNum[StudyMainFragment.currentChapter] + 1)
        }
        //to negative 1 / positive 2
        val animationDir = when (phraseOffset) {
            -1 -> 1
            1 -> 2
            else -> 1
        }


        StudyMainFragment.changeStudyContents(fragmentManager, chapter, phrase, animationDir)

        fa_comment.isClickable = true
        fa_interpret.isClickable = false

        if (StudyMainFragment.currentPhrase == 0) fa_fav.setImageResource(R.drawable.bmark_final_white)
        else {
            if (MainActivity.isCheckedBookMark(StudyMainFragment.currentChapter, StudyMainFragment.currentPhrase))
                fa_fav.setImageResource(R.drawable.bmark_final_dark)
            else
                fa_fav.setImageResource(R.drawable.bmark_final_white)
        }
    }

    private fun setChapterTextView(v: View) {
        val displayPhrase = when (StudyMainFragment.currentPhrase) {
            0 -> "총괄"
            else -> "${StudyMainFragment.currentPhrase} 절"
        }
        val currentChapterText =
            "${resources.getString(R.string.chapter_prefix_name)} ${StudyMainFragment.currentChapter} ${resources.getString(
                R.string.chapter_postfix_name
            )} $displayPhrase"

    }


    private fun fab_ani() {
        if (isFabOpen) {
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
        } else {
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








