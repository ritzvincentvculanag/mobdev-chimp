package io.github.mobdevchimp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import io.github.mobdevchimp.components.BottomNavigationBar
import io.github.mobdevchimp.components.Screen
import io.github.mobdevchimp.ui.screen.CreateDeckScreen
import io.github.mobdevchimp.ui.screen.AddCardScreen
import io.github.mobdevchimp.ui.screen.CardSavedScreen
import io.github.mobdevchimp.ui.screen.DeckInformationScreen
import io.github.mobdevchimp.ui.screen.DashboardScreen
import io.github.mobdevchimp.ui.screen.EditCardScreen
import io.github.mobdevchimp.ui.screen.EditDeckScreen
import io.github.mobdevchimp.ui.screen.GetStartedScreen
import io.github.mobdevchimp.ui.screen.ProfileScreen
import io.github.mobdevchimp.ui.screen.ReviewDeckScreen
import io.github.mobdevchimp.ui.screen.MatchGameScreen
import io.github.mobdevchimp.ui.screen.SessionSummaryScreen
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
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    val showBottomBar = currentRoute in setOf(
        Screen.Dashboard.route,
        Screen.CreateDeck.route,
        Screen.Profile.route
    )
    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                BottomNavigationBar(navController)
            }
        }
    ) { innerPaddings ->
        NavHost(
            navController = navController,
            startDestination = Screen.GetStarted.route,
            modifier = Modifier.padding(innerPaddings)
        ) {
            composable(route = Screen.GetStarted.route) {
                GetStartedScreen(navController = navController)
            }
            composable(route = Screen.Dashboard.route) {
                DashboardScreen(navController = navController)
            }
            composable(route = Screen.CreateDeck.route) {
                CreateDeckScreen(navController = navController)
            }
            composable(route = Screen.Profile.route) { ProfileScreen() }
            composable(
                route = Screen.DeckInformation.route,
                arguments = listOf(navArgument("deckId") { type = NavType.IntType })
            ) { backStackEntry ->
                val deckId = backStackEntry.arguments?.getInt("deckId") ?: return@composable
                DeckInformationScreen(deckId = deckId, navController = navController)
            }
            composable(
                route = Screen.EditDeck.route,
                arguments = listOf(navArgument("deckId") { type = NavType.IntType })
            ) { backStackEntry ->
                val deckId = backStackEntry.arguments?.getInt("deckId") ?: return@composable
                EditDeckScreen(deckId = deckId, navController = navController)
            }
            composable(
                route = Screen.AddCard.route,
                arguments = listOf(navArgument("deckId") { type = NavType.IntType })
            ) { backStackEntry ->
                val deckId = backStackEntry.arguments?.getInt("deckId") ?: return@composable
                AddCardScreen(deckId = deckId, navController = navController)
            }
            composable(
                route = Screen.CardSaved.route,
                arguments = listOf(navArgument("deckId") { type = NavType.IntType })
            ) { backStackEntry ->
                val deckId = backStackEntry.arguments?.getInt("deckId") ?: return@composable
                CardSavedScreen(deckId = deckId, navController = navController)
            }
            composable(
                route = Screen.EditCard.route,
                arguments = listOf(
                    navArgument("deckId") { type = NavType.IntType },
                    navArgument("cardId") { type = NavType.IntType }
                )
            ) { backStackEntry ->
                val deckId = backStackEntry.arguments?.getInt("deckId") ?: return@composable
                val cardId = backStackEntry.arguments?.getInt("cardId") ?: return@composable
                EditCardScreen(
                    deckId = deckId,
                    cardId = cardId,
                    navController = navController
                )
            }
            composable(
                route = Screen.ReviewDeck.route,
                arguments = listOf(
                    navArgument("deckId") { type = NavType.IntType },
                    navArgument("missedOnly") { type = NavType.BoolType }
                )
            ) { backStackEntry ->
                val deckId = backStackEntry.arguments?.getInt("deckId") ?: return@composable
                val missedOnly = backStackEntry.arguments?.getBoolean("missedOnly") ?: false
                ReviewDeckScreen(
                    deckId = deckId,
                    missedOnly = missedOnly,
                    navController = navController
                )
            }
            composable(
                route = Screen.SessionSummary.route,
                arguments = listOf(
                    navArgument("deckId") { type = NavType.IntType },
                    navArgument("allowReview") { type = NavType.BoolType }
                )
            ) { backStackEntry ->
                val deckId = backStackEntry.arguments?.getInt("deckId") ?: return@composable
                val allowReview = backStackEntry.arguments?.getBoolean("allowReview") ?: true
                SessionSummaryScreen(
                    deckId = deckId,
                    allowReview = allowReview,
                    navController = navController
                )
            }
            composable(
                route = Screen.MatchGame.route,
                arguments = listOf(navArgument("deckId") { type = NavType.IntType })
            ) { backStackEntry ->
                val deckId = backStackEntry.arguments?.getInt("deckId") ?: return@composable
                MatchGameScreen(deckId = deckId, navController = navController)
            }
        }
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    MobdevchimpTheme {
        MainScreen()
    }
}