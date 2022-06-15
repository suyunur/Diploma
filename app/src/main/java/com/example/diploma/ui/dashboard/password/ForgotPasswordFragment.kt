package com.example.diploma.ui.dashboard.password

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.diploma.databinding.DiplomaFragmentForgotPasswordBinding
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
class ForgotPasswordFragment : Fragment() {

    private var _binding: DiplomaFragmentForgotPasswordBinding? = null
    private val binding get() = _binding!!

    private var chosenOption: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DiplomaFragmentForgotPasswordBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        DrawableCompat.setTint(
            DrawableCompat.wrap(binding.title.root.background),
            Color.WHITE
        )

        binding.title.backButton.setOnClickListener { findNavController().popBackStack() }

        binding.continueButton.setOnClickListener {
            if (chosenOption == "sms") {
                resetWithPhone(binding.phoneNumber.text.toString())
            } else if (chosenOption == "email") {
                resetWithEmail(binding.email.text.toString())
            }
        }

        binding.smsContactDetail.setOnClickListener {
            val drawable = binding.smsContactDetail.background as GradientDrawable
            drawable.setStroke(3, Color.BLUE)
            chosenOption = "sms"

            val secondDrawable = binding.emailContactDetail.background as GradientDrawable
            secondDrawable.setStroke(2, Color.WHITE)
        }

        binding.emailContactDetail.setOnClickListener {
            val drawable = binding.emailContactDetail.background as GradientDrawable
            drawable.setStroke(3, Color.BLUE)
            chosenOption = "email"

            val secondDrawable = binding.smsContactDetail.background as GradientDrawable
            secondDrawable.setStroke(2, Color.WHITE)
        }
    }

    private fun resetWithPhone(phoneNumber: String) {
        val fragment = VerifyPasswordFragment("sms", phoneNumber)
        fragment.show(parentFragmentManager, fragment::class.qualifiedName)
    }

    private fun resetWithEmail(email: String) {
        val fragment = VerifyPasswordFragment("email", email)
        fragment.show(parentFragmentManager, fragment::class.qualifiedName)
    }
}