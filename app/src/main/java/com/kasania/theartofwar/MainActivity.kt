package com.kasania.theartofwar

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kasania.theartofwar.mainfragment.HomeFragment
import com.kasania.theartofwar.mainfragment.ProfileFragment
import com.kasania.theartofwar.mainfragment.SearchFragment
import kotlinx.android.synthetic.main.activity_main.*
import android.support.design.widget.TabLayout
import com.kasania.theartofwar.studyfragment.SubjectSelectFragment

class MainActivity : AppCompatActivity() {

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

    }
}
