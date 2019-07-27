package dev.emg.lesson1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.util.Random

class MainActivity : AppCompatActivity() {

  private lateinit var resultText: TextView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    resultText = findViewById(R.id.result_tv)

    val rollButton: Button = findViewById(R.id.roll_btn)
    rollButton.setOnClickListener { rollDice() }

    val countUpButton: Button = findViewById(R.id.countUp_btn)
    countUpButton.setOnClickListener { countUp() }
  }

  private fun rollDice() {
    val randomInt = Random().nextInt(6) + 1
    resultText.text = randomInt.toString()
    Toast.makeText(this, "button clicked", Toast.LENGTH_SHORT)
        .show()
  }

  private fun countUp() {
    when (val currentValue = resultText.text.toString()) {
      "Hello World!" -> resultText.text = "1"
      "6" -> return
      else -> resultText.text = (currentValue.toInt() + 1).toString()
    }
  }
}
