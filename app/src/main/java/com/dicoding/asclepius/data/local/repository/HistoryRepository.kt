package com.dicoding.asclepius.data.local.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.dicoding.asclepius.data.local.database.History
import com.dicoding.asclepius.data.local.database.HistoryDao
import com.dicoding.asclepius.data.local.database.HistoryRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class HistoryRepository(application: Application) {
    private val mHistoryDao: HistoryDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()
    init {
        val db = HistoryRoomDatabase.getDatabase(application)
        mHistoryDao = db.historyDao()
    }

    fun getAllHistories(): LiveData<List<History>> = mHistoryDao.getAllHistories()

    fun deleteAllHistories() {
        executorService.execute { mHistoryDao.deleteAllHistories() }
    }

    fun insert(history: History) {
        executorService.execute { mHistoryDao.insert(history) }
    }

    fun delete(history: History) {
        executorService.execute { mHistoryDao.delete(history) }
    }
}