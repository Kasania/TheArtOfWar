package com.kasania.theartofwar.studyfragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kasania.theartofwar.R
import kotlinx.android.synthetic.main.fragment_study_chapter_summary.view.*
import kotlinx.android.synthetic.main.fragment_study_phrase_voca.view.*

class StudyPhraseVocaFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_study_phrase_voca, container, false)

        view.tv_study_phrase_voca.text = "${StudyMainFragment.currentChapter} 편 ${StudyMainFragment.currentPhrase} 절 내용요약"


        return view
    }
}