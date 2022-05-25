package com.example.diploma.ui.dashboard.roadmap

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import com.example.diploma.R
import com.example.diploma.data.TOPIC_ID
import com.example.diploma.data.model.Material
import com.example.diploma.databinding.DiplomaStudyMaterialBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MaterialFragment: DialogFragment(), MaterialAdapter.ClickListener{

    companion object {
        fun newInstance(): MaterialFragment {
            return MaterialFragment()
        }
    }

    private var _binding: DiplomaStudyMaterialBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: MaterialAdapter

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
        _binding = DiplomaStudyMaterialBinding.inflate(inflater, container, false)

        adapter = MaterialAdapter(this)
        binding.linksRecyclerView.adapter = adapter

        viewModel.getMaterial()

        fillMaterials()

        setUpObservers()

        return binding.root
    }

    private fun fillMaterials() {
        binding.backButton.setOnClickListener {
            dismiss()
        }
    }

    private fun setUpObservers() {
        viewModel.materialLiveData.observe(viewLifecycleOwner, materialObserver)
        viewModel.loadLiveData.observe(viewLifecycleOwner, loadObserver)
    }

    private val loadObserver = Observer<Boolean> {
        if (it) {
            binding.content.visibility = View.GONE
            binding.bar.visibility = View.VISIBLE
        } else {
            binding.content.visibility = View.VISIBLE
            binding.bar.visibility = View.GONE
        }
    }

    @SuppressLint("SetTextI18n")
    private val materialObserver = Observer<Material> {
        binding.chapterTitle.text = it.name
        binding.chapterText.text = it.description
        binding.chapterNum.text = "Chapter ${TOPIC_ID!!}"
        adapter.setList(it.subtopic)
    }

    private fun openText(link: String) {
        val uri = Uri.parse(link)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    private fun openVideo(link: String) {
        val uri = Uri.parse(link)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    override fun onClick(link: String, type: String) {
        if (type == "READ")
            openText(link)
        else openVideo(link)
    }

}