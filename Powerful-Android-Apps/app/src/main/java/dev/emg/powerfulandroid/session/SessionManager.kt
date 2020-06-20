package dev.emg.powerfulandroid.session

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.emg.powerfulandroid.models.AuthToken
import dev.emg.powerfulandroid.persistance.AuthTokenDao
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor(
  val authTokenDao: AuthTokenDao,
  val application: Application
) {

  private val _cachedToken = MutableLiveData<AuthToken>()
  val cachedToken: LiveData<AuthToken>
    get() = _cachedToken

  fun login(newValue: AuthToken) {
    setValue(newValue)
  }

  fun logout() {
    Timber.d("logout()...")
    GlobalScope.launch(IO) {
      var errorMsg: String? = null
      try {
        cachedToken.value!!.account_pk?.let {
          authTokenDao.nullifyToken(it)
        }
      } catch (e: CancellationException) {
        Timber.e(e)
        errorMsg = e.message
      } catch (e: Exception) {
        Timber.e(e)
        errorMsg = e.message
      } finally {
        errorMsg?.let {
          Timber.e("logout(): $errorMsg")
        }
        setValue(null)
      }
    }
  }

  fun setValue(newValue: AuthToken?) {
    GlobalScope.launch(Main) {
      if (_cachedToken.value != null) {
        _cachedToken.value = newValue
      }
    }
  }

  fun isConnectedToInternet(): Boolean {
    val cm = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    try {
      return cm.activeNetworkInfo.isConnected
    } catch (e: Exception) {
      Timber.e(e)
    }
    return false
  }
}