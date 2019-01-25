package com.kasania.theartofwar.studyfragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kasania.theartofwar.R
import kotlinx.android.synthetic.main.fragment_study_phrase_main.view.*


class StudyPhraseMainFragment : Fragment() {

//    private val arg1 = "param1"
//    private val arg2 = "param2"
//
//    var chapter: Int? = null
//    var phrase: Int? = null
//
//    fun newInstance(chapter:Int,phrase:Int): StudyPhraseMainFragment
//    {
//        val args = Bundle()
//        val frag = StudyPhraseMainFragment()
//        args.putInt(arg1,chapter)
//        args.putInt(arg2,phrase)
//        frag.arguments = args
//
//        return frag
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        if (arguments != null) {
//            chapter = arguments?.getInt(arg1)
//            phrase = arguments?.getInt(arg2)
//        }
//    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater.inflate(R.layout.fragment_study_phrase_main, container, false)

        fragmentManager?.beginTransaction()?.replace(R.id.contents_panel_study_phrase,StudyPhraseSummaryFragment())?.commit()
        

        return view
    }


    fun MakeContents(){

    }
}