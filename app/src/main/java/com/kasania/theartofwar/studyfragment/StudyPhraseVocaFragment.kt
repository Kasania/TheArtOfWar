package com.kasania.theartofwar.studyfragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.Gravity
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
            vocaChar.textSize = 24f
            vocaChar.setTextColor(ContextCompat.getColor(context!!,R.color.Black))

            vocaContainer.addView(vocaChar)

            if(vocaValue.lastIndex > 0){
                vocaContainer.addView(vocaInterpret)
                vocaInterpret.text = vocaValue[1]
                vocaInterpret.textSize = 18f
                vocaInterpret.setTextColor(ContextCompat.getColor(context!!,R.color.Black))
                vocaInterpret.gravity = Gravity.CENTER_VERTICAL
                val layout = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT)
                layout.setMargins(10,0,0,0)
                vocaInterpret.layoutParams = layout
            }

            val layout = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT)
            layout.setMargins(10,20,10,10)
            vocaContainer.layoutParams = layout

            view.sv_phrase_voca.addView(vocaContainer)

        }


        return view
    }
}