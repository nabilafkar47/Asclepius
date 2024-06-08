package com.dicoding.asclepius.ui.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.dicoding.asclepius.data.local.database.History
import com.dicoding.asclepius.data.local.repository.HistoryRepository

class ResultViewModel(application: Application) : ViewModel() {
    private val mHistoryRepository = HistoryRepository(application)

    fun insert(history: History) {
        mHistoryRepository.insert(history)
    }
}