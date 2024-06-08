package com.dicoding.asclepius.data.remote.model

import com.google.gson.annotations.SerializedName

data class ArticleResponse(

	@field:SerializedName("totalResults")
	val totalResults: Int,

	@field:SerializedName("articles")
	val articles: List<Article>,

	@field:SerializedName("status")
	val status: String
)

data class Article(
	@field:SerializedName("author")
	val author: String? = null,

	@field:SerializedName("urlToImage")
	val urlToImage: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("title")
	val title: String? = null
)