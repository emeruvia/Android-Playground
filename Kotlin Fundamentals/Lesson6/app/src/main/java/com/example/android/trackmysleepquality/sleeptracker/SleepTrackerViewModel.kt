/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.trackmysleepquality.sleeptracker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.android.trackmysleepquality.database.SleepDatabaseDao
import com.example.android.trackmysleepquality.database.SleepNight
import com.example.android.trackmysleepquality.formatNights
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * ViewModel for SleepTrackerFragment.
 */
class SleepTrackerViewModel(
  val database: SleepDatabaseDao,
  application: Application
) : AndroidViewModel(application) {

  private var viewModelJob = Job()
  private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

  private var tonight = MutableLiveData<SleepNight?>()
  private val nights = database.getAllNights()
  val nightsString = Transformations.map(nights) { nights ->
    formatNights(nights, application.resources)
  }

  private val _navigateToSleepQuality = MutableLiveData<SleepNight>()
  val navigateToSleepQuality: LiveData<SleepNight>
    get() = _navigateToSleepQuality

  private var _showSnackBarEvent = MutableLiveData<Boolean>()
  val showSnackBarEvent: LiveData<Boolean>
    get() = _showSnackBarEvent

  val startButtonVisible = Transformations.map(tonight) {
    it == null
  }

  val stopButtonVisible = Transformations.map(tonight) {
    it != null
  }

  val clearButtonVisible = Transformations.map(nights) {
    it?.isNotEmpty()
  }

  init {
    initializeTonight()
  }

  override fun onCleared() {
    super.onCleared()
    viewModelJob.cancel()
  }

  private fun initializeTonight() {
    uiScope.launch {
      tonight.value = getTonightFromDatabase()
    }
  }

  fun onStartTracking() {
    uiScope.launch {
      val newNight = SleepNight()
      insert(newNight)
      tonight.value = getTonightFromDatabase()
    }
  }

  fun onStopTracking() {
    uiScope.launch {
      val oldNight = tonight.value ?: return@launch
      oldNight.endTimeMilli = System.currentTimeMillis()
      update(oldNight)
      _navigateToSleepQuality.value = oldNight
    }
  }

  fun onClearTracking() {
    uiScope.launch {
      clear()
      tonight.value = null
      _showSnackBarEvent.value = true
    }
  }

  fun doneNavigating() {
    _navigateToSleepQuality.value = null
  }

  fun doneShowingSnackBar() {
    _showSnackBarEvent.value = false
  }

  private suspend fun getTonightFromDatabase(): SleepNight? {
    return withContext(Dispatchers.IO) {
      var night = database.getTonight()
      if (night?.endTimeMilli != night?.startTimeMilli) {
        night = null
      }
      night
    }
  }

  private suspend fun insert(night: SleepNight) {
    withContext(Dispatchers.IO) {
      database.insert(night)
    }
  }

  private suspend fun update(night: SleepNight) {
    withContext(Dispatchers.IO) {
      database.update(night)
    }
  }

  private suspend fun clear() {
    withContext(Dispatchers.IO) {
      database.clear()
    }
  }
}

