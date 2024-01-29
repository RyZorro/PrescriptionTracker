package com.example.medicalprescriptiontracker.Application.UseCases.SignInUseCase

import com.example.medicalprescriptiontracker.Domain.Entities.User
import com.example.medicalprescriptiontracker.Infrastructure.Repositories.FirebaseAuthenticationRepository

/**
 * Implementation of the SignInWithEmailAndPasswordUseCase interface.
 * Handles the business logic for signing in with email and password.
 *
 * @param repository The repository responsible for interacting with Firebase authentication.
 */
class SignInWithEmailAndPasswordUseCaseImpl(
    private val repository: FirebaseAuthenticationRepository
) : SignInWithEmailAndPasswordUseCase {

    /**
     * Asynchronously signs in the user with the provided email and password.
     *
     * @param email The user's email address.
     * @param password The user's password.
     * @return A [User] object representing the signed-in user if successful, or null otherwise.
     */
    override suspend fun signIn(email: String, password: String): User? {
        return try {
            // Delegate the actual sign-in operation to the repository
            repository.signInWithEmailAndPassword(email, password)
        } catch (e: Exception) {
            // Handle exceptions, returning null in case of any errors
            null
        }
    }
}