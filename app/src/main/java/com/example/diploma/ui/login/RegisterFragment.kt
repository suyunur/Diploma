package com.example.diploma.ui.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.diploma.R
import com.example.diploma.databinding.DiplomaFragmentRegisterBinding
import kotlinx.coroutines.DelicateCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel


@DelicateCoroutinesApi
class RegisterFragment: Fragment() {

    private var _binding: DiplomaFragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AuthViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DiplomaFragmentRegisterBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.topBar.title.text = context?.getString(R.string.sign_up)

        setUpObservers()
        setUpClickListeners()
    }

    @SuppressLint("SetTextI18n")
    private val loadObserver = Observer<Boolean> {
        if (it == true) {
            binding.signUpButton.text = ""
            binding.signProgressBar.visibility = View.VISIBLE
        } else {
            binding.signUpButton.text = context?.getString(R.string.sign_up)
        }
    }

    private fun setUpObservers() {
        viewModel.loadLiveData.observe(viewLifecycleOwner, loadObserver)
    }

    private fun setUpClickListeners() {
        binding.signUpButton.setOnClickListener {
            checkIfEmpty()
        }

        binding.seePasswordButton.setOnClickListener {
            changePasswordVisibility()
        }

        binding.seePasswordConfirm.setOnClickListener {
           changeConfirmVisibility()
        }

        binding.signActionTextView.setOnClickListener {
            openLoginFragment()
        }
    }

    private fun register() {
        val name = binding.nameEditText.text.toString()
        val surname = binding.surnameEditText.text.toString()
        val email = binding.emailEditText.text.toString()
        val phone = binding.phoneNumberEditText.text.toString()
        val password = binding.passwordEditText.text.toString()

        viewModel.register(
            name = name, surname = surname, email = email,
            phoneNumber = phone, password = password
        )
    }

    private fun checkIfEmpty() {
        if (binding.nameEditText.text.isEmpty()) {
            binding.nameErrorText.visibility = View.VISIBLE
        }
        if (binding.surnameEditText.text.isEmpty()) {
            binding.surnameErrorText.visibility = View.VISIBLE
        }
        if (binding.emailEditText.text.isEmpty()) {
            binding.emailErrorText.visibility = View.VISIBLE
        }
        if (binding.phoneNumberEditText.text.isEmpty()) {
            binding.phoneNumberErrorText.visibility = View.VISIBLE
        }
        if (binding.passwordEditText.text.isEmpty()) {
            binding.passwordErrorText.visibility = View.VISIBLE
        }
        if (binding.confirmPasswordEditText.text.isEmpty()) {
            binding.confirmErrorText.visibility = View.VISIBLE
        }

        else {
            hideErrorTexts()
            if (passwordsMatch()) register()
        }
    }

    private fun agreementChecked(): Boolean {
        return if (binding.agreementCheckbox.isChecked) {
            true
        } else {
            binding.agreementCheckbox.isChecked
            false
        }
    }

    private fun passwordsMatch(): Boolean {
        return if (binding.passwordEditText.text.toString() ==
            binding.confirmPasswordEditText.text.toString()) {
            true
        } else {
            binding.confirmErrorText.visibility = View.VISIBLE
            false
        }
    }

    private fun hideErrorTexts() {
        binding.nameErrorText.visibility = View.GONE
        binding.surnameErrorText.visibility = View.GONE
        binding.emailErrorText.visibility = View.GONE
        binding.phoneNumberErrorText.visibility = View.GONE
        binding.passwordErrorText.visibility = View.GONE
        binding.confirmErrorText.visibility = View.GONE
    }

    private fun openLoginFragment() {
        findNavController().navigate(R.id.action_register_to_login)
    }

    private fun changePasswordVisibility() {
        if (binding.passwordEditText.inputType == InputType.TYPE_TEXT_VARIATION_PASSWORD) {
            binding.passwordEditText.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        } else {
            binding.passwordEditText.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
    }

    private fun changeConfirmVisibility() {
        if (binding.confirmPasswordEditText.inputType == InputType.TYPE_TEXT_VARIATION_PASSWORD) {
            binding.confirmPasswordEditText.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        } else {
            binding.confirmPasswordEditText.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
    }
}