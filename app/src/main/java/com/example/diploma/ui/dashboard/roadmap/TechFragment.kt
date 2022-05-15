package com.example.diploma.ui.dashboard.roadmap

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.diploma.R
import com.example.diploma.databinding.DiplomaLayoutTechnologiesBinding

class TechFragment: Fragment() {

    private var _binding: DiplomaLayoutTechnologiesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DiplomaLayoutTechnologiesBinding.inflate(inflater, container, false)

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.topPanel.title.text = "Backend"

        fillTechLayouts()
    }

    @SuppressLint("SetTextI18n")
    private fun fillTechLayouts() {
        binding.djangoTech.roadmapImage.setImageResource(R.mipmap.ic_django)
        binding.djangoTech.roadmapText.text = "Django"

        binding.goTech.roadmapImage.setImageResource(R.mipmap.ic_golang)
        binding.goTech.roadmapText.text = "GO lang"

        binding.djangoTech.root.setOnClickListener {
            val dialog = TopicsFragment.newInstance()
            dialog.show(parentFragmentManager, dialog::class.qualifiedName)
        }
    }

}