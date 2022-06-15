package com.example.diploma.ui.dashboard.roadmap

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.diploma.data.CHOSEN_ROADMAP
import com.example.diploma.data.CHOSEN_SECTION
import com.example.diploma.data.TECHNOLOGY_ID
import com.example.diploma.data.TECH_IMAGE_URL
import com.example.diploma.data.model.Technology
import com.example.diploma.databinding.DiplomaLayoutTechnologiesBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class TechFragment : Fragment(), TechAdapter.ClickListener {

    private var _binding: DiplomaLayoutTechnologiesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RoadmapViewModel by viewModel()

    private lateinit var techAdapter: TechAdapter

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

        binding.topPanel.title.text = CHOSEN_ROADMAP

        techAdapter = TechAdapter(this)
        binding.techRecycler.adapter = techAdapter

        setObservers()

        viewModel.getTechs()
    }

    private fun setObservers() {
        viewModel.techsLiveData.observe(viewLifecycleOwner, techsObserver)
        viewModel.loadLiveData.observe(viewLifecycleOwner, loadObserver)
    }

    private val techsObserver = Observer<List<Technology>> {
        techAdapter.setList(it)
    }

    private val loadObserver = Observer<Boolean> {
        if (it) {
            binding.bar.visibility = View.VISIBLE
            binding.techRecycler.visibility = View.GONE
        } else {
            binding.bar.visibility = View.GONE
            binding.techRecycler.visibility = View.VISIBLE
        }
    }

    override fun onClick(tech: Technology) {
        TECHNOLOGY_ID = tech.id
        CHOSEN_SECTION = tech.name
        TECH_IMAGE_URL = tech.image_url
        val dialog = TopicsFragment()
        dialog.show(parentFragmentManager, dialog::class.qualifiedName)
    }

}