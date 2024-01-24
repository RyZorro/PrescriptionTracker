package com.example.medicalprescriptiontracker

/**
 * This interface defines the contract for the use case of signing in a user.
 */
interface SignInWithEmailAndPasswordUseCase {
    suspend fun signIn(email: String, password: String): User?
}