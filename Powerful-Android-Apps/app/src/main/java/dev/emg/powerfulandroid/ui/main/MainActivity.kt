package dev.emg.powerfulandroid.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import dev.emg.powerfulandroid.BaseApplication
import dev.emg.powerfulandroid.databinding.ActivityMainBinding
import dev.emg.powerfulandroid.di.main.MainComponent
import dev.emg.powerfulandroid.session.SessionManager
import dev.emg.powerfulandroid.ui.auth.AuthActivity
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

  lateinit var mainComponent: MainComponent
  @Inject
  lateinit var sessionManager: SessionManager

  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {

    mainComponent = (application as BaseApplication).appComponent.mainComponent()
      .create()
    mainComponent.inject(this)

    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    val view = binding.root
    setContentView(view)

    binding.toolBar.setOnClickListener {
      sessionManager.logout()
    }

    subscribeObservers()
  }

  fun subscribeObservers() {
    Timber.d("subscribeObservers()... is called")
    sessionManager.cachedToken.observe(this, Observer { authToken ->
      if (authToken == null || authToken.account_pk == -1 || authToken.token == null) {
        navAuthActivity()
        finish()
      }
    })
  }

  private fun navAuthActivity() {
    Timber.d("navAuthActivity()... is called")
    val intent = Intent(this, AuthActivity::class.java)
    startActivity(intent)
    finish()
  }

}
