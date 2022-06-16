package com.example.diploma.ui.dashboard.profile

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.diploma.data.colorProgress
import com.example.diploma.data.colorsBack
import com.example.diploma.data.model.User
import com.example.diploma.databinding.ItemSkillApprovedBinding

class SkillsAdapter : RecyclerView.Adapter<SkillsAdapter.ViewHolder>() {

    private var listSkills = mutableListOf<User.UserSkills>()

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<User.UserSkills>) {
        this.listSkills = list.toMutableList()
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        private val binding: ItemSkillApprovedBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(skill: User.UserSkills) = with(binding) {
            skillName.text = skill.name
            val color = colorProgress[(layoutPosition + 1) % colorProgress.size]
            val backColor = colorsBack[(layoutPosition + 1) % colorProgress.size]
            skillName.setTextColor(Color.parseColor(color))
            DrawableCompat.setTint(
                DrawableCompat.wrap(root.background),
                Color.parseColor(backColor)
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemSkillApprovedBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val skills = listSkills[position]
        skills.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return listSkills.size
    }

}