package edu.miu.quiz.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import edu.miu.quiz.R
import edu.miu.quiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val quiz = findViewById<Button>(R.id.quiz)
        quiz.setOnClickListener {
            val quizIntent = Intent(applicationContext, QuizActivity::class.java)
            startActivity(quizIntent)
        }
    }
}