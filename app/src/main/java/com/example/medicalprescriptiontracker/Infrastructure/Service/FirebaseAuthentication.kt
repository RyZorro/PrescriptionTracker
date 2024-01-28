package com.example.medicalprescriptiontracker.Infrastructure.Service

import com.example.medicalprescriptiontracker.User

interface FirebaseAuthentication {
    suspend fun signInWithEmailAndPassword(email: String, password: String): User?
}