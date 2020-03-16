package dev.emg.powerfulandroid.ui.main

import android.os.Bundle
import dev.emg.powerfulandroid.BaseApplication
import dev.emg.powerfulandroid.R
import dev.emg.powerfulandroid.ui.BaseActivity

class MainActivity : BaseActivity() {

  override fun inject() {
    (application as BaseApplication).mainComponent()
        .inject(this)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  }
}
