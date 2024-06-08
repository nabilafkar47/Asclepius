package com.dicoding.asclepius.ui.view

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dicoding.asclepius.R
import com.dicoding.asclepius.databinding.ActivityArticleDetailBinding

class ArticleDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArticleDetailBinding

    companion object {
        const val ARTICLE_AUTHOR = "ARTICLE_AUTHOR"
        const val ARTICLE_TITLE = "ARTICLE_TITLE"
        const val ARTICLE_DESCRIPTION = "ARTICLE_DESCRIPTION"
        const val ARTICLE_IMAGE_URL = "ARTICLE_IMAGE_URL"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setTitle(R.string.news_detail)
            setDisplayHomeAsUpEnabled(true)
        }

        val author = intent.getStringExtra(ARTICLE_AUTHOR)
        val title = intent.getStringExtra(ARTICLE_TITLE)
        val description = intent.getStringExtra(ARTICLE_DESCRIPTION)
        val image = intent.getStringExtra(ARTICLE_IMAGE_URL)

        binding.apply {
            tvDetailAuthor.text = author
            tvDetailTitle.text = title
            tvDetailDescription.text = description

            Glide.with(this@ArticleDetailActivity)
                .load(image)
                .centerCrop()
                .into(ivDetailArticleImage)
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