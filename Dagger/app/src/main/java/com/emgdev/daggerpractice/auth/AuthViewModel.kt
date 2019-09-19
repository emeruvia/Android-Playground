package com.emgdev.daggerpractice.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.emgdev.daggerpractice.network.auth.AuthApi
import com.emgdev.daggerpractice.network.auth.User
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by emeruvia on 9/14/2019.
 */
class AuthViewModel @Inject constructor(authApi: AuthApi) : ViewModel() {

  private var authApi: AuthApi? = authApi

  private val authUser: MediatorLiveData<User> = MediatorLiveData()

  init {
    when (this.authApi) {
      null -> Timber.d("AuthApi is null")
      else -> Timber.d("AuthApi has been injected")
    }
  }

  fun authenticateWithId(userId: Int) {
    val source = LiveDataReactiveStreams.fromPublisher(
      authApi!!.getUser(userId).subscribeOn(Schedulers.io())
    )

    authUser.addSource(source) { user ->
      authUser.value = user
      authUser.removeSource(source)
    }
  }

  fun observerUser(): LiveData<User> {
    return authUser
  }
}
