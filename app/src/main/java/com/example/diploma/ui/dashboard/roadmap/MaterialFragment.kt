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
import com.bumptech.glide.Glide
import com.example.diploma.R
import com.example.diploma.data.TOPIC_ID
import com.example.diploma.data.model.Material
import com.example.diploma.databinding.DiplomaStudyMaterialBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

open class MaterialFragment : DialogFragment(), MaterialAdapter.ClickListener {

    private var _binding: DiplomaStudyMaterialBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: MaterialAdapter

    private val viewModel: RoadmapViewModel by viewModel()

    private lateinit var listener: DismissListener

    fun interface DismissListener {
        fun onDismissed()
    }

    fun setListener(listener: DismissListener) {
        this.listener = listener
    }

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

        binding.nextButton.setOnClickListener {
            onDoneClicked()
        }

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

        Glide
            .with(binding.materialImageView.context)
            .load(it.image_url)
            .centerCrop()
            .into(binding.materialImageView)
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

    private fun onDoneClicked() {
        viewModel.doneTopic()
        listener.onDismissed()
        dismiss()
    }

    override fun onClick(link: String, type: String) {
        if (type == "READ")
            openText(link)
        else openVideo(link)
    }
}