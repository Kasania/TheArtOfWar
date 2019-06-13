package com.kasania.theartofwar.studyfragment

import android.os.Build
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.text.util.Linkify
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.kasania.theartofwar.R
import com.kasania.theartofwar.TTS
import kotlinx.android.synthetic.main.fragment_study_phrase_comment.view.*
import java.util.regex.Pattern

class StudyPhraseCommentFragment :Fragment() {

    lateinit var tts : TTS

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_study_phrase_comment, container, false)
        addTTS()
        StudyMainFragment.tts_id = tts
        Log.i("TAG", "Found tts in comment: ${StudyMainFragment.tts_id}")
        view.sv_phrase_comment.setBackgroundResource(R.drawable.outline)
        val commentId = resources.getIdentifier("@string/comment_${StudyMainFragment.currentChapter}_${StudyMainFragment.currentPhrase}","String",context?.packageName)
        val commentValue = getText(commentId).split("//")

        for (s in commentValue){
            val commentView = TextView(context)
            commentView.setLineSpacing(20f,1f)
            commentView.text = StudyMainFragment.convertColoredText(s)
            commentView.textSize = 22f
            commentView.setTextColor(ContextCompat.getColor(context!!,R.color.Black))

            val transform = Linkify.TransformFilter { _, _ ->  ""}

            //val sunjaURL = Pattern.compile("손자")
            //Linkify.addLinks(interpretView,sunjaURL,"https://www.google.com/search?q=손무",null,transform)

            val a1URL = Pattern.compile("\\◆ 제 2차 세계대전\\(Wikipedia\\)")
            Linkify.addLinks(commentView,a1URL,"https://ko.wikipedia.org/wiki/제2차_세계_대전",null,transform)

            val a2URL = Pattern.compile("\\◆ 총력전\\(Wikipedia\\)")
            Linkify.addLinks(commentView,a2URL,"https://ko.wikipedia.org/wiki/총력전",null,transform)

            val a3URL = Pattern.compile("\\◆ 베트남 전쟁\\(Wikipedia\\)")
            Linkify.addLinks(commentView,a3URL,"https://ko.wikipedia.org/wiki/%EB%B2%A0%ED%8A%B8%EB%82%A8_%EC%A0%84%EC%9F%81",null,transform)

            val a4URL = Pattern.compile("\\◆ 러시아 원정\\(Wikipedia\\)")
            Linkify.addLinks(commentView,a4URL,"https://ko.wikipedia.org/wiki/%EB%9F%AC%EC%8B%9C%EC%95%84_%EC%9B%90%EC%A0%95",null,transform)

            val a5URL = Pattern.compile("\\◆ 조지 S. 패튼\\(Wikipedia\\)")
            Linkify.addLinks(commentView,a5URL,"https://ko.wikipedia.org/wiki/%EC%A1%B0%EC%A7%80_S._%ED%8C%A8%ED%8A%BC",null,transform)

            val a6URL = Pattern.compile("\\◆ 타넨베르크 전투\\(Wikipedia\\)")
            Linkify.addLinks(commentView,a6URL,"https://ko.wikipedia.org/wiki/%ED%83%80%EB%84%A8%EB%B2%A0%EB%A5%B4%ED%81%AC_%EC%A0%84%ED%88%AC_(1914%EB%85%84)",null,transform)

            val a7URL = Pattern.compile("\\◆ 합려\\(Wikipedia\\)")
            Linkify.addLinks(commentView,a7URL,"https://ko.wikipedia.org/wiki/%ED%95%A9%EB%A0%A4",null,transform)

            val a8URL = Pattern.compile("\\◆ 부차\\(Wikipedia\\)")
            Linkify.addLinks(commentView,a8URL,"https://ko.wikipedia.org/wiki/%EB%B6%80%EC%B0%A8",null,transform)

            val a9URL = Pattern.compile("\\◆ 조조\\(Wikipedia\\)")
            Linkify.addLinks(commentView,a9URL,"https://ko.wikipedia.org/wiki/%EC%A1%B0%EC%A1%B0",null,transform)

            val a10URL = Pattern.compile("\\◆ 진주만 공격\\(Wikipedia\\)")
            Linkify.addLinks(commentView,a10URL,"https://ko.wikipedia.org/wiki/%EC%A7%84%EC%A3%BC%EB%A7%8C_%EA%B3%B5%EA%B2%A9",null,transform)

            val a11URL = Pattern.compile("\\◆ 제2차 세계 대전\\(Wikipedia\\)")
            Linkify.addLinks(commentView,a11URL,"https://ko.wikipedia.org/wiki/%EC%A0%9C2%EC%B0%A8_%EC%84%B8%EA%B3%84_%EB%8C%80%EC%A0%84",null,transform)

            val a12URL = Pattern.compile("\\◆ 이라크 전쟁\\(Wikipedia\\)")
            Linkify.addLinks(commentView,a12URL,"https://ko.wikipedia.org/wiki/%EC%9D%B4%EB%9D%BC%ED%81%AC_%EC%A0%84%EC%9F%81",null,transform)

            val a13URL = Pattern.compile("\\◆ 아프가니스탄 전쟁 \\(2001년-현재\\)\\(Wikipedia\\)")
            Linkify.addLinks(commentView,a13URL,"https://ko.wikipedia.org/wiki/%EC%95%84%ED%94%84%EA%B0%80%EB%8B%88%EC%8A%A4%ED%83%84_%EC%A0%84%EC%9F%81_(2001%EB%85%84-%ED%98%84%EC%9E%AC)",null,transform)

            val a14URL = Pattern.compile("\\◆ 한스 폰 젝트\\(Wikipedia\\)")
            Linkify.addLinks(commentView,a14URL,"https://ko.wikipedia.org/wiki/%ED%95%9C%EC%8A%A4_%ED%8F%B0_%EC%A0%9D%ED%8A%B8",null,transform)

            val a15URL = Pattern.compile("\\◆ 나폴레옹 전쟁\\(Wikipedia\\)")
            Linkify.addLinks(commentView,a15URL,"https://ko.wikipedia.org/wiki/%EB%82%98%ED%8F%B4%EB%A0%88%EC%98%B9_%EC%A0%84%EC%9F%81",null,transform)

            val a16URL = Pattern.compile("\\◆ 바이마르 공화국\\(Wikipedia\\)")
            Linkify.addLinks(commentView,a16URL,"https://ko.wikipedia.org/wiki/%EB%B0%94%EC%9D%B4%EB%A7%88%EB%A5%B4_%EA%B3%B5%ED%99%94%EA%B5%AD",null,transform)

            val a17URL = Pattern.compile("\\◆ 통조림\\(Wikipedia\\)")
            Linkify.addLinks(commentView,a17URL,"https://ko.wikipedia.org/wiki/%ED%86%B5%EC%A1%B0%EB%A6%BC",null,transform)

            val a18URL = Pattern.compile("\\◆ 스페인 제국\\(Wikipedia\\)")
            Linkify.addLinks(commentView,a18URL,"https://ko.wikipedia.org/wiki/%EC%8A%A4%ED%8E%98%EC%9D%B8_%EC%A0%9C%EA%B5%AD",null,transform)

            val a19URL = Pattern.compile("\\◆ 공성전\\(Wikipedia\\)")
            Linkify.addLinks(commentView,a19URL,"https://ko.wikipedia.org/wiki/%EA%B3%B5%EC%84%B1%EC%A0%84",null,transform)

            val a20URL = Pattern.compile("\\◆ 제1차 중동 전쟁\\(Wikipedia\\)")
            Linkify.addLinks(commentView,a20URL,"https://ko.wikipedia.org/wiki/%EC%A0%9C1%EC%B0%A8_%EC%A4%91%EB%8F%99_%EC%A0%84%EC%9F%81",null,transform)

            val a21URL = Pattern.compile("\\◆ 바르바로사 작전\\(Wikipedia\\)")
            Linkify.addLinks(commentView,a21URL,"https://ko.wikipedia.org/wiki/%EB%B0%94%EB%A5%B4%EB%B0%94%EB%A1%9C%EC%82%AC_%EC%9E%91%EC%A0%84",null,transform)

            val a22URL = Pattern.compile("\\◆ 조지 마셜\\(Wikipedia\\)")
            Linkify.addLinks(commentView,a22URL,"https://ko.wikipedia.org/wiki/%EC%A1%B0%EC%A7%80_%EB%A7%88%EC%85%9C",null,transform)

            val a23URL = Pattern.compile("\\◆ 춘천 전투\\(Wikipedia\\)")
            Linkify.addLinks(commentView,a23URL,"https://ko.wikipedia.org/wiki/%EC%B6%98%EC%B2%9C_%EC%A0%84%ED%88%AC",null,transform)

            val a24URL = Pattern.compile("\\◆ 전쟁론\\(Wikipedia\\)")
            Linkify.addLinks(commentView,a24URL,"https://ko.wikipedia.org/wiki/%EC%A0%84%EC%9F%81%EB%A1%A0",null,transform)

            val a25URL = Pattern.compile("\\◆ 걸프 전쟁\\(Wikipedia\\)")
            Linkify.addLinks(commentView,a25URL,"https://ko.wikipedia.org/wiki/%EA%B1%B8%ED%94%84_%EC%A0%84%EC%9F%81",null,transform)

            val a26URL = Pattern.compile("\\◆ 제3차 중동 전쟁\\(Wikipedia\\)")
            Linkify.addLinks(commentView,a26URL,"https://ko.wikipedia.org/wiki/%EC%A0%9C3%EC%B0%A8_%EC%A4%91%EB%8F%99_%EC%A0%84%EC%9F%81",null,transform)

            val a27URL = Pattern.compile("\\◆ 켄투리오\\(Wikipedia\\)")
            Linkify.addLinks(commentView,a27URL,"https://ko.wikipedia.org/wiki/%EC%BC%84%ED%88%AC%EB%A6%AC%EC%98%A4",null,transform)

            val a28URL = Pattern.compile("\\◆ 파르살루스 전투\\(Wikipedia\\)")
            Linkify.addLinks(commentView,a28URL,"https://ko.wikipedia.org/wiki/%ED%8C%8C%EB%A5%B4%EC%82%B4%EB%A3%A8%EC%8A%A4_%EC%A0%84%ED%88%AC",null,transform)

            val a29URL = Pattern.compile("\\◆ 브루실로프 공세\\(Wikipedia\\)")
            Linkify.addLinks(commentView,a29URL,"https://ko.wikipedia.org/wiki/%EB%B8%8C%EB%A3%A8%EC%8B%A4%EB%A1%9C%ED%94%84_%EA%B3%B5%EC%84%B8",null,transform)

            val a30URL = Pattern.compile("\\◆ 무하마드 알리\\(Wikipedia\\)")
            Linkify.addLinks(commentView,a30URL,"https://ko.wikipedia.org/wiki/%EB%AC%B4%ED%95%98%EB%A7%88%EB%93%9C_%EC%95%8C%EB%A6%AC",null,transform)

            val a31URL = Pattern.compile("\\◆ 아우스터리츠 전투\\(Wikipedia\\)")
            Linkify.addLinks(commentView,a31URL,"https://ko.wikipedia.org/wiki/%EC%95%84%EC%9A%B0%EC%8A%A4%ED%84%B0%EB%A6%AC%EC%B8%A0_%EC%A0%84%ED%88%AC",null,transform)

            val a32URL = Pattern.compile("\\◆ 부산 교두보 전투\\(Wikipedia\\)")
            Linkify.addLinks(commentView,a32URL,"https://ko.wikipedia.org/wiki/%EB%B6%80%EC%82%B0_%EA%B5%90%EB%91%90%EB%B3%B4_%EC%A0%84%ED%88%AC",null,transform)

            val a33URL = Pattern.compile("\\◆ 제2차 포에니 전쟁\\(Wikipedia\\)")
            Linkify.addLinks(commentView,a33URL,"https://ko.wikipedia.org/wiki/%EC%A0%9C2%EC%B0%A8_%ED%8F%AC%EC%97%90%EB%8B%88_%EC%A0%84%EC%9F%81",null,transform)

            val a34URL = Pattern.compile("\\◆ 풍림화산\\(Wikipedia\\)")
            Linkify.addLinks(commentView,a34URL,"https://ko.wikipedia.org/wiki/%ED%92%8D%EB%A6%BC%ED%99%94%EC%82%B0",null,transform)

            val a35URL = Pattern.compile("\\◆ 합동지휘통제체계\\(Wikipedia\\)")
            Linkify.addLinks(commentView,a35URL,"https://ko.wikipedia.org/wiki/%ED%95%A9%EB%8F%99%EC%A7%80%ED%9C%98%ED%86%B5%EC%A0%9C%EC%B2%B4%EA%B3%84",null,transform)

            val a36URL = Pattern.compile("\\◆ 심리전\\(Wikipedia\\)")
            Linkify.addLinks(commentView,a36URL,"https://ko.wikipedia.org/wiki/%EC%8B%AC%EB%A6%AC%EC%A0%84",null,transform)

            val a37URL = Pattern.compile("\\◆ 섬멸전\\(Wikipedia\\)")
            Linkify.addLinks(commentView,a37URL,"https://ko.wikipedia.org/wiki/%EC%84%AC%EB%A9%B8%EC%A0%84",null,transform)

            val a38URL = Pattern.compile("\\◆ 인천 상륙 작전\\(Wikipedia\\)")
            Linkify.addLinks(commentView,a38URL,"https://ko.wikipedia.org/wiki/%EC%9D%B8%EC%B2%9C_%EC%83%81%EB%A5%99_%EC%9E%91%EC%A0%84",null,transform)

            val a39URL = Pattern.compile("\\◆ 스탈린그라드 전투\\(Wikipedia\\)")
            Linkify.addLinks(commentView,a39URL,"https://ko.wikipedia.org/wiki/%EC%8A%A4%ED%83%88%EB%A6%B0%EA%B7%B8%EB%9D%BC%EB%93%9C_%EC%A0%84%ED%88%AC",null,transform)

            val a40URL = Pattern.compile("\\◆ 장비\\(Wikipedia\\)")
            Linkify.addLinks(commentView,a40URL,"https://ko.wikipedia.org/wiki/%EC%9E%A5%EB%B9%84",null,transform)

            val a41URL = Pattern.compile("\\◆ 한신\\(Wikipedia\\)")
            Linkify.addLinks(commentView,a41URL,"https://ko.wikipedia.org/wiki/%ED%95%9C%EC%8B%A0_(%ED%9A%8C%EC%9D%8C%ED%9B%84)",null,transform)

            val a42URL = Pattern.compile("\\◆ 산악전\\(Wikipedia\\)")
            Linkify.addLinks(commentView,a42URL,"https://ko.wikipedia.org/wiki/%EC%82%B0%EC%95%85%EC%A0%84",null,transform)

            val a43URL = Pattern.compile("\\◆ 중공군 춘계공세\\(Wikipedia\\)")
            Linkify.addLinks(commentView,a43URL,"https://ko.wikipedia.org/wiki/%EC%A4%91%EA%B3%B5%EA%B5%B0_%EC%B6%98%EA%B3%84%EA%B3%B5%EC%84%B8",null,transform)

            val a44URL = Pattern.compile("\\◆ 감청\\(Wikipedia\\)")
            Linkify.addLinks(commentView,a44URL,"https://ko.wikipedia.org/wiki/%EA%B0%90%EC%B2%AD",null,transform)

            val a45URL = Pattern.compile("\\◆ 마속\\(Wikipedia\\)")
            Linkify.addLinks(commentView,a45URL,"https://ko.wikipedia.org/wiki/%EB%A7%88%EC%86%8D",null,transform)

            val a46URL = Pattern.compile("\\◆ 단장의 능선 전투\\(Wikipedia\\)")
            Linkify.addLinks(commentView,a46URL,"https://ko.wikipedia.org/wiki/%EB%8B%A8%EC%9E%A5%EC%9D%98_%EB%8A%A5%EC%84%A0_%EC%A0%84%ED%88%AC",null,transform)

            val a47URL = Pattern.compile("\\◆ 저격능선 전투\\(Wikipedia\\)")
            Linkify.addLinks(commentView,a47URL,"https://ko.wikipedia.org/wiki/%EC%A0%80%EA%B2%A9%EB%8A%A5%EC%84%A0_%EC%A0%84%ED%88%AC",null,transform)

            val a48URL = Pattern.compile("\\◆ 이순신\\(Wikipedia\\)")
            Linkify.addLinks(commentView,a48URL,"https://ko.wikipedia.org/wiki/%EC%9D%B4%EC%88%9C%EC%8B%A0",null,transform)

            val a49URL = Pattern.compile("\\◆ 오기\\(Wikipedia\\)")
            Linkify.addLinks(commentView,a49URL,"https://ko.wikipedia.org/wiki/%EC%98%A4%EA%B8%B0",null,transform)

            val a50URL = Pattern.compile("\\◆ 현리 전투\\(Wikipedia\\)")
            Linkify.addLinks(commentView,a50URL,"https://ko.wikipedia.org/wiki/%ED%98%84%EB%A6%AC_%EC%A0%84%ED%88%AC",null,transform)

            val a51URL = Pattern.compile("\\◆ 지평리 전투\\(Wikipedia\\)")
            Linkify.addLinks(commentView,a51URL,"https://ko.wikipedia.org/wiki/%EC%A7%80%ED%8F%89%EB%A6%AC_%EC%A0%84%ED%88%AC",null,transform)

            val a52URL = Pattern.compile("\\◆ 장진호 전투\\(Wikipedia\\)")
            Linkify.addLinks(commentView,a52URL,"https://ko.wikipedia.org/wiki/%EC%9E%A5%EC%A7%84%ED%98%B8_%EC%A0%84%ED%88%AC",null,transform)

            val a53URL = Pattern.compile("\\◆ 살수 대첩\\(Wikipedia\\)")
            Linkify.addLinks(commentView,a53URL,"https://ko.wikipedia.org/wiki/%EC%82%B4%EC%88%98_%EB%8C%80%EC%B2%A9",null,transform)

            val a54URL = Pattern.compile("\\◆ 쿠바 미사일 위기\\(Wikipedia\\)")
            Linkify.addLinks(commentView,a54URL,"https://ko.wikipedia.org/wiki/%EC%BF%A0%EB%B0%94_%EB%AF%B8%EC%82%AC%EC%9D%BC_%EC%9C%84%EA%B8%B0",null,transform)

            val a55URL = Pattern.compile("\\◆ 비잔티움 제국\\(Wikipedia\\)")
            Linkify.addLinks(commentView,a55URL,"https://ko.wikipedia.org/wiki/%EB%B9%84%EC%9E%94%ED%8B%B0%EC%9B%80_%EC%A0%9C%EA%B5%AD",null,transform)

            val a56URL = Pattern.compile("\\◆ 간첩 행위\\(Wikipedia\\)")
            Linkify.addLinks(commentView,a56URL,"https://ko.wikipedia.org/wiki/%EA%B0%84%EC%B2%A9_%ED%96%89%EC%9C%84",null,transform)

            val a57URL = Pattern.compile("\\◆ 리하르트 조르게\\(Wikipedia\\)")
            Linkify.addLinks(commentView,a57URL,"https://ko.wikipedia.org/wiki/%EB%A6%AC%ED%95%98%EB%A5%B4%ED%8A%B8_%EC%A1%B0%EB%A5%B4%EA%B2%8C",null,transform)

            val a58URL = Pattern.compile("\\◆ 마타 하리\\(Wikipedia\\)")
            Linkify.addLinks(commentView,a58URL,"https://ko.wikipedia.org/wiki/%EB%A7%88%ED%83%80_%ED%95%98%EB%A6%AC",null,transform)


            val commentViewLayout = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            commentViewLayout.setMargins(30,20,30,30)
            commentView.layoutParams = commentViewLayout
            commentView.setOnClickListener {
                tts.speech(commentView.text.toString())
            }

            view.sv_phrase_comment.addView(commentView)
        }

        return view
    }

    fun addTTS() : Fragment{
        this.tts = TTS()
        tts.initialize(context!!)
        return this
    }

}