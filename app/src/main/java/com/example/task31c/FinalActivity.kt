package com.example.task31c

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_final.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.app_name

class FinalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final)

        val userName = intent.getStringExtra(Constants.USER_NAME)
        app_name.text = userName

        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
        val correctAnswers = intent.getIntExtra(Constants.RIGHT_ANSWERS, 0)

        app_score.text = "Your Score is $correctAnswers out of $totalQuestions."

        btn_new_quiz.setOnClickListener {
            startActivity(Intent(this@FinalActivity, QuestionsActivity::class.java))
        }

        btn_finish.setOnClickListener {
            startActivity(Intent(this@FinalActivity, MainActivity::class.java))
        }
    }
}