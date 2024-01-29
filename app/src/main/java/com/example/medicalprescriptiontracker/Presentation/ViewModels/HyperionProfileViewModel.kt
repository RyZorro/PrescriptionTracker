package com.example.medicalprescriptiontracker.Presentation.ViewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicalprescriptiontracker.Application.UseCases.GetUserProfile.GetUserProfileUseCase
import com.example.medicalprescriptiontracker.Domain.ValueObjects.UserInfo
import com.example.medicalprescriptiontracker.Application.UseCases.GetUserInfo.GetUserInfoUseCase
import com.example.medicalprescriptiontracker.Domain.ValueObjects.UserProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel responsible for managing the data related to the user profile in the Hyperion app.
 *
 * @property getUserProfileUseCase The use case for retrieving user profile information.
 * @property getUserInfoUseCase The use case for retrieving user medical information.
 */
class HyperionProfileViewModel(
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val getUserInfoUseCase: GetUserInfoUseCase
) : ViewModel() {

    // Mutable state flow for user profile information
    private val _userUserProfile = MutableStateFlow<UserProfile?>(null)
    val userUserProfile: StateFlow<UserProfile?> get() = _userUserProfile

    // Mutable state flow for user medical information
    private val _userInfo = MutableStateFlow<UserInfo?>(null)
    val userInfo: StateFlow<UserInfo?> get() = _userInfo

    /**
     * Fetches the user profile information for the specified user ID.
     *
     * @param userId The unique identifier of the user.
     */
    fun fetchUserProfile(userId: String) {
        viewModelScope.launch {
            try {
                val userProfileInfo = getUserProfileUseCase.getUserProfileInfo(userId)

                _userUserProfile.value = userProfileInfo

                Log.d("UserProfileViewModel", "User profile loaded successfully")
            } catch (e: Exception) {
                Log.e("UserProfileViewModel", "Error loading user profile", e)
            }
        }
    }

    /**
     * Fetches the user medical information for the specified user ID.
     *
     * @param userId The unique identifier of the user.
     */
    fun fetchUserMedicalInfo(userId: String) {
        viewModelScope.launch {
            try {
                val loadedPrescriptions = getUserInfoUseCase.getMedicalInformation(userId)

                _userInfo.value = loadedPrescriptions

                Log.d("UserProfileViewModel", "Prescriptions loaded successfully")
            } catch (e: Exception) {
                Log.e("UserProfileViewModel", "Error loading prescriptions", e)
            }
        }
    }
}