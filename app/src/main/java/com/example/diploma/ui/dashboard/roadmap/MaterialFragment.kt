package com.example.diploma.ui.dashboard.roadmap

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.diploma.R
import com.example.diploma.data.PROGRESS
import com.example.diploma.data.SKILL
import com.example.diploma.data.SKILLS
import com.example.diploma.databinding.DiplomaStudyMaterialBinding
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.net.URI

class MaterialFragment: DialogFragment() {

    companion object {
        fun newInstance(): MaterialFragment {
            return MaterialFragment()
        }
    }

    private var _binding: DiplomaStudyMaterialBinding? = null
    private val binding get() = _binding!!

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

        fillMaterials()

        return binding.root
    }

    private fun fillMaterials() {
        binding.nextButton.setOnClickListener {
            dismiss()
            PROGRESS = "12"
            SKILL = "Python"
            SKILLS = "Django"
        }

        binding.textMaterial.linkTypeImage.setImageResource(R.drawable.ic_link_read)
        binding.textMaterial.root.setOnClickListener { openText() }

        binding.videoMaterial.linkTypeImage.setImageResource(R.drawable.ic_link_video)
        binding.videoMaterial.root.setOnClickListener { openVideo() }
    }

    private fun openText() {
        val uri = Uri.parse("https://highload.today/django-orm/")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    private fun openVideo() {
        val uri = Uri.parse("https://youtu.be/SF5G-6yeND4")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

}