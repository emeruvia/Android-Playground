package dev.emg.powerfulandroid.repository.auth

import androidx.lifecycle.LiveData
import dev.emg.powerfulandroid.api.auth.OpenApiAuthService
import dev.emg.powerfulandroid.api.auth.responses.LoginResponse
import dev.emg.powerfulandroid.api.auth.responses.RegistrationResponse
import dev.emg.powerfulandroid.persistance.AccountPropertiesDao
import dev.emg.powerfulandroid.persistance.AuthTokenDao
import dev.emg.powerfulandroid.session.SessionManager
import dev.emg.powerfulandroid.utils.GenericApiResponse
import javax.inject.Inject

class AuthRepository @Inject constructor(
  val authTokenDao: AuthTokenDao,
  val accountPropertiesDao: AccountPropertiesDao,
  val openApiAuthService: OpenApiAuthService,
  val sessionManager: SessionManager
) {

  fun testLoginRequest(
    email: String,
    password: String
  ): LiveData<GenericApiResponse<LoginResponse>> {
    return openApiAuthService.login(email, password)
  }

  fun testRegistrationRequest(
    email: String,
    username: String,
    password: String,
    password2: String
  ): LiveData<GenericApiResponse<RegistrationResponse>> {
    return openApiAuthService.register(email, username, password, password2)
  }

}