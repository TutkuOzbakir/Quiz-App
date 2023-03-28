package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startButton : Button = findViewById(R.id.buttonStart)
        val editText : AppCompatEditText = findViewById(R.id.username)


        startButton.setOnClickListener {
            if(editText.text.toString().isEmpty()){
                Toast.makeText(this,"This field cannot be empty.",Toast.LENGTH_SHORT).show()
            }else{
                val intent = Intent(this, QuestionActivity :: class.java)
                intent.putExtra(Constants.USER_NAME, editText.text.toString())
                startActivity(intent)
            }
        }


    }



}