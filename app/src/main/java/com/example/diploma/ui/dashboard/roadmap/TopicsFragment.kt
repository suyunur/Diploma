package com.example.diploma.ui.dashboard.roadmap

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import com.example.diploma.R
import com.example.diploma.data.*
import com.example.diploma.data.model.Topic
import com.example.diploma.databinding.DiplomaFragmentTopicsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class TopicsFragment : DialogFragment(), TopicAdapter.ClickListener {

    override fun onResume() {
        super.onResume()
        setObservers()
        viewModel.getTopics()
    }

    private var _binding: DiplomaFragmentTopicsBinding? = null
    private val binding get() = _binding!!

    private lateinit var topicAdapter: TopicAdapter

    private val viewModel: RoadmapViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DiplomaFragmentTopicsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        topicAdapter = TopicAdapter(this)
        binding.topicsRecyclerView.adapter = topicAdapter

        binding.courseName.text = CHOSEN_ROADMAP
        binding.sectionName.text = CHOSEN_SECTION
    }

    private fun setObservers() {
        viewModel.loadLiveData.observe(viewLifecycleOwner, loadObserver)
        viewModel.topicsLiveData.observe(viewLifecycleOwner, topicObserver)
    }

    private val topicObserver = Observer<List<Topic>> {
        topicAdapter.setList(it)
    }

    private val loadObserver = Observer<Boolean> {
        if (it) {
            binding.load.visibility = View.VISIBLE
            binding.topicsRecyclerView.visibility = View.GONE
            binding.progressBar.visibility = View.GONE
            binding.progressText.visibility = View.GONE
        } else
            binding.load.visibility = View.GONE
        binding.topicsRecyclerView.visibility = View.VISIBLE
        binding.progressBar.visibility = View.VISIBLE
        binding.progressText.visibility = View.VISIBLE
    }

    override fun onClick(topic: Topic) {
        TOPIC_NAME = topic.name
        TOPIC_ID = topic.id
        IS_DONE = topic.is_done

        val dialog = MaterialFragment()
        dialog.setListener {
            viewModel.getTopics()
        }

        dialog.show(parentFragmentManager, dialog::class.qualifiedName)
    }
}