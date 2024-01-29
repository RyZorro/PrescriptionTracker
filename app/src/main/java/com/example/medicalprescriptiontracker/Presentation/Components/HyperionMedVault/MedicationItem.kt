package com.example.medicalprescriptiontracker.Presentation.Components.HyperionMedVault

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.medicalprescriptiontracker.Domain.Entities.Medication
import com.example.medicalprescriptiontracker.Presentation.ViewModels.HyperionMedVaultViewModel

@Composable
fun MedicationItem(
    medication: Medication,
    modifier: Modifier = Modifier,
    viewModel: HyperionMedVaultViewModel
) {

    var showDialog by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.Black)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {

            MedicationItemDetail(Icons.Default.Warning, "Medication", medication.medicationName)
            MedicationItemDetail(null, "Dosage", medication.dosage)
            MedicationItemDetail(null, "Frequency", medication.frequency)
            MedicationItemDetail(null, "Side Effects", medication.sideEffects)
            MedicationItemDetail(null, "Instructions", medication.specialInstructions)

            Spacer(modifier = Modifier.height(16.dp))

            AddMedicationButton(
                modifier = Modifier.align(Alignment.End),
                onAddClick = {
                    showDialog = true
                }
            )
        }
    }
    if (showDialog) {
        AddMedicationConfirmationDialog(
            onConfirm = {
                viewModel.addMedicationToUserPrescriptions(medication)
                showDialog = false
            },
            onDismiss = {
                showDialog = false
            }
        )
    }
}