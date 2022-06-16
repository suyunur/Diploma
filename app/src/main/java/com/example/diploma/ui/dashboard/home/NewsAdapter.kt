package com.example.diploma.ui.dashboard.home

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.diploma.data.RECS_IMAGES
import com.example.diploma.data.model.News
import com.example.diploma.databinding.ItemRecommendationBinding


class NewsAdapter(
    private val clickListener: ClickListener
) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private var news = mutableListOf<News?>()

    @SuppressLint("NotifyDataSetChanged")
    fun setList(news: List<News?>) {
        this.news = news.toMutableList()
        notifyDataSetChanged()
    }

    interface ClickListener {
        fun onClick(news: News)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecommendationBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return news.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = news[position]
        news?.let { holder.bind(it) }
    }

    inner class ViewHolder(
        val binding: ItemRecommendationBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(news: News) = with(binding) {
            root.setOnClickListener {
                clickListener.onClick(news)
            }

            newsTitle.text = news.title

            RECS_IMAGES[layoutPosition]?.let { newsImage.setImageBitmap(
                getBitmap(root.context.resources, it)
            ) }
        }
    }

    private fun getBitmap(res: Resources, image: Int): Bitmap? {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = false
        options.inSampleSize = 1
        options.inScaled = false
        options.inPreferredConfig = Bitmap.Config.ARGB_8888

        return BitmapFactory.decodeResource(res, image, options)
    }
}