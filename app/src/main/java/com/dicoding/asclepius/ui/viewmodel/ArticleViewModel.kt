package com.dicoding.asclepius.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.asclepius.data.remote.api.ApiConfig
import com.dicoding.asclepius.data.remote.model.Article
import com.dicoding.asclepius.data.remote.model.ArticleResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticleViewModel : ViewModel() {
    companion object {
        private val TAG = ArticleViewModel::class.simpleName
    }

    private val _listArticles = MutableLiveData<List<Article>>()
    val listArticles: LiveData<List<Article>> = _listArticles

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        fetchArticles()
    }

    private fun fetchArticles() {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getArticles()

        client.enqueue(object : Callback<ArticleResponse> {
            override fun onResponse(
                call: Call<ArticleResponse>,
                response: Response<ArticleResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val articles = response.body()?.articles.orEmpty().filter { article ->
                        article.author != null && article.author != "[Removed]" &&
                                article.title != null && article.title != "[Removed]" &&
                                article.description != null && article.description != "[Removed]" &&
                                article.urlToImage != null && article.urlToImage != "[Removed]" }
                    _listArticles.value = articles
                } else {
                    Log.e(TAG, "onResponse: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ArticleResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }
}