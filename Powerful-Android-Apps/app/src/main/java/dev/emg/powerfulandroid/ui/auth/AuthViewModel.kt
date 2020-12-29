package dev.emg.powerfulandroid.ui.auth

import androidx.lifecycle.LiveData
import dev.emg.powerfulandroid.models.AuthToken
import dev.emg.powerfulandroid.repository.auth.AuthRepository
import dev.emg.powerfulandroid.ui.BaseViewModel
import dev.emg.powerfulandroid.ui.DataState
import dev.emg.powerfulandroid.ui.auth.state.AuthStateEvent
import dev.emg.powerfulandroid.ui.auth.state.AuthStateEvent.CheckPreviousAuthEvent
import dev.emg.powerfulandroid.ui.auth.state.AuthStateEvent.LoadingAttemptEvent
import dev.emg.powerfulandroid.ui.auth.state.AuthStateEvent.RegisterAttemptEvent
import dev.emg.powerfulandroid.ui.auth.state.AuthViewState
import dev.emg.powerfulandroid.ui.auth.state.LoginFields
import dev.emg.powerfulandroid.ui.auth.state.RegistrationFields
import dev.emg.powerfulandroid.utils.AbsentLiveData
import javax.inject.Inject

class AuthViewModel @Inject constructor(val authRepository: AuthRepository) :
  BaseViewModel<AuthStateEvent, AuthViewState>() {

  override fun handleStateEvent(stateEvent: AuthStateEvent): LiveData<DataState<AuthViewState>> {
    when (stateEvent) {
      is LoadingAttemptEvent -> {
        return AbsentLiveData.create()
      }
      is CheckPreviousAuthEvent -> {
        return AbsentLiveData.create()
      }
      is RegisterAttemptEvent -> {
        return AbsentLiveData.create()
      }
    }
  }

  override fun initNewViewState(): AuthViewState {
    return AuthViewState()
  }

  fun setRegistrationFields(registrationFields: RegistrationFields) {
    val update = getCurrentViewStateOrNew()
    if (update.registrationFields == registrationFields) return
    update.registrationFields = registrationFields
    _viewState.value = update
  }

  fun setLoginFields(loginFields: LoginFields) {
    val update = getCurrentViewStateOrNew()
    if (update.loginFields == loginFields) return
    update.loginFields = loginFields
    _viewState.value = update
  }

  fun setAuthToken(authToken: AuthToken) {
    val update = getCurrentViewStateOrNew()
    if (update.authToken == authToken) return
    update.authToken = authToken
    _viewState.value = update
  }
}