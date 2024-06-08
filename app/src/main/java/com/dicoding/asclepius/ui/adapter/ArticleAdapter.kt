package com.dicoding.asclepius.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.asclepius.ui.view.ArticleDetailActivity
import com.dicoding.asclepius.data.remote.model.Article
import com.dicoding.asclepius.databinding.ItemArticleBinding

class ArticleAdapter :
    RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    private var list: List<Article> = emptyList()

    fun setData(articles: List<Article>) {
        list = articles
        notifyDataSetChanged()
    }

    inner class ArticleViewHolder(private val binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(article: Article) {
            binding.apply {
                tvTitle.text = article.title
                tvDescription.text = article.description
                tvAuthor.text = article.author

                Glide.with(itemView.context)
                    .load(article.urlToImage)
                    .centerCrop()
                    .into(ivArticleImage)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, ArticleDetailActivity::class.java)
                    intent.putExtra(ArticleDetailActivity.ARTICLE_AUTHOR, article.author)
                    intent.putExtra(ArticleDetailActivity.ARTICLE_TITLE, article.title)
                    intent.putExtra(ArticleDetailActivity.ARTICLE_DESCRIPTION, article.description)
                    intent.putExtra(ArticleDetailActivity.ARTICLE_IMAGE_URL, article.urlToImage)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = list[position]
        holder.bind(article)
    }
}