package com.example.medicalprescriptiontracker

import com.example.medicalprescriptiontracker.Prescription

interface GetUserPrescriptionUseCase {
    suspend fun getPrescriptions(userId: String): List<Prescription>
}