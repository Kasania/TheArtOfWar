package com.kasania.theartofwar.studyfragment

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.widget.NumberPicker
import android.widget.Toast
import com.kasania.theartofwar.R
import com.kasania.theartofwar.studyfragment.StudyMainFragment.Companion.currentChapter
import com.kasania.theartofwar.studyfragment.StudyMainFragment.Companion.displayPhraseName
import com.kasania.theartofwar.studyfragment.StudyMainFragment.Companion.currentPhrase
import com.kasania.theartofwar.studyfragment.StudyMainFragment.Companion.maxPhraseNum
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

        var targetChapter = currentChapter!!
        var targetPhrase = currentPhrase!!

        view.np_quick_chapter_chapter.minValue = 1
        view.np_quick_chapter_chapter.maxValue = 13
        view.np_quick_chapter_chapter.value = targetChapter
        view.np_quick_chapter_chapter.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS

        view.np_quick_chapter_chapter.setOnValueChangedListener { picker, oldVal, newVal ->
            view.np_quick_chapter_phrase.maxValue = maxPhraseNum[newVal]
        }

        view.np_quick_chapter_phrase.minValue = 0
        view.np_quick_chapter_phrase.maxValue = maxPhraseNum[targetChapter]
        view.np_quick_chapter_phrase.value = targetPhrase
        view.np_quick_chapter_phrase.displayedValues = displayPhraseName
        view.np_quick_chapter_phrase.descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS

        dialog.setView(view)
        dialog.setNegativeButton("취소",null)
        dialog.setPositiveButton("확인", DialogInterface.OnClickListener { dialog, which ->
            Toast.makeText(context,"${view.np_quick_chapter_chapter.value},${view.np_quick_chapter_phrase.value}",Toast.LENGTH_SHORT).show()
            currentChapter = view.np_quick_chapter_chapter.value
            currentPhrase = view.np_quick_chapter_phrase.value

            fragmentManager?.beginTransaction()?.replace(R.id.contents_panel_main,StudyMainFragment().newInstance(
                currentChapter!!, currentPhrase!!))?.commit()

        })
        return dialog.create()
    }

}