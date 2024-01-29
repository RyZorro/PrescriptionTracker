package com.example.medicalprescriptiontracker.Infrastructure.Repositories

import com.example.medicalprescriptiontracker.Domain.Entities.User
import com.example.medicalprescriptiontracker.Infrastructure.Service.FirebaseAuthentication
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

/**
 * This repository class serves as a bridge between the application and Firebase Authentication services.
 * It implements the FirebaseAuthentication interface to handle user authentication operations.
 */
class FirebaseAuthenticationRepository() : FirebaseAuthentication {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    /**
     * Signs in a user with the provided email and password using Firebase authentication.
     * @param email The user's email address.
     * @param password The user's password.
     * @param callback A callback to handle the result of the sign-in operation.
     * The callback receives a User object if successful, or an Exception if there's an error.
     */
    override suspend fun signInWithEmailAndPassword(email: String, password: String): User? {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            result.user?.let { mapFirebaseUserToUser(it) }
        } catch (e: Exception) {
            throw e
        }
    }

    /**
     * Maps a FirebaseUser object to a User object.
     * @param firebaseUser The FirebaseUser object to be mapped.
     * @return The User object mapped from FirebaseUser.
     */
    private fun mapFirebaseUserToUser(firebaseUser: FirebaseUser): User {
        return User(
            id = firebaseUser.uid,
            email = firebaseUser.email ?: "",
            )
    }
}