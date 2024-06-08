package com.dicoding.asclepius.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.asclepius.data.local.database.History
import com.dicoding.asclepius.databinding.ItemHistoryBinding

class HistoryAdapter :
    RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    private var list: List<History> = emptyList()

    var onItemClick: ((History) -> Unit)? = null

    fun setData(histories: List<History>) {
        list = histories
        notifyDataSetChanged()
    }

    inner class HistoryViewHolder(private val binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(history: History) {
            binding.apply {
                tvPrediction.text = history.prediction
                tvScore.text = StringBuilder("Confident score: ").append(history.score)
                tvCreatedAt.text = StringBuilder("Added at ").append(history.createdAt)

                Glide.with(itemView)
                    .load(history.image)
                    .centerCrop()
                    .into(ivAnalysisImage)

                deleteButton.setOnClickListener {
                    onItemClick?.invoke(history)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val history = list[position]
        holder.bind(history)
    }
}