package dev.emg.mvi.repository

import androidx.lifecycle.LiveData
import dev.emg.mvi.api.RetrofitBuilder
import dev.emg.mvi.model.BlogPost
import dev.emg.mvi.model.User
import dev.emg.mvi.ui.main.state.MainViewState
import dev.emg.mvi.util.ApiSuccessResponse
import dev.emg.mvi.util.DataState
import dev.emg.mvi.util.GenericApiResponse

object MainRepository {

  fun getBlogPosts(): LiveData<DataState<MainViewState>> {
    return object : NetworkBoundResource<List<BlogPost>, MainViewState>() {
      override fun handleApiSuccessResponse(response: ApiSuccessResponse<List<BlogPost>>) {
        result.value = DataState.data(data = MainViewState(blogPosts = response.body))
      }

      override fun createCall(): LiveData<GenericApiResponse<List<BlogPost>>> {
        return RetrofitBuilder.apiService.getBlogPosts()
      }
    }.asLiveData()
  }

  fun getUser(userId: String): LiveData<DataState<MainViewState>> {
    return object : NetworkBoundResource<User, MainViewState>() {
      override fun handleApiSuccessResponse(response: ApiSuccessResponse<User>) {
        result.value = DataState.data(data = MainViewState(user = response.body))
      }

      override fun createCall(): LiveData<GenericApiResponse<User>> {
        return RetrofitBuilder.apiService.getUser(userId)
      }
    }.asLiveData()
  }
}