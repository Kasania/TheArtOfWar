package com.kasania.theartofwar.mainfragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kasania.theartofwar.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_setting.view.*
import java.util.*

class SettingFragment :Fragment(), MainActivity.OnBackPressedListener {

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
            fragmentManager!!.popBackStack("Setting", FragmentManager.POP_BACK_STACK_INCLUSIVE)
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
        val view = inflater.inflate(R.layout.fragment_setting,container,false)

        view.button2.setOnClickListener() {
            activity!!.getSharedPreferences(SharedPrefName, Context.MODE_PRIVATE).edit().putInt(BackGroundImg, 0).apply()
            activity!!.contents_root.setBackgroundResource(R.drawable.background_main)
        }

        view.button3.setOnClickListener() {
            activity!!.getSharedPreferences(SharedPrefName, Context.MODE_PRIVATE).edit().putInt(BackGroundImg, 1).apply()
            activity!!.contents_root.setBackgroundResource(R.drawable.summary_1_background)
        }

        view.button4.setOnClickListener() {
            activity!!.getSharedPreferences(SharedPrefName, Context.MODE_PRIVATE).edit().putInt(BackGroundImg, 2).apply()
            activity!!.contents_root.setBackgroundResource(R.drawable.summary_1_comment)
        }

        view.button5.setOnClickListener() {
            val ValPitch = activity!!.getSharedPreferences(SharedPrefName,Context.MODE_PRIVATE).getFloat(SetPitchValue, 1.0f)
            activity!!.getSharedPreferences(SharedPrefName, Context.MODE_PRIVATE).edit().putFloat(SetPitchValue, (ValPitch + 0.1f)).apply()
            MainActivity.VaSetPitchValue = MainActivity.VaSetPitchValue + 0.1f
            view.tone.setText(MainActivity.VaSetPitchValue.toString())
        }

        view.button6.setOnClickListener() {
            val ValPitch = activity!!.getSharedPreferences(SharedPrefName,Context.MODE_PRIVATE).getFloat(SetPitchValue, 1.0f)
            activity!!.getSharedPreferences(SharedPrefName, Context.MODE_PRIVATE).edit().putFloat(SetPitchValue, (ValPitch - 0.1f)).apply()
            MainActivity.VaSetPitchValue = MainActivity.VaSetPitchValue - 0.1f
            view.tone.setText(MainActivity.VaSetPitchValue.toString())
        }

        view.button7.setOnClickListener() {
            val ValRate = activity!!.getSharedPreferences(SharedPrefName,Context.MODE_PRIVATE).getFloat(SetPitchRate, 1.0f)
            activity!!.getSharedPreferences(SharedPrefName, Context.MODE_PRIVATE).edit().putFloat(SetPitchRate, (ValRate + 0.1f)).apply()
            MainActivity.VaSetPitchRate = MainActivity.VaSetPitchRate + 0.1f
            view.speed.setText(MainActivity.VaSetPitchRate.toString())
        }

        view.button8.setOnClickListener() {
            val ValRate = activity!!.getSharedPreferences(SharedPrefName,Context.MODE_PRIVATE).getFloat(SetPitchRate, 1.0f)
            activity!!.getSharedPreferences(SharedPrefName, Context.MODE_PRIVATE).edit().putFloat(SetPitchRate, (ValRate - 0.1f)).apply()
            MainActivity.VaSetPitchRate = MainActivity.VaSetPitchRate - 0.1f
            view.speed.setText(MainActivity.VaSetPitchRate.toString())
        }
        view.tone.setText(MainActivity.VaSetPitchValue.toString())
        view.speed.setText(MainActivity.VaSetPitchRate.toString())

        return view
    }


}

