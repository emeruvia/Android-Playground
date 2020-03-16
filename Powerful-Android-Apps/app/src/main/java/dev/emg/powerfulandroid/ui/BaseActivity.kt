package dev.emg.powerfulandroid.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.emg.powerfulandroid.BaseApplication
import dev.emg.powerfulandroid.session.SessionManager
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

  abstract fun inject()

  @Inject lateinit var sessionManager: SessionManager

  override fun onCreate(savedInstanceState: Bundle?) {
    (application as BaseApplication).appComponent
        .inject(this)
    super.onCreate(savedInstanceState)
  }
}
