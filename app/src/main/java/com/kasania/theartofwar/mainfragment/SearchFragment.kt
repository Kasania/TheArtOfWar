package com.kasania.theartofwar.mainfragment

import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import android.widget.TextView
import com.kasania.theartofwar.*
import com.kasania.theartofwar.studyfragment.StudyMainFragment
import kotlinx.android.synthetic.main.fragment_search.view.*
import java.lang.Exception
import kotlin.math.max

open class SearchFragment :Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        view.edt_search.setOnEditorActionListener { v, actionId, _ ->

            when(actionId){
                EditorInfo.IME_ACTION_SEARCH -> searchFromAll(v.text.toString(),view.contents_search_result)
                    else -> Log.d("edt_search","$actionId")
            }
            (context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                .hideSoftInputFromWindow(view.windowToken,InputMethodManager.RESULT_UNCHANGED_SHOWN)
            true
        }

        return view
    }


    private fun searchFromAll(text:String, registerTarget:LinearLayout):Int{

        var sumOfSearchResult =  0
        registerTarget.removeAllViews()

        for (chapter in minChapterNum..maxChapterNum){
            for (phrase in 1..maxPhraseNum[chapter]){

                val searchResult = searchData(text,chapter,phrase)
                if(searchResult[SEARCH_RESULT_SOUND] || searchResult[SEARCH_RESULT_INTERPRET]){
                    addSearchResultView(chapter,phrase,registerTarget, SEARCH_RESULT_SOUND)
                    sumOfSearchResult++
                }
                if(searchResult[SEARCH_RESULT_VOCA]){
                    addSearchResultView(chapter,phrase,registerTarget, SEARCH_RESULT_VOCA)
                    sumOfSearchResult++
                }
                if(searchResult[SEARCH_RESULT_COMMENT]){
                    addSearchResultView(chapter,phrase,registerTarget, SEARCH_RESULT_COMMENT)
                    sumOfSearchResult++
                }
            }
        }
        return sumOfSearchResult
    }



    private fun searchData(text:String,chapter:Int,phrase:Int):Array<Boolean>{

        val result = Array(4) {false}

        val soundId = resources.getIdentifier("@string/sound_${chapter}_$phrase","String",context?.packageName)
        val interpretId = resources.getIdentifier("@string/interpret_${chapter}_$phrase","String",context?.packageName)
        val vocaId = resources.getIdentifier("@string/voca_${chapter}_$phrase","String",context?.packageName)
        val commentId = resources.getIdentifier("@string/comment_${chapter}_$phrase","String",context?.packageName)
        try{
            if(LCS(text,getText(soundId)) == text.length){
                result[SEARCH_RESULT_SOUND] = true
            }
            if(LCS(text,context!!.getText(interpretId).toString()) == text.length){
                result[SEARCH_RESULT_INTERPRET] = true
            }
            if(LCS(text,context!!.getText(vocaId).toString()) == text.length){
                result[SEARCH_RESULT_VOCA] = true
            }
            if(LCS(text,context!!.getText(commentId).toString()) == text.length){
                result[SEARCH_RESULT_COMMENT] = true
            }
        }catch (e:Exception){
            Log.e("SEARCH","$chapter, $phrase")
        }

        return result

    }

    private fun LCS(srcText:String, dstText:CharSequence):Int{

        val srcTextLen = srcText.length
        val dstTextLen = dstText.length
        val LCSuff = Array((srcTextLen+1)*(dstTextLen+1)) {0}
        var result = 0

        for (i in 0..srcTextLen){
            for (j in 0..dstTextLen){
                if(i == 0 || j == 0)
                    LCSuff[i * dstTextLen + j] = 0
                else if(srcText[i-1] == dstText[j-1]){
                    LCSuff[i * dstTextLen + j] = LCSuff[((i-1) * dstTextLen) + j-1] + 1
                    result = max(result,LCSuff[i * dstTextLen + j])
                }
                else LCSuff[i * dstTextLen + j] = 0
            }
        }
        return result
    }

    private fun addSearchResultView(chapter:Int,phrase:Int,layout:LinearLayout, type:Int){
        val searchResultViewSet = LinearLayout(context)

        val chapterView = TextView(context)
        val typeView = TextView(context)
        val typeText = when(type) {
            SEARCH_RESULT_SOUND, SEARCH_RESULT_INTERPRET -> "문장 해석"
            SEARCH_RESULT_VOCA -> "중요 어휘"
            SEARCH_RESULT_COMMENT -> "구절 해설"
            else -> "ERROR : Can't find search type"
        }
        chapterView.text = String.format("제 %3d편 %3s",chapter,displayPhraseName[phrase])
        chapterView.gravity = Gravity.CENTER
        chapterView.textSize = 24.0f
        chapterView.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT,0.7f)
        chapterView.typeface = Typeface.MONOSPACE
        chapterView.setTextColor(ContextCompat.getColor(context!!,R.color.OnPrimary))

        typeView.text = typeText
        typeView.gravity = Gravity.CENTER
        typeView.textSize = 24.0f
        typeView.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT,0.3f)
        typeView.typeface = Typeface.MONOSPACE
        typeView.setTextColor(ContextCompat.getColor(context!!,R.color.OnPrimary))

        searchResultViewSet.background = ContextCompat.getDrawable(context!!,R.drawable.btn_search_result)
        searchResultViewSet.setOnClickListener {
            StudyMainFragment.createStudyContents(fragmentManager,chapter,phrase)
        }
        searchResultViewSet.weightSum = 1f
        val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT)
        layoutParams.setMargins(8,8,8,8)
        searchResultViewSet.layoutParams = layoutParams

        searchResultViewSet.addView(typeView)
        searchResultViewSet.addView(chapterView)
        layout.addView(searchResultViewSet)
    }

    companion object {
        private const val SEARCH_RESULT_SOUND = 0
        private const val SEARCH_RESULT_INTERPRET = 1
        private const val SEARCH_RESULT_VOCA = 2
        private const val SEARCH_RESULT_COMMENT = 3
    }
}