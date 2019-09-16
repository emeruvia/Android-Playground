package com.emgdev.daggerpractice.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by emeruvia on 9/14/2019.
 */
class AuthViewModel : ViewModel {

  @Inject constructor() {
    Timber.d("View model constructor injection is working!")
  }

}