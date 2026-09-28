package com.saurav.cinemax.domain.usecase

import com.saurav.cinemax.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<Unit>{
        return authRepository.login(email,password)
    }
}