package com.example.diploma.ui.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.diploma.R
import com.example.diploma.data.responseBody.AuthResponse
import com.example.diploma.databinding.DiplomaFragmentLoginBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel


@DelicateCoroutinesApi
class LoginFragment : Fragment() {

    private var _binding: DiplomaFragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AuthViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DiplomaFragmentLoginBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.topBar.title.text = context?.getString(R.string.login)

        setUpObservers()

        setUpClickListeners()

    }

    private fun setUpObservers() {
        viewModel.loadLiveData.observe(viewLifecycleOwner, loadObserver)
        viewModel.authLiveData.observe(viewLifecycleOwner, tokenObserver)
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

    private val tokenObserver = Observer<AuthResponse?> {
        if (it.access != null) {
            viewModel.saveAuthResponse(it)
            openContainerFragment()
            binding.errorLayout.visibility = View.VISIBLE
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
        findNavController().navigate(R.id.action_login_to_register)
    }

    private fun openContainerFragment() {
        findNavController().navigate(R.id.action_login_to_container)
    }

    private fun hideErrorTexts() {
        binding.emailErrorText.visibility = View.GONE
        binding.passwordErrorText.visibility = View.GONE
        binding.errorLayout.visibility = View.GONE
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}