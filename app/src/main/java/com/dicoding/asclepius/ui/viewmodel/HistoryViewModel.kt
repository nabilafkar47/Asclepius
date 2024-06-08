package com.dicoding.asclepius.ui.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.asclepius.data.local.database.History
import com.dicoding.asclepius.data.local.repository.HistoryRepository

class HistoryViewModel(application: Application) : ViewModel() {
    private val mHistoryRepository: HistoryRepository = HistoryRepository(application)

    fun getAllHistories(): LiveData<List<History>> = mHistoryRepository.getAllHistories()

    fun deleteAllHistories() {
        mHistoryRepository.deleteAllHistories()
    }

    fun delete(history: History) {
        mHistoryRepository.delete(history)
    }
}