package io.github.mobdevchimp.components

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val route: String
)

sealed class Screen(val route: String) {
    object GetStarted: Screen("get_started_screen")
    object Dashboard: Screen("dashboard_screen")
    object CreateDeck: Screen("create_deck_screen")
    object Profile: Screen("profile_screen")
    object DeckInformation: Screen("deck_information_screen/{deckId}") {
        fun createRoute(deckId: Int) = "deck_information_screen/$deckId"
    }
    object EditDeck: Screen("edit_deck_screen/{deckId}") {
        fun createRoute(deckId: Int) = "edit_deck_screen/$deckId"
    }
    object AddCard: Screen("add_card_screen/{deckId}") {
        fun createRoute(deckId: Int) = "add_card_screen/$deckId"
    }
    object CardSaved: Screen("card_saved_screen/{deckId}") {
        fun createRoute(deckId: Int) = "card_saved_screen/$deckId"
    }
    object EditCard: Screen("edit_card_screen/{deckId}/{cardId}") {
        fun createRoute(deckId: Int, cardId: Int) = "edit_card_screen/$deckId/$cardId"
    }
    object ReviewDeck: Screen("review_deck_screen/{deckId}/{missedOnly}") {
        fun createRoute(deckId: Int, missedOnly: Boolean = false) =
            "review_deck_screen/$deckId/$missedOnly"
    }
    object SessionSummary: Screen("session_summary_screen/{deckId}") {
        fun createRoute(deckId: Int) = "session_summary_screen/$deckId"
    }
}