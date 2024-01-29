package com.example.medicalprescriptiontracker.Presentation.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicalprescriptiontracker.Domain.Entities.User
import com.example.medicalprescriptiontracker.Application.UseCases.SignInUseCase.SignInWithEmailAndPasswordUseCase
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

    private val _signInResult = MutableStateFlow<User?>(null)
    val signInResult: StateFlow<User?> = _signInResult

    /**
     * Function to initiate the sign-in process.
     * @param email User's email address.
     * @param password User's password.
     */
    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            try {
                val user = signInWithEmailAndPasswordUseCase.signIn(email, password)

                if (user != null) {
                    _signInResult.value = user
                } else {
                    println("Sign-in failed. User is null.")
                }
            } catch (e: Exception) {
                println("Sign-in failed. Exception: ${e.message}")
            }
        }
    }
}