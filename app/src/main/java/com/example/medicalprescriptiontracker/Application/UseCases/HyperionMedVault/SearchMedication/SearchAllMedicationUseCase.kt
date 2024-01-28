package com.example.medicalprescriptiontracker.Application.UseCases.HyperionMedVault.SearchMedication

import com.example.medicalprescriptiontracker.Medication

interface SearchAllMedicationUseCase {
    suspend fun searchAllMedication(searchQuery: String): List<Medication>
}