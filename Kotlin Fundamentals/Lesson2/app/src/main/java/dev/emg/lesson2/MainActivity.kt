package dev.emg.lesson2

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import dev.emg.lesson2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding
  private val myName: MyName = MyName("EMG")

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    binding.myName = myName
    binding.doneButton.setOnClickListener {
      addNickName(it)
    }
    binding.nicknameText.setOnClickListener {
      updateNickname(it)
    }
  }

  private fun addNickName(view: View) {
    binding.apply {
      myName?.nickname = nicknameEdit.text.toString()
      invalidateAll()
      nicknameEdit.visibility = View.GONE
      doneButton.visibility = View.GONE
      nicknameText.visibility = View.VISIBLE
    }
    view.visibility = View.GONE
    // Hide the keyboard
    val inputMethodManager =
      getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
  }

  private fun updateNickname(view: View) {
    binding.apply {
      nicknameEdit.visibility = View.VISIBLE
      doneButton.visibility = View.VISIBLE
      view.visibility = View.GONE
      nicknameEdit.requestFocus()
      // Show the keyboard.
      val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
      imm.showSoftInput(nicknameEdit, 0)
    }
  }
}
