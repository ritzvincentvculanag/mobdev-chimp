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
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import io.github.mobdevchimp.ui.theme.macawBlue100
import io.github.mobdevchimp.ui.theme.macawBlue200
import io.github.mobdevchimp.ui.theme.macawBlue500

@Composable
fun BottomNavigationBar(navController: NavController) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
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
        navigationItems.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                label = { Text(text = item.title )},
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(Screen.Dashboard.route) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
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
