package com.kasania.theartofwar.mainfragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kasania.theartofwar.*

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






        return view
    }
}