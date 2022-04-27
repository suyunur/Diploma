package com.example.diploma.ui.dashboard.vacancies

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.diploma.data.model.Skill
import com.example.diploma.databinding.ItemRequirementBinding


class RequirementsAdapter
    : RecyclerView.Adapter<RequirementsAdapter.ViewHolder>() {

    private var skills = mutableListOf<Skill?>()

    @SuppressLint("NotifyDataSetChanged")
    fun setList(skills: List<Skill?>) {
        this.skills = skills.toMutableList()
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        private val binding: ItemRequirementBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(skill: Skill) = with(binding) {
            text.text = skill.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemRequirementBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val skill = skills[position]
        skill?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return skills.size
    }

}