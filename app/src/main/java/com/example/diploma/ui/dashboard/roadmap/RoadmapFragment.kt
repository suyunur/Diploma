package com.example.diploma.ui.dashboard.roadmap

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.diploma.R
import com.example.diploma.data.CHOSEN_ROADMAP
import com.example.diploma.data.LAST_PAGE
import com.example.diploma.data.ROADMAP_ID
import com.example.diploma.data.model.Roadmap
import com.example.diploma.databinding.DiplomaFragmentRoadmapBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class RoadmapFragment : Fragment() {

    private var _binding: DiplomaFragmentRoadmapBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RoadmapViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DiplomaFragmentRoadmapBinding.inflate(inflater, container, false)

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.roadmapLiveData.observe(viewLifecycleOwner, roadmapObserver)
        viewModel.getRoadmaps()

        binding.topPanel.title.text = "Roadmap page"

        LAST_PAGE = 4

        fillRoadmapLayouts()
    }

    private val roadmapObserver = Observer<List<Roadmap>> {
        it.map { roadmap ->
            when (roadmap.name) {
                "Back-end" -> {
                    binding.backendRoadmap.root.setOnClickListener {
                        CHOSEN_ROADMAP = "Backend"
                        ROADMAP_ID = 1
                        findNavController().navigate(R.id.action_container_to_tech)
                    }
                }
                "Front-end" -> {
                    binding.frontEndRoadmap.root.setOnClickListener {
                        CHOSEN_ROADMAP = "Frontend"
                        ROADMAP_ID = 2
                        findNavController().navigate(R.id.action_container_to_tech)
                    }
                }
                "Android" -> {
                    binding.androidRoadmap.root.setOnClickListener {
                        CHOSEN_ROADMAP = "Android"
                        ROADMAP_ID = 3
                        findNavController().navigate(R.id.action_container_to_tech)
                    }
                }
                "Ios" -> {
                    binding.iosRoadmap.root.setOnClickListener {
                        CHOSEN_ROADMAP = "IOS"
                        ROADMAP_ID = 4
                        findNavController().navigate(R.id.action_container_to_tech)
                    }
                }
                "QA" -> {
                    binding.qaRoadmap.root.setOnClickListener {
                        CHOSEN_ROADMAP = "QA"
                        ROADMAP_ID = 5
                        findNavController().navigate(R.id.action_container_to_tech)
                    }
                }
                "Network" -> {
                    binding.networkRoadmap.root.setOnClickListener {
                        CHOSEN_ROADMAP = "Network"
                        ROADMAP_ID = 6
                        findNavController().navigate(R.id.action_container_to_tech)
                    }
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun fillRoadmapLayouts() {
        binding.frontEndRoadmap.roadmapImage.setImageResource(R.mipmap.ic_frontend)
        binding.backendRoadmap.roadmapImage.setImageResource(R.mipmap.ic_backend)
        binding.androidRoadmap.roadmapImage.setImageResource(R.mipmap.ic_android)
        binding.iosRoadmap.roadmapImage.setImageResource(R.mipmap.ic_ios)
        binding.networkRoadmap.roadmapImage.setImageResource(R.mipmap.ic_network)
        binding.qaRoadmap.roadmapImage.setImageResource(R.mipmap.ic_qa)

        binding.frontEndRoadmap.roadmapText.text = "Frontend"
        binding.backendRoadmap.roadmapText.text = "Backend"
        binding.androidRoadmap.roadmapText.text = "Android"
        binding.iosRoadmap.roadmapText.text = "IOS"
        binding.networkRoadmap.roadmapText.text = "Network"
        binding.qaRoadmap.roadmapText.text = "QA"
    }
}