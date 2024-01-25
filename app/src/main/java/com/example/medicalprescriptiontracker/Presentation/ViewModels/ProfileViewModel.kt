package com.example.medicalprescriptiontracker.Presentation.ViewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicalprescriptiontracker.Application.UseCase.GetAllPrescriptionUseCase
import com.example.medicalprescriptiontracker.Application.UseCase.GetUserProfileUseCase
import com.example.medicalprescriptiontracker.Prescription
import com.example.medicalprescriptiontracker.Profile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel class responsible for handling user profile-related UI logic and interactions.
 *
 * @param getUserProfileUseCase The use case for fetching user profile information.
 */
class ProfileViewModel(
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val getAllPrescriptionUseCase: GetAllPrescriptionUseCase
): ViewModel() {

    private val _userProfile = MutableStateFlow<Profile?>(null)
    val userProfile: StateFlow<Profile?> get() =_userProfile

    private val _prescriptions = MutableStateFlow<List<Prescription>>(emptyList())
    val prescriptions: StateFlow<List<Prescription>> = _prescriptions

    /**
     * Fetches user profile information for the specified user ID.
     *
     * @param userId The unique identifier of the user.
     */
    fun fetchUserProfile(userId: String) {
        viewModelScope.launch {
            try {
                val userProfileInfo = getUserProfileUseCase.getUserProfileInfo(userId)

                _userProfile.value = userProfileInfo

                // Log success
                Log.d("UserProfileViewModel", "User profile loaded successfully")
            } catch (e: Exception) {
                // Log error
                Log.e("UserProfileViewModel", "Error loading user profile", e)
            }
        }
    }

    /**
     * Loads prescriptions for a specific user asynchronously.
     * @param userId The unique identifier of the user.
     */
    fun fetchUserPrescriptions(userId: String) {
        viewModelScope.launch {
            try {
                // Call the use case to get prescriptions for the specified user
                val loadedPrescriptions = getAllPrescriptionUseCase.getPrescriptions(userId)

                // Update the StateFlow with the result
                _prescriptions.value = loadedPrescriptions

                // Log successful loading
                Log.d("UserProfileViewModel", "Prescriptions loaded successfully")
            } catch (e: Exception) {
                // Log error
                Log.e("UserProfileViewModel", "Error loading prescriptions", e)
            }
        }
    }
}