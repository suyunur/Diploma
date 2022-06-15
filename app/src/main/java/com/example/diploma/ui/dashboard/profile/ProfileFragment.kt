package com.example.diploma.ui.dashboard.profile

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.diploma.R
import com.example.diploma.data.model.User
import com.example.diploma.databinding.DiplomaFragmentProfileBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {

    private var _binding: DiplomaFragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProfileViewModel by viewModel()

    private val sharedPreferences: SharedPreferences by inject()

    private lateinit var studyAdapter: StudyAdapter
    private lateinit var skillsAdapter: SkillsAdapter

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DiplomaFragmentProfileBinding.inflate(inflater, container, false)

        setObservers()

        viewModel.getUser()

        binding.topPanel.title.text = "My Profile"
        binding.topPanel.title.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
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

        skillsAdapter = SkillsAdapter()
        binding.skillsRecyclerView.adapter = skillsAdapter
    }

    private fun logout() {
        sharedPreferences.edit().remove(context?.getString(R.string.auth_token)).apply()
        sharedPreferences.edit().remove(context?.getString(R.string.user_id)).apply()
        findNavController().navigate(R.id.action_container_to_login)
    }

    private fun setObservers() {
        viewModel.loadLiveData.observe(viewLifecycleOwner, loadObserver)
        viewModel.userLiveData.observe(viewLifecycleOwner, profileObserver)
    }

    @SuppressLint("SetTextI18n")
    private val profileObserver = Observer<User> {
        binding.userName.text = "${it.first_name} ${it.last_name}"
        it.user_studies?.let { it1 -> studyAdapter.setList(it1) }
        it.skills?.let { it1 -> skillsAdapter.setList(it1) }
    }

    private val loadObserver = Observer<Boolean> {
        if (it) {
            binding.progressBar.visibility = View.VISIBLE
            binding.contentLayout.visibility = View.GONE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.contentLayout.visibility = View.VISIBLE
        }
    }

}