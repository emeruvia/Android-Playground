package dev.emg.powerfulandroid.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dev.emg.powerfulandroid.api.auth.responses.LoginResponse
import dev.emg.powerfulandroid.api.auth.responses.RegistrationResponse
import dev.emg.powerfulandroid.repository.auth.AuthRepository
import dev.emg.powerfulandroid.utils.GenericApiResponse
import javax.inject.Inject

class AuthViewModel @Inject constructor(val authRepository: AuthRepository) : ViewModel() {

  fun testLogin(): LiveData<GenericApiResponse<LoginResponse>> {
    return authRepository.testLoginRequest("test123@gmail.com", "test123")
  }

  fun testRegister(): LiveData<GenericApiResponse<RegistrationResponse>> {
    return authRepository.testRegistrationRequest(
        "test123@gmail.com", "testing user", "test123", "test123"
    )
  }

}