package dev.emg.powerfulandroid.persistance

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.emg.powerfulandroid.models.AccountProperties
import dev.emg.powerfulandroid.models.AuthToken

@Database(entities = [AuthToken::class, AccountProperties::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

  abstract fun authTokenDao(): AuthTokenDao
  abstract fun accountProperties(): AccountPropertiesDao

  companion object {
    const val DB_NAME = "app_db"

  }
}