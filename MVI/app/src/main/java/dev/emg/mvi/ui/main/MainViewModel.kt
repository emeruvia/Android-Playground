package dev.emg.mvi.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import dev.emg.mvi.model.BlogPost
import dev.emg.mvi.model.User
import dev.emg.mvi.repository.MainRepository
import dev.emg.mvi.ui.main.state.MainStateEvent
import dev.emg.mvi.ui.main.state.MainViewState

import dev.emg.mvi.ui.main.state.MainStateEvent.*
import dev.emg.mvi.util.AbsentLiveData
import dev.emg.mvi.util.DataState

class MainViewModel : ViewModel() {

  private val _stateEvent: MutableLiveData<MainStateEvent> = MutableLiveData()
  private val _viewState: MutableLiveData<MainViewState> = MutableLiveData()

  val viewState: LiveData<MainViewState>
    get() = _viewState

  val dataState: LiveData<DataState<MainViewState>> = Transformations
      .switchMap(_stateEvent) { stateEvent ->
        stateEvent?.let {
          handleStateEvent(it)
        }
      }

  private fun handleStateEvent(stateEvent: MainStateEvent): LiveData<DataState<MainViewState>> {
    return when (stateEvent) {
      is GetBlogPostEvent -> {
        return MainRepository.getBlogPosts()
      }
      is GetUserEvent -> {
        return MainRepository.getUser(stateEvent.userId)
      }
      is None -> {
        AbsentLiveData.create()
      }
    }
  }

  fun setBlogListData(blogPosts: List<BlogPost>) {
    val update = getCurrentViewStateOrnew()
    update.blogPosts = blogPosts
    _viewState.value = update
  }

  fun setUser(user: User) {
    val update = getCurrentViewStateOrnew()
    update.user = user
    _viewState.value = update
  }

  fun setStateEvent(event: MainStateEvent) {
    _stateEvent.value = event
  }

  fun getCurrentViewStateOrnew(): MainViewState {
    return viewState.value?.let {
      it
    } ?: MainViewState()
  }
}