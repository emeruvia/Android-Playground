package dev.emg.lesson2

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    val doneButton: Button = findViewById(R.id.done_button)
    doneButton.setOnClickListener {
      addNickName(it)
    }
    findViewById<TextView>(R.id.nickname_text).setOnClickListener {
      updateNickname(it)
    }
  }

  private fun addNickName(view: View) {
    val editText = findViewById<EditText>(R.id.nickname_edit)
    val nicknameTextView = findViewById<TextView>(R.id.nickname_text)
    nicknameTextView.text = editText.text
    nicknameTextView.visibility = View.VISIBLE
    editText.visibility = View.GONE
    view.visibility = View.GONE
    // Hide the keyboard
    val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
  }

  private fun updateNickname(view: View) {
    val editText = findViewById<EditText>(R.id.nickname_edit)
    val doneButton = findViewById<Button>(R.id.done_button)
    editText.visibility = View.VISIBLE
    doneButton.visibility = View.VISIBLE
    view.visibility = View.GONE
    editText.requestFocus()
    // Show the keyboard.
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(editText, 0)
  }
}
