package com.kasania.theartofwar.mainfragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.kasania.theartofwar.*
import com.kasania.theartofwar.studyfragment.StudyMainFragment
import kotlinx.android.synthetic.main.fragment_favorite.view.*

class FavoriteFragment : Fragment(){

    private var currentSelectChapter = 0

    lateinit var buttonArray : ArrayList<Button>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_favorite,container,false)

        view.setOnTouchListener(object:onSwipeTouchListener(context!!){
            override fun onSwipeBottom() {
                super.onSwipeBottom()
                fragmentManager!!.popBackStack()
            }
        })

        buttonArray = ArrayList(13)

        buttonArray.add(view.btn_favorite_chapter_1)
        buttonArray.add(view.btn_favorite_chapter_2)
        buttonArray.add(view.btn_favorite_chapter_3)
        buttonArray.add(view.btn_favorite_chapter_4)
        buttonArray.add(view.btn_favorite_chapter_5)
        buttonArray.add(view.btn_favorite_chapter_6)
        buttonArray.add(view.btn_favorite_chapter_7)
        buttonArray.add(view.btn_favorite_chapter_8)
        buttonArray.add(view.btn_favorite_chapter_9)
        buttonArray.add(view.btn_favorite_chapter_10)
        buttonArray.add(view.btn_favorite_chapter_11)
        buttonArray.add(view.btn_favorite_chapter_12)
        buttonArray.add(view.btn_favorite_chapter_13)

        changeSelectButtonColor()
        showFavoriteContents(view)

        for (i in buttonArray.indices){
            buttonArray[i].setOnClickListener{
                currentSelectChapter = i
                changeSelectButtonColor()
                showFavoriteContents(view)
            }
        }


        return view

    }

    fun changeSelectButtonColor(){
        for (b in buttonArray){
            b.background= ContextCompat.getDrawable(context!!, R.drawable.btn_favorite_chapter)
        }
        buttonArray[currentSelectChapter].background = ContextCompat.getDrawable(context!!, R.drawable.btn_favorite_chapter_light)
    }

    fun showFavoriteContents(view:View){


        view.contents_favorite.removeAllViews()

        for (i in 0..(maxPhraseNum[currentSelectChapter+1]-1)){
            if(MainActivity.isCheckedBookMark(currentSelectChapter+1,i)){
                val textView = TextView(context)
                val text = "${currentSelectChapter+1} 편 ${displayPhraseName[i]}"
                textView.text = text
                textView.gravity = Gravity.CENTER
                textView.setTextColor(ContextCompat.getColor(context!!,R.color.OnPrimary))
                textView.setBackgroundColor(ContextCompat.getColor(context!!,R.color.SecondaryMain))
                textView.textSize = 24.0f
                textView.setOnClickListener{
                    StudyMainFragment.createStudyContents(fragmentManager,currentSelectChapter+1,i)
                }
                view.contents_favorite.addView(textView)

            }


        }

    }

}