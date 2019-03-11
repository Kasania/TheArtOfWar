package com.kasania.theartofwar

import android.content.Context
import android.os.Build
import android.speech.tts.TextToSpeech
import android.widget.Toast
import java.util.*

class TTS{

    lateinit var tts : TextToSpeech
    lateinit var context: Context
    var ttsSupport = 0

    fun initialize(context: Context){
        this.context = context
        tts = TextToSpeech(context, TextToSpeech.OnInitListener { status ->
            if (status == TextToSpeech.SUCCESS) {
                val result = tts.setLanguage(Locale.KOREAN)
                ttsSupport = result
                when (result) {
                    TextToSpeech.LANG_MISSING_DATA -> false
                    TextToSpeech.LANG_NOT_SUPPORTED -> false
                    else -> {
                        tts.setPitch(1.0f)
                        tts.setSpeechRate(1.0f)
                        ttsSupport = result
                    }
                }
            }
        })
    }

    fun speech(text:String){
        when (ttsSupport) {
            TextToSpeech.LANG_MISSING_DATA -> Toast.makeText(context,"TTS에 필요한 음성팩이 없습니다.", Toast.LENGTH_SHORT).show()
            TextToSpeech.LANG_NOT_SUPPORTED -> Toast.makeText(context,"TTS가 지원되지 않는 언어입니다.", Toast.LENGTH_SHORT).show()
            else -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
                // API 20
                else
                    tts.speak(text, TextToSpeech.QUEUE_FLUSH, null)
            }
        }
    }

//    fun stop(){
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
//            tts.speak("", TextToSpeech.QUEUE_FLUSH, null, null)
//        // API 20
//        else
//            tts.speak("", TextToSpeech.QUEUE_FLUSH, null)
//    }

    fun destroy(){

        tts.stop()
        tts.shutdown()
    }

}