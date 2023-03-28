package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat

class QuestionActivity : AppCompatActivity(), View.OnClickListener {

    private var tvQuestion : TextView? = null
    private var progressBar : ProgressBar? = null
    private var tvProgress : TextView? = null
    private var tvOptionOne : TextView? = null
    private var tvOptionTwo : TextView? = null
    private var tvOptionThree : TextView? = null
    private var tvOptionFour : TextView? = null
    private var buttonSubmit : Button? = null

    private var questionNumber: Int = 0
    private var questions = ArrayList<Question>()
    private var userAnswer: String? = null

    private var numberOfCorrectAnswers : Int = 0
    private var userName : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        tvQuestion = findViewById(R.id.questionText)
        progressBar = findViewById(R.id.progressBar)
        tvProgress = findViewById(R.id.tvProgress)
        tvOptionOne = findViewById(R.id.tvOptionOne)
        tvOptionTwo = findViewById(R.id.tvOptionTwo)
        tvOptionThree = findViewById(R.id.tvOptionThree)
        tvOptionFour = findViewById(R.id.tvOptionFour)
        buttonSubmit = findViewById(R.id.buttonSubmit)

        tvOptionOne?.setOnClickListener(this)
        tvOptionTwo?.setOnClickListener(this)
        tvOptionThree?.setOnClickListener(this)
        tvOptionFour?.setOnClickListener(this)
        buttonSubmit?.setOnClickListener(this)

        userName = intent.getStringExtra(Constants.USER_NAME)
        questions = getQuestions()

        setQuestion()

    }

    private fun setQuestion() {
        defaultOptionsView()
        tvQuestion?.text = questions[questionNumber].questionText
        progressBar?.progress = questionNumber +1
        tvProgress?.text = "${questionNumber + 1}/${progressBar?.max}"
        tvOptionOne?.text = questions[questionNumber].optionOne
        tvOptionTwo?.text = questions[questionNumber].optionTwo
        tvOptionThree?.text = questions[questionNumber].optionThree
        tvOptionFour?.text = questions[questionNumber].optionFour

        buttonSubmit?.text = "SUBMIT"

    }

    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()
        tvOptionOne?.let {
            options.add(it)
        }
        tvOptionTwo?.let {
            options.add(it)
        }
        tvOptionThree?.let {
            options.add(it)
        }
        tvOptionFour?.let {
            options.add(it)
        }

        for(i in options){
            i.setTextColor(Color.parseColor("#505050"))
            i.typeface = Typeface.DEFAULT
            i.background = ContextCompat.getDrawable(this, R.drawable.default_option_border)
        }
    }

    private fun userAnswerView(tv: TextView, userAnswer_: String){
        defaultOptionsView()   //Set each button to its normal state

        userAnswer = userAnswer_
        tv.setTextColor(Color.parseColor("#91A1DA"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tvOptionOne -> {
                tvOptionOne?.let{
                    userAnswerView(it, "A")
                }
            }
            R.id.tvOptionTwo -> {
                tvOptionTwo?.let {
                    userAnswerView(it, "B")
                }
            }
            R.id.tvOptionThree -> {
                    tvOptionThree?.let{
                        userAnswerView(it, "C")
                    }
            }
            R.id.tvOptionFour -> {
                tvOptionFour?.let {
                    userAnswerView(it, "D")
                }
            }
            R.id.buttonSubmit -> {
                if(userAnswer == null){
                    questionNumber += 1
                    if(questionNumber < questions.size){
                        setQuestion()
                    }else{
                        val intent = Intent(this, ResultActivity::class.java)
                        intent.putExtra(Constants.USER_NAME, userName)
                        intent.putExtra(Constants.CORRECT_ANSWERS,numberOfCorrectAnswers)
                        intent.putExtra(Constants.TOTAL_QUESTIONS, questions.size)
                        startActivity(intent)
                        finish()
                    }
                }else {
                    val question = questions[questionNumber]
                    if (question.answer != userAnswer) {
                        answerView(userAnswer, R.drawable.wrong_option_border)
                    }else{
                        numberOfCorrectAnswers += 1
                    }
                    answerView(question.answer, R.drawable.correct_option_border)

                    userAnswer = null
                    if(questionNumber == questions.size -1){
                        buttonSubmit?.text = "FINISH"
                    }else {
                        buttonSubmit?.text = "NEXT QUESTION"
                    }
                }
            }
        }
    }

    private fun answerView(answer: String?, drawableView: Int) {
        when(answer){
            "A" -> {
                tvOptionOne?.background = ContextCompat.getDrawable(this, drawableView)
            }
            "B" -> {
                tvOptionTwo?.background = ContextCompat.getDrawable(this, drawableView)
            }
            "C" -> {
                tvOptionThree?.background = ContextCompat.getDrawable(this, drawableView)
            }
            "D" -> {
                tvOptionFour?.background = ContextCompat.getDrawable(this, drawableView)
            }
        }
    }
}