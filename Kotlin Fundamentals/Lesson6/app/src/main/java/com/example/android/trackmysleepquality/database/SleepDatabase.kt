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

package com.example.android.trackmysleepquality.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SleepNight::class], version = 1, exportSchema = false)
abstract class SleepDatabase : RoomDatabase() {

  abstract val sleepDatabaseDao: SleepDatabaseDao

  companion object {
    /**
     * Volatile variable will never be caches, and all writes and reads will be done to and from the
     * main memory. This makes sure that the INSTANCE is always up-to-date and the same
     * to all execution threads. All changes done by one thread are visible to all other threads
     * immediately.
     */
    @Volatile
    private var INSTANCE: SleepDatabase? = null

    fun getInstance(context: Context): SleepDatabase {
      // Synchronized means that only one thread of execution at a time can enter this block of code.
      // This makes sure that the DB is only instantiated once
      synchronized(this) {
        var instance = INSTANCE
        if (instance == null) {
          instance = Room.databaseBuilder(
              context.applicationContext,
              SleepDatabase::class.java,
              "sleep_history_database"
          )
              .fallbackToDestructiveMigration()
              .build()
        }

        return instance
      }
    }
  }

}