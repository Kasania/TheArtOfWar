package com.kasania.theartofwar

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.kasania.theartofwar.mainfragment.HomeFragment
import com.kasania.theartofwar.mainfragment.MainFragment
import com.kasania.theartofwar.mainfragment.SearchFragment
import com.kasania.theartofwar.studyfragment.SubjectSelectFragment
import java.io.FileNotFoundException


class MainActivity : AppCompatActivity() {

    companion object {
        private const val BookmarkDataSize = totalPhraseNum + maxChapterNum
        var BookmarkData = ByteArray(BookmarkDataSize) {0.toByte()}

        fun toggleBookMark(chapter:Int = 1, phrase:Int = 0){
            if (isCheckedBookMark(chapter,phrase)){

                BookmarkData[sumOfPhrase[chapter-1] + phrase] = 0.toByte()
            }else{
                BookmarkData[sumOfPhrase[chapter-1] + phrase] = 1.toByte()
            }
        }

        fun isCheckedBookMark(chapter:Int = 1, phrase:Int = 0) : Boolean{
            if(BookmarkData[sumOfPhrase[chapter-1] + phrase] == 1.toByte()){
                return true
            }
            return false
        }

        var currentMainFragmentPage : Int = 0
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Bookmark
        loadFavorite()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.contents_root, MainFragment())
            .commit()

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

//    override fun onBackPressed() {
//        val fragment =
//            this.supportFragmentManager.findFragmentById(R.id.contents_root)
//        (fragment as? IOnBackPressed)?.onBackPressed()?.not()?.let {
//            super.onBackPressed()
//        }
//    }

}
