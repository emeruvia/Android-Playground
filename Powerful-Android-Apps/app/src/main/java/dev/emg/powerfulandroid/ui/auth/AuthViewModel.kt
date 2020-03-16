package dev.emg.powerfulandroid.ui.auth

import androidx.lifecycle.ViewModel
import dev.emg.powerfulandroid.repository.auth.AuthRepository
import javax.inject.Inject

class AuthViewModel @Inject constructor(authRepository: AuthRepository) : ViewModel()