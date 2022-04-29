package com.example.diploma.ui.dashboard.roadmap

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.diploma.R
import com.example.diploma.data.CHOSEN_ROADMAP
import com.example.diploma.databinding.DiplomaFragmentRoadmapBinding


class RoadmapFragment: Fragment() {

    private var _binding: DiplomaFragmentRoadmapBinding? = null
    private val binding get() = _binding!!

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

        binding.topPanel.title.text = "Roadmap page"

        fillRoadmapLayouts()

        setClickListeners()

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

    private fun setClickListeners() {
        binding.frontEndRoadmap.root.setOnClickListener {
            CHOSEN_ROADMAP = "Frontend"
        }
        binding.backendRoadmap.root.setOnClickListener {
            CHOSEN_ROADMAP = "Backend"
        }
        binding.androidRoadmap.root.setOnClickListener {
            CHOSEN_ROADMAP = "Android"
        }
        binding.iosRoadmap.root.setOnClickListener {
            CHOSEN_ROADMAP = "IOS"
        }
        binding.networkRoadmap.root.setOnClickListener {
            CHOSEN_ROADMAP = "Network"
        }
        binding.qaRoadmap.root.setOnClickListener {
            CHOSEN_ROADMAP = "QA"
        }
        findNavController().navigate(R.id.action_container_to_tech)
    }

}