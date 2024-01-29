package com.example.medicalprescriptiontracker.SignIn

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInForm(email: String, password: String, onSignInClick: (String, String) -> Unit) {
    var userEmail by remember { mutableStateOf(email) }
    var userPassword by remember { mutableStateOf(password) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = userEmail,
            onValueChange = { userEmail = it },
            label = { Text("Email", color = Color.White, style = MaterialTheme.typography.bodyMedium) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .background(color = Color(0xFF3C1642), shape = CircleShape),
            textStyle = MaterialTheme.typography.bodyMedium.copy(color = Color.White)
        )

        // Password input field
        TextField(
            value = userPassword,
            onValueChange = { userPassword = it },
            label = { Text("Password", color = Color.White, style = MaterialTheme.typography.bodyMedium) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .background(color = Color(0xFF3C1642), shape = CircleShape),
            visualTransformation = PasswordVisualTransformation(),
            textStyle = MaterialTheme.typography.bodyMedium.copy(color = Color.White)
        )

        Button(
            onClick = { onSignInClick(userEmail, userPassword) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .clip(CircleShape),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.White,
            )
        ) {
            Text("Sign In")
        }
    }
}