package com.example.medicalprescriptiontracker.Presentation.Components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AddMedicationButton(
    modifier: Modifier = Modifier,
    onAddClick: () -> Unit
) {
    IconButton(
        onClick = onAddClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add Medication"
        )
    }
}