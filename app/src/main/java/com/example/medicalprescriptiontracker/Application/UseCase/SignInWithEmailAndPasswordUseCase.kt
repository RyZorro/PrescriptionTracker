package com.example.medicalprescriptiontracker.Application.UseCase

import com.example.medicalprescriptiontracker.User

/**
 * This interface defines the contract for the use case of signing in a user.
 */
interface SignInWithEmailAndPasswordUseCase {
    suspend fun signIn(email: String, password: String): User?
}