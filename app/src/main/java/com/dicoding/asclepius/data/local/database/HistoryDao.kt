package com.dicoding.asclepius.data.local.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(history: History)

    @Delete
    fun delete(history: History)

    @Query("DELETE FROM History")
    fun deleteAllHistories()

    @Query("SELECT * from History ORDER BY created_at DESC")
    fun getAllHistories(): LiveData<List<History>>
}