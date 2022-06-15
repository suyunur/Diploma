package com.example.diploma.ui.dashboard.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.diploma.data.CAROUSEL
import com.example.diploma.databinding.ItemCarouselBinding

class RecsAdapter : RecyclerView.Adapter<RecsAdapter.ViewHolder>() {

    var listMain = mutableListOf<Int>()

    fun setList(images: List<Int>) {
        this.listMain = images.toMutableList()

    }

    inner class ViewHolder(
        val binding: ItemCarouselBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(int: Int) = with(binding) {

            carouselImage.setImageResource(CAROUSEL[layoutPosition])

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCarouselBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listMain[position]
        item.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return listMain.size
    }

}