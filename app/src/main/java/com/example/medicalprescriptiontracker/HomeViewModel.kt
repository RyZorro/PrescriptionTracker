package com.example.medicalprescriptiontracker

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicalprescriptiontracker.GetAllPrescriptionUseCase
import com.example.medicalprescriptiontracker.Prescription
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel responsible for handling prescription-related data and interactions.
 * @param getAllPrescriptionUseCase The use case responsible for fetching all prescriptions.
 */
class HomeViewModel(
    private val getAllPrescriptionUseCase: GetAllPrescriptionUseCase
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
                val loadedPrescriptions = getAllPrescriptionUseCase.getPrescriptions(userId)

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