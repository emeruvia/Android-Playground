package com.example.android.guesstheword.screens.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

  // The current word
  private val _word = MutableLiveData<String>()
  val word: LiveData<String>
    get() = _word

  // The current score
  private val _score = MutableLiveData<Int>()
  val score: LiveData<Int>
    get() = _score

  // Event which triggers the end of a game
  private val _eventGameFinish = MutableLiveData<Boolean>()
  val eventGameFinish: LiveData<Boolean>
    get() = _eventGameFinish

  // The list of words - the front of the list is the next word to guess
  private lateinit var wordList: MutableList<String>

  init {
    Log.i("GameViewModel", "GameViewModel created!")

    _word.value = ""
    _score.value = 0

    resetList()
    nextWord()
  }

  override fun onCleared() {
    super.onCleared()
    Log.i("GameViewModel", "GameViewModel destroyed!")
  }

  /**
   * Resets the list of words and randomizes the order
   */
  private fun resetList() {
    wordList = mutableListOf(
        "queen",
        "hospital",
        "basketball",
        "cat",
        "change",
        "snail",
        "soup",
        "calendar",
        "sad",
        "desk",
        "guitar",
        "home",
        "railway",
        "zebra",
        "jelly",
        "car",
        "crow",
        "trade",
        "bag",
        "roll",
        "bubble"
    )
    wordList.shuffle()
  }

  /**
   * Moves to the next word in the list
   */
  private fun nextWord() {
    if (wordList.isEmpty()) {
      onGameFinished()
    } else {
      //Select and remove a word from the list
      _word.value = wordList.removeAt(0)
    }
  }

  /** Methods for buttons presses **/

  fun onSkip() {
    if (!wordList.isEmpty()) {
      _score.value = (score.value)?.minus(1)
    }
    nextWord()
  }

  fun onCorrect() {
    if (!wordList.isEmpty()) {
      _score.value = (score.value)?.plus(1)
    }
    nextWord()
  }

  fun onGameFinished() {
    _eventGameFinish.value = true
  }

  fun onGameFinishComlete() {
    _eventGameFinish.value = false
  }

}