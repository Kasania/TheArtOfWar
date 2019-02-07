package com.kasania.theartofwar.studyfragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.kasania.theartofwar.R
import kotlinx.android.synthetic.main.fragment_study_phrase_voca.view.*

class StudyPhraseVocaFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_study_phrase_voca, container, false)

        val id = resources.getIdentifier("@string/voca_${StudyMainFragment.currentChapter}_${StudyMainFragment.currentPhrase}","String",context?.packageName)

        val vocaLines = getText(id).split("/")

        for (s in vocaLines){

            val vocaValue = s.split("|")
            val vocaContainer = LinearLayout(context)

            val vocaChar = TextView(context)
            val vocaInterpret = TextView(context)

            vocaChar.text = vocaValue[0]
            vocaInterpret.text = vocaValue[1]

            vocaContainer.addView(vocaChar)
            vocaContainer.addView(vocaInterpret)

            view.sv_phrase_voca.addView(vocaContainer)

        }


        return view
    }
}