package com.example.medicalprescriptiontracker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medicalprescriptiontracker.HyperionMedVault.MedicationItem
import com.example.medicalprescriptiontracker.HyperionMedVault.SearchBar
import com.example.medicalprescriptiontracker.HyperionMedVaultViewModel

@Composable
fun HyperionMedVaultScreen(viewModel: HyperionMedVaultViewModel) {
    var searchQuery by remember { mutableStateOf("") }

    val medicationList by viewModel.medications.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchAllMedication()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Hyperion Med Vault",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center
            )
        )

        SearchBar(
            searchQuery = searchQuery,
            onSearchQueryChange = {
                searchQuery = it
                viewModel.searchMedication(it)
            }
        )

        LazyColumn {
            items(medicationList) { medication ->

                MedicationItem(medication = medication, viewModel = viewModel)
            }
        }
    }
}