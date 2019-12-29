package com.example.android.guesstheword.screens.score

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ScoreViewModelFactory(private val finalScore: Int) : ViewModelProvider.Factory {

  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    if (modelClass.isAssignableFrom(ScoreViewModel::class.java)) {
      return ScoreViewModel(finalScore) as T
    }
    throw IllegalArgumentException("Unknown ViewModel class")
  }
}