package com.kasania.theartofwar.studyfragment

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.text.Html
import android.text.Spanned
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.kasania.theartofwar.*
import kotlinx.android.synthetic.main.fragment_study_main.view.*
import kotlinx.android.synthetic.main.fragment_study_menu.view.*


class StudyMainFragment : Fragment()/*, IOnBackPressed*/{
//    override fun onBackPressed(): Boolean {
//        for(b in 0..fragmentManager!!.backStackEntryCount){
//            fragmentManager!!.popBackStack("StudyMain", FragmentManager.POP_BACK_STACK_INCLUSIVE)
//        }
//        return true
//    }

    companion object {
        var currentChapter: Int = 1
        var currentPhrase: Int = 0

        private fun setContentsView(ft:FragmentTransaction){

            ft.replace(R.id.contents_mainmenu,StudyMenuFragment())
            when(currentPhrase){
                0 -> ft.replace(R.id.contents_panel_study_main,StudyChapterSummaryFragment())
                else -> ft.replace(R.id.contents_panel_study_main,StudyPhraseMainFragment())
            }
            MainActivity.increaseAccessCount(currentChapter, currentPhrase)
        }

        fun createStudyContents(fm:FragmentManager?, chapter:Int = 1, phrase:Int = 0){
            currentChapter = chapter
            currentPhrase = phrase
            val ft = fm!!.beginTransaction()
            if(enableAnimation){
                ft.setCustomAnimations(R.anim.slide_in_bottom,R.anim.slide_out_top,R.anim.slide_in_top,R.anim.slide_out_bottom)
            }
            ft.replace(R.id.contents_root,StudyMainFragment().newInstance())
                .addToBackStack("StudyMain")
            ft.commit()
        }

        fun changeStudyContents(fm:FragmentManager?, chapter:Int = 1, phrase:Int = 0,action:Int = 1){
            currentChapter = chapter
            currentPhrase = phrase
            val ft = fm!!.beginTransaction()
            if(enableAnimation)
            when (action){
                1 -> ft.setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right)
                2 -> ft.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                3 -> ft.setCustomAnimations(R.anim.slide_in_bottom, R.anim.slide_out_top)
            }
            setContentsView(ft)
            ft.commit()
        }

        fun convertColoredText(s:String):Spanned{
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(s,Html.FROM_HTML_MODE_COMPACT)
            } else {
                Html.fromHtml(s)
            }
        }

    }
    private fun newInstance():StudyMainFragment{
        val fragment = StudyMainFragment()

        return fragment
    }

    private var startTime = 0L

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {

        val view = inflater.inflate(R.layout.fragment_study_main, container, false)
        startTime = System.currentTimeMillis()
        setChapterTextView(view)

        changeStudyContents(fragmentManager, currentChapter, currentPhrase,0)


        view.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_UP) {
                Log.d("key","back")

                return@OnKeyListener true
            }
            false
        })

                /*view.btn_study_back_to_chapter.setOnClickListener {
            for(b in 0..fragmentManager!!.backStackEntryCount){
                fragmentManager!!.popBackStack("StudyMain", FragmentManager.POP_BACK_STACK_INCLUSIVE)
            }
        }*/
        // 뒤로가기버튼



        return view
    }

    override fun onStop() {
        val endTime = System.currentTimeMillis()
        val accessTime = ((endTime - startTime))
        val lastAccessTime =  activity!!.getSharedPreferences(SharedPrefName, Context.MODE_PRIVATE).getLong(SharedPrefKeyTotalAccessTimeMil,0L)

        activity!!.getSharedPreferences(SharedPrefName, Context.MODE_PRIVATE).edit()
            .putInt(SharedPrefKeyLastChapter, currentChapter)
            .putInt(SharedPrefKeyLastPhrase, currentPhrase)
            .putLong(SharedPrefKeyTotalAccessTimeMil,accessTime+lastAccessTime)
            .apply()
        MainActivity.todayAccessTimeMil = MainActivity.todayAccessTimeMil + accessTime

        super.onStop()
    }

    private fun updateStudyFragmentWithOffset(phraseOffset:Int){

        var phrase = currentPhrase + phraseOffset
        var chapter = currentChapter

        if(phrase< 0){
            //negative
            chapter--
            if (chapter < minChapterNum){
                chapter = maxChapterNum
            }
            phrase = maxPhraseNum[chapter] + (phrase + 1)

        }else if(phrase > maxPhraseNum[currentChapter]){
            //positive
            chapter++
            if (chapter > maxChapterNum){
                chapter = minChapterNum
            }
            phrase -= (maxPhraseNum[currentChapter] + 1)
        }
        //to negative 1 / positive 2
        val animationDir = when(phraseOffset){
            -1 -> 1
            1 -> 2
            else -> 1
        }

        changeStudyContents(fragmentManager,chapter,phrase,animationDir)
    }

    private fun setChapterTextView(v:View){
        val displayPhrase = when (currentPhrase){
            0-> "총괄"
            else -> "$currentPhrase 절"
        }
        val currentChapterText = "${resources.getString(R.string.chapter_prefix_name)} $currentChapter ${resources.getString(R.string.chapter_postfix_name)} $displayPhrase"
        v.tv_study_current_chapter?.text = currentChapterText
    }

}