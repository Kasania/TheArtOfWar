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

        view.btn_home_bookmark.setOnClickListener {
            val ft = fragmentManager!!.beginTransaction()
            ft.setCustomAnimations(R.anim.slide_in_bottom,R.anim.slide_out_top,R.anim.slide_in_top,R.anim.slide_out_bottom)
            ft.replace(R.id.contents_root,FavoriteFragment())
            ft.addToBackStack("Favorite")
            ft.commit()
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