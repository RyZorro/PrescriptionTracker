package com.example.medicalprescriptiontracker.Presentation.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.medicalprescriptiontracker.Presentation.Components.ProfileScreen.UserProfileBody
import com.example.medicalprescriptiontracker.Presentation.Components.ProfileScreen.UserProfileSectionHeader
import com.example.medicalprescriptiontracker.Presentation.ViewModels.HyperionProfileViewModel

@Composable
fun ProfileScreen(userId: String, viewModel: HyperionProfileViewModel) {
    // State to track whether the screen is in edit mode
    var isEditMode by remember { mutableStateOf(false) }

    // Collect user profile data from the ViewModel
    val userProfile by viewModel.userUserProfile.collectAsState()

    // Fetch user profile data when the screen is launched
    LaunchedEffect(Unit) {
        viewModel.fetchUserProfile(userId)
    }

    // Main UI Surface
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF191D22),
        contentColor = Color(0xFF191D22)
    ) {
        // Column to arrange UI components vertically
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Row for the user details and edit icon
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Display user image and name
                userProfile?.let {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = rememberImagePainter(data = it.image),
                            contentDescription = null,
                            modifier = Modifier
                                .size(80.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF7A9BAE))
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = it.fullName,
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF7A9BAE),
                            modifier = Modifier.weight(1f)
                        )
                    }
                }

                // Edit icon to toggle edit mode
                IconButton(onClick = { isEditMode = !isEditMode }) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit",
                        tint = Color.White
                    )
                }
            }

            // Divider to separate sections
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color(0xFF7A9BAE))
                    .padding(vertical = 8.dp)
            )

            // Section displaying user profile information
            UserProfileSectionHeader(text = "Profile")
            userProfile?.let { UserProfileBody(it, isEditMode, userId, viewModel) }
        }
    }
}