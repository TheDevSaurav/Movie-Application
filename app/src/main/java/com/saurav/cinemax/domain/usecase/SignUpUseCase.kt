package com.saurav.cinemax.domain.usecase

import com.saurav.cinemax.domain.repository.AuthRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<Unit> {
        return authRepository.singUp(email,password)
    }
}