package com.example.medicalprescriptiontracker.Application.ViewModelProvider

import com.example.medicalprescriptiontracker.Presentation.ViewModels.HyperionDashboardViewModel
import com.example.medicalprescriptiontracker.Presentation.ViewModels.HyperionMedVaultViewModel
import com.example.medicalprescriptiontracker.Presentation.ViewModels.HyperionProfileViewModel
import com.example.medicalprescriptiontracker.Presentation.ViewModels.SignInViewModel

/**
 * Interface defining methods for providing various ViewModels in the Hyperion application.
 */
interface ViewModelProvider {

    /**
     * Provides an instance of [HyperionProfileViewModel].
     *
     * @return An instance of [HyperionProfileViewModel].
     */
    fun provideHyperionProfileViewModel(): HyperionProfileViewModel

    /**
     * Provides an instance of [HyperionMedVaultViewModel].
     *
     * @return An instance of [HyperionMedVaultViewModel].
     */
    fun provideHyperionMedVaultViewModel(): HyperionMedVaultViewModel

    /**
     * Provides an instance of [HyperionDashboardViewModel].
     *
     * @return An instance of [HyperionDashboardViewModel].
     */
    fun provideHyperionDashboardViewModel(): HyperionDashboardViewModel

    /**
     * Provides an instance of [SignInViewModel].
     *
     * @return An instance of [SignInViewModel].
     */
    fun provideSignInViewModel(): SignInViewModel
}