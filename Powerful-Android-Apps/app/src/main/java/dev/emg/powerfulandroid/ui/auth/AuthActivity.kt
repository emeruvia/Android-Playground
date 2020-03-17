package dev.emg.powerfulandroid.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.emg.powerfulandroid.BaseApplication
import dev.emg.powerfulandroid.R.layout
import dev.emg.powerfulandroid.di.auth.AuthComponent

//import dev.emg.powerfulandroid.ui.BaseActivity

class AuthActivity : AppCompatActivity() {

  //  override fun inject() {
//    (application as BaseApplication).authComponent()
//        .inject(this)
//  }
  lateinit var authComponent: AuthComponent

  override fun onCreate(savedInstanceState: Bundle?) {

    authComponent = (application as BaseApplication).appComponent.authComponent()
        .create()
    authComponent.inject(this)

    super.onCreate(savedInstanceState)
    setContentView(layout.activity_auth)
  }
}
