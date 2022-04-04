package com.example.diploma.ui.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.diploma.R
import com.example.diploma.databinding.DiplomaFragmentLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginFragment : Fragment() {

    private lateinit var binding: DiplomaFragmentLoginBinding

    private val viewModel: AuthViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = DiplomaFragmentLoginBinding.bind(view)

        setUpObservers()

        setUpClickListeners()
    }

    private fun setUpObservers() {
        viewModel.loadLiveData.observe(viewLifecycleOwner, loadObserver)
    }

    @SuppressLint("SetTextI18n")
    private val loadObserver = Observer<Boolean> {
        if (it == true) {
            binding.loginActionButton.text = ""
            binding.loginProgressBar.visibility = View.VISIBLE
        } else {
            binding.loginActionButton.text = context?.getString(R.string.login)
            binding.loginProgressBar.visibility = View.GONE
        }
    }

    private fun setUpClickListeners() {
        binding.signActionTextView.setOnClickListener {
            openRegisterFragment()
        }

        binding.loginActionButton.setOnClickListener {
            login()
        }
    }

    private fun login() {
        hideErrorTexts()
        if (checkIfEmpty()) {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            viewModel.login(
                email = email,
                password = password
            )
        }
    }

    private fun openRegisterFragment() {
        findNavController().navigate(R.id.action_register_to_login)
    }

    private fun hideErrorTexts() {
        binding.emailErrorText.visibility = View.GONE
        binding.passwordErrorText.visibility = View.GONE
    }

    private fun checkIfEmpty(): Boolean {
        if (binding.emailEditText.text.isEmpty()) {
            binding.emailErrorText.visibility = View.VISIBLE
            return false
        }
        if (binding.passwordEditText.text.isEmpty()) {
            binding.passwordErrorText.visibility = View.VISIBLE
            return false
        }
        return true
    }
}