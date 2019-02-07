package com.kasania.theartofwar.mainfragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kasania.theartofwar.MainActivity
import com.kasania.theartofwar.MainActivity.Companion.currentMainFragmentPage
import com.kasania.theartofwar.R
import com.kasania.theartofwar.studyfragment.SubjectSelectFragment
import kotlinx.android.synthetic.main.fragment_main.view.*

class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_main,container,false)

        val ft = fragmentManager!!
            .beginTransaction()
            when(MainActivity.currentMainFragmentPage){
                0 -> ft.replace(R.id.contents_panel_main,HomeFragment())
                1 -> ft.replace(R.id.contents_panel_main, SubjectSelectFragment())
                2 -> ft.replace(R.id.contents_panel_main, SearchFragment())
                else ->ft.replace(R.id.contents_panel_main,HomeFragment())
            }
            ft.commit()
        setButtonBackground(view)



        view.btn_main_bottom_home.setOnClickListener {
            bottomButtonAction(0)
            currentMainFragmentPage = 0
            setButtonBackground(view)
        }
        view.btn_main_bottom_study.setOnClickListener {
            when (currentMainFragmentPage){
                0->bottomButtonAction(1)
                2->bottomButtonAction(2)
            }
            currentMainFragmentPage = 1
            setButtonBackground(view)
        }
        view.btn_main_bottom_search.setOnClickListener {
            bottomButtonAction(3)
            currentMainFragmentPage = 2
            setButtonBackground(view)
        }


        return view

    }

    private fun bottomButtonAction(action:Int){
        val fm = fragmentManager!!.beginTransaction()
        when (action){
            0 -> fm.setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right)
                .replace(R.id.contents_panel_main,HomeFragment())
            1 -> fm.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left)
                .replace(R.id.contents_panel_main, SubjectSelectFragment())
            2 -> fm.setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right)
                .replace(R.id.contents_panel_main, SubjectSelectFragment())
            3 -> fm.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left)
                .replace(R.id.contents_panel_main, SearchFragment())
        }
        fm.commit()
    }

    private fun setButtonBackground(v : View){
        when(currentMainFragmentPage){
            0 -> {
                v.btn_main_bottom_home.background =
                    ContextCompat.getDrawable(context!!, R.drawable.img_btn_main_home_dark)
                v.btn_main_bottom_study.background =
                    ContextCompat.getDrawable(context!!, R.drawable.btn_main_study)
                v.btn_main_bottom_search.background =
                    ContextCompat.getDrawable(context!!, R.drawable.btn_main_search)
            }
            1 -> {
                v.btn_main_bottom_study.background =
                    ContextCompat.getDrawable(context!!, R.drawable.img_btn_main_study_dark)
                v.btn_main_bottom_home.background =
                    ContextCompat.getDrawable(context!!, R.drawable.btn_main_home)
                v.btn_main_bottom_search.background =
                    ContextCompat.getDrawable(context!!, R.drawable.btn_main_search)
            }
            2 -> {
                v.btn_main_bottom_search.background =
                    ContextCompat.getDrawable(context!!, R.drawable.img_btn_main_search_dark)
                v.btn_main_bottom_home.background =
                    ContextCompat.getDrawable(context!!, R.drawable.btn_main_home)
                v.btn_main_bottom_study.background =
                    ContextCompat.getDrawable(context!!, R.drawable.btn_main_study)
            }
        }
    }
}