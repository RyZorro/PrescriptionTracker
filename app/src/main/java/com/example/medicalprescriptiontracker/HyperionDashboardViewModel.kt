package com.example.medicalprescriptiontracker

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicalprescriptiontracker.Application.UseCases.GetUserPrescriptions.GetUserPrescriptionUseCase
import com.example.medicalprescriptiontracker.Domain.Entities.Prescription
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel responsible for handling prescription-related data and interactions.
 * @param getUserPrescriptionUseCase The use case responsible for fetching all prescriptions.
 */
class HyperionDashboardViewModel(
    private val getUserPrescriptionUseCase: GetUserPrescriptionUseCase
): ViewModel() {

    private val _prescriptions = MutableStateFlow<List<Prescription>>(emptyList())
    val prescriptions: StateFlow<List<Prescription>> = _prescriptions

    /**
     * Loads prescriptions for a specific user asynchronously.
     * @param userId The unique identifier of the user.
     */
    fun loadPrescriptions(userId: String) {
        viewModelScope.launch {
            try {
                val loadedPrescriptions = getUserPrescriptionUseCase.getPrescriptions(userId)

                _prescriptions.value = loadedPrescriptions

                Log.d("PrescriptionViewModel", "Prescriptions loaded successfully")
            } catch (e: Exception) {
                Log.e("PrescriptionViewModel", "Error loading prescriptions", e)
            }
        }
    }
}