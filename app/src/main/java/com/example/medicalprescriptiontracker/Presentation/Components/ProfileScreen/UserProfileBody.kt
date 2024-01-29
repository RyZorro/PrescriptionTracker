package com.example.medicalprescriptiontracker.Presentation.Components.ProfileScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.medicalprescriptiontracker.Domain.ValueObjects.UserProfile
import com.example.medicalprescriptiontracker.Presentation.ViewModels.HyperionProfileViewModel

@Composable
fun UserProfileBody(
    userUserProfile: UserProfile,
    isEditMode: Boolean,
    userId: String,
    viewModel: HyperionProfileViewModel
) {
    // Keep a mutable state for the edited user profile
    var editedUserProfile by remember { mutableStateOf(userUserProfile.copy()) }

    // Display editable detail items for gender, date of birth, blood type, and contact
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        EditableDetailItem("Gender", editedUserProfile.gender, isEditMode) { updatedValue ->
            // Handle updated gender value
            editedUserProfile = editedUserProfile.copy(gender = updatedValue)
        }
        EditableDetailItem("Date Of Birth", editedUserProfile.dateOfBirth, isEditMode) { updatedValue ->
            // Handle updated date of birth value
            editedUserProfile = editedUserProfile.copy(dateOfBirth = updatedValue)
        }
        EditableDetailItem("Blood Type", editedUserProfile.bloodType, isEditMode) { updatedValue ->
            // Handle updated blood type value
            editedUserProfile = editedUserProfile.copy(bloodType = updatedValue)
        }
        EditableDetailItem("Contact", editedUserProfile.contactInformation, isEditMode) { updatedValue ->
            // Handle updated contact information value
            editedUserProfile = editedUserProfile.copy(contactInformation = updatedValue)
        }

        // Save and Cancel buttons for edit mode
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (isEditMode) {
                Button(onClick = {
                    // Save changes to the user profile
                    viewModel.updateUserProfile(userId, editedUserProfile)
                    // Exit edit mode
                    viewModel.toggleEditMode()
                }) {
                    Text("Save")
                }

                Button(onClick = {
                    // Reset to original values
                    editedUserProfile = userUserProfile.copy()
                    // Exit edit mode
                    viewModel.toggleEditMode()
                }) {
                    Text("Cancel")
                }
            }
        }
    }
}