package io.github.mobdevchimp.model

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val route: String
)

sealed class Screen(val route: String) {
    object Dashboard: Screen("dashboard_screen")
    object CreateDeck: Screen("create_deck_screen")
    object Profile: Screen("profile_screen")
}