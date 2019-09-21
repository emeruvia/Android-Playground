package com.emgdev.daggerpractice

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.lifecycle.Observer
import com.emgdev.daggerpractice.auth.AuthActivity
import com.emgdev.daggerpractice.auth.AuthStatus.AUTHENTICATED
import com.emgdev.daggerpractice.auth.AuthStatus.ERROR
import com.emgdev.daggerpractice.auth.AuthStatus.LOADING
import com.emgdev.daggerpractice.auth.AuthStatus.NOT_AUTHENTICATED
import dagger.android.support.DaggerAppCompatActivity
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by emeruvia on 9/20/2019.
 */
abstract class BaseActivity : DaggerAppCompatActivity() {

  @Inject lateinit var sessionManager: SessionManager

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }

  private fun subscribeObservers() {
    sessionManager.cachedUser?.observe(this, Observer { authResource ->
      if (authResource != null) {
        when (authResource.status) {
          LOADING -> {
          }
          AUTHENTICATED -> Timber.d("Observer onChanged(): AUTHENTICATED")
          ERROR -> Timber.d("Observer onChanged(): ERROR - ${authResource.message}")
          NOT_AUTHENTICATED -> navLoginScreen()
        }
      }
    })
  }

  private fun navLoginScreen() {
    val intent = Intent(this, AuthActivity::class.java)
    startActivity(intent)
    finish()
  }

}