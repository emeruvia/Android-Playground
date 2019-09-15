package com.emgdev.daggerpractice.auth

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import com.emgdev.daggerpractice.R.layout
import com.emgdev.daggerpractice.di.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_auth.login_logo
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity() {

  @Inject lateinit var providerFactory: ViewModelProviderFactory
  @Inject lateinit var logo: Drawable
  @Inject lateinit var requestManager: RequestManager

  private lateinit var viewModel: AuthViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_auth)

    viewModel = ViewModelProvider(this, providerFactory).get(AuthViewModel::class.java)

    loadLogo()
  }

  private fun loadLogo() {
    requestManager
        .load(logo)
        .into(login_logo)
  }
}
