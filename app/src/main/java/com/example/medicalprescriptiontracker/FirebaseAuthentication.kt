package com.example.medicalprescriptiontracker

/**
 * This interface defines the contract for interacting with Firebase Authentication services.
 * It provides methods for user authentication operations.
 */
interface FirebaseAuthentication {
    suspend fun signInWithEmailAndPassword(email: String, password: String): User?
}