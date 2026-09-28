package com.saurav.cinemax.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saurav.cinemax.domain.usecase.IsUserLoggedInUseCase
import com.saurav.cinemax.domain.usecase.LoginUseCase
import com.saurav.cinemax.domain.usecase.LogoutUseCase
import com.saurav.cinemax.domain.usecase.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val loginUseCase: LoginUseCase,
    private val isUserLoggedInUseCase: IsUserLoggedInUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    sealed interface UiState{
        data object Idle : UiState
        data object Loading : UiState
        data object Success : UiState
        data class Error(val message: String) : UiState
    }

    sealed interface UiAction{
        data class EmailChanged(val email: String) : UiAction
        data class PasswordChanged(val password: String) : UiAction
        data object LoginClicked : UiAction
        data object SignUpClicked : UiAction
        data object LogoutClicked : UiAction
    }

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun handleUiEvent(action : UiAction){
        when(action){
            is UiAction.EmailChanged -> {
                _email.value = action.email
            }

            is UiAction.PasswordChanged ->{
                _password.value = action.password
            }

            is UiAction.LoginClicked -> {
                viewModelScope.launch {
                    _uiState.value = UiState.Loading
                    delay(1500) // Artificial delay to show the loader

                    val result = loginUseCase(
                        email = _email.value,
                        password = _password.value
                    )

                    result
                        .onSuccess {
                            _uiState.value = UiState.Success
                        }
                        .onFailure { exception ->
                            _uiState.value = UiState.Error(
                                exception.message ?: "Login failed"
                            )
                        }

                }
            }

            is UiAction.SignUpClicked -> {
                viewModelScope.launch {
                    _uiState.value = UiState.Loading
                    delay(1500) // Artificial delay to show the loader

                    val result = signUpUseCase(
                        email = _email.value,
                        password = _password.value
                    )

                    result
                        .onSuccess {
                            _uiState.value = UiState.Success
                        }
                        .onFailure { exception ->
                            _uiState.value = UiState.Error(
                                exception.message ?: "Signup failed"
                            )
                        }
                }
            }

            is UiAction.LogoutClicked -> {
                logoutUseCase()
                _uiState.value = UiState.Idle
                _email.value = ""
                _password.value = ""
            }
        }
    }
}
