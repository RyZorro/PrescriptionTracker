package com.example.medicalprescriptiontracker

import com.example.medicalprescriptiontracker.Application.UseCase.AddMedicationUseCase
import com.example.medicalprescriptiontracker.Application.UseCase.GetAllMedicationUseCase
import com.example.medicalprescriptiontracker.Application.UseCase.GetUserProfileUseCase
import com.example.medicalprescriptiontracker.Application.UseCase.SearchAllMedicationUseCase
import com.example.medicalprescriptiontracker.Application.UseCase.SignInWithEmailAndPasswordUseCase
import com.example.medicalprescriptiontracker.Application.UseCaseImpl.AddMedicationUseCaseImpl
import com.example.medicalprescriptiontracker.Application.UseCaseImpl.GetAllMedicationUseCaseImpl
import com.example.medicalprescriptiontracker.Application.UseCaseImpl.GetUserProfileUseCaseImpl
import com.example.medicalprescriptiontracker.Application.UseCaseImpl.SearchAllMedicationUseCaseImpl
import com.example.medicalprescriptiontracker.Application.UseCaseImpl.SignInWithEmailAndPasswordUseCaseImpl
import com.example.medicalprescriptiontracker.Infrastructure.Repositories.FirebaseAuthenticationRepository
import com.example.medicalprescriptiontracker.Infrastructure.Repositories.FirebaseCloudFirestoreRepository
import com.example.medicalprescriptiontracker.Presentation.ViewModels.MedicationViewModel
import com.example.medicalprescriptiontracker.Presentation.ViewModels.ProfileViewModel

/**
 * Default implementation of [ViewModelProvider].
 */
class ViewModelProviderImpl: ViewModelProvider {



    // Implement Repositories
    private val firebaseAuthenticationRepository: FirebaseAuthenticationRepository = FirebaseAuthenticationRepository()
    private val firebaseFirestoreRepository: FirebaseCloudFirestoreRepository = FirebaseCloudFirestoreRepository()

    // Implement UseCaseImpl
    private val signInWithEmailAndPasswordUseCase: SignInWithEmailAndPasswordUseCase = SignInWithEmailAndPasswordUseCaseImpl(firebaseAuthenticationRepository)
    private val getAllPrescriptionUseCase: GetAllPrescriptionUseCase = GetAllPrescriptionUseCaseImpl(firebaseFirestoreRepository)
    //private val getPrescriptionByIdUseCase: GetPrescriptionByIdUseCase = GetPrescriptionByIdUseCaseImpl(firebaseFirestoreRepository)
    private val getAllMedicationUseCase: GetAllMedicationUseCase = GetAllMedicationUseCaseImpl(firebaseFirestoreRepository)
    private val searchAllMedicationUseCase: SearchAllMedicationUseCase = SearchAllMedicationUseCaseImpl(firebaseFirestoreRepository)
    private val addMedicationUseCase: AddMedicationUseCase = AddMedicationUseCaseImpl(firebaseFirestoreRepository)
    private val getUserProfileUseCase: GetUserProfileUseCase = GetUserProfileUseCaseImpl(firebaseFirestoreRepository)

    override fun provideMedicationViewModel(): MedicationViewModel {
        return MedicationViewModel(getAllMedicationUseCase, searchAllMedicationUseCase,addMedicationUseCase)
    }

    /**
     * Provides an instance of [SignInViewModel].
     */
    override fun provideSignInViewModel(): SignInViewModel { return SignInViewModel(signInWithEmailAndPasswordUseCase) }

    /**
     * Provides an instance of [HomeViewModel].
     */
    override fun provideHomeViewModel(): HomeViewModel { return HomeViewModel(getAllPrescriptionUseCase) }

    override fun provideProfileViewModel(): ProfileViewModel {
        return ProfileViewModel(getUserProfileUseCase)
    }

    /**
     * Provides an instance of [PrescriptionViewModel].
     */
    //override fun providePrescriptionViewModel(): PrescriptionViewModel { return PrescriptionViewModel(getPrescriptionByIdUseCase) }
}