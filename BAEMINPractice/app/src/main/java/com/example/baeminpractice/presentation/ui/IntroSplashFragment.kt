package com.example.baeminpractice.presentation.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.baeminpractice.R
import com.example.baeminpractice.presentation.utils.REQUEST_KEY_PERMISSION_CHECK_READ_EXTERNAL_STORAGE
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class IntroSplashFragment : Fragment(R.layout.fragment_intro_splash) {
    private val neededPermission = Manifest.permission.READ_EXTERNAL_STORAGE

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            delay(1000)
            checkPermissions()
            listenToFragmentResultListeners()
        }
    }

    private fun checkPermissions() {
        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(
                requireContext(),
                neededPermission
            ) -> navigateToIntroTermAgreementScreen()
            else -> navigateToCheckPermissionDialogScreen()
        }
    }

    private fun listenToFragmentResultListeners() {
        setFragmentResultListener(requestKey = REQUEST_KEY_PERMISSION_CHECK_READ_EXTERNAL_STORAGE) { requestKey, _ ->
            require(requestKey == REQUEST_KEY_PERMISSION_CHECK_READ_EXTERNAL_STORAGE)
            navigateToIntroTermAgreementScreen()
        }
    }

    private fun navigateToIntroTermAgreementScreen() {
        findNavController().navigate(resId = R.id.action_introSplashFragment_to_introTermAgreementFragment)
    }

    private fun navigateToCheckPermissionDialogScreen() {
        findNavController().navigate(resId = R.id.action_introSplashFragment_to_checkPermissionsDialogFragment)
    }
}