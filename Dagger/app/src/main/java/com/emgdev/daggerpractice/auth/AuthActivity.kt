package com.emgdev.daggerpractice.auth

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import com.emgdev.daggerpractice.R.layout
import com.emgdev.daggerpractice.auth.AuthStatus.AUTHENTICATED
import com.emgdev.daggerpractice.auth.AuthStatus.ERROR
import com.emgdev.daggerpractice.auth.AuthStatus.LOADING
import com.emgdev.daggerpractice.auth.AuthStatus.NOT_AUTHENTICATED
import com.emgdev.daggerpractice.di.ViewModelFactory
import com.emgdev.daggerpractice.main.MainActivity
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_auth.login_button
import kotlinx.android.synthetic.main.activity_auth.login_logo
import kotlinx.android.synthetic.main.activity_auth.progress_bar
import kotlinx.android.synthetic.main.activity_auth.user_id_input
import timber.log.Timber
import java.lang.NumberFormatException
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity() {

  @Inject lateinit var providerFactory: ViewModelFactory
  @Inject lateinit var logo: Drawable
  @Inject lateinit var requestManager: RequestManager

  private lateinit var viewModel: AuthViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_auth)

    login_button.setOnClickListener { attemptLogin() }
    viewModel = ViewModelProvider(this, providerFactory).get(AuthViewModel::class.java)

    loadLogo()
    subscribeObserver()
  }

  private fun subscribeObserver() {
    viewModel.observerAuthState().observe(this, Observer { authResource ->
      if (authResource != null) {
        when (authResource.status) {
          LOADING -> showProgressBar(true)
          AUTHENTICATED -> {
            showProgressBar(false)
            Timber.d("onChanged(): LOGIN SUCCESS: ${authResource.data?.email}")
            onLoginSuccess()
          }
          ERROR -> {
            showProgressBar(false)
            Toast.makeText(this@AuthActivity, authResource.message, Toast.LENGTH_SHORT).show()
          }
          NOT_AUTHENTICATED -> showProgressBar(false)
        }
      }
    })
  }

  private fun onLoginSuccess() {
    val intent = Intent(this, MainActivity::class.java)
    startActivity(intent)
    finish()
  }

  private fun showProgressBar(isVisible: Boolean) {
    when (isVisible) {
      true -> progress_bar.visibility = View.VISIBLE
      false -> progress_bar.visibility = View.GONE
    }
  }

  private fun loadLogo() {
    requestManager
      .load(logo)
      .into(login_logo)
  }

  private fun attemptLogin() {
    if (user_id_input.text.toString().isEmpty()) return
    try {
      val userId: Int = user_id_input.text.toString().toInt()
      viewModel.authenticateWithId(userId)
    } catch (e: NumberFormatException) {
      Timber.e(e)
      return
    }
  }
}
