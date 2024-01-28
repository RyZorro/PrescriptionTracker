package com.example.medicalprescriptiontracker.UseCaseImpl

import com.example.medicalprescriptiontracker.AddMedicationUseCase
import com.example.medicalprescriptiontracker.Infrastructure.Repositories.FirebaseCloudFirestoreRepository
import com.example.medicalprescriptiontracker.Medication

/**
 * Implementation of the AddMedicationUseCase interface.
 *
 * @param repository The repository responsible for data operations related to medications.
 */
class AddMedicationUseCaseImpl(
    private val repository: FirebaseCloudFirestoreRepository
) : AddMedicationUseCase {

    /**
     * Adds the specified medication to the user's prescriptions.
     *
     * @param userId The unique identifier of the user.
     * @param medication The medication to be added to the user's prescriptions.
     */
    override suspend fun addMedicationToUserPrescriptions(userId: String, medication: Medication) {
        repository.addMedicationToUserPrescriptions(userId, medication)
    }
}