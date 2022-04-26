package com.example.diploma.ui.splash

import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.diploma.R
import com.example.diploma.databinding.DiplomaFragmentSplashBinding
import org.koin.android.ext.android.inject


class SplashFragment: Fragment() {

    private var _binding: DiplomaFragmentSplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DiplomaFragmentSplashBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed({
            val prefs: SharedPreferences by inject()
            val token = prefs.getString(getString(R.string.auth_token), null)
            if (token.isNullOrEmpty()) {
                findNavController().navigate(R.id.action_splash_to_login)
            } else {
                findNavController().navigate(R.id.action_splash_to_container)
            }
        }, 3000)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}