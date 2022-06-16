package com.example.diploma.ui.dashboard.profile

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.ColorFilter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.diploma.data.colorProgress
import com.example.diploma.data.colorsBack
import com.example.diploma.data.model.User
import com.example.diploma.databinding.ItemStudyProgressBinding

class StudyAdapter : RecyclerView.Adapter<StudyAdapter.ViewHolder>() {

    private var listStudy = mutableListOf<User.UserProgress>()

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<User.UserProgress>) {
        this.listStudy = list.toMutableList()
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        private val binding: ItemStudyProgressBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(study: User.UserProgress) = with(binding) {
            progressPercent.text = "${study.progress.toInt()} %"
            courseName.text = study.tech_name

            val textColor = colorProgress[(layoutPosition + 1) % colorProgress.size]
            val backColor = colorsBack[(layoutPosition + 1) % colorsBack.size]
            courseName.setTextColor(Color.parseColor(textColor))
            DrawableCompat.setTint(
                DrawableCompat.wrap(root.background),
                Color.parseColor(backColor)
            )
            progressPercent.setTextColor(Color.parseColor(textColor))
            progressCircle.progress = study.progress.toInt()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemStudyProgressBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val study = listStudy[position]
        study.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return listStudy.size
    }
}