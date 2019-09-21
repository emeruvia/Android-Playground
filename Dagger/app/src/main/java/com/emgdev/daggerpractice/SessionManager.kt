package com.emgdev.daggerpractice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.emgdev.daggerpractice.auth.AuthResource
import com.emgdev.daggerpractice.data.User
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by emeruvia on 9/20/2019.
 */
@Singleton
class SessionManager @Inject constructor() {

  var cachedUser: MediatorLiveData<AuthResource<User>>? = MediatorLiveData()
    private set

  fun authenticateWithId(source: LiveData<AuthResource<User>>) {
    when (cachedUser) {
      null -> Timber.d("authenticateWithId(): previous session detected. Retrieving user from cache")
      else -> {
        cachedUser?.value = AuthResource.loading(null)
        cachedUser?.addSource(source) { authUser ->
          cachedUser?.value = authUser
          cachedUser?.removeSource(source)
        }
      }
    }
  }

  private fun logOut() {
    Timber.d("logOut(): Logging out...")
    cachedUser?.value = AuthResource.logout()
  }
}
