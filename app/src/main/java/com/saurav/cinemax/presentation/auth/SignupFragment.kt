package com.saurav.cinemax.presentation.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.saurav.cinemax.R

class SignupFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return ComposeView(requireContext()).apply {

            setContent {

                SignupScreen(
                    onSignupSuccess = {
                        findNavController().navigate(
                            R.id.action_signupFragment_to_mainFragment,
                            null,
                            androidx.navigation.NavOptions.Builder()
                                .setPopUpTo(
                                    R.id.signupFragment,
                                    true
                                )
                                .build()
                        )
                    },
                    onLoginClick = {
                        findNavController().navigate(
                            R.id.action_signupFragment_to_loginFragment,
                            null,
                            androidx.navigation.NavOptions.Builder()
                                .setPopUpTo(
                                    R.id.signupFragment,
                                    true
                                )
                                .build()
                        )
                    }
                )
            }
        }
    }
}