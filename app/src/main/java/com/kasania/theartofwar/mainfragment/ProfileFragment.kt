package com.kasania.theartofwar.mainfragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kasania.theartofwar.*
import kotlinx.android.synthetic.main.fragment_profile.view.*

open class ProfileFragment:Fragment() {
    private val milisecToMinute = 60000.0f

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val sharedPref = activity!!.getSharedPreferences(SharedPrefName, Context.MODE_PRIVATE)

        val totalAccessDate = sharedPref.getInt(SharedPrefKeyTotalAccessDate,0)
        view.tv_totalaccessdate_time.text = String.format("%d",totalAccessDate)

        val longestDate = sharedPref.getInt(SharedPrefKeyLongestAccessDate,0)
        view.tv_longestcontinuedate_time.text = String.format("%d",longestDate)

        val totalUsageTime = sharedPref.getLong(SharedPrefKeyTotalAccessTimeMil,0L)
        val totalMinute = totalUsageTime/milisecToMinute
        view.tv_totalusagetime_time.text = String.format("%.1f",totalMinute)

        if(MainActivity.todayAccessTimeMil > sharedPref.getLong(SharedPrefKeyLongestDailyTime,0L)){
            sharedPref.edit()
                .putLong(SharedPrefKeyLongestDailyTime,MainActivity.todayAccessTimeMil)
                .apply()
        }
        val longestDailyTime = sharedPref.getLong(SharedPrefKeyLongestDailyTime,0L)/milisecToMinute
        view.tv_longestdailytime_time.text = String.format("%.1f",longestDailyTime)

        val dailyMinute = totalUsageTime/totalAccessDate.toFloat()/milisecToMinute
        view.tv_meandailytime_time.text = String.format("%.1f",dailyMinute)

        return view
    }
}