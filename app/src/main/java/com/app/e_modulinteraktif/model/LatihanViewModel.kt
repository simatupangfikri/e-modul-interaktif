package com.app.e_modulinteraktif

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class LatihanViewModel : ViewModel() {

    private val _score = MutableLiveData(0)
    val score: LiveData<Int> get() = _score

    private val _currentQuestion = MutableLiveData("")
    val currentQuestion: LiveData<String> get() = _currentQuestion

    private val _currentAnswer = MutableLiveData<List<String>>()
    val currentAnswer: LiveData<List<String>> = _currentAnswer

    private val _currentQuestionCount = MutableLiveData(0)
    val curentQuestionCount: LiveData<Int> get() = _currentQuestionCount

    private val _currentDescrip = MutableLiveData("")
    val currentDescrip: LiveData<String> get() = _currentDescrip

    init {
        getNextQuestion()
    }

    private fun getNextQuestion() {
        _currentQuestion.value = allQuestions.get(curentQuestionCount.value!!)
        _currentAnswer.value = allAnswers.get(curentQuestionCount.value!!)
        _currentDescrip.value = allDescrip.get(curentQuestionCount.value!!)
        _currentQuestionCount.value = (_currentQuestionCount.value)?.inc()
    }

    private fun increaseScore() {
        _score.value =(_score.value)?.plus(SCORE_INC)
    }

    fun NextQuestion(): Boolean {
        return if (curentQuestionCount.value!! < MAX_NO_OF_QUESTIONS) {
            getNextQuestion()
            true
        } else false
    }

    fun isUserCorrect(playerAnswer: String): Boolean {
        if (playerAnswer.equals(keyAnswers.get(curentQuestionCount.value!! - 1))) {
            increaseScore()

            return true
        }
        return false
    }

}