package io.github.mobdevchimp.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavController
import io.github.mobdevchimp.model.NavigationItem
import io.github.mobdevchimp.model.Screen
import io.github.mobdevchimp.ui.theme.macawBlue100
import io.github.mobdevchimp.ui.theme.macawBlue200
import io.github.mobdevchimp.ui.theme.macawBlue500

@Composable
fun BottomNavigationBar(navController: NavController) {
    val selectedNavigationIndex = rememberSaveable { mutableIntStateOf(0) }
    val navigationItems = listOf(
        NavigationItem(
            title = "Dashboard",
            icon = Icons.Default.Dashboard,
            route = Screen.Dashboard.route
        ),
        NavigationItem(
            title = "Create Deck",
            icon = Icons.Default.Create,
            route = Screen.CreateDeck.route
        ),
        NavigationItem(
            title = "Profile",
            icon = Icons.Default.Person,
            route = Screen.Profile.route
        )
    )

    NavigationBar(containerColor = macawBlue100) {
        navigationItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedNavigationIndex.intValue == index,
                icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                label = { Text(text = item.title )},
                onClick = {
                    selectedNavigationIndex.intValue = index
                    navController.navigate(item.route)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = macawBlue500,
                    selectedTextColor = macawBlue500,
                    unselectedIconColor = macawBlue200,
                    unselectedTextColor = macawBlue200,
                    indicatorColor = macawBlue100,
                )
            )
        }
    }
}

