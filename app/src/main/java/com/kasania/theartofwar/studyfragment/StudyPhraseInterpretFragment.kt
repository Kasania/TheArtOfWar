package com.kasania.theartofwar.studyfragment
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.text.Html
import android.text.util.Linkify
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.kasania.theartofwar.R
import com.kasania.theartofwar.TTS
import kotlinx.android.synthetic.main.fragment_study_phrase_interpret.view.*
import java.util.regex.Pattern
import android.opengl.ETC1.getHeight
import android.opengl.ETC1.getWidth
import android.graphics.Outline
import android.support.v4.app.FragmentManager
import android.util.Log
import android.view.ViewOutlineProvider
import com.kasania.theartofwar.MainActivity
import com.kasania.theartofwar.studyfragment.StudyMainFragment.Companion.tts_id
import kotlinx.android.synthetic.main.fragment_study_menu.*
import kotlinx.android.synthetic.main.fragment_study_menu.view.*


class StudyPhraseInterpretFragment : Fragment(){

    private lateinit var tts : TTS

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_study_phrase_interpret, container, false)
        addTTS()
        StudyMainFragment.tts_id = tts
        Log.i("TAG", "Found tts: $tts_id")
        createHanjaSet(view)

        createVocaSet(view)

        createInterpretSet(view)



        //tts.destroy()
        return view
    }

    private fun createHanjaSet(view : View){
        val hanjaId = resources.getIdentifier("@string/hanja_${StudyMainFragment.currentChapter}_${StudyMainFragment.currentPhrase}","String",context?.packageName)
        val soundId = resources.getIdentifier("@string/sound_${StudyMainFragment.currentChapter}_${StudyMainFragment.currentPhrase}","String",context?.packageName)


        val hanjaValue = getText(hanjaId).split("//")
        val soundValue = getText(soundId).split("//")
        var ttsSound = ""
        val hanjaLayout = LinearLayout(context)
        hanjaLayout.orientation = LinearLayout.VERTICAL
            for (i in 0.. hanjaValue.lastIndex){
            val hanjaSet = LinearLayout(context)
            hanjaSet.orientation = LinearLayout.VERTICAL
            hanjaSet.gravity = Gravity.CENTER
            val hanjaSetLayout = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            hanjaSetLayout.setMargins(10,20,10,30)
            hanjaSet.layoutParams = hanjaSetLayout

            val hanjaView = TextView(context)
            hanjaView.text = StudyMainFragment.convertColoredText(hanjaValue[i])
            hanjaView.textSize=22.0f
            hanjaView.gravity = Gravity.CENTER
            hanjaView.setTextColor(ContextCompat.getColor(context!!,R.color.Black))

            val soundView = TextView(context)
            soundView.text = StudyMainFragment.convertColoredText(soundValue[i])
            soundView.textSize=18.0f
            soundView.gravity = Gravity.CENTER
            soundView.setTextColor(ContextCompat.getColor(context!!,R.color.Black))

//            hanjaSet.setOnClickListener {
//                speech(soundValue[i])
//            }

            ttsSound += soundView.text.toString() + "\n"
            hanjaSet.addView(hanjaView)
            hanjaSet.addView(soundView)
            hanjaLayout.addView(hanjaSet)
        }
        hanjaLayout.setOnClickListener {
            tts.speech(ttsSound)
        }
        view.scrollview3_con.setBackgroundResource(R.drawable.outline)
        view.sv_phrase_interpret1.addView(hanjaLayout)

    }

    private fun createVocaSet(view:View){
        val id = resources.getIdentifier("@string/voca_${StudyMainFragment.currentChapter}_${StudyMainFragment.currentPhrase}","String",context?.packageName)
        val vocaLines = getText(id).split("//")
        if (vocaLines[0] != ""){
            val vocaLayout_left = LinearLayout(context)
            vocaLayout_left.orientation = LinearLayout.VERTICAL

            val vocaLayout_right = LinearLayout(context)
            vocaLayout_right.orientation = LinearLayout.VERTICAL

            var LRboolean = true

            for (s in vocaLines) {

                val vocaValue = s.split("|")
                val vocaContainer = LinearLayout(context)

                val vocaChar = TextView(context)
                val vocaInterpret = TextView(context)

                vocaChar.text = "• ${StudyMainFragment.convertColoredText(vocaValue[0])} : "
                vocaChar.textSize = 18f
                vocaChar.setTextColor(ContextCompat.getColor(context!!, R.color.Black))

                vocaContainer.addView(vocaChar)

                if (vocaValue.lastIndex > 0) {
                    vocaContainer.addView(vocaInterpret)
                    vocaInterpret.text = vocaValue[1]
                    vocaInterpret.textSize = 18f
                    vocaInterpret.setTextColor(ContextCompat.getColor(context!!, R.color.Black))
                    vocaInterpret.gravity = Gravity.CENTER_VERTICAL
                    val layout = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    layout.setMargins(20, 0, 10, 0)
                    vocaInterpret.layoutParams = layout
                }

                val layout = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                layout.setMargins(20, 20, 10, 30)
                vocaContainer.layoutParams = layout

                if (LRboolean) {
                    vocaLayout_left.addView(vocaContainer)
                    LRboolean = false
                }
                else {
                    vocaLayout_right.addView(vocaContainer)
                    LRboolean = true
                }




            }

            //vocaLayout.setBackgroundColor(Color.parseColor("#ffc1e8"))
            //vocaLayout.background = ContextCompat.getDrawable(context!!,R.drawable.img_line_contants)
            view.scrollview_con.setBackgroundResource(R.drawable.outline)
            view.sv_phrase_interpret_left.addView(vocaLayout_left)
            view.sv_phrase_interpret_right.addView(vocaLayout_right)
        }

    }

    private fun createInterpretSet(view : View){
        val interpretId = resources.getIdentifier("@string/interpret_${StudyMainFragment.currentChapter}_${StudyMainFragment.currentPhrase}","String",context?.packageName)
        val interpretValue = getText(interpretId)/*.split("/")*/
        if(interpretValue != ""){
            val interpretView = TextView(context)
            interpretView.setLineSpacing(20f,1f)
            interpretView.text = StudyMainFragment.convertColoredText(interpretValue.toString())
            interpretView.textSize=22.0f
            interpretView.gravity = Gravity.CENTER
            interpretView.setTextColor(ContextCompat.getColor(context!!,R.color.Black))

            interpretView.setOnClickListener {
                tts.speech(interpretView.text.toString())
            }

            val transform = Linkify.TransformFilter { _, _ ->  ""}

            //val sunjaURL = Pattern.compile("손자")
            //Linkify.addLinks(interpretView,sunjaURL,"https://www.google.com/search?q=손무",null,transform)

            val a1URL = Pattern.compile("\\*손자병법")
            Linkify.addLinks(interpretView,a1URL,"https://blog.naver.com/hku0119/221553270727",null,transform)

            val a3URL = Pattern.compile("\\*소리 없는 영웅")
            Linkify.addLinks(interpretView,a3URL,"http://hku0119.blog.me/221537227562",null,transform)

            val a4URL = Pattern.compile("\\*천의 요소")
            Linkify.addLinks(interpretView,a4URL,"http://hku0119.blog.me/221533188897?Redirect=Log&from=postView",null,transform)

            val a5URL = Pattern.compile("\\*김일성 오판, 민족 비극")
            Linkify.addLinks(interpretView,a5URL,"http://hku0119.blog.me/221528696887?Redirect=Log&from=postView",null,transform)

            val a6URL = Pattern.compile("\\*6사단의 성공 비결")
            Linkify.addLinks(interpretView,a6URL,"https://hku0119.blog.me/221560908278?Redirect=Log&from=postView",null,transform)

            val a8URL = Pattern.compile("\\*독일과 일본")
            Linkify.addLinks(interpretView,a8URL,"http://hku0119.blog.me/221528739637?Redirect=Log&from=postView",null,transform)

            val a10URL = Pattern.compile("\\*전쟁수행 비용")
            Linkify.addLinks(interpretView,a10URL,"http://hku0119.blog.me/221533111037?Redirect=Log&from=postView",null,transform)

            val a12URL = Pattern.compile("\\*전쟁에서 지다")
            Linkify.addLinks(interpretView,a12URL,"http://hku0119.blog.me/221528694609?Redirect=Log&from=postView",null,transform)

            val a13URL = Pattern.compile("\\*스페인의 쇠락")
            Linkify.addLinks(interpretView,a13URL,"http://hku0119.blog.me/221531291644?Redirect=Log&from=postView",null,transform)

            val a14URL = Pattern.compile("\\*가장 훌륭한 전략·전술")
            Linkify.addLinks(interpretView,a14URL,"http://hku0119.blog.me/221559591652?Redirect=Log&from=postView",null,transform)

            val a15URL = Pattern.compile("\\*명분에서도 이긴 걸프전")
            Linkify.addLinks(interpretView,a15URL,"https://blog.naver.com/hku0119/221570096194",null,transform)

            val a16URL = Pattern.compile("\\*서희의 외교담판")
            Linkify.addLinks(interpretView,a16URL,"http://hku0119.blog.me/221531335823?Redirect=Log&from=postView",null,transform)

            val a17URL = Pattern.compile("\\*전쟁을 끝내기 위한 전쟁\\?")
            Linkify.addLinks(interpretView,a17URL,"http://hku0119.blog.me/221531443815?Redirect=Log&from=postView",null,transform)

            val a19URL = Pattern.compile("\\*문무를 겸비한 장군")
            Linkify.addLinks(interpretView,a19URL,"http://hku0119.blog.me/221533228846?Redirect=Log&from=postView",null,transform)

            val a20URL = Pattern.compile("\\*사막의 여우 롬멜")
            Linkify.addLinks(interpretView,a20URL,"https://hku0119.blog.me/221559564465?Redirect=Log&from=postView",null,transform)

            val a21URL = Pattern.compile("\\*지피지기 백전불태!")
            Linkify.addLinks(interpretView,a21URL,"http://hku0119.blog.me/221534000562?Redirect=Log&from=postView",null,transform)

            val a22URL = Pattern.compile("\\*나폴레옹도 실패할 때가 있다")
            Linkify.addLinks(interpretView,a22URL,"http://hku0119.blog.me/221531586942?Redirect=Log&from=postView",null,transform)

            val a24URL = Pattern.compile("\\*도착하기 전에 승리")
            Linkify.addLinks(interpretView,a24URL,"http://hku0119.blog.me/221533344940?Redirect=Log&from=postView",null,transform)

            val a25URL = Pattern.compile("\\◆ 영국의 수상 처칠")
            Linkify.addLinks(interpretView,a25URL,"http://hku0119.blog.me/221554082104?Redirect=Log&from=postView",null,transform)

            val a26URL = Pattern.compile("\\*부대 편성의 중요성")
            Linkify.addLinks(interpretView,a26URL,"http://hku0119.blog.me/221533270171?Redirect=Log&from=postView",null,transform)

            val a27URL = Pattern.compile("\\*적의 약점에 집중")
            Linkify.addLinks(interpretView,a27URL,"http://hku0119.blog.me/221534070395?Redirect=Log&from=postView",null,transform)

            val a28URL = Pattern.compile("\\*적의 허점을 노려라")
            Linkify.addLinks(interpretView,a28URL,"http://hku0119.blog.me/221534105090?Redirect=Log&from=postView",null,transform)

            val a30URL = Pattern.compile("\\*스텔스와 이지스")
            Linkify.addLinks(interpretView,a30URL,"http://hku0119.blog.me/221533928750?Redirect=Log&from=postView",null,transform)

            val a31URL = Pattern.compile("\\*원하는 장소에서 싸워라")
            Linkify.addLinks(interpretView,a31URL,"http://hku0119.blog.me/221534050164?Redirect=Log&from=postView",null,transform)

            val a34URL = Pattern.compile("\\*나폴레옹의 대우회기동")
            Linkify.addLinks(interpretView,a34URL,"http://hku0119.blog.me/221533370016?Redirect=Log&from=postView",null,transform)

            val a35URL = Pattern.compile("\\*해로운 기동이 적용된 전례")
            Linkify.addLinks(interpretView,a35URL,"http://hku0119.blog.me/221533920102?Redirect=Log&from=postView",null,transform)

            val a36URL = Pattern.compile("\\*군수를 무시하면 패배한다.")
            Linkify.addLinks(interpretView,a36URL,"http://hku0119.blog.me/221534131978?Redirect=Log&from=postView",null,transform)

            val a38URL = Pattern.compile("\\*군주의 명령도 받지 않는 경우")
            Linkify.addLinks(interpretView,a38URL,"http://hku0119.blog.me/221537223042?Redirect=Log&from=postView",null,transform)

            val a39URL = Pattern.compile("\\*용문산 전투")
            Linkify.addLinks(interpretView,a39URL,"http://hku0119.blog.me/221537191606?Redirect=Log&from=postView",null,transform)

            val a40URL = Pattern.compile("\\*전원 옥쇄하더라도 목표를 확보하라")
            Linkify.addLinks(interpretView,a40URL,"http://hku0119.blog.me/221537087743?Redirect=Log&from=postView",null,transform)

            val a41URL = Pattern.compile("\\*필사가살")
            Linkify.addLinks(interpretView,a41URL,"http://hku0119.blog.me/221560161623?Redirect=Log&from=postView",null,transform)

            val a44URL = Pattern.compile("\\*작은 징후가 승패를 가른다.")
            Linkify.addLinks(interpretView,a44URL,"http://hku0119.blog.me/221533984989?Redirect=Log&from=postView",null,transform)

            val a45URL = Pattern.compile("\\*방향을 바꾸어 공격한다.")
            Linkify.addLinks(interpretView,a45URL,"https://blog.naver.com/hku0119/221537186238",null,transform)

            val a46URL = Pattern.compile("\\*노벨 평화상을 받은 군인")
            Linkify.addLinks(interpretView,a46URL,"http://hku0119.blog.me/221537262753?Redirect=Log&from=postView",null,transform)

            val a47URL = Pattern.compile("\\*내가 후퇴하면 나를 쏴라")
            Linkify.addLinks(interpretView,a47URL,"http://hku0119.blog.me/221537099210?Redirect=Log&from=postView",null,transform)

            val a48URL = Pattern.compile("\\*연저지인")
            Linkify.addLinks(interpretView,a48URL,"http://hku0119.blog.me/221533181616?Redirect=Log&from=postView",null,transform)

            val a49URL = Pattern.compile("\\*지형을 알면 승리한다.")
            Linkify.addLinks(interpretView,a49URL,"http://hku0119.blog.me/221534123366?Redirect=Log&from=postView",null,transform)

            val a50URL = Pattern.compile("\\*파부침주")
            Linkify.addLinks(interpretView,a50URL,"http://hku0119.blog.me/221537186238?Redirect=Log&from=postView",null,transform)

            val a51URL = Pattern.compile("\\*손무가 애첩의 목을 벤 까닭")
            Linkify.addLinks(interpretView,a51URL,"http://hku0119.blog.me/221533281327?Redirect=Log&from=postView",null,transform)

            val a52URL = Pattern.compile("\\*광해군의 중립외교")
            Linkify.addLinks(interpretView,a52URL,"http://hku0119.blog.me/221532330239?Redirect=Log&from=postView",null,transform)

            val a53URL = Pattern.compile("\\*증오심이 국가를 망하게 한다.")
            Linkify.addLinks(interpretView,a53URL,"http://hku0119.blog.me/221532359910?Redirect=Log&from=postView",null,transform)

            val a54URL = Pattern.compile("\\*스파이 조르게")
            Linkify.addLinks(interpretView,a54URL,"http://hku0119.blog.me/221534005236?Redirect=Log&from=postView",null,transform)

            val a55URL = Pattern.compile("\\*인류의 역사 = 전쟁의 역사")
            Linkify.addLinks(interpretView,a55URL,"http://hku0119.blog.me/221558606412?Redirect=Log&from=postView",null,transform)

            val a57URL = Pattern.compile("\\*바다로의 진군")
            Linkify.addLinks(interpretView,a57URL,"http://hku0119.blog.me/221558636069?Redirect=Log&from=postView",null,transform)

            val a58URL = Pattern.compile("\\*새로운 수단과 방법")
            Linkify.addLinks(interpretView,a58URL,"http://hku0119.blog.me/221558710407?Redirect=Log&from=postView",null,transform)

            val a61URL = Pattern.compile("\\*영구불변의 진리")
            Linkify.addLinks(interpretView,a61URL,"http://hku0119.blog.me/221559254001?Redirect=Log&from=postView",null,transform)

            val a62URL = Pattern.compile("\\*나폴레옹 전략의 핵심")
            Linkify.addLinks(interpretView,a62URL,"http://hku0119.blog.me/221564883498",null,transform)

            val a63URL = Pattern.compile("\\*러시아 - 체첸전쟁")
            Linkify.addLinks(interpretView,a63URL,"http://hku0119.blog.me/221559270681?Redirect=Log&from=postView",null,transform)

            val a64URL = Pattern.compile("\\*탄금대 전투")
            Linkify.addLinks(interpretView,a64URL,"http://hku0119.blog.me/221559279339?Redirect=Log&from=postView",null,transform)

            val a65URL = Pattern.compile("\\*란체스터의 제 2법칙")
            Linkify.addLinks(interpretView,a65URL,"http://hku0119.blog.me/221559302859?Redirect=Log&from=postView",null,transform)

            val a66URL = Pattern.compile("\\*죽을 땅에 빠져야 살 수 있다")
            Linkify.addLinks(interpretView,a66URL,"http://hku0119.blog.me/221533206771?Redirect=Log&from=postView",null,transform)

            val a67URL = Pattern.compile("\\*살라미스 해전")
            Linkify.addLinks(interpretView,a67URL,"https://blog.naver.com/hku0119/221568770415",null,transform)

            val a68URL = Pattern.compile("\\*러시아 - 체첸 전쟁")
            Linkify.addLinks(interpretView,a68URL,"https://blog.naver.com/hku0119/221537210412",null,transform)

            val a69URL = Pattern.compile("\\*SNS 보안 유지의 중요성")
            Linkify.addLinks(interpretView,a69URL,"https://blog.naver.com/hku0119/221564724148",null,transform)

            val a70URL = Pattern.compile("\\*사막의 여우 롬멜")
            Linkify.addLinks(interpretView,a70URL,"https://blog.naver.com/hku0119/221537199119",null,transform)


            val interpretLayout = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT)
            interpretLayout.setMargins(30,20,30,30)
            interpretView.layoutParams = interpretLayout
            view.scrollview_con2.setBackgroundResource(R.drawable.outline)
            view.sv_phrase_interpret3.addView(interpretView)
        }
    }

    private fun addTTS() : Fragment{
        this.tts = TTS()
        tts.initialize(context!!)
        return this
    }




}