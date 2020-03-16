package dev.emg.powerfulandroid.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.emg.powerfulandroid.R

class BaseActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_base)
  }
}
