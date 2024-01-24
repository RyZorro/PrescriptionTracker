package com.example.medicalprescriptiontracker

sealed class Screen(val route: String) {
    object Home: Screen("sign_in")
    object Page1: Screen("home")
    object Doctor: Screen("doctor")
    object PrescriptionDetail : Screen("prescriptions") {
        fun createRoute(prescriptionId: String): String {
            return "prescriptions/$prescriptionId"
        }
    }
    object Medication: Screen("medication")
    object Profile: Screen("profile")
}