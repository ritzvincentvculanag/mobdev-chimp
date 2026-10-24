package io.github.mobdevchimp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import io.github.mobdevchimp.components.ChimpCardAnalytics
import io.github.mobdevchimp.components.ChimpDeckItem
import io.github.mobdevchimp.components.ChimpScreenHeader
import io.github.mobdevchimp.components.Screen
import io.github.mobdevchimp.components.ChimpSectionTitle
import io.github.mobdevchimp.db.Storage
import io.github.mobdevchimp.models.Deck
import io.github.mobdevchimp.ui.theme.MobdevchimpTheme

@Composable
fun DashboardScreen(navController: NavController) {
    var emptyDeckName by remember { mutableStateOf<String?>(null) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val decks = Storage.getDecks()

        ChimpScreenHeader(title = "Dashboard")
        DashboardAnalytics()
        ChimpSectionTitle(text = "My Decks")
        DashboardDecks(
            decks = decks,
            onCardClick = { deck -> navController.navigate(Screen.DeckInformation.createRoute(deck.id)) },
            onPlayClick = { deck ->
                if (deck.cards.isEmpty()) {
                    emptyDeckName = deck.name
                } else {
                    navController.navigate(Screen.ReviewDeck.createRoute(deck.id))
                }
            }
        )
    }

    if (emptyDeckName != null) {
        AlertDialog(
            onDismissRequest = { emptyDeckName = null },
            title = { Text("Deck is empty") },
            text = { Text("Add cards to ${emptyDeckName.orEmpty()} before starting a study session.") },
            confirmButton = {
                Button(onClick = { emptyDeckName = null }) {
                    Text("OK")
                }
            }
        )
    }
}

@Composable
private fun DashboardAnalytics() {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        ChimpCardAnalytics(
            modifier = Modifier.weight(1f),
            value = 3,
            title = "Day Streak"
        )
        ChimpCardAnalytics(
            modifier = Modifier.weight(1f),
            value = 42,
            title = "Total cards"
        )
    }
}

@Composable
private fun DashboardDecks(
    decks: List<Deck>,
    onCardClick: (Deck) -> Unit,
    onPlayClick: (Deck) -> Unit
) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(
            items = decks,
            key = { deck -> deck.id }
        ) { deck ->
            ChimpDeckItem(
                title = deck.name,
                onCardClick = { onCardClick(deck) },
                onPlayClick = { onPlayClick(deck) },
                subtitle = "${deck.cards.size} ${if (deck.cards.size <= 1) "card" else "cards"}"
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun DashboardScreenPreview() {
    MobdevchimpTheme {
        DashboardScreen(navController = rememberNavController())
    }
}