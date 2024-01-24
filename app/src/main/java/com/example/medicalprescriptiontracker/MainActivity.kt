package com.example.medicalprescriptiontracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.medicalprescriptiontracker.Presentation.Screens.MedicationListScreen
import com.example.medicalprescriptiontracker.ui.theme.MedicalPrescriptionTrackerTheme
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MedicalPrescriptionTrackerTheme {

                // Firebase Authentication Initilisation
                val firebaseAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
                // Jetpack NavController
                val navController = rememberNavController()

                // ViewModelProvider Implementations
                val signInViewModel = ViewModelProviderImpl().provideSignInViewModel()
                val homeViewModel = ViewModelProviderImpl().provideHomeViewModel()
                //val prescriptionViewModel = ViewModelProviderImpl().providePrescriptionViewModel()
                val viewModel = ViewModelProviderImpl().provideMedicationViewModel()


                // Navigation Drawer Items
                val items = listOf(
                    NavigationItem(
                        title = "Home",
                        selectedIcon = Icons.Filled.Home,
                        unselectedIcon = Icons.Outlined.Home,
                        route = Screen.Page1.route
                    ),
                    NavigationItem(
                        title = "Zed's Medication",
                        selectedIcon = Icons.Filled.Info,
                        unselectedIcon = Icons.Outlined.Info,
                        route = Screen.Medication.route
                    )
                )

                // Remember the state of the drawer
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                // Remember a coroutine scope to launch coroutines
                val scope = rememberCoroutineScope()
                // Remember the selected index of the item in Drawer
                var selectedItemIndex by rememberSaveable { mutableStateOf(0) }

                // Modal Navigation Drawer
                ModalNavigationDrawer(
                    drawerContent = { DrawerContent(
                        items = items,
                        selectedItemIndex = selectedItemIndex, onItemSelected = { index ->
                            selectedItemIndex = index
                            scope.launch {
                                drawerState.close()
                            } },
                        navController = navController)
                                    },
                    drawerState = drawerState
                ) {
                    // Navigation Defined For App
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Page1.route,
                    ) {
                        composable(Screen.Home.route) {
                            HomeScreen(signInViewModel, navController)
                        }
                        composable(Screen.Page1.route) {
                            val userId = firebaseAuth.currentUser?.uid
                            if (userId != null) {
                                Page1Screen(userId = userId, viewModel = homeViewModel, navController = navController)
                            }
                        }
                        composable(Screen.PrescriptionDetail.createRoute("{prescriptionId}")) { backStackEntry ->
                            val prescriptionId =
                                requireNotNull(backStackEntry.arguments?.getString("prescriptionId")) {
                                    "Missing prescriptionId in PrescriptionDetail route"
                                }

                            // Retrieve the userId from your authentication system (e.g., Firebase Auth)
                            val userId = firebaseAuth.currentUser?.uid

                            // Use the PrescriptionDetailViewModel to display the details of the prescription
                            //PrescriptionDetailScreen(prescriptionViewModel, userId, prescriptionId)
                        }
                        composable(Screen.Medication.route) {
                            MedicationListScreen(viewModel)
                        }
                    }
                }
            }
        }
    }
}

data class NavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerContent(
    items: List<NavigationItem>,
    selectedItemIndex: Int,
    onItemSelected: (Int) -> Unit,
    navController: NavController
) {
    ModalDrawerSheet {
        items.forEachIndexed { index, navigationItem ->
            NavigationDrawerItem(
                label = { Text(text = navigationItem.title) },
                selected = index == selectedItemIndex,
                onClick = {
                    onItemSelected(index)
                    navController.navigate(navigationItem.route)
                },
                icon = {
                    Icon(
                        imageVector = if (index == selectedItemIndex) {
                            navigationItem.selectedIcon
                        } else navigationItem.unselectedIcon,
                        contentDescription = navigationItem.title
                    )
                }
            )
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrescriptionDetailScreen(
    viewModel: PrescriptionViewModel,
    userId: String?,
    prescriptionId: String
) {
    // Trigger the loading of prescription details when the composable is first composed
    LaunchedEffect(userId, prescriptionId) {
        userId?.let {
            viewModel.loadPrescriptionById(it, prescriptionId)
        }
    }

    // Access prescription details from viewModel using the provided prescriptionId
    val prescription by viewModel.prescriptions.collectAsState()

    // Scaffold provides a basic structure for your screen
    Scaffold(
        topBar = {
            // TopAppBar with a title
            TopAppBar(
                title = {
                    Text(
                        text = "Prescription Details",
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth() // Fill the width of the TopAppBar
                            .padding(start = 16.dp, end = 16.dp), // Add padding to center the text
                    )
                })
        },
        content = {
            // Use a Surface to provide background color and padding
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
            ) {
                // Display prescription details
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Title
                    Text(
                        text = "Prescription Details",
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        fontSize = 24.sp,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    prescription?.let {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.Start
                        ) {
                            PrescriptionDetailItem("Medication", it.medicationName)
                            PrescriptionDetailItem("Instructions", it.instructions)
                            PrescriptionDetailItem("Dosage", it.dosage)
                            PrescriptionDetailItem("Prescribed", it.createdAt)
                        }
                    } ?: run {
                        // Handle the case where prescription is null (e.g., loading or not found)
                        Text(text = "Prescription not found")
                    }
                }
            }
        }
    )
}

// Define a reusable composable for prescription details
@Composable
fun PrescriptionDetailItem(title: String, value: String) {
    Column(
        modifier = Modifier.padding(vertical = 8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = title, fontWeight = FontWeight.Bold, color = Color.Black)
        Text(text = value, color = Color.Black)
    }
}