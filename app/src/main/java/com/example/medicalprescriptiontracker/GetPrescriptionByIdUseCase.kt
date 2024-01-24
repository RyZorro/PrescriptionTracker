package com.example.medicalprescriptiontracker

/**
 * This interface defines the contract for the use case of getting a Prescription by Id
 */
interface GetPrescriptionByIdUseCase {
    suspend fun getPrescriptionById(userId: String?, prescriptionId: String): Prescription?
}