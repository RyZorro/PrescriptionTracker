package com.example.medicalprescriptiontracker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicalprescriptiontracker.SignInWithEmailAndPasswordUseCase
import com.example.medicalprescriptiontracker.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for handling user sign-in operations.
 *
 * @property signInWithEmailAndPasswordUseCase Use case for signing in with email and password.
 */
class SignInViewModel(
    private val signInWithEmailAndPasswordUseCase: SignInWithEmailAndPasswordUseCase
) : ViewModel() {

    // MutableStateFlow to represent the sign-in result
    private val _signInResult = MutableStateFlow<User?>(null)

    // Exposed StateFlow to observe the sign-in result
    val signInResult: StateFlow<User?> = _signInResult

    /**
     * Function to initiate the sign-in process.
     * @param email User's email address.
     * @param password User's password.
     */
    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            try {
                // Move the signIn method to your UseCase implementation
                val user = signInWithEmailAndPasswordUseCase.signIn(email, password)

                // Check if the sign-in was successful
                if (user != null) {
                    _signInResult.value = user
                } else {
                    println("Sign-in failed. User is null.")
                }
            } catch (e: Exception) {
                // Log and handle the exception if needed
                println("Sign-in failed. Exception: ${e.message}")
            }
        }
    }
}