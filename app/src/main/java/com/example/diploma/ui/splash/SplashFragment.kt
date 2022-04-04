package com.example.diploma.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.example.diploma.R

class SplashFragment: Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed({
            val prefs = PreferenceManager.getDefaultSharedPreferences(this.requireContext())
            val userPref = prefs.getString("", "")
            if (userPref.isNullOrEmpty()) {
                findNavController().navigate(R.id.action_splash_to_login)
            } else {
                findNavController().navigate(R.id.action_splash_to_container)
            }
        }, 3000)
    }

}