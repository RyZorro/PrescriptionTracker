package com.example.medicalprescriptiontracker.Presentation.NavigationDrawer

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.medicalprescriptiontracker.Domain.ValueObjects.NavigationItem

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