/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.kotlincoroutines.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * MainViewModel designed to store and manage UI-related data in a lifecycle conscious way. This
 * allows data to survive configuration changes such as screen rotations. In addition, background
 * work such as fetching network results can continue through configuration changes and deliver
 * results after the new Fragment or Activity is available.
 */
class MainViewModel : ViewModel() {

  private val viewModelJob = Job()
  private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

  /**
   * Request a snackbar to display a string.
   *
   * This variable is private because we don't want to expose MutableLiveData
   *
   * MutableLiveData allows anyone to set a value, and MainViewModel is the only
   * class that should be setting values.
   */
  private val _snackBar = MutableLiveData<String>()

  /**
   * Request a snackbar to display a string.
   */
  val snackbar: LiveData<String>
    get() = _snackBar

  /**
   * Cancel the Job when the ViewModel is cleared
   */
  override fun onCleared() {
    super.onCleared()
    viewModelJob.cancel()
  }

  /**
   * Wait one second then display a snackbar.
   */
  fun onMainViewClicked() {
    viewModelScope.launch {
      delay(1_000)
      _snackBar.value = "Hello, from coroutines!"
    }
  }

  /**
   * Called immediately after the UI shows the snackbar.
   */
  fun onSnackbarShown() {
    _snackBar.value = null
  }
}
