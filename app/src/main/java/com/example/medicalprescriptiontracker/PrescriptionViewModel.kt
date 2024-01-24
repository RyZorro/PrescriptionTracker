package com.example.medicalprescriptiontracker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicalprescriptiontracker.GetPrescriptionByIdUseCase
import com.example.medicalprescriptiontracker.Prescription
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel responsible for handling prescription-related data and interactions.
 * @param getPrescriptionByIdUseCase The use case responsible for fetching a prescription by Id.
 */
class PrescriptionViewModel(
    private val getPrescriptionByIdUseCase: GetPrescriptionByIdUseCase
): ViewModel() {

    // StateFlow to hold the current prescription
    private val _prescriptions = MutableStateFlow<Prescription?>(null)
    val prescriptions: StateFlow<Prescription?> get() = _prescriptions

    /**
     * Function to load prescription details by Id.
     * @param userId User's ID.
     * @param prescriptionId ID of the prescription to load.
     */
    fun loadPrescriptionById(userId: String?, prescriptionId: String) {
        viewModelScope.launch {
            try {
                // Call the getPrescriptionById function from the use case
                val prescription =
                    getPrescriptionByIdUseCase.getPrescriptionById(userId, prescriptionId)

                // Update the state with the loaded prescription
                _prescriptions.value = prescription
            } catch (e: Exception) {
                // Handle the exception
                e.printStackTrace()
            }
        }
    }
}