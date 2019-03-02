package com.kasania.theartofwar

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.kasania.theartofwar.mainfragment.MainFragment
import java.io.FileNotFoundException
import java.util.*

class MainActivity : AppCompatActivity() {

    companion object {

        var currentMainFragmentPage = 0

        var todayAccessTimeMil = 0L

        private const val DataSize = totalPhraseNum + maxChapterNum

        var BookmarkData = ByteArray(DataSize) {0.toByte()}

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

        var PhraseAccessCount = IntArray(DataSize){0}
        fun increaseAccessCount(chapter:Int = 1, phrase:Int = 0){
            PhraseAccessCount[sumOfPhrase[chapter-1] + phrase]++
            Log.d("count","${PhraseAccessCount[sumOfPhrase[chapter-1] + phrase]}")
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Bookmark
        loadFavorite()
        loadPhraseAccessCount()
        checkPresent()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.contents_root, MainFragment())
            .commit()

    }

    private fun checkPresent() {
        val today = Calendar.getInstance()
        val date = today.get(Calendar.DAY_OF_YEAR)
        val sharedPref = getSharedPreferences(SharedPrefName,Context.MODE_PRIVATE)
        val totalDate = sharedPref.getInt(SharedPrefKeyTotalAccessDate,0)
        val lastDate = sharedPref.getInt(SharedPrefKeyLastAccessDate,1)
        val currentLongestDate = sharedPref.getInt(SharedPrefKeyCurrentContinueAccessDate,0)

        if(date == lastDate){
            Log.d("LastDate","Today")
        }
        else {
            sharedPref.edit()
                .putLong(SharedPrefKeyTodayAccessTime,0L)
                .putInt(SharedPrefKeyTotalAccessDate,totalDate+1)
                .putInt(SharedPrefKeyLastAccessDate,date)
                .apply()

            if(date == lastDate+1){
                sharedPref.edit().putInt(SharedPrefKeyCurrentContinueAccessDate,currentLongestDate+1).apply()
                if(currentLongestDate+1 > sharedPref.getInt(SharedPrefKeyLongestAccessDate,0)){
                    sharedPref.edit()
                        .putInt(SharedPrefKeyLongestAccessDate,currentLongestDate+1)
                        .apply()
                }
                else{
                    sharedPref.edit().putInt(SharedPrefKeyCurrentContinueAccessDate,1).apply()
                }
            }

        }
        todayAccessTimeMil = sharedPref.getLong(SharedPrefKeyTodayAccessTime,0L)

        Log.d("totalDate",totalDate.toString())
        Log.d("LastDate",lastDate.toString())
        Log.d("Today",date.toString())
        Log.d("CurrentLongestDate",currentLongestDate.toString())
        Log.d("TodayAccessTime",sharedPref.getLong(SharedPrefKeyTodayAccessTime,0L).toString())
        Log.d("TotalAccessTime",sharedPref.getLong(SharedPrefKeyTotalAccessTimeMil,0L).toString())

    }

    override fun onStop() {
        super.onStop()

        val output = openFileOutput(BookmarkDataFileName, Context.MODE_PRIVATE)
        output.write(BookmarkData)
        output.close()

        val output2 = openFileOutput(PhraseAccessCountDataFileName, Context.MODE_PRIVATE)
        output2.write(PhraseAccessCount.toString().toByteArray())
        output2.close()

        val sharedPref = getSharedPreferences(SharedPrefName,Context.MODE_PRIVATE)
        sharedPref.edit()
            .putLong(SharedPrefKeyTodayAccessTime, todayAccessTimeMil)
            .apply()

        if(MainActivity.todayAccessTimeMil > sharedPref.getLong(SharedPrefKeyLongestDailyTime,0L)){
            sharedPref.edit()
                .putLong(SharedPrefKeyLongestDailyTime,MainActivity.todayAccessTimeMil)
                .apply()
        }
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

    private fun loadPhraseAccessCount(){
        //dosent work you think
        try {
            val input = openFileInput(PhraseAccessCountDataFileName)
            val inputData = ByteArray(DataSize)
            input.read(inputData)
            input.close()
            String(inputData).forEachIndexed { index, c ->  PhraseAccessCount[index] = c.toInt()}

        }catch(e: FileNotFoundException){
            val output = openFileOutput(PhraseAccessCountDataFileName, Context.MODE_PRIVATE)
            output.write(PhraseAccessCount.toString().toByteArray())
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
