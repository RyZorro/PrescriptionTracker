package com.example.medicalprescriptiontracker.Application.ViewModelProvider

import com.example.medicalprescriptiontracker.Application.UseCases.GetAllMedication.GetAllMedicationUseCase
import com.example.medicalprescriptiontracker.Application.UseCases.GetAllMedication.GetAllMedicationUseCaseImpl
import com.example.medicalprescriptiontracker.Application.UseCases.SearchAllMedication.SearchAllMedicationUseCase
import com.example.medicalprescriptiontracker.Application.UseCases.SearchAllMedication.SearchAllMedicationUseCaseImpl
import com.example.medicalprescriptiontracker.Application.UseCases.GetUserInfo.GetUserInfoUseCase
import com.example.medicalprescriptiontracker.Application.UseCases.GetUserInfo.GetUserInfoUseCaseImpl
import com.example.medicalprescriptiontracker.Application.UseCases.GetUserProfile.GetUserProfileUseCase
import com.example.medicalprescriptiontracker.Application.UseCases.GetUserProfile.GetUserProfileUseCaseImpl
import com.example.medicalprescriptiontracker.Infrastructure.Repositories.FirebaseAuthenticationRepository
import com.example.medicalprescriptiontracker.Infrastructure.Repositories.FirebaseMedicationRepository
import com.example.medicalprescriptiontracker.Infrastructure.Repositories.FirebaseUserProfileRepository
import com.example.medicalprescriptiontracker.HyperionMedVaultViewModel
import com.example.medicalprescriptiontracker.Presentation.ViewModels.HyperionProfileViewModel
import com.example.medicalprescriptiontracker.Application.UseCases.AddUserMedication.AddMedicationUseCase
import com.example.medicalprescriptiontracker.Application.UseCases.AddUserMedication.AddMedicationUseCaseImpl
import com.example.medicalprescriptiontracker.Application.UseCases.GetUserPrescriptions.GetUserPrescriptionUseCase
import com.example.medicalprescriptiontracker.Application.UseCases.GetUserPrescriptions.GetUserPrescriptionUseCaseImpl
import com.example.medicalprescriptiontracker.Application.UseCases.SignInUseCase.SignInWithEmailAndPasswordUseCase
import com.example.medicalprescriptiontracker.Application.UseCases.SignInUseCase.SignInWithEmailAndPasswordUseCaseImpl
import com.example.medicalprescriptiontracker.Infrastructure.Repositories.FirebaseUserPrescriptionRepository
import com.example.medicalprescriptiontracker.HyperionDashboardViewModel
import com.example.medicalprescriptiontracker.SignInViewModel
import com.example.medicalprescriptiontracker.UpdateUserMedicalInfoUseCase
import com.example.medicalprescriptiontracker.UpdateUserMedicalInfoUseCaseImpl
import com.example.medicalprescriptiontracker.UpdateUserProfileUseCase
import com.example.medicalprescriptiontracker.UpdateUserProfileUseCaseImpl

/**
 * Implementation of [ViewModelProvider] that provides instances of various ViewModels
 * for the Hyperion application.
 */
class ViewModelProviderImpl : ViewModelProvider {

    // Repositories
    private val firebaseAuthenticationRepository: FirebaseAuthenticationRepository = FirebaseAuthenticationRepository()
    private val firebaseUserProfileRepository: FirebaseUserProfileRepository = FirebaseUserProfileRepository()
    private val firebaseMedicationRepository: FirebaseMedicationRepository = FirebaseMedicationRepository()
    private val firebaseUserPrescriptionRepository: FirebaseUserPrescriptionRepository = FirebaseUserPrescriptionRepository()

    // Use Cases
    private val getUserProfileUseCase: GetUserProfileUseCase = GetUserProfileUseCaseImpl(firebaseUserProfileRepository)
    private val getUserInfoUseCase: GetUserInfoUseCase = GetUserInfoUseCaseImpl(firebaseUserProfileRepository)

    private val getAllMedicationUseCase: GetAllMedicationUseCase = GetAllMedicationUseCaseImpl(firebaseMedicationRepository)
    private val searchAllMedicationUseCase: SearchAllMedicationUseCase = SearchAllMedicationUseCaseImpl(firebaseMedicationRepository)
    private val addMedicationUseCase: AddMedicationUseCase = AddMedicationUseCaseImpl(firebaseMedicationRepository)

    private val getUserPrescriptionUseCase: GetUserPrescriptionUseCase = GetUserPrescriptionUseCaseImpl(firebaseUserPrescriptionRepository)

    private val updateUserProfileUseCase: UpdateUserProfileUseCase = UpdateUserProfileUseCaseImpl(firebaseUserProfileRepository)
    private val updateUserMedicalInfoUseCase: UpdateUserMedicalInfoUseCase = UpdateUserMedicalInfoUseCaseImpl(firebaseUserProfileRepository)


    private val signInWithEmailAndPasswordUseCase: SignInWithEmailAndPasswordUseCase = SignInWithEmailAndPasswordUseCaseImpl(firebaseAuthenticationRepository)

    /**
     * Provides an instance of [HyperionProfileViewModel].
     *
     * @return An instance of [HyperionProfileViewModel].
     */
    override fun provideHyperionProfileViewModel(): HyperionProfileViewModel {
        return HyperionProfileViewModel(getUserProfileUseCase, getUserInfoUseCase,updateUserProfileUseCase,updateUserMedicalInfoUseCase)
    }

    /**
     * Provides an instance of [HyperionMedVaultViewModel].
     *
     * @return An instance of [HyperionMedVaultViewModel].
     */
    override fun provideHyperionMedVaultViewModel(): HyperionMedVaultViewModel {
        return HyperionMedVaultViewModel(getAllMedicationUseCase, searchAllMedicationUseCase, addMedicationUseCase)
    }

    /**
     * Provides an instance of [HyperionDashboardViewModel].
     *
     * @return An instance of [HyperionDashboardViewModel].
     */
    override fun provideHyperionDashboardViewModel(): HyperionDashboardViewModel {
        return HyperionDashboardViewModel(getUserPrescriptionUseCase)
    }

    /**
     * Provides an instance of [SignInViewModel].
     *
     * @return An instance of [SignInViewModel].
     */
    override fun provideSignInViewModel(): SignInViewModel {
        return SignInViewModel(signInWithEmailAndPasswordUseCase)
    }
}