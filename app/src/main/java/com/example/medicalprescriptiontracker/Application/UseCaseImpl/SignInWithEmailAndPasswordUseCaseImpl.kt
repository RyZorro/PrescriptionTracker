package com.example.medicalprescriptiontracker.Application.UseCaseImpl

import com.example.medicalprescriptiontracker.Application.UseCase.SignInWithEmailAndPasswordUseCase
import com.example.medicalprescriptiontracker.Infrastructure.Repositories.FirebaseAuthenticationRepository
import com.example.medicalprescriptiontracker.User

/**
 * This implementation of the SignInUseCase interface handles the sign-in logic.
 * @param repository The repository responsible for user authentication operations.
 */
class SignInWithEmailAndPasswordUseCaseImpl(
    private val repository: FirebaseAuthenticationRepository
) : SignInWithEmailAndPasswordUseCase {

    /**
     * Attempts to sign in a user using the provided email and password.
     *
     * @param email The user's email address.
     * @param password The user's password.
     * @return A [User] object if the sign-in is successful, otherwise null.
     */
    override suspend fun signIn(email: String, password: String): User? {
        return try {
            repository.signInWithEmailAndPassword(email, password)
        } catch (e: Exception) {
            null
        }
    }
}