package com.example.diploma.ui.dashboard.roadmap

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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

    }

    private fun fillRoadmapLayouts() {

    }

}