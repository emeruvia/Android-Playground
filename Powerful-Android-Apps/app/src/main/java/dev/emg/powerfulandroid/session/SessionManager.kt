package dev.emg.powerfulandroid.session

import android.app.Application
import dev.emg.powerfulandroid.persistance.AuthTokenDao

class SessionManager constructor(val authTokenDao: AuthTokenDao, val application: Application) {

}