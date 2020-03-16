package dev.emg.powerfulandroid.session

import android.app.Application
import dev.emg.powerfulandroid.persistance.AuthTokenDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor(
  val authTokenDao: AuthTokenDao,
  val application: Application
)