package com.kasania.theartofwar.mainfragment

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kasania.theartofwar.*
import com.kasania.theartofwar.MainActivity.Companion.todayAccessTimeMil
import kotlinx.android.synthetic.main.fragment_profile.view.*

open class ProfileFragment:Fragment() {
    private val milliSecToMinute = 60000.0f

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        createStatisticsView(view)
        createStatisticsResetButton(view)

        return view
    }

    private fun createStatisticsView(view:View){
        val sharedPref = activity!!.getSharedPreferences(SharedPrefName, Context.MODE_PRIVATE)

        val currentLongestDate = sharedPref.getInt(SharedPrefKeyCurrentContinueAccessDate,1)
        view.tv_currentlongestdate_time.text = String.format("%d",currentLongestDate)

        val totalAccessDate = sharedPref.getInt(SharedPrefKeyTotalAccessDate,1)
        view.tv_totalaccessdate_time.text = String.format("%d",totalAccessDate)

        val longestDate = sharedPref.getInt(SharedPrefKeyLongestAccessDate,1)
        view.tv_longestcontinuedate_time.text = String.format("%d",longestDate)


        val totalUsageTime = sharedPref.getLong(SharedPrefKeyTotalAccessTimeMil,0L)
        val totalMinute = totalUsageTime/milliSecToMinute
        view.tv_totalusagetime_time.text = String.format("%.1f",totalMinute)

        if(MainActivity.todayAccessTimeMil > sharedPref.getLong(SharedPrefKeyLongestDailyTime,0L)){
            sharedPref.edit()
                .putLong(SharedPrefKeyLongestDailyTime,MainActivity.todayAccessTimeMil)
                .apply()
        }
        val longestDailyTime = sharedPref.getLong(SharedPrefKeyLongestDailyTime,0L)/milliSecToMinute
        view.tv_longestdailytime_time.text = String.format("%.1f",longestDailyTime)

        val dailyMinute = totalUsageTime/totalAccessDate.toFloat()/milliSecToMinute
        view.tv_meandailytime_time.text = String.format("%.1f",dailyMinute)
    }

    private fun createStatisticsResetButton(view:View){
        view.btn_statistics_reset.setOnClickListener {
            val builder = AlertDialog.Builder(context)

            builder.setMessage(R.string.confirm_reset_statistics)
                .setPositiveButton(R.string.say_yes)
                { _, _ ->
                    val sharedPref = activity!!.getSharedPreferences(SharedPrefName,Context.MODE_PRIVATE)

                    sharedPref.edit()
                        .putInt(SharedPrefKeyCurrentContinueAccessDate,1)
                        .putInt(SharedPrefKeyTotalAccessDate,1)
                        .putInt(SharedPrefKeyLongestAccessDate,1)
                        .putLong(SharedPrefKeyTotalAccessTimeMil,0L)
                        .putLong(SharedPrefKeyLongestDailyTime,0L)
                        .apply()
                    todayAccessTimeMil = 0L
                    createStatisticsView(view)

                }

                .setNegativeButton(R.string.say_no)
                {_,_ ->}

            builder.create().show()

        }
    }
}