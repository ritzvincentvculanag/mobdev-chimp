package io.github.mobdevchimp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.mobdevchimp.components.ChimpCardAnalytics
import io.github.mobdevchimp.components.ChimpDeckItem
import io.github.mobdevchimp.components.ChimpSectionTitle
import io.github.mobdevchimp.components.ChimpTitle
import io.github.mobdevchimp.db.Storage
import io.github.mobdevchimp.models.Deck

@Composable
fun DashboardScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        val storage = Storage()
        val decks = storage.getDecks()

        ChimpTitle(text = "Dashboard")
        DashboardAnalytics()
        ChimpSectionTitle(text = "My Decks")
        DashboardDecks(
            decks = decks,
            onCardClick = { deck -> println("Redirecting to details for deck id ${deck.id}") },
            onPlayClick = { deck -> println("Now playing deck with deck id ${deck.id}") }
        )
    }
}

@Composable
private fun DashboardAnalytics() {
    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
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
    LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
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