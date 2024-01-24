package com.example.medicalprescriptiontracker

/**
 * This implementation of the SignInUseCase interface handles the sign-in logic.
 * @param repository The repository responsible for user authentication operations.
 */
class SignInWithEmailAndPasswordUseCaseImpl(
    private val repository: FirebaseAuthenticationRepository
) : SignInWithEmailAndPasswordUseCase {
    override suspend fun signIn(email: String, password: String): User? {
        return try {
            // Suspend until the authentication process completes
            repository.signInWithEmailAndPassword(email, password)
        } catch (e: Exception) {
            // Handle the exception if needed
            null
        }
    }
}