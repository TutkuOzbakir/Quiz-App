package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity  : AppCompatActivity(){

    private var textResult1: TextView? = null
    private var textResult2: TextView? = null
    private var buttonResult : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        textResult1 = findViewById(R.id.tvResult1)
        textResult2 = findViewById(R.id.tvResult2)
        buttonResult = findViewById(R.id.buttonResult)

        val userName = intent.getStringExtra(Constants.USER_NAME)
        val numberOfCorrectAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)
        val numberOfQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)

        textResult1?.text = "Congratulations $userName"
        textResult2?.text = "Number of correct answers $numberOfCorrectAnswers \n Number of questions: $numberOfQuestions"

        buttonResult?.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}