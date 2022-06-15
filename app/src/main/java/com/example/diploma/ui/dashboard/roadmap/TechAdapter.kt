package com.example.diploma.ui.dashboard.roadmap

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.diploma.data.model.Technology
import com.example.diploma.databinding.LayoutRoadmapBinding

class TechAdapter(
    private val listener: ClickListener
) : RecyclerView.Adapter<TechAdapter.ViewHolder>() {

    private var techs = mutableListOf<Technology>()

    @SuppressLint("NotifyDataSetChanged")
    fun setList(techs: List<Technology>) {
        this.techs = techs.toMutableList()
        notifyDataSetChanged()
    }

    interface ClickListener {
        fun onClick(tech: Technology)
    }

    inner class ViewHolder(
        private val binding: LayoutRoadmapBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(tech: Technology) = with(binding) {
            roadmapText.text = tech.name
            root.setOnClickListener {
                listener.onClick(tech)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = LayoutRoadmapBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tech = techs[position]
        tech.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return techs.size
    }

}