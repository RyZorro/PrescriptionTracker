package com.example.medicalprescriptiontracker

import com.example.medicalprescriptiontracker.Presentation.ViewModels.MedicationViewModel

/**
 * Provides instances of ViewModels for the application.
 */
interface ViewModelProvider {
    fun provideMedicationViewModel(): MedicationViewModel


    fun provideSignInViewModel(): SignInViewModel
    fun provideHomeViewModel(): HomeViewModel
    //fun providePrescriptionViewModel(): PrescriptionViewModel
}