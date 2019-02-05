package com.kasania.theartofwar

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kasania.theartofwar.mainfragment.HomeFragment
import com.kasania.theartofwar.mainfragment.SearchFragment
import kotlinx.android.synthetic.main.activity_main.*
import com.kasania.theartofwar.studyfragment.SubjectSelectFragment
import java.io.FileNotFoundException


class MainActivity : AppCompatActivity() {

    companion object {
        var BookmarkData = ByteArray(totalPhraseNum + maxChapterNum) {0.toByte()}

        fun toggleBookMark(chapter:Int = 1, phrase:Int = 0){
            if (isCheckedBookMark(chapter,phrase)){
                BookmarkData[maxPhraseNum[chapter-1] + phrase] = 0.toByte()
            }else{
                BookmarkData[maxPhraseNum[chapter-1] + phrase] = 1.toByte()
            }
        }

        fun isCheckedBookMark(chapter:Int = 1, phrase:Int = 0) : Boolean{
            if(BookmarkData[maxPhraseNum[chapter-1] + phrase] == 1.toByte()){
                return true
            }
            return false
        }

        val homeFragment = HomeFragment()
        val subjectSelectFragment = SubjectSelectFragment()
        val searchFragment = SearchFragment()

    }
    private var currentFragmentPage : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Bookmark
        loadFavorite()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.contents_panel_main,homeFragment)
            .commit()

        btn_main_bottom_home.setOnClickListener {
            bottomButtonAction(0)
            currentFragmentPage = 0
        }
        btn_main_bottom_study.setOnClickListener {
            when (currentFragmentPage){
                0->bottomButtonAction(1)
                2->bottomButtonAction(2)
            }
            currentFragmentPage = 1
        }
        btn_main_bottom_search.setOnClickListener {
            bottomButtonAction(3)
            currentFragmentPage = 2
        }

    }

    override fun onStop() {
        super.onStop()
        val output = openFileOutput(BookmarkDataFileName, Context.MODE_PRIVATE)
        output.write(BookmarkData)
        output.close()
    }

    private fun loadFavorite(){
        try {
            val input = openFileInput(BookmarkDataFileName)
            input.read(BookmarkData)
            input.close()

        }catch(e: FileNotFoundException){
            val output = openFileOutput(BookmarkDataFileName, Context.MODE_PRIVATE)
            output.write(BookmarkData)
            output.close()
        }
    }

    private fun bottomButtonAction(action:Int){
        val fm = supportFragmentManager.beginTransaction()
        when (action){
            0 -> fm.setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right)
                .replace(R.id.contents_panel_main,homeFragment)
            1 -> fm.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left)
                .replace(R.id.contents_panel_main, subjectSelectFragment)
            2 -> fm.setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right)
                .replace(R.id.contents_panel_main, subjectSelectFragment)
            3 -> fm.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left)
                .replace(R.id.contents_panel_main,searchFragment)
        }
        fm.commit()
    }
}
