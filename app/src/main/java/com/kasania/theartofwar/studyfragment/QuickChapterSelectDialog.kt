package com.kasania.theartofwar.studyfragment

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.NumberPicker
import android.widget.Toast
import com.kasania.theartofwar.R
import com.kasania.theartofwar.studyfragment.StudyMainFragment.Companion.chapter
import com.kasania.theartofwar.studyfragment.StudyMainFragment.Companion.phrase
import com.kasania.theartofwar.studyfragment.StudyMainFragment.Companion.phraseNum
import kotlinx.android.synthetic.main.dialog_study_quick_chepter_select.*
import kotlinx.android.synthetic.main.dialog_study_quick_chepter_select.view.*

class QuickChapterSelectDialog:DialogFragment() {

    fun newInstance() :QuickChapterSelectDialog{
        val dialog = QuickChapterSelectDialog()
        val args = Bundle()

        dialog.arguments = args

        return dialog
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = AlertDialog.Builder(activity)

        val view = activity!!.layoutInflater.inflate(R.layout.dialog_study_quick_chepter_select,null)

        dialog.setView(view)

//        dialog.setTitle("빠른 이동")
//        dialog.setMessage("quick")
        dialog.setNegativeButton("취소",null)
        dialog.setPositiveButton("확인",null)

        var targetChapter = chapter!!
        var targetPhrase = phrase!!

        view.np_quick_chapter_chapter.minValue = 0
        view.np_quick_chapter_chapter.maxValue = 13
        view.np_quick_chapter_chapter.value = targetChapter
//        view.np_quick_chapter_chapter.wrapSelectorWheel = false
        view.np_quick_chapter_chapter.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS

        view.np_quick_chapter_chapter.setOnValueChangedListener { picker, oldVal, newVal ->
            view.np_quick_chapter_phrase.maxValue = phraseNum[newVal]
        }

        view.np_quick_chapter_phrase.minValue = 0
        view.np_quick_chapter_phrase.maxValue = phraseNum[targetChapter]
        view.np_quick_chapter_phrase.value = targetPhrase
//        view.np_quick_chapter_phrase.wrapSelectorWheel = false
        view.np_quick_chapter_phrase.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS

//        Toast.makeText(context,"${view.np_quick_chapter_chapter.value},${view.np_quick_chapter_phrase.value}",Toast.LENGTH_SHORT).show()

        return dialog.create()
    }

}