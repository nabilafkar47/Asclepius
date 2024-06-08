package com.dicoding.asclepius.ui.view

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.asclepius.R
import com.dicoding.asclepius.databinding.ActivityArticleBinding
import com.dicoding.asclepius.ui.adapter.ArticleAdapter
import com.dicoding.asclepius.ui.viewmodel.ArticleViewModel

class ArticleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArticleBinding
    private lateinit var articleAdapter: ArticleAdapter
    private val articleViewModel by viewModels<ArticleViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setTitle(R.string.news)
            setDisplayHomeAsUpEnabled(true)
        }

        articleAdapter = ArticleAdapter()
        binding.rvArticle.apply {
            layoutManager = LinearLayoutManager(this@ArticleActivity)
            adapter = articleAdapter
        }

        articleViewModel.listArticles.observe(this) { articles ->
            articleAdapter.setData(articles)
        }

        articleViewModel.isLoading.observe(this) { isLoading ->
            binding.progressBarArticle.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
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