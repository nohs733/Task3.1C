package com.example.task31c

object Constants {

    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val RIGHT_ANSWERS: String = "right_answers"

    fun getQuestions(): ArrayList<Question> {
        val questionsList = ArrayList<Question>()

        val que1 = Question(
            1, "What's the wrong explanation for Android?",
            "Android is Close Source", "Anyone can customize Android OS",
            "Contribute to the source code to improve it", 1
        )
        questionsList.add(que1)

        val que2 = Question(
            2, "What is a collection of software development tools in one installable package?",
            "IDE", "SDK",
            "API", 2
        )
        questionsList.add(que2)

        val que3 = Question(
            3, "This is class represents the basic building block for user interface components, what is this?",
            "ViewGroup", "ViewGroup.LayoutParams",
            "View", 3
        )
        questionsList.add(que3)

        val que4 = Question(
            4, "Which of the following is not included in the widget function?",
            "Button", "Text View",
            "CheckBox", 2
        )
        questionsList.add(que4)

        val que5 = Question(
            5, "Which of the following is not included in the widget function?",
            "Partially hidden", "Fully hidden",
            "Background", 3
        )
        questionsList.add(que5)

        return questionsList
    }
}