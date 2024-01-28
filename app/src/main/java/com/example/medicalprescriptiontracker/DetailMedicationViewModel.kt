package com.example.medicalprescriptiontracker

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicalprescriptiontracker.GetUserPrescriptionUseCase
import com.example.medicalprescriptiontracker.Prescription
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class detailMedicationViewModel(
    private val getUserPrescriptionUseCase: GetUserPrescriptionUseCase
): ViewModel() {

    private val _prescriptions = MutableStateFlow<List<Prescription>>(emptyList())
    val prescriptions: StateFlow<List<Prescription>> = _prescriptions

    fun fetchMedication(userId: String) {
        viewModelScope.launch {
            try {
                val loadedPrescriptions = getUserPrescriptionUseCase.getPrescriptions(userId)

                _prescriptions.value = loadedPrescriptions

                Log.d("UserProfileViewModel", "Prescriptions loaded successfully")
            } catch (e: Exception) {
                Log.e("UserProfileViewModel", "Error loading prescriptions", e)
            }
        }
    }
}