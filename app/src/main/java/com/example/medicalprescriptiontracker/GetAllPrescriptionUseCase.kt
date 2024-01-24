package com.example.medicalprescriptiontracker

/**
 * This interface defines the contract for the use case of getting Prescriptions related to the User.
 */
interface GetAllPrescriptionUseCase {
    suspend fun getPrescriptions(userId: String): List<Prescription>
}