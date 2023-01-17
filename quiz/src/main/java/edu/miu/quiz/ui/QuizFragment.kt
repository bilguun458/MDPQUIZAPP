package edu.miu.quiz.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import edu.miu.quiz.R
import edu.miu.quiz.db.Quiz
import edu.miu.quiz.db.QuizDatabase
import edu.miu.quiz.ui.AppUtils.*
import kotlinx.coroutines.launch

class QuizFragment : BaseFragment() {

    private lateinit var tvQuestion: TextView
    private lateinit var tvScore: TextView
    private lateinit var radioGroup: RadioGroup
    private lateinit var questions: List<Quiz>
    private var currentIndex = 0
    private var homeViewModel: HomeViewModel? = null
    private var selectedChoice: String? = null
    private var answers: MutableList<String> = mutableListOf()
    private lateinit var currentQuiz: Quiz
    private var isFirstTime = true
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        tvQuestion = view.findViewById(R.id.tv_question)
        tvScore = view.findViewById(R.id.tv_score)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val scoreLiveData: MutableLiveData<Int> = homeViewModel!!.getInitialScore()
        scoreLiveData.observe(viewLifecycleOwner) {
            tvScore.text = String.format("%d/15", it)
        }
        launch {
            context?.let {
                questions = QuizDatabase(it).getQuizDao().getAllQuizzes()
                changeQuestion(view)
            }
        }

        val homeBtn = view.findViewById<Button>(R.id.btn_home)
        val nextBtn = view.findViewById<Button>(R.id.btn_next)
        homeBtn.setOnClickListener {
            val mainIntent = Intent(activity, MainActivity::class.java)
            startActivity(mainIntent)
        }
        nextBtn.setOnClickListener {
            if (selectedChoice != null){
                evaluateAnswer(selectedChoice!!)
            }
            changeQuestion(view)
        }
        radioGroup = view.findViewById(R.id.question_radio)
        radioGroup.setOnCheckedChangeListener(this::handler)
        return view
    }

    private fun changeQuestion(view:View) {
        if (questions.isEmpty()) return

        if(!isFirstTime){
            val selectedAns = if(selectedChoice!=null) selectedChoice else ""
            answers.add(selectedAns!!)
        }
        isFirstTime = false
        if (currentIndex == 15) {
            val action = QuizFragmentDirections.actionQuizFragmentToResultFragment(
                score = homeViewModel?.getFinalScore()?.value!!, answers = answers.toTypedArray()
            )
            Navigation.findNavController(requireView()).navigate(action)
            return
        }
        currentQuiz = questions[currentIndex]
        tvQuestion.text = currentQuiz.question
        val radioGroup = view.findViewById(R.id.question_radio) as RadioGroup
        val questionChoices = listOf(currentQuiz.a, currentQuiz.b, currentQuiz.c, currentQuiz.d)
        for (i in 0 until radioGroup.childCount) {
            (radioGroup.getChildAt(i) as RadioButton).text = questionChoices[i]
        }
        currentIndex++
        selectedChoice = null
        radioGroup.clearCheck()
    }

    private fun handler(group: RadioGroup, checkedId: Int) {
        when (checkedId) {
            R.id.radio_q1_a -> selectedChoice = AnswerChoice.A.value
            R.id.radio_q1_b -> selectedChoice = AnswerChoice.B.value
            R.id.radio_q1_c -> selectedChoice = AnswerChoice.C.value
            R.id.radio_q1_d -> selectedChoice = AnswerChoice.D.value
        }
    }

    private fun evaluateAnswer(ans: String) {
        if (currentQuiz.answer == ans) {
            homeViewModel!!.getCurrentScore()
        }
    }


}