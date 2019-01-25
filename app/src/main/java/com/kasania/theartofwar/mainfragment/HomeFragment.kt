package com.kasania.theartofwar.mainfragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.kasania.theartofwar.R
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlin.random.Random


open class HomeFragment :Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        view.tv_home_level_percent.text = "${view.exp_percent_bar.progress}%"

        view.button_home_continue.setOnClickListener { v ->
            val rand = Random.nextInt(0,100)
            view.tv_home_level_percent.text = "$rand%"
            view.exp_percent_bar.progress = rand
        }

        view.exp_percent_bar.max = 100
        return view
    }
}