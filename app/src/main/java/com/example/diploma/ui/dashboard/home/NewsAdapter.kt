package com.example.diploma.ui.dashboard.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.diploma.data.model.News
import com.example.diploma.databinding.ItemRecommendationBinding
import com.example.diploma.ext.loadImage

class NewsAdapter(private val newsList: List<News?>)
    : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecommendationBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(newsList[position]) {
                binding.newsImage.loadImage(this?.imageUrl)
                binding.newsTitle.text = this?.title ?: ""
            }
        }
    }

    inner class ViewHolder(val binding: ItemRecommendationBinding):
        RecyclerView.ViewHolder(binding.root)
}