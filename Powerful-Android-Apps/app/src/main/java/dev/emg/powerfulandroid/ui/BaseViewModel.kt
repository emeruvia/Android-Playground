package dev.emg.powerfulandroid.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<StateEvent, ViewState> : ViewModel() {

  protected val _viewState: MutableLiveData<ViewState> = MutableLiveData()
  val viewState: LiveData<ViewState>
    get() = _viewState

  protected val _stateEvent: MutableLiveData<StateEvent> = MutableLiveData()
  val dataState: LiveData<DataState<ViewState>> =
    Transformations.switchMap(_stateEvent) { stateEvent ->
      stateEvent?.let {
        handleStateEvent(stateEvent)
      }
    }

  abstract fun handleStateEvent(stateEvent: StateEvent): LiveData<DataState<ViewState>>

  abstract fun initNewViewState(): ViewState

  fun setStateEvent(event: StateEvent) {
    _stateEvent.value = event
  }

  fun getCurrentViewStateOrNew(): ViewState {
    return viewState.value?.let { it } ?: initNewViewState()
  }

}