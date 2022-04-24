package com.example.diploma.ui.dashboard.vacancies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.diploma.data.model.Requirement
import com.example.diploma.databinding.ItemRequirementBinding

class RequirementsAdapter :
    ListAdapter<Requirement, RequirementsAdapter.ViewHolder>(DiffCallback()) {

    inner class ViewHolder(
        private val binding: ItemRequirementBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(requirement: Requirement) = with(binding) {
            text.text = requirement.name
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Requirement>() {
        override fun areItemsTheSame(oldItem: Requirement, newItem: Requirement): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Requirement, newItem: Requirement): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemRequirementBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}