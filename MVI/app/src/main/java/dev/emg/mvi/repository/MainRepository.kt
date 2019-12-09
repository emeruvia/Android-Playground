package dev.emg.mvi.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import dev.emg.mvi.api.RetrofitBuilder
import dev.emg.mvi.ui.main.state.MainViewState
import dev.emg.mvi.util.ApiEmptyResponse
import dev.emg.mvi.util.ApiErrorResponse
import dev.emg.mvi.util.ApiSuccessResponse
import dev.emg.mvi.util.DataState

object MainRepository {

  fun getBlogPosts(): LiveData<DataState<MainViewState>> {
    return Transformations
        .switchMap(RetrofitBuilder.apiService.getBlogPosts()) { apiResponse ->
          object : LiveData<DataState<MainViewState>>() {
            override fun onActive() {
              super.onActive()
              value = when (apiResponse) {
                is ApiSuccessResponse -> {
                  DataState.data(
                      data = MainViewState(blogPosts = apiResponse.body)
                  )
                }
                is ApiErrorResponse -> {
                  DataState.error(message = apiResponse.errorMessage)
                }
                is ApiEmptyResponse -> {
                  DataState.error(message = "Empty response.")
                }
              }
            }
          }

        }
  }

  fun getUser(userId: String): LiveData<DataState<MainViewState>> {
    return Transformations
        .switchMap(RetrofitBuilder.apiService.getUser(userId)) { apiResponse ->
          object : LiveData<DataState<MainViewState>>() {
            override fun onActive() {
              super.onActive()
              value = when (apiResponse) {
                is ApiSuccessResponse -> {
                  DataState.data(
                      data = MainViewState(user = apiResponse.body)
                  )
                }
                is ApiErrorResponse -> {
                  DataState.error(message = apiResponse.errorMessage)
                }
                is ApiEmptyResponse -> {
                  DataState.error(message = "Empty response.")
                }
              }
            }
          }

        }
  }

}