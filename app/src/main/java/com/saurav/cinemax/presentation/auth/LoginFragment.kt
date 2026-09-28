package com.saurav.cinemax.presentation.auth

import android.os.Bundle
import android.view.View
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.saurav.cinemax.R
import com.saurav.cinemax.domain.usecase.IsUserLoggedInUseCase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {

    @Inject
    lateinit var isUserLoggedInUseCase: IsUserLoggedInUseCase

    override fun onCreateView(
        inflater: android.view.LayoutInflater,
        container: android.view.ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        if (isUserLoggedInUseCase()) {
            findNavController().navigate(
                R.id.action_loginFragment_to_mainFragment
            )
            return View(requireContext())
        }

        return ComposeView(requireContext()).apply {

            setContent {

                LoginScreen(
                    onLoginSuccess = {
                        findNavController().navigate(
                            R.id.action_loginFragment_to_mainFragment,
                            null,
                            androidx.navigation.NavOptions.Builder()
                                .setPopUpTo(
                                    R.id.loginFragment,
                                    true
                                )
                                .build()
                        )
                    },
                    onSignUpClick = {
                        findNavController().navigate(
                            R.id.action_loginFragment_to_signupFragment,
                            null,
                            androidx.navigation.NavOptions.Builder()
                                .setPopUpTo(
                                    R.id.loginFragment,
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