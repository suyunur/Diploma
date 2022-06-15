package com.example.diploma.ui.dashboard.roadmap

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.diploma.R
import com.example.diploma.data.model.Topic
import com.example.diploma.databinding.ItemTopicBinding

class TopicAdapter(
    private val listener: ClickListener
) : RecyclerView.Adapter<TopicAdapter.ViewHolder>() {

    private var topics = mutableListOf<Topic>()

    @SuppressLint("NotifyDataSetChanged")
    fun setList(vacancies: List<Topic>) {
        this.topics = vacancies.toMutableList()
        notifyDataSetChanged()
    }

    interface ClickListener {
        fun onClick(topic: Topic)
    }

    inner class ViewHolder(
        private val binding: ItemTopicBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(topic: Topic) = with(binding) {
            root.setOnClickListener {
                listener.onClick(topic)
            }
            topicName.text = topic.name
            topicNum.text = "Chapter ${(layoutPosition + 1)}"
            if (topic.is_done == true) {
                topicStatusImage.setImageResource(R.drawable.ic_done)
            } else {
                topicStatusImage.setImageResource(R.drawable.ic_next_black)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemTopicBinding = ItemTopicBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(itemTopicBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val topic = topics[position]
        topic.let { holder.bind(it) }
    }

    override fun getItemCount(): Int {
        return topics.size
    }

}