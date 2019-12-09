package dev.emg.mvi.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import dev.emg.mvi.api.RetrofitBuilder
import dev.emg.mvi.ui.main.state.MainViewState
import dev.emg.mvi.util.ApiEmptyResponse
import dev.emg.mvi.util.ApiErrorResponse
import dev.emg.mvi.util.ApiSuccessResponse

object MainRepository {

  fun getBlogPosts(): LiveData<MainViewState> {
    return Transformations
        .switchMap(RetrofitBuilder.apiService.getBlogPosts()) { apiResponse ->
          object : LiveData<MainViewState>() {
            override fun onActive() {
              super.onActive()
              value = when (apiResponse) {
                is ApiSuccessResponse -> {
                  MainViewState(
                      blogPosts = apiResponse.body
                  )
                }
                is ApiErrorResponse -> {
                  MainViewState() // Handle error?
                }
                is ApiEmptyResponse -> {
                  MainViewState() // Handle empty/error?
                }
              }
            }
          }

        }
  }

  fun getUser(userId: String): LiveData<MainViewState> {
    return Transformations
        .switchMap(RetrofitBuilder.apiService.getUser(userId)) { apiResponse ->
          object : LiveData<MainViewState>() {
            override fun onActive() {
              super.onActive()
              value = when (apiResponse) {
                is ApiSuccessResponse -> {
                  MainViewState(
                      user = apiResponse.body
                  )
                }
                is ApiErrorResponse -> {
                  MainViewState() // Handle error?
                }
                is ApiEmptyResponse -> {
                  MainViewState() // Handle empty/error?
                }
              }
            }
          }

        }
  }

}