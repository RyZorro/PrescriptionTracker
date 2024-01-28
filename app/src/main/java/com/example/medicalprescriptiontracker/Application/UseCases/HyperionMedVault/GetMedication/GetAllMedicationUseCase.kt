package com.example.medicalprescriptiontracker.Application.UseCases.HyperionMedVault.GetMedication

import com.example.medicalprescriptiontracker.Medication

interface GetAllMedicationUseCase {
    suspend fun getAllMedication(): List<Medication>
}