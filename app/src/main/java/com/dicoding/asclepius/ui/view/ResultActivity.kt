package com.dicoding.asclepius.ui.view

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import com.dicoding.asclepius.R
import com.dicoding.asclepius.data.local.database.History
import com.dicoding.asclepius.databinding.ActivityResultBinding
import com.dicoding.asclepius.helper.DateHelper
import com.dicoding.asclepius.ui.viewmodel.ResultViewModel
import com.dicoding.asclepius.ui.viewmodel.ResultViewModelFactory

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    private val resultViewModel by viewModels<ResultViewModel>{
        ResultViewModelFactory.getInstance(application)
    }

    companion object {
        const val EXTRA_IMAGE_URI = "EXTRA_IMAGE_URI"
        const val EXTRA_RESULT = "EXTRA_RESULT"
        const val EXTRA_PREDICTION = "EXTRA_PREDICTION"
        const val EXTRA_SCORE = "EXTRA_SCORE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setTitle(R.string.result)
            setDisplayHomeAsUpEnabled(true)
        }

        val result = intent.getStringExtra(EXTRA_RESULT)
        val prediction = intent.getStringExtra(EXTRA_PREDICTION)
        val score = intent.getStringExtra(EXTRA_SCORE)
        val createdAt = DateHelper.getCurrentDate()
        val imageUriString = intent.getStringExtra(EXTRA_IMAGE_URI)
        val imageUri = Uri.parse(imageUriString)

        binding.saveButton.setOnClickListener {
            val history = History(
                image = imageUri.toString(),
                prediction = prediction,
                score = score,
                createdAt = createdAt
            )

            resultViewModel.insert(history)
            binding.saveButton.isEnabled = false
            Toast.makeText(this, "Data saved successfully", Toast.LENGTH_SHORT).show()
        }

        binding.resultImage.setImageURI(imageUri)
        binding.resultText.text = result
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}