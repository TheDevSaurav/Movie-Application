package com.saurav.cinemax.domain.usecase

import com.saurav.cinemax.domain.repository.AuthRepository
import javax.inject.Inject

class IsUserLoggedInUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    operator fun invoke(): Boolean {
        return authRepository.isUserLoggedIn()
    }
}