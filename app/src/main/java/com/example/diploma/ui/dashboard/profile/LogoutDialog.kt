package com.example.diploma.ui.dashboard.profile

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.diploma.R
import com.example.diploma.databinding.LogoutDialogBinding

class LogoutDialog : DialogFragment() {

    private var _binding: LogoutDialogBinding? = null
    private val binding get() = _binding!!

    private lateinit var listener: LogoutListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LogoutDialogBinding.inflate(inflater, container, false)
        setStyle(STYLE_NORMAL, R.style.TransparentDialog)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        return binding.root
    }

    fun interface LogoutListener {
        fun onLeaveClicked()
    }

    fun setListener(listener: LogoutListener) {
        this.listener = listener
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cancelButton.setOnClickListener { dismiss() }
        binding.leaveButton.setOnClickListener { listener.onLeaveClicked() }
    }

}