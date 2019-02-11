package com.kasania.theartofwar.mainfragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kasania.theartofwar.*
import com.kasania.theartofwar.studyfragment.StudyMainFragment
import kotlinx.android.synthetic.main.fragment_home.view.*
import java.util.*


open class HomeFragment :Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        view.btn_home_favorite.setOnClickListener {
            val ft = fragmentManager!!.beginTransaction()
            if(enableAnimation)
            ft.setCustomAnimations(R.anim.slide_in_bottom,R.anim.slide_out_top,R.anim.slide_in_top,R.anim.slide_out_bottom)

            ft.replace(R.id.contents_root,FavoriteFragment())
            ft.addToBackStack("Favorite")
            ft.commit()
        }


        val sharedPreferences = activity!!.getSharedPreferences(SharedPrefName, Context.MODE_PRIVATE)
        val lastChapter = sharedPreferences.getInt(SharedPrefKeyLastChapter,1)
        val lastPhrase = sharedPreferences.getInt(SharedPrefKeyLastPhrase,0)
        val displayContinueButtonText = "${lastChapter}편\n${displayPhraseName[lastPhrase]}"
        view.tv_btn_home_continue.text = displayContinueButtonText
        view.btn_home_continue.setOnClickListener {
            StudyMainFragment.createStudyContents(fragmentManager,lastChapter,lastPhrase)
        }

        val today = Calendar.getInstance()

        val todayChapter = (today.get(Calendar.YEAR)*(today.get(Calendar.MONTH)+1)*(today.get(Calendar.DATE)+1)) % maxChapterNum + 1
        val todayPhrase = (today.get(Calendar.YEAR)*(today.get(Calendar.MONTH)+1)*(today.get(Calendar.DATE)+1)) % maxPhraseNum[todayChapter]


        view.tv_btn_home_today_date.text = "${today.get(Calendar.DAY_OF_MONTH)}"
        view.tv_btn_home_today_month.text ="${displayMonthName[today.get(Calendar.MONTH)]}"
        view.tv_btn_home_today_phrase.text = "${todayChapter}편 ${displayPhraseName[todayPhrase]}"
        view.btn_home_todayphrase.setOnClickListener {
            StudyMainFragment.createStudyContents(fragmentManager,todayChapter,todayPhrase)
        }


        return view
    }
}