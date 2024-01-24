package com.example.medicalprescriptiontracker

import com.example.medicalprescriptiontracker.Presentation.ViewModels.MedicationViewModel
import com.example.medicalprescriptiontracker.Presentation.ViewModels.ProfileViewModel

/**
 * Provides instances of ViewModels for the application.
 */
interface ViewModelProvider {
    fun provideMedicationViewModel(): MedicationViewModel


    fun provideSignInViewModel(): SignInViewModel
    fun provideHomeViewModel(): HomeViewModel
    //fun providePrescriptionViewModel(): PrescriptionViewModel
    fun provideProfileViewModel(): ProfileViewModel
}