package com.example.medicalprescriptiontracker.Presentation.Components.ProfileScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.medicalprescriptiontracker.Profile

@Composable
fun UserProfileHeader(userProfile: Profile?) {
    userProfile?.let {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center
        ) {
            // Profile picture
            Image(
                painter = rememberImagePainter(data = userProfile.image),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clip(shape = CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // User name
            Text(
                text = "Name: ${it.firstName} ${it.lastName}",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Age, Gender, and Occupation
            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {

                Text(text = "Age: ${it.age}", style = MaterialTheme.typography.bodyMedium)

                Text(text = "Gender: ${it.gender}", style = MaterialTheme.typography.bodyMedium)

                Text(text = "Occupation: ${it.occupation}", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}