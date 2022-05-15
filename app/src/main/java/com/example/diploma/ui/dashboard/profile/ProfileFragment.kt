package com.example.diploma.ui.dashboard.profile

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import com.example.diploma.R
import com.example.diploma.data.SKILL
import com.example.diploma.data.SKILLS
import com.example.diploma.databinding.DiplomaFragmentProfileBinding
import org.koin.android.ext.android.inject


class ProfileFragment: Fragment() {

    private var _binding: DiplomaFragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val sharedPreferences: SharedPreferences by inject()

    private lateinit var studyAdapter: StudyAdapter

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DiplomaFragmentProfileBinding.inflate(inflater, container, false)

        binding.topPanel.title.text = "My Profile"
        binding.topPanel.title.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
        binding.topPanel.rightButton.visibility = View.VISIBLE
        binding.topPanel.rightButton.setImageResource(R.drawable.ic_logout)
        binding.topPanel.rightButton.setOnClickListener {
            logout()
        }

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        studyAdapter = StudyAdapter()
        binding.progressRecyclerView.adapter = studyAdapter

        if (!SKILL.isNullOrEmpty() && !SKILLS.isNullOrEmpty()) {
            binding.skills.visibility = View.VISIBLE
            binding.skill1.skillName.text = "Python"
            binding.skill2.skillName.text = "Django"
            binding.progressRecyclerView.visibility = View.VISIBLE
            studyAdapter.setList(listOf("12"))
        }
    }

    private fun logout() {
        sharedPreferences.edit().remove(context?.getString(R.string.auth_token)).apply()
        sharedPreferences.edit().remove(context?.getString(R.string.user_id)).apply()
        findNavController().navigate(R.id.action_container_to_login)
        parentFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

}