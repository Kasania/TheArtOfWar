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

            val a1URL = Pattern.compile("\\*손자병법과 전쟁론\\(도해세계전사 P40\\)")
            Linkify.addLinks(interpretView,a1URL,"https://blog.naver.com/hku0119/221553270727",null,transform)

            val a2URL = Pattern.compile("\\*우리 아들을 절대 장군으로 임명하지 마십시오\\.")
            Linkify.addLinks(interpretView,a2URL,"https://hku0119.blog.me/221537077555",null,transform)

            val a3URL = Pattern.compile("\\*소리 없는 영웅\\(Unsung hero\\)")
            Linkify.addLinks(interpretView,a3URL,"http://hku0119.blog.me/221537227562",null,transform)

            val a4URL = Pattern.compile("\\*천\\(天\\)의 요소를 만들어 기습을 달성한 전쟁! 진주만 공습")
            Linkify.addLinks(interpretView,a4URL,"http://hku0119.blog.me/221533188897?Redirect=Log&from=postView",null,transform)

            val a5URL = Pattern.compile("\\*김일성의 오판으로 시작된 민족의 비극 6.25전쟁")
            Linkify.addLinks(interpretView,a5URL,"http://hku0119.blog.me/221528696887?Redirect=Log&from=postView",null,transform)

            val a6URL = Pattern.compile("\\*춘천에서 북한군을 막아낸 6사단의 성공 비결은 교육훈련")
            Linkify.addLinks(interpretView,a6URL,"https://hku0119.blog.me/221560908278?Redirect=Log&from=postView",null,transform)

            val a7URL = Pattern.compile("\\*리델하트의 간접접근 전략\\(도해세계전사 P231\\)")
            Linkify.addLinks(interpretView,a7URL,"http://hku0119.blog.me/221553944599?Redirect=Log&from=postView",null,transform)

            val a8URL = Pattern.compile("\\*독일과 일본은 승리할것이라 생각했을까?")
            Linkify.addLinks(interpretView,a8URL,"http://hku0119.blog.me/221528739637?Redirect=Log&from=postView",null,transform)

            val a9URL = Pattern.compile("\\*전쟁수행에는 막대한 비용이 든다")
            Linkify.addLinks(interpretView,a9URL,"http://hku0119.blog.me/221559473656?Redirect=Log&from=postView",null,transform)

            val a10URL = Pattern.compile("\\*철저한 양병 체계로 최고의 부대 창설! 스파르타!")
            Linkify.addLinks(interpretView,a10URL,"http://hku0119.blog.me/221533111037?Redirect=Log&from=postView",null,transform)

            val a11URL = Pattern.compile("\\*전격전\\(도해세계전사 P262~265\\)")
            Linkify.addLinks(interpretView,a11URL,"http://hku0119.blog.me/221553984310?Redirect=Log&from=postView",null,transform)

            val a12URL = Pattern.compile("\\*전투에서 이기고 전쟁에서 지다")
            Linkify.addLinks(interpretView,a12URL,"http://hku0119.blog.me/221528694609?Redirect=Log&from=postView",null,transform)

            val a13URL = Pattern.compile("\\*전 세계를 호령한 무적함대, 스페인의 쇠락")
            Linkify.addLinks(interpretView,a13URL,"http://hku0119.blog.me/221531291644?Redirect=Log&from=postView",null,transform)

            val a14URL = Pattern.compile("\\*가장 훌륭한 전략·전술은 경제력이다")
            Linkify.addLinks(interpretView,a14URL,"http://hku0119.blog.me/221559591652?Redirect=Log&from=postView",null,transform)

            val a15URL = Pattern.compile("\\*전쟁에서도 이기고 명분에서도 이긴 걸프전")
            Linkify.addLinks(interpretView,a15URL,"http://hku0119.blog.me/221559511190?Redirect=Log&from=postView",null,transform)

            val a16URL = Pattern.compile("\\*서희의 외교담판")
            Linkify.addLinks(interpretView,a16URL,"http://hku0119.blog.me/221531335823?Redirect=Log&from=postView",null,transform)

            val a17URL = Pattern.compile("\\*전쟁을 끝내기 위한 전쟁? 평화를 끝내기 위한 평화?")
            Linkify.addLinks(interpretView,a17URL,"http://hku0119.blog.me/221531443815?Redirect=Log&from=postView",null,transform)

            val a18URL = Pattern.compile("\\*해전\\(海戰\\)은 배로만 하는 것은 아니다")
            Linkify.addLinks(interpretView,a18URL,"http://hku0119.blog.me/221559589167?Redirect=Log&from=postView",null,transform)

            val a19URL = Pattern.compile("\\*역사를 만들어낸 문무\\(文武\\)를 겸비한 장군, 맥아더 장군!")
            Linkify.addLinks(interpretView,a19URL,"http://hku0119.blog.me/221533228846?Redirect=Log&from=postView",null,transform)

            val a20URL = Pattern.compile("\\*지피지기 백전불태!")
            Linkify.addLinks(interpretView,a20URL,"http://hku0119.blog.me/221534000562?Redirect=Log&from=postView",null,transform)

            val a21URL = Pattern.compile("\\*지피지기 백전불태!")
            Linkify.addLinks(interpretView,a21URL,"http://hku0119.blog.me/221534000562?Redirect=Log&from=postView",null,transform)

            val a22URL = Pattern.compile("\\*이길 수 없다면 패배하지 않아야 한다")
            Linkify.addLinks(interpretView,a22URL,"http://hku0119.blog.me/221531586942?Redirect=Log&from=postView",null,transform)

            val a23URL = Pattern.compile("\\*천하의 나폴레옹도 실패할 때가 있다.")
            Linkify.addLinks(interpretView,a23URL,"http://hku0119.blog.me/221559500776?Redirect=Log&from=postView",null,transform)

            val a24URL = Pattern.compile("\\◆사막의 여우 롬멜\\(도해세계전사 P355\\)")
            Linkify.addLinks(interpretView,a24URL,"http://hku0119.blog.me/221554042425?Redirect=Log&from=postView",null,transform)

            val a25URL = Pattern.compile("\\◆ 영국의 수상 처칠\\(도해세계전사 P202\\)")
            Linkify.addLinks(interpretView,a25URL,"http://hku0119.blog.me/221554082104?Redirect=Log&from=postView",null,transform)

            val a26URL = Pattern.compile("\\*적재적소, 부대 편성의 중요성, 징기스칸에게서 찾다!")
            Linkify.addLinks(interpretView,a26URL,"http://hku0119.blog.me/221533270171?Redirect=Log&from=postView",null,transform)

            val a27URL = Pattern.compile("\\*적의 약점에 모든 힘을 집중해라")
            Linkify.addLinks(interpretView,a27URL,"http://hku0119.blog.me/221534070395?Redirect=Log&from=postView",null,transform)

            val a28URL = Pattern.compile("\\*나의 허점을 막고 적의 허점을 노려라")
            Linkify.addLinks(interpretView,a28URL,"http://hku0119.blog.me/221534105090?Redirect=Log&from=postView",null,transform)

            val a29URL = Pattern.compile("\\*천년 제국 로마의 멸망")
            Linkify.addLinks(interpretView,a29URL,"http://hku0119.blog.me/221534115044?Redirect=Log&from=postView",null,transform)

            val a30URL = Pattern.compile("\\*스텔스와 이지스")
            Linkify.addLinks(interpretView,a30URL,"http://hku0119.blog.me/221533928750?Redirect=Log&from=postView",null,transform)

            val a31URL = Pattern.compile("\\*주도권을 장악하는 측이 승리할 확률이 높다")
            Linkify.addLinks(interpretView,a31URL,"http://hku0119.blog.me/221534050164?Redirect=Log&from=postView",null,transform)

            val a32URL = Pattern.compile("\\*수적 열세에도 전투력 집중으로 적을 이긴 테베 군")
            Linkify.addLinks(interpretView,a32URL,"http://hku0119.blog.me/221534080027?Redirect=Log&from=postView",null,transform)

            val a33URL = Pattern.compile("\\*나의 허점을 막고 적의 허점을 노려라")
            Linkify.addLinks(interpretView,a33URL,"http://hku0119.blog.me/221534105090?Redirect=Log&from=postView",null,transform)

            val a34URL = Pattern.compile("\\*나폴레옹의 대우회기동이 적용된 울름 전역")
            Linkify.addLinks(interpretView,a34URL,"http://hku0119.blog.me/221533370016?Redirect=Log&from=postView",null,transform)

            val a35URL = Pattern.compile("\\*해로운 기동이 적용된 전례")
            Linkify.addLinks(interpretView,a35URL,"http://hku0119.blog.me/221533920102?Redirect=Log&from=postView",null,transform)

            val a36URL = Pattern.compile("\\*제아무리 명장이라도 군수를 무시하면 승리하지 못한다.")
            Linkify.addLinks(interpretView,a36URL,"http://hku0119.blog.me/221534131978?Redirect=Log&from=postView",null,transform)

            val a37URL = Pattern.compile("\\*대군을 일사분란하게 운용하는 쪽이 승리한다.")
            Linkify.addLinks(interpretView,a37URL,"http://hku0119.blog.me/221534091196?Redirect=Log&from=postView",null,transform)

            val a38URL = Pattern.compile("\\*장수가 전장에 나갈 때는 군주의 명령도 받지 않는 경우가 있다.")
            Linkify.addLinks(interpretView,a38URL,"http://hku0119.blog.me/221537223042?Redirect=Log&from=postView",null,transform)

            val a39URL = Pattern.compile("\\*성공확률 1:5,000")
            Linkify.addLinks(interpretView,a39URL,"http://hku0119.blog.me/221537191606?Redirect=Log&from=postView",null,transform)

            val a40URL = Pattern.compile("\\*전원 옥쇄하더라도 목표를 확보하라")
            Linkify.addLinks(interpretView,a40URL,"http://hku0119.blog.me/221537087743?Redirect=Log&from=postView",null,transform)

            val a41URL = Pattern.compile("\\*적이 강을 건너올 때 공격하는 것은 비겁한 행동이다.\\(宋襄之仁\\)")
            Linkify.addLinks(interpretView,a41URL,"http://hku0119.blog.me/221560161623?Redirect=Log&from=postView",null,transform)

            val a42URL = Pattern.compile("\\*특수조건하 전투")
            Linkify.addLinks(interpretView,a42URL,"http://hku0119.blog.me/221560938892?Redirect=Log&from=postView",null,transform)

            val a43URL = Pattern.compile("\\*지형을 이용해 승리를 가져온 살라미스 해전")
            Linkify.addLinks(interpretView,a43URL,"http://hku0119.blog.me/221534125696?Redirect=Log&from=postView",null,transform)

            val a44URL = Pattern.compile("\\*작은 징후가 전쟁의 승패를 가른다.")
            Linkify.addLinks(interpretView,a44URL,"http://hku0119.blog.me/221533984989?Redirect=Log&from=postView",null,transform)

            val a45URL = Pattern.compile("\\*우리는 후퇴하는 것이 아니라 단지 방향을 바꾸어 공격하는 것이다.")
            Linkify.addLinks(interpretView,a45URL,"http://hku0119.blog.me/221537210412?Redirect=Log&from=postView",null,transform)

            val a46URL = Pattern.compile("\\*노벨 평화상을 받은 유일한 군인")
            Linkify.addLinks(interpretView,a46URL,"http://hku0119.blog.me/221537262753?Redirect=Log&from=postView",null,transform)

            val a47URL = Pattern.compile("\\*내가 후퇴하면 너희들이 나를 쏴라")
            Linkify.addLinks(interpretView,a47URL,"http://hku0119.blog.me/221537099210?Redirect=Log&from=postView",null,transform)

            val a48URL = Pattern.compile("\\*연저지인\\(吮疽之仁\\), 병사들의 고름까지 빨아준 오기 장군")
            Linkify.addLinks(interpretView,a48URL,"http://hku0119.blog.me/221533181616?Redirect=Log&from=postView",null,transform)

            val a49URL = Pattern.compile("\\*적과 나를 알면 패하지 않지만, 지형을 알면 승리한다.")
            Linkify.addLinks(interpretView,a49URL,"http://hku0119.blog.me/221534123366?Redirect=Log&from=postView",null,transform)

            val a50URL = Pattern.compile("\\*출전할때 솥을 부수고 배를 침몰시킨다\\(破釜沈舟\\)")
            Linkify.addLinks(interpretView,a50URL,"http://hku0119.blog.me/221537186238?Redirect=Log&from=postView",null,transform)

            val a51URL = Pattern.compile("\\*손무가 합려왕의 애첩의 목을 벤 까닭은?")
            Linkify.addLinks(interpretView,a51URL,"http://hku0119.blog.me/221533281327?Redirect=Log&from=postView",null,transform)

            val a52URL = Pattern.compile("\\*전쟁은 국가 존망과 생사가 걸린 중대한 일")
            Linkify.addLinks(interpretView,a52URL,"http://hku0119.blog.me/221532330239?Redirect=Log&from=postView",null,transform)

            val a53URL = Pattern.compile("\\*분별없는 증오심이 국가를 망하게 할 수 있다.")
            Linkify.addLinks(interpretView,a53URL,"http://hku0119.blog.me/221532359910?Redirect=Log&from=postView",null,transform)

            val a54URL = Pattern.compile("\\*모국인 독일보다 사상의 조국을 더 사랑한 스파이, 조르게")
            Linkify.addLinks(interpretView,a54URL,"http://hku0119.blog.me/221534005236?Redirect=Log&from=postView",null,transform)




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