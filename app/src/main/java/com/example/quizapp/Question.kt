package com.example.quizapp

data class Question(val id: Int,
                    val questionText: String,
                    val optionOne: String,
                    val optionTwo: String,
                    val optionThree: String,
                    val optionFour: String,
                    val answer: String
                    )

fun getQuestions() : ArrayList<Question> {
    val questions = ArrayList<Question>()

    val q1 = Question(1,
        "Which is not an activity lifecycle method?",
        "onPause()",
        "onDestroy()",
        "onCreate()",
        "onViewCreated()",
        "D"
        )
    questions.add(q1)

    val q2 = Question(1,
        "Which layout is designed to block an area of the screen to display a single item?",
        "Table Layout",
        "Linear Layout",
        "Frame Layout",
        "Constraint Layout",
        "C"
    )
    questions.add(q2)

    val q3 = Question(1,
        "Which parameter specifies the Android API level that Gradle should use to compile the app?",
        "minSdkVersion",
        "compileSdkVersion",
        "targetSdkVersion",
        "testSdkVersion",
        "B"
    )
    questions.add(q3)

    val q4 = Question(1,
        "Which of the following methods is used to send data to another activity?",
        "putExtra()",
        "finish()",
        "startActivity()",
        "setAction()",
        "A"
    )
    questions.add(q4)

    val q5 = Question(1,
        "Which of the following is false about fragments?",
        "Fragments must be hosted by an activity or another fragment.",
        "A fragment defines and manages its own layout, has its own lifecycle",
        "A Fragment represents a reusable portion of the app's UI. ",
        "Fragments do not have their own lifecycle.",
        "D"
    )
    questions.add(q5)

    return questions
}

object Constants{
    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS: String = "correct_answers"
}