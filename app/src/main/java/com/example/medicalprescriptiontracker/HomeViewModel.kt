package com.example.medicalprescriptiontracker

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel responsible for handling prescription-related data and interactions.
 * @param getUserPrescriptionUseCase The use case responsible for fetching all prescriptions.
 */
class HomeViewModel(
    private val getUserPrescriptionUseCase: GetUserPrescriptionUseCase
): ViewModel() {

    // StateFlow to observe the list of prescriptions
    private val _prescriptions = MutableStateFlow<List<Prescription>>(emptyList())
    val prescriptions: StateFlow<List<Prescription>> = _prescriptions

    /**
     * Loads prescriptions for a specific user asynchronously.
     * @param userId The unique identifier of the user.
     */
    fun loadPrescriptions(userId: String) {
        viewModelScope.launch {
            try {
                // Call the use case to get prescriptions for the specified user
                val loadedPrescriptions = getUserPrescriptionUseCase.getPrescriptions(userId)

                // Update the StateFlow with the result
                _prescriptions.value = loadedPrescriptions

                // Log successful loading
                Log.d("PrescriptionViewModel", "Prescriptions loaded successfully")
            } catch (e: Exception) {
                // Log error
                Log.e("PrescriptionViewModel", "Error loading prescriptions", e)
            }
        }
    }
}