package com.emgdev.daggerpractice.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import com.emgdev.daggerpractice.network.auth.AuthApi
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by emeruvia on 9/14/2019.
 */
class AuthViewModel @Inject constructor(authApi: AuthApi) : ViewModel() {

  private var authApi: AuthApi? = authApi

  init {
    when (this.authApi) {
      null -> Timber.d("AuthApi is null")
      else -> Timber.d("AuthApi has been injected")
    }
  }
}
