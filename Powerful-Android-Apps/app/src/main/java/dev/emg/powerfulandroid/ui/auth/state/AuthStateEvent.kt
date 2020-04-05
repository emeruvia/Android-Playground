package dev.emg.powerfulandroid.ui.auth.state

sealed class AuthStateEvent {

  data class LoadingAttemptEvent(
    val email: String,
    val password: String
  ) : AuthStateEvent()

  data class RegisterAttemptEvent(
    val email: String,
    val username: String,
    val password: String,
    val confirmPassword: String
  ) : AuthStateEvent()

  class CheckPreviousAuthEvent : AuthStateEvent()

}