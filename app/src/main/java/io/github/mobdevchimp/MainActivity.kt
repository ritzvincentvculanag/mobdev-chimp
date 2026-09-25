package io.github.mobdevchimp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.github.mobdevchimp.components.BottomNavigationBar
import io.github.mobdevchimp.model.Screen
import io.github.mobdevchimp.ui.screen.CreateDeckScreen
import io.github.mobdevchimp.ui.screen.DashboardScreen
import io.github.mobdevchimp.ui.screen.ProfileScreen
import io.github.mobdevchimp.ui.theme.MobdevchimpTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobdevchimpTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets.safeDrawing,
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPaddings ->
        NavHost(
            navController = navController,
            startDestination = Screen.Dashboard.route,
            modifier = Modifier.padding(innerPaddings).padding(16.dp)
        ) {
            composable(route = Screen.Dashboard.route) { DashboardScreen() }
            composable(route = Screen.CreateDeck.route) { CreateDeckScreen() }
            composable(route = Screen.Profile.route) { ProfileScreen() }
        }
    }
}