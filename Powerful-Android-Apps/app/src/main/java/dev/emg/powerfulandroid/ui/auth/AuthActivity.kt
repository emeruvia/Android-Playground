package dev.emg.powerfulandroid.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import dev.emg.powerfulandroid.BaseApplication
import dev.emg.powerfulandroid.databinding.ActivityAuthBinding
import dev.emg.powerfulandroid.di.auth.AuthComponent
import dev.emg.powerfulandroid.di.auth.AuthScope
import dev.emg.powerfulandroid.session.SessionManager
import dev.emg.powerfulandroid.ui.main.MainActivity
import timber.log.Timber
import javax.inject.Inject

//import dev.emg.powerfulandroid.ui.BaseActivity

class AuthActivity : AppCompatActivity() {

  lateinit var authComponent: AuthComponent
  @AuthScope
  @Inject lateinit var viewModel: AuthViewModel
  @Inject lateinit var sessionManager: SessionManager

  private lateinit var binding: ActivityAuthBinding

  override fun onCreate(savedInstanceState: Bundle?) {

    authComponent = (application as BaseApplication).appComponent.authComponent()
      .create()
    authComponent.inject(this)

    super.onCreate(savedInstanceState)
    binding = ActivityAuthBinding.inflate(layoutInflater)
    val view = binding.root
    setContentView(view)

    subscribeObservers()
  }

  fun subscribeObservers() {
    Timber.d("subscribeObservers()...")
    viewModel.viewState.observe(this, Observer {
      it.authToken?.let {
        Timber.d("subscribeObservers(): inside viewmodel login")
        sessionManager.login(it)
      }
    })

    sessionManager.cachedToken.observe(this, Observer { authToken ->
      if (authToken == null || authToken.account_pk == -1 || authToken.token == null) {
        navMainActivity()
        finish()
      }
    })
  }

  private fun navMainActivity() {
    Timber.d("navMainActivity() intent is being called.")
    val intent = Intent(this, MainActivity::class.java)
    startActivity(intent)
    finish()
  }
}
