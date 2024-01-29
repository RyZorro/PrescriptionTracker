package com.example.medicalprescriptiontracker

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicalprescriptiontracker.Application.UseCases.GetAllMedication.GetAllMedicationUseCase
import com.example.medicalprescriptiontracker.Application.UseCases.SearchAllMedication.SearchAllMedicationUseCase
import com.example.medicalprescriptiontracker.Application.UseCases.AddUserMedication.AddMedicationUseCase
import com.example.medicalprescriptiontracker.Domain.Entities.Medication
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel responsible for handling medication-related data and interactions.
 * @param getAllMedicationUseCase The use case responsible for fetching all medications.
 */
class HyperionMedVaultViewModel(
    private val getAllMedicationUseCase: GetAllMedicationUseCase,
    private val searchAllMedicationUseCase: SearchAllMedicationUseCase,
    private val addMedicationUseCase: AddMedicationUseCase
): ViewModel() {

    val firebaseAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    private val _medication = MutableStateFlow<List<Medication>>(emptyList())
    val medications: StateFlow<List<Medication>> = _medication

    /**
     * Asynchronously fetches all medications and updates the StateFlow.
     */
    fun fetchAllMedication() {
        viewModelScope.launch {
            try {
                val loadedPrescriptions = getAllMedicationUseCase.getAllMedication()

                _medication.value = loadedPrescriptions

                Log.d("PrescriptionViewModel", "Prescriptions loaded successfully")
            } catch (e: Exception) {
                Log.e("PrescriptionViewModel", "Error loading prescriptions", e)
            }
        }
    }

    /**
     * Searches for medications based on the provided search query using the associated use case.
     * Updates the StateFlow with the search result.
     * @param searchQuery The query used to filter medications.
     */
    fun searchMedication(searchQuery: String) {
        viewModelScope.launch {
            try {
                val searchedMedications = searchAllMedicationUseCase.searchAllMedication(searchQuery)

                // Update the state flow with the searched medications
                _medication.value = searchedMedications

                Log.d("MedicationViewModel", "Medications searched successfully for query: $searchQuery")
            } catch (e: Exception) {
                Log.e("MedicationViewModel", "Error searching medications", e)
            }
        }
    }

    /**
     * Adds medication to the active cycle for the current user.
     * @param medication The medication to be added.
     */
    fun addMedicationToUserPrescriptions(medication: Medication) {
        viewModelScope.launch {
            try {
                val userId = firebaseAuth.currentUser?.uid

                if (userId != null) {
                    addMedicationUseCase.addMedicationToUserPrescriptions(userId, medication)
                    Log.d("MedicationViewModel", "Medication added to user's prescriptions successfully")
                } else {
                    Log.e("MedicationViewModel", "Current user ID is null")
                }
            } catch (e: Exception) {
                Log.e("MedicationViewModel", "Error adding medication to user's prescriptions", e)
            }
        }
    }
}