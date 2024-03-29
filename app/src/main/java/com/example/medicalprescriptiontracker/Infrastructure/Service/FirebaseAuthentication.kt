package com.example.medicalprescriptiontracker.Infrastructure.Service

import com.example.medicalprescriptiontracker.Domain.Entities.User

/**
 * Interface defining authentication operations with Firebase.
 */
interface FirebaseAuthentication {

    /**
     * Asynchronously signs in the user with the provided email and password.
     *
     * @param email The user's email address.
     * @param password The user's password.
     * @return A [User] object representing the signed-in user if successful, or null otherwise.
     */
    suspend fun signInWithEmailAndPassword(email: String, password: String): User?
}