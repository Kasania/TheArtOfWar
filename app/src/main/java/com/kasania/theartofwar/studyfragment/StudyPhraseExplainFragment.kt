package com.kasania.theartofwar.studyfragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kasania.theartofwar.R
import com.kasania.theartofwar.studyfragment.StudyMainFragment.Companion.currentChapter
import com.kasania.theartofwar.studyfragment.StudyMainFragment.Companion.currentPhrase
import kotlinx.android.synthetic.main.fragment_study_phrase_explain.view.*

class StudyPhraseExplainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_study_phrase_explain, container, false)

        view.tv_study_phrase_explain.text = "$currentChapter 편 $currentPhrase 절 내용풀이"


        return view
    }
}