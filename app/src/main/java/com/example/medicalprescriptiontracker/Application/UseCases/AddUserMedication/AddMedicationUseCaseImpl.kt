package com.example.medicalprescriptiontracker.Application.UseCases.AddUserMedication

import com.example.medicalprescriptiontracker.Infrastructure.Repositories.FirebaseMedicationRepository
import com.example.medicalprescriptiontracker.Domain.Entities.Medication

/**
 * Implementation of the [AddMedicationUseCase] interface.
 *
 * @property repository The repository responsible for data operations related to medications.
 */
class AddMedicationUseCaseImpl(
    private val repository: FirebaseMedicationRepository
) : AddMedicationUseCase {

    /**
     * Adds the specified medication to the user's prescriptions.
     *
     * @param userId The unique identifier of the user.
     * @param medication The [Medication] object to be added to the user's prescriptions.
     */
    override suspend fun addMedicationToUserPrescriptions(userId: String, medication: Medication) {
        repository.addMedicationToUserPrescriptions(userId, medication)
    }
}