package com.example.medicalprescriptiontracker.Presentation.ViewModels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicalprescriptiontracker.Application.UseCases.GetUserProfile.GetUserProfileUseCase
import com.example.medicalprescriptiontracker.Domain.ValueObjects.UserInfo
import com.example.medicalprescriptiontracker.Application.UseCases.GetUserInfo.GetUserInfoUseCase
import com.example.medicalprescriptiontracker.Domain.ValueObjects.UserProfile
import com.example.medicalprescriptiontracker.UpdateUserMedicalInfoUseCase
import com.example.medicalprescriptiontracker.UpdateUserProfileUseCase
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
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val updateUserProfileUseCase: UpdateUserProfileUseCase,
    private val updateMedicalInfoUseCase: UpdateUserMedicalInfoUseCase
) : ViewModel() {

    // Mutable state flow for user profile information
    private val _userUserProfile = MutableStateFlow<UserProfile?>(null)
    val userUserProfile: StateFlow<UserProfile?> get() = _userUserProfile

    // Mutable state flow for user medical information
    private val _userInfo = MutableStateFlow<UserInfo?>(null)
    val userInfo: StateFlow<UserInfo?> get() = _userInfo

    private val _isEditMode = mutableStateOf(false)
    val isEditMode: State<Boolean> get() = _isEditMode

    // Function to toggle the edit mode
    fun toggleEditMode() {
        _isEditMode.value = !_isEditMode.value
    }

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

    fun updateUserProfile(userId: String, userProfile: UserProfile) {
        viewModelScope.launch {
            try {
                updateUserProfileUseCase.updateUserProfileInfo(userId, userProfile)
                fetchUserProfile(userId)
                Log.d("UserProfileViewModel", "User profile updated successfully")
            } catch (e: Exception) {
                Log.e("UserProfileViewModel", "Error updating user profile", e)
            }
        }
    }

    fun updateProfileField(userId: String, field: String, updatedValue: String) {
        viewModelScope.launch {
            try {
                updateUserProfileUseCase.updateUserProfileField(userId, field, updatedValue)
                fetchUserProfile(userId)
                Log.d("HyperionProfileViewModel", "User profile field updated successfully")
            } catch (e: Exception) {
                Log.e("HyperionProfileViewModel", "Error updating user profile field", e)
            }
        }
    }

    fun updateMedicalInformation(userId: String, medicalInfo: UserInfo) {
        viewModelScope.launch {
            try {
                updateMedicalInfoUseCase.updateMedicalInformation(userId, medicalInfo)

                Log.d("UserProfileViewModel", "Medical information updated successfully")
            } catch (e: Exception) {
                Log.e("UserProfileViewModel", "Error updating medical information", e)
            }
        }
    }
}