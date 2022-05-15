package com.example.diploma.ui.dashboard.profile

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.diploma.databinding.ItemStudyProgressBinding

class StudyAdapter : RecyclerView.Adapter<StudyAdapter.ViewHolder>() {

    var listStudy = mutableListOf<String>()

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<String>) {
        this.listStudy = list.toMutableList()
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        private val binding: ItemStudyProgressBinding
    ): RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(string: String) = with(binding) {
            progressPercent.text = "12 %"
            progressCircle.progress = 12
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