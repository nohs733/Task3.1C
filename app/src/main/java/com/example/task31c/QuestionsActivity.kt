package com.example.task31c

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.media.tv.TvView
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_questions.*

class QuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition: Int = 1
    private var mQuestionsList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0
    private var mCorrectAnswers: Int = 0
    private var mUserName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

        mQuestionsList = Constants.getQuestions()
        mUserName = intent.getStringExtra(Constants.USER_NAME)

        setQuestion()

        app_option_one.setOnClickListener(this)
        app_option_two.setOnClickListener(this)
        app_option_three.setOnClickListener(this)
        btn_submit.setOnClickListener(this)
    }

    private fun setQuestion(){

        val question: Question? = mQuestionsList!![mCurrentPosition -1]

        defaultOptionsView()

        if(mCurrentPosition == mQuestionsList!!.size){
            btn_submit.text = "Finish"
        }else{
            btn_submit.text = "Submit"
        }

        progressBar.progress = mCurrentPosition
        app_progress.text = "$mCurrentPosition" + "/" + progressBar.max

        app_question.text = question!!.question
        app_option_one.text = question.answerOne
        app_option_two.text = question.answerTwo
        app_option_three.text = question.answerThree
    }

    private fun defaultOptionsView() {

        val options = ArrayList<TextView>()
        options.add(0, app_option_one)
        options.add(1, app_option_two)
        options.add(2, app_option_three)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                    this@QuestionsActivity,
                    R.drawable.option_border
            )
        }
    }

    override fun onClick(v: View?) {

        when (v?.id) {

            R.id.app_option_one -> {

                selectedOptionView(app_option_one, 1)
            }

            R.id.app_option_two -> {

                selectedOptionView(app_option_two, 2)
            }

            R.id.app_option_three -> {

                selectedOptionView(app_option_three, 3)
            }

            R.id.btn_submit -> {

                if (mSelectedOptionPosition == 0) {

                    mCurrentPosition++

                    when {

                        mCurrentPosition <= mQuestionsList!!.size -> {

                            setQuestion()
                        }
                        else -> {
                            val intent = Intent(this@QuestionsActivity, FinalActivity::class.java)
                            intent.putExtra(Constants.USER_NAME, mUserName)
                            intent.putExtra(Constants.RIGHT_ANSWERS, mCorrectAnswers)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList!!.size)
                            startActivity(intent)
                            finish()
                        }
                    }
                } else {
                    val question = mQuestionsList?.get(mCurrentPosition - 1)

                    if (question!!.correctAnswer != mSelectedOptionPosition) {
                        answerView(mSelectedOptionPosition, R.drawable.option_border_wrong)
                    }
                    else {
                        mCorrectAnswers++
                    }

                    answerView(question.correctAnswer, R.drawable.option_border_right)

                    if (mCurrentPosition == mQuestionsList!!.size) {
                        btn_submit.text = "Finish"
                    } else {
                        btn_submit.text = "Next"
                    }

                    mSelectedOptionPosition = 0
                }
            }
        }
    }

    private fun answerView(answer: Int, drawableView: Int) {

        when (answer) {

            1 -> {
                app_option_one.background = ContextCompat.getDrawable(
                        this@QuestionsActivity,
                        drawableView
                )
            }
            2 -> {
                app_option_two.background = ContextCompat.getDrawable(
                        this@QuestionsActivity,
                        drawableView
                )
            }
            3 -> {
                app_option_three.background = ContextCompat.getDrawable(
                        this@QuestionsActivity,
                        drawableView
                )
            }
        }
    }

    private fun selectedOptionView(tv: TextView,selectedOptionNum: Int){

        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#7A8089"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
                this,
                R.drawable.option_border)
    }
}