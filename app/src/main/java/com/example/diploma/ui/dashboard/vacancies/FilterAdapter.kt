package com.example.diploma.ui.dashboard.vacancies

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.diploma.R
import com.example.diploma.data.VACANCY_FILTERS
import com.example.diploma.databinding.ItemFilerBinding

class FilterAdapter(
    private val listener: ClickListener
) : RecyclerView.Adapter<FilterAdapter.ViewHolder>(){

    interface ClickListener {
        fun onFilter(string: String)
    }

    private var index = -1
    private var filters = VACANCY_FILTERS

    inner class ViewHolder(
        private val binding: ItemFilerBinding
    ): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("NotifyDataSetChanged")
        fun bind(filter: String) = with(binding) {
            root.setOnClickListener {
                listener.onFilter(filter)
                index = layoutPosition
                notifyDataSetChanged()
            }

            if (index == layoutPosition) {
                root.setBackgroundResource(R.drawable.bg_filter_selected)
                this.filter.setTextColor(ContextCompat.getColor(root.context, R.color.white))
            } else {
                root.setBackgroundResource(R.drawable.bg_filter)
                this.filter.setTextColor(ContextCompat.getColor(root.context, R.color.blue))
            }

            this.filter.text = filter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemFilerBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val filter = filters[position]
        filter?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return filters.size
    }

}