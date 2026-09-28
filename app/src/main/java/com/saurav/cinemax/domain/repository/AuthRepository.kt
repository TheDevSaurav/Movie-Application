package com.saurav.cinemax.domain.repository

interface AuthRepository {
    suspend fun singUp(email : String, password: String) : Result<Unit>
    suspend fun login(email : String, password: String) : Result<Unit>
    fun logout()
    fun isUserLoggedIn() : Boolean
}