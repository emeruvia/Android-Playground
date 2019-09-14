package com.emgdev.daggerpractice

import android.graphics.drawable.Drawable
import android.os.Bundle
import com.bumptech.glide.RequestManager
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_auth.login_logo
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity() {

  @Inject lateinit var logo: Drawable
  @Inject lateinit var requestManager: RequestManager

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_auth)

    loadLogo()
  }

  private fun loadLogo() {
    requestManager
        .load(logo)
        .into(login_logo)
  }
}
