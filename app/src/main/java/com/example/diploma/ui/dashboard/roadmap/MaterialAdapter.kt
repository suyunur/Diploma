package com.example.diploma.ui.dashboard.roadmap

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.diploma.R
import com.example.diploma.data.model.Material
import com.example.diploma.databinding.ItemLinkBinding

class MaterialAdapter(
    private val listener: ClickListener
) : RecyclerView.Adapter<MaterialAdapter.ViewHolder>() {

    var links = mutableListOf<Material.Subtopic>()

    @SuppressLint("NotifyDataSetChanged")
    fun setList(links: List<Material.Subtopic>) {
        this.links = links.toMutableList()
        notifyDataSetChanged()
    }

    interface ClickListener {
        fun onClick(link: String, type: String)
    }

    inner class ViewHolder(
        private val binding: ItemLinkBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(link: Material.Subtopic) = with(binding) {
            if (link.type_name == "READ")
                linkTypeImage.setImageResource(R.drawable.ic_link_read)
            else linkTypeImage.setImageResource(R.drawable.ic_link_video)
            linkName.text = link.name
            openBtn.setOnClickListener { listener.onClick(link.link, link.type_name) }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val link = links[position]
        link.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemLinkBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return links.size
    }

}