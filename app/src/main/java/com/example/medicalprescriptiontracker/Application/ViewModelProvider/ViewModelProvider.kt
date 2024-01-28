package com.example.medicalprescriptiontracker.Application.ViewModelProvider

import com.example.medicalprescriptiontracker.Presentation.ViewModels.HyperionMedVaultViewModel
import com.example.medicalprescriptiontracker.detailMedicationViewModel
import com.example.medicalprescriptiontracker.Presentation.ViewModels.HyperionProfileViewModel

interface ViewModelProvider {
    fun provideProfileViewModel(): HyperionProfileViewModel
    fun providePrescriptionTrackingViewModel(): detailMedicationViewModel
    fun provideMedicalViewModel(): HyperionMedVaultViewModel
}