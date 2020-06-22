package com.example.android.codelabs.paging.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = RemoteKeys.TABLE_NAME)
data class RemoteKeys(
        @PrimaryKey val repoId: Long,
        val prevKey: Int?,
        val nextKey: Int?
) {
    companion object {
        const val TABLE_NAME = "remote_keys_table"
    }
}