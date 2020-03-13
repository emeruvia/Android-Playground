package dev.emg.powerfulandroid.repository.auth

import dev.emg.powerfulandroid.api.auth.OpenApiAuthService
import dev.emg.powerfulandroid.persistance.AccountPropertiesDao
import dev.emg.powerfulandroid.persistance.AuthTokenDao
import dev.emg.powerfulandroid.session.SessionManager

class AuthRepository constructor(
  val authTokenDao: AuthTokenDao,
  val accountPropertiesDao: AccountPropertiesDao,
  val openApiAuthService: OpenApiAuthService,
  val sessionManager: SessionManager
) {

}