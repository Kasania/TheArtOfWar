package com.kasania.theartofwar

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kasania.theartofwar.mainfragment.HomeFragment
import com.kasania.theartofwar.mainfragment.ProfileFragment
import com.kasania.theartofwar.mainfragment.SearchFragment
import kotlinx.android.synthetic.main.activity_main.*
import android.support.design.widget.TabLayout
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


    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Bookmark
        try {
            val input = openFileInput(BookmarkDataFileName)
            input.read(BookmarkData)
            input.close()

        }catch(e: FileNotFoundException){
            val output = openFileOutput(BookmarkDataFileName, Context.MODE_PRIVATE)
            output.write(BookmarkData)
            output.close()
        }

        supportFragmentManager.beginTransaction().replace(R.id.contents_panel_main,HomeFragment()).commit()
        tabbar_main.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when(tabbar_main.selectedTabPosition){
                    0 -> supportFragmentManager.beginTransaction().replace(R.id.contents_panel_main,HomeFragment()).commit()
                    1 -> supportFragmentManager.beginTransaction().replace(R.id.contents_panel_main,SubjectSelectFragment()).commit()
                    2 -> supportFragmentManager.beginTransaction().replace(R.id.contents_panel_main,SearchFragment()).commit()
                    3 -> supportFragmentManager.beginTransaction().replace(R.id.contents_panel_main,ProfileFragment()).commit()
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    override fun onStop() {
        super.onStop()
        val output = openFileOutput(BookmarkDataFileName, Context.MODE_PRIVATE)
        output.write(BookmarkData)
        output.close()
    }
}
