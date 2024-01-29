package com.example.medicalprescriptiontracker.Application.UseCases.SignInUseCase

import com.example.medicalprescriptiontracker.Domain.Entities.User

/**
 * Interface defining the use case for signing in with email and password.
 */
interface SignInWithEmailAndPasswordUseCase {

    /**
     * Asynchronously signs in the user with the provided email and password.
     *
     * @param email The user's email address.
     * @param password The user's password.
     * @return A [User] object representing the signed-in user if successful, or null otherwise.
     */
    suspend fun signIn(email: String, password: String): User?
}