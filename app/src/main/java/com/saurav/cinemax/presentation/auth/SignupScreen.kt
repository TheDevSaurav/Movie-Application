package com.saurav.cinemax.presentation.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.saurav.cinemax.R

private val CinemaxYellow = Color(0xFFFFD21F)
private val CinemaxTextSecondary = Color(0xFFB8B8C7)
private val GlassColor = Color.White.copy(alpha = 0.10f)
private val GlassBorder = Color.White.copy(alpha = 0.20f)

@Composable
fun SignupScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    onSignupSuccess: () -> Unit = {},
    onLoginClick: () -> Unit = {}
) {

    val uiState by viewModel.uiState.collectAsState()
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()

    LaunchedEffect(uiState) {
        if (uiState is AuthViewModel.UiState.Success) {
            onSignupSuccess()
        }
    }

    SignupContent(
        uiState = uiState,
        email = email,
        password = password,
        onEmailChange = {
            viewModel.handleUiEvent(
                AuthViewModel.UiAction.EmailChanged(it)
            )
        },
        onPasswordChange = {
            viewModel.handleUiEvent(
                AuthViewModel.UiAction.PasswordChanged(it)
            )
        },
        onSignupClick = {
            viewModel.handleUiEvent(
                AuthViewModel.UiAction.SignUpClicked
            )
        },
        onLoginClick = onLoginClick
    )
}

@Composable
fun SignupContent(
    uiState: AuthViewModel.UiState,
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignupClick: () -> Unit,
    onLoginClick: () -> Unit
) {

    var passwordVisible by remember {
        mutableStateOf(false)
    }

    var confirmPasswordVisible by remember {
        mutableStateOf(false)
    }

    var confirmPassword by remember {
        mutableStateOf("")
    }

    var showPasswordError by remember {
        mutableStateOf(false)
    }

    val composition = rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            R.raw.login_signup_lottie
        )
    )

    val progress = animateLottieCompositionAsState(
        composition = composition.value,
        iterations = LottieConstants.IterateForever
    )

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        // Background
        Image(
            painter = painterResource(
                id = R.drawable.cinemax_bg
            ),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Dark overlay
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Color.Black.copy(alpha = 0.45f)
                )
        )

        // Signup content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 24.dp,
                    end = 24.dp,
                    top = 48.dp,
                    bottom = 24.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            // Lottie
            LottieAnimation(
                composition = composition.value,
                progress = { progress.value },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            // Title
            Text(
                text = "Create Account",
                color = Color.White,
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            // Subtitle
            Text(
                text = "Create your account to start watching",
                color = CinemaxTextSecondary,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            // Email
            OutlinedTextField(
                value = email,
                onValueChange = onEmailChange,
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                placeholder = {
                    Text(
                        text = "Email",
                        color = CinemaxTextSecondary
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Email",
                        tint = CinemaxYellow
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedBorderColor = CinemaxYellow,
                    unfocusedBorderColor = GlassBorder,
                    focusedContainerColor = GlassColor,
                    unfocusedContainerColor = GlassColor,
                    cursorColor = CinemaxYellow
                )
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            // Password
            OutlinedTextField(
                value = password,
                onValueChange = {
                    onPasswordChange(it)
                    showPasswordError = false
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                placeholder = {
                    Text(
                        text = "Password",
                        color = CinemaxTextSecondary
                    )
                },
                visualTransformation = if (passwordVisible) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            passwordVisible = !passwordVisible
                        }
                    ) {
                        Icon(
                            imageVector = if (passwordVisible) {
                                Icons.Default.VisibilityOff
                            } else {
                                Icons.Default.Visibility
                            },
                            contentDescription = if (passwordVisible) {
                                "Hide password"
                            } else {
                                "Show password"
                            },
                            tint = CinemaxYellow
                        )
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedBorderColor = CinemaxYellow,
                    unfocusedBorderColor = GlassBorder,
                    focusedContainerColor = GlassColor,
                    unfocusedContainerColor = GlassColor,
                    cursorColor = CinemaxYellow
                )
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            // Confirm Password
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = {
                    confirmPassword = it
                    showPasswordError = false
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                placeholder = {
                    Text(
                        text = "Confirm Password",
                        color = CinemaxTextSecondary
                    )
                },
                visualTransformation = if (confirmPasswordVisible) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            confirmPasswordVisible = !confirmPasswordVisible
                        }
                    ) {
                        Icon(
                            imageVector = if (confirmPasswordVisible) {
                                Icons.Default.VisibilityOff
                            } else {
                                Icons.Default.Visibility
                            },
                            contentDescription = if (confirmPasswordVisible) {
                                "Hide password"
                            } else {
                                "Show password"
                            },
                            tint = CinemaxYellow
                        )
                    }
                },
                isError = showPasswordError,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedBorderColor = if (showPasswordError) {
                        Color.Red
                    } else {
                        CinemaxYellow
                    },
                    unfocusedBorderColor = if (showPasswordError) {
                        Color.Red
                    } else {
                        GlassBorder
                    },
                    focusedContainerColor = GlassColor,
                    unfocusedContainerColor = GlassColor,
                    cursorColor = CinemaxYellow
                )
            )

            // Password mismatch error
            if (showPasswordError) {

                Spacer(
                    modifier = Modifier.height(6.dp)
                )

                Text(
                    text = "Passwords do not match",
                    color = Color(0xFFFF6B6B),
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            // Create Account button
            Button(
                onClick = {

                    if (password == confirmPassword) {
                        showPasswordError = false
                        onSignupClick()
                    } else {
                        showPasswordError = true
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                enabled = uiState !is AuthViewModel.UiState.Loading,
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = CinemaxYellow,
                    contentColor = Color.Black
                )
            ) {

                if (uiState is AuthViewModel.UiState.Loading) {

                    CircularProgressIndicator(
                        modifier = Modifier
                            .width(22.dp)
                            .height(22.dp),
                        color = Color.Black,
                        strokeWidth = 2.dp
                    )

                } else {

                    Text(
                        text = "Create Account",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }

            // Firebase error
            if (uiState is AuthViewModel.UiState.Error) {

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = uiState.message,
                    color = Color(0xFFFF6B6B),
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            // Login
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Already have an account?",
                    color = CinemaxTextSecondary
                )

                TextButton(
                    onClick = onLoginClick
                ) {

                    Text(
                        text = "Login",
                        color = CinemaxYellow
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignupScreenPreview() {

    SignupContent(
        uiState = AuthViewModel.UiState.Idle,
        email = "",
        password = "",
        onEmailChange = {},
        onPasswordChange = {},
        onSignupClick = {},
        onLoginClick = {}
    )
}