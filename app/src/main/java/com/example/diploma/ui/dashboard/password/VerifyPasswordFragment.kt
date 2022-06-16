package com.example.diploma.ui.dashboard.password

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import com.example.diploma.R
import com.example.diploma.databinding.DiplomaFragmentVerifyCodeBinding

class VerifyPasswordFragment(
    private val option: String,
    private val value: String
) : DialogFragment() {

    private var _binding: DiplomaFragmentVerifyCodeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.FullScreenDialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DiplomaFragmentVerifyCodeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.topPanel.backButton.setOnClickListener { dismiss() }

        val code = generateCode()

        Log.d("OPTION", option)

        if (option == "sms")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                sendSms(code, value)
            } else
                sendEmail(code, value)
    }

    private fun generateCode(): String {
        val firstNumber = (0..9).random()
        val secondNumber = (0..9).random()
        val thirdNumber = (0..9).random()
        val fourthNumber = (0..9).random()

        return "$firstNumber$secondNumber$thirdNumber$fourthNumber"
    }

    private fun sendEmail(code: String, email: String) {

    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun sendSms(code: String, phone: String) {
        try {
            Toast.makeText(
                requireContext(), "Message Sent",
                Toast.LENGTH_SHORT
            ).show();
        } catch (ex: Exception) {
            Toast.makeText(
                requireContext(), ex.message.toString(),
                Toast.LENGTH_LONG
            ).show();
            ex.printStackTrace();
        }
    }

}