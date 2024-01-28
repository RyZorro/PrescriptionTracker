package com.example.medicalprescriptiontracker.Presentation.ViewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medicalprescriptiontracker.Application.UseCases.HyperionProfile.UserProfile.GetUserProfileUseCase
import com.example.medicalprescriptiontracker.UserInfo
import com.example.medicalprescriptiontracker.Application.UseCases.HyperionProfile.UserInfo.GetUserInfoUseCase
import com.example.medicalprescriptiontracker.UserProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HyperionProfileViewModel(
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val getUserInfoUseCase: GetUserInfoUseCase
): ViewModel() {

    private val _userUserProfile = MutableStateFlow<UserProfile?>(null)
    val userUserProfile: StateFlow<UserProfile?> get() = _userUserProfile

    private val _userInfo = MutableStateFlow<UserInfo?>(null)
    val userInfo: StateFlow<UserInfo?> get() = _userInfo

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