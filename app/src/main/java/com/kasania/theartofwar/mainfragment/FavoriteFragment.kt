package com.kasania.theartofwar.mainfragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.kasania.theartofwar.*
import com.kasania.theartofwar.studyfragment.StudyMainFragment
import kotlinx.android.synthetic.main.fragment_favorite.view.*
import kotlinx.android.synthetic.main.fragment_study_phrase_interpret.view.*

class FavoriteFragment : Fragment(), MainActivity.OnBackPressedListener {

    private var currentSelectChapter = 0

    lateinit var buttonArray : ArrayList<Button>

    override fun onBack() {
        Log.e("Other", "onBack()")
        // 리스너를 설정하기 위해 Activity 를 받아옵니다.
        val activity = activity as MainActivity?
        // 한번 뒤로가기 버튼을 눌렀다면 Listener 를 null 로 해제해줍니다.
        //activity!!.setOnBackPressedListener()
        activity?.setOnBackPressedListener(null)
        // MainFragment 로 교체
        //fragmentManager!!.beginTransaction().replace(R.id.contents_root, MainFragment()).commit()
        //activity?.onBackPressed();
        //for (b in 0..fragmentManager!!.backStackEntryCount) {
        fragmentManager!!.popBackStack("Favorite", FragmentManager.POP_BACK_STACK_INCLUSIVE)
        //}
    }

    // Fragment 호출 시 반드시 호출되는 오버라이드 메소드입니다.
    override//                     혹시 Context 로 안되시는분은 Activity 로 바꿔보시기 바랍니다.
    fun onAttach(context: Context?) {
        super.onAttach(context)
        Log.e("Other", "onAttach()")
        (context as MainActivity).setOnBackPressedListener(this)
    }

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

        for (i in 1..(maxPhraseNum[currentSelectChapter+1])){
            if(MainActivity.isCheckedBookMark(currentSelectChapter+1,i)){
                val textView = TextView(context)
                val text = "${currentSelectChapter+1} 편 ${displayPhraseName[i]}"
                textView.text = text
                textView.gravity = Gravity.CENTER
                textView.setTextColor(ContextCompat.getColor(context!!,R.color.Black))
                //textView.setBackgroundColor(ContextCompat.getColor(context!!,R.color.OnPrimary))
                textView.setBackgroundResource(R.drawable.outline)
                textView.textSize = 22.0f
                val layout = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT)
                layout.setMargins(5,5,5,5)
                textView.layoutParams = layout
                textView.setOnClickListener{
                    StudyMainFragment.createStudyContents(fragmentManager,currentSelectChapter+1,i)
                }
                view.contents_favorite.addView(textView)

            }


        }

    }

}