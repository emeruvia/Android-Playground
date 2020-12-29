package dev.emg.powerfulandroid.persistance

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.emg.powerfulandroid.models.AuthToken

@Dao
interface AuthTokenDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insert(authToken: AuthToken): Long

  // Method will be used to log out the token in the DB
  @Query("UPDATE auth_token SET token = null WHERE account_pk = :pk")
  fun nullifyToken(pk: Int): Int
}