package dev.emg.lesson1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import java.util.Random

class MainActivity : AppCompatActivity() {

  private lateinit var diceImage: ImageView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    diceImage = findViewById(R.id.dice_iv)

    val rollButton: Button = findViewById(R.id.roll_btn)
    rollButton.setOnClickListener { rollDice() }

  }

  private fun rollDice() {
    val drawableResource = when (Random().nextInt(6) + 1) {
      1 -> R.drawable.dice_1
      2 -> R.drawable.dice_2
      3 -> R.drawable.dice_3
      4 -> R.drawable.dice_4
      5 -> R.drawable.dice_5
      else -> R.drawable.dice_6
    }
    diceImage.setImageResource(drawableResource)
  }

}
