package com.emgdev.daggerpractice.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.emgdev.daggerpractice.SessionManager
import com.emgdev.daggerpractice.network.auth.AuthApi
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject
import com.emgdev.daggerpractice.data.User

/**
 * Created by emeruvia on 9/14/2019.
 */
class AuthViewModel @Inject constructor(authApi: AuthApi, sessionManager: SessionManager) :
  ViewModel() {

  private var authApi: AuthApi? = authApi
  private var sessionManager: SessionManager? = sessionManager
//  private val authUser = MediatorLiveData<AuthResource<User>>()

  init {
    Timber.d("AuthViewModel(): Is working!!!!")
  }

  fun authenticateWithId(userId: Int) {
    Timber.d("authenticateWithId(): attempting to log in.")
    sessionManager?.authenticateWithId(queryUserId(userId))
  }

  private fun queryUserId(userId: Int): LiveData<AuthResource<User>> {
    return LiveDataReactiveStreams.fromPublisher(
      authApi!!.getUser(userId)
        .onErrorReturn {
          val errorUser = User()
          errorUser.id = -1
          errorUser
        }
        // Wrap User object in AuthResource
        .map {
          when (it.id) {
            -1 -> AuthResource.error("Could not authenticate", null)
            else -> AuthResource.authenticated(it)
          }
        }
        .subscribeOn(Schedulers.io()))
  }

  fun observerAuthState(): LiveData<AuthResource<User>> {
    return sessionManager?.cachedUser as LiveData<AuthResource<User>>
  }
}
