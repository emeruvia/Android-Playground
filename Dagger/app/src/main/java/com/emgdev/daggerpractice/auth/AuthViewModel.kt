package com.emgdev.daggerpractice.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.emgdev.daggerpractice.network.auth.AuthApi
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject
import com.emgdev.daggerpractice.data.User

/**
 * Created by emeruvia on 9/14/2019.
 */
class AuthViewModel @Inject constructor(authApi: AuthApi) : ViewModel() {

  private var authApi: AuthApi? = authApi

//  private val authUser = MediatorLiveData<AuthResource<User>>()

  private val authUser: MediatorLiveData<AuthResource<User>> = MediatorLiveData()

  init {
    when (this.authApi) {
      null -> Timber.d("AuthApi is null")
      else -> Timber.d("AuthApi has been injected")
    }
  }

  fun authenticateWithId(userId: Int) {
    authUser.value = AuthResource.loading(null)

    val source = LiveDataReactiveStreams.fromPublisher(
      authApi!!.getUser(userId)
        .onErrorReturn {
          val errorUser = User()
          errorUser.id = -1
          errorUser
        }
        .map {
          when (it.id) {
            -1 -> AuthResource.error("Could not authenticate", null)
            else -> AuthResource.authenticated(it)
          }
        }
        .subscribeOn(Schedulers.io())
    )
    authUser.addSource(source) {
      authUser.value = it
      authUser.removeSource(source)
    }
  }

  fun observerUser(): LiveData<AuthResource<User>> {
    return authUser
  }
}
