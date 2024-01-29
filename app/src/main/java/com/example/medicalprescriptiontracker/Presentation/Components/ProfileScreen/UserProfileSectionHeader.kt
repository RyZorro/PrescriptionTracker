package com.example.medicalprescriptiontracker.Presentation.Components.ProfileScreen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun UserProfileSectionHeader(text: String) {
    // Display a text header with specified style and color
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium
            .copy(
                color = Color(0xFF7A9BAE), // Light blue text color
                fontWeight = FontWeight.Bold
            ),
        modifier = Modifier.padding(vertical = 8.dp)
    )
}