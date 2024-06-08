package com.dicoding.asclepius.ui.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.asclepius.R
import com.dicoding.asclepius.data.local.database.History
import com.dicoding.asclepius.databinding.ActivityHistoryBinding
import com.dicoding.asclepius.ui.adapter.HistoryAdapter
import com.dicoding.asclepius.ui.viewmodel.HistoryViewModel
import com.dicoding.asclepius.ui.viewmodel.HistoryViewModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class HistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryBinding
    private lateinit var historyAdapter: HistoryAdapter
    private val historyViewModel by viewModels<HistoryViewModel>{
        HistoryViewModelFactory.getInstance(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setTitle(R.string.history)
            setDisplayHomeAsUpEnabled(true)
        }

        historyAdapter = HistoryAdapter()
        binding.rvHistory.apply {
            layoutManager = LinearLayoutManager(this@HistoryActivity)
            adapter = historyAdapter
        }

        historyViewModel.getAllHistories().observe(this) { histories ->
            if (histories.isEmpty()) {
                binding.tvEmptyFavorite.visibility = View.VISIBLE
                historyAdapter.setData(emptyList())
            } else {
                binding.tvEmptyFavorite.visibility = View.GONE
                historyAdapter.setData(histories)
            }
        }

        historyAdapter.onItemClick = { history ->
            showDeleteHistoryDialog(history)
        }
    }

    private fun showClearHistoryAlertDialog() {
        MaterialAlertDialogBuilder(this, R.style.AlertDialogCustom)
            .setTitle("Delete All Analysis History")
            .setMessage("Do you want to delete all your analysis history?")
            .setPositiveButton("Delete") { _, _ ->
                historyViewModel.deleteAllHistories()
                Toast.makeText(this, "Successfully deleted all analysis from history", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showDeleteHistoryDialog(history: History) {
        MaterialAlertDialogBuilder(this, R.style.AlertDialogCustom)
            .setTitle("Delete Analysis History")
            .setMessage("Do you want to delete this analysis from history?")
            .setPositiveButton("Delete") { _, _ ->
                historyViewModel.delete(history)
                Toast.makeText(this, "Successfully deleted analysis from history", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_history_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            R.id.menu_clear -> {
                if (historyAdapter.itemCount > 0) {
                    showClearHistoryAlertDialog()
                } else {
                    Toast.makeText(this, "You don't have any analysis history", Toast.LENGTH_SHORT).show()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}