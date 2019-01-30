package com.kasania.theartofwar.studyfragment

import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.TextViewCompat
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.kasania.theartofwar.R
import kotlinx.android.synthetic.main.fragment_study_phrase_interpret.view.*

class StudyPhraseInterpretFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_study_phrase_interpret, container, false)

        val hanjaId = resources.getIdentifier("@string/hanja_${StudyMainFragment.currentChapter}_${StudyMainFragment.currentPhrase}","String",context?.packageName)
        val soundId = resources.getIdentifier("@string/sound_${StudyMainFragment.currentChapter}_${StudyMainFragment.currentPhrase}","String",context?.packageName)
        val interpretId = resources.getIdentifier("@string/interpret_${StudyMainFragment.currentChapter}_${StudyMainFragment.currentPhrase}","String",context?.packageName)

        val hanjaValue = getText(hanjaId).split("/")
        val soundValue = getText(soundId).split("/")
        val interpretValue = getText(interpretId).split("/")

        for (i in 0.. hanjaValue.lastIndex){
            val interpretSet = LinearLayout(context)
            interpretSet.orientation = LinearLayout.VERTICAL
            interpretSet.gravity = Gravity.LEFT


            val hanjaView = TextView(context)

            hanjaView.text = hanjaValue[i]
            hanjaView.textSize=24.0f
            hanjaView.gravity = Gravity.LEFT


            val soundView = TextView(context)
            soundView.text = soundValue[i]
            soundView.textSize=24.0f
            soundView.gravity = Gravity.LEFT

            val interpretView = TextView(context)
            interpretView.text = interpretValue[i]
            interpretView.textSize=24.0f
            interpretView.gravity = Gravity.FILL
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                interpretView.setAutoSizeTextTypeUniformWithConfiguration(20,80,4,TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM)
            }

            interpretSet.addView(hanjaView)
            interpretSet.addView(soundView)
            interpretSet.addView(interpretView)


            view.sv_phrase_interpret.addView(interpretSet)
        }


        return view
    }
}