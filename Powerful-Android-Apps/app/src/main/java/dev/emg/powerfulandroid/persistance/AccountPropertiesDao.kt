package dev.emg.powerfulandroid.persistance

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.emg.powerfulandroid.models.AccountProperties

@Dao
interface AccountPropertiesDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertAndReplace(accountProperties: AccountProperties): Long

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  fun insertOrIgnore(accountProperties: AccountProperties): Long

  @Query("SELECT * FROM account_properties WHERE pk = :pk")
  fun searchByPrimaryKey(pk: String): LiveData<AccountProperties>

  @Query("SELECT * FROM account_properties WHERE email = :email")
  fun searchByEmail(email: String): AccountProperties?
}