package dev.emg.powerfulandroid.ui.auth

import android.os.Bundle
import dev.emg.powerfulandroid.BaseApplication
import dev.emg.powerfulandroid.R.layout
import dev.emg.powerfulandroid.ui.BaseActivity

class AuthActivity : BaseActivity() {

  override fun inject() {
    (application as BaseApplication).authComponent()
        .inject(this)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_auth)
  }
}
