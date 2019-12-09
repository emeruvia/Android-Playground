package dev.emg.mvi.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import dev.emg.mvi.util.ApiEmptyResponse
import dev.emg.mvi.util.ApiErrorResponse
import dev.emg.mvi.util.ApiSuccessResponse
import dev.emg.mvi.util.Constants.Companion.TESTING_NETWORK_DELAY
import dev.emg.mvi.util.DataState
import dev.emg.mvi.util.GenericApiResponse
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A generic class that can provide a resource backed by both the sqlite database and the network.
 */
abstract class NetworkBoundResource<ResponseObject, ViewStateType> {

  private val TAG = NetworkBoundResource::class.java.name
  protected val result = MediatorLiveData<DataState<ViewStateType>>()

  init {
    result.value = DataState.loading(true)
    GlobalScope.launch(IO) {
      delay(TESTING_NETWORK_DELAY) // delay for testing loading

      withContext(Main) {
        val apiResponse = createCall()
        result.addSource(apiResponse) { response ->
          result.removeSource(apiResponse)
          handleNetworkCall(response)
        }
      }
    }
  }

  fun handleNetworkCall(response: GenericApiResponse<ResponseObject>) {
    when (response) {
      is ApiSuccessResponse -> {
        handleApiSuccessResponse(response)
      }
      is ApiErrorResponse -> {
        Log.d(TAG, "handleNetworkCall(): ApiErrorResponse: response = ${response.errorMessage}")
        onReturnError(response.errorMessage)
      }
      is ApiEmptyResponse -> {
        Log.d(TAG, "handleNetworkCall(): ApiEmptyResponse: response = HTTP 204. Empty response.")
        onReturnError("HTTP 204. Empty Response.")
      }
    }
  }

  fun onReturnError(message: String) {
    result.value = DataState.error(message)
  }

  abstract fun handleApiSuccessResponse(response: ApiSuccessResponse<ResponseObject>)

  abstract fun createCall(): LiveData<GenericApiResponse<ResponseObject>>

  fun asLiveData() = result as LiveData<DataState<ViewStateType>>
}