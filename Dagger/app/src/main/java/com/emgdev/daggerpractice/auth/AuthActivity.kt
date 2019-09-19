package com.emgdev.daggerpractice.auth

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import com.emgdev.daggerpractice.R.layout
import com.emgdev.daggerpractice.di.ViewModelFactory
import com.emgdev.daggerpractice.network.auth.User
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_auth.login_button
import kotlinx.android.synthetic.main.activity_auth.login_logo
import kotlinx.android.synthetic.main.activity_auth.user_id_input
import timber.log.Timber
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
    viewModel.observerUser().observe(this, Observer<User> {
      if (it != null) {
        Timber.d("onChanged(): ${it.email}")
      }
    })
  }


  private fun loadLogo() {
    requestManager
      .load(logo)
      .into(login_logo)
  }

  private fun attemptLogin() {
    if (TextUtils.isEmpty(user_id_input.text.toString())) return
    viewModel.authenticateWithId(Integer.parseInt(user_id_input.text.toString()))
  }
}
