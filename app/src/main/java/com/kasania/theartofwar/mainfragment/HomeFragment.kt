package com.kasania.theartofwar.mainfragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.kasania.theartofwar.*
import com.kasania.theartofwar.studyfragment.StudyMainFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.view.*


open class HomeFragment :Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {

        val view = inflater.inflate(R.layout.fragment_home, container, false)

//        view.exp_percent_bar.max = 100
//        view.tv_home_level_percent.text = "${view.exp_percent_bar.progress}%"

        view.btn_home_bookmark.setOnClickListener {
            Toast.makeText(context,"${MainActivity.isCheckedBookMark(1,0)}",Toast.LENGTH_SHORT).show()
        }

        val sharedPreferences = activity!!.getSharedPreferences(SharedPrefName, Context.MODE_PRIVATE)
        val chapter = sharedPreferences.getInt(SharedPrefKeyLastChapter,1)
        val phrase = sharedPreferences.getInt(SharedPrefKeyLastPhrase,0)
        val displayPhrase = when (phrase){
            0-> "총괄"
            else -> "$phrase 절"
        }
        val displayContinueButtonText = "${getText(R.string.btn_home_continue)}\n $chapter 장 $displayPhrase"
        view.btn_home_continue.text = displayContinueButtonText
        view.btn_home_continue.setOnClickListener {
            StudyMainFragment.changeStudyContents(fragmentManager,chapter,phrase)
        }




        return view
    }
}