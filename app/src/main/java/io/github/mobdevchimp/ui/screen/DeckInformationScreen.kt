package io.github.mobdevchimp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.github.mobdevchimp.components.ChimpButton
import io.github.mobdevchimp.components.ChimpCardItem
import io.github.mobdevchimp.components.ChimpSectionTitle
import io.github.mobdevchimp.components.Screen
import io.github.mobdevchimp.db.Storage
import io.github.mobdevchimp.ui.theme.MobdevchimpTheme
import io.github.mobdevchimp.ui.theme.beeYellow300
import io.github.mobdevchimp.ui.theme.beeYellow500
import io.github.mobdevchimp.ui.theme.beetlePurple300
import io.github.mobdevchimp.ui.theme.beetlePurple500

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeckInformationScreen(
    deckId: Int,
    navController: NavController? = null,
    modifier: Modifier = Modifier
) {
    val deck = Storage.getDecks().find { it.id == deckId }
    var deletedCardIds by remember { mutableStateOf(emptySet<Int>()) }
    val cards = Storage.getDecks()
        .firstOrNull { it.id == deckId }
        ?.cards
        .orEmpty()
        .filterNot { it.id in deletedCardIds }
    var menuExpanded by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var showEmptyDeckDialog by remember { mutableStateOf(false) }
    var cardToDelete by remember { mutableStateOf<io.github.mobdevchimp.models.Card?>(null) }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(deck?.name ?: "Deck") },
                navigationIcon = {
                    IconButton(onClick = { navController?.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { menuExpanded = true }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "Deck options")
                    }
                    DropdownMenu(
                        expanded = menuExpanded,
                        onDismissRequest = { menuExpanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Edit Deck") },
                            onClick = {
                                menuExpanded = false
                                navController?.navigate(Screen.EditDeck.createRoute(deckId))
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Delete Deck") },
                            onClick = {
                                menuExpanded = false
                                showDeleteDialog = true
                            }
                        )
                    }
                }
            )
        },
        bottomBar = {
            ChimpButton(
                text = "Add Card",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                onClick = { navController?.navigate(Screen.AddCard.createRoute(deckId)) }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ChimpSectionTitle(text = "Study Options")
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                ChimpButton(
                    text = "Flashcards",
                    modifier = Modifier.weight(1f),
                    foreground = beeYellow300,
                    background = beeYellow500,
                    onClick = {
                        if (cards.isEmpty()) {
                            showEmptyDeckDialog = true
                        } else {
                            navController?.navigate(Screen.ReviewDeck.createRoute(deckId))
                        }
                    }
                )
                ChimpButton(
                    text = "Match Game",
                    modifier = Modifier.weight(1f),
                    foreground = beetlePurple300,
                    background = beetlePurple500,
                    onClick = {
                        if (cards.isEmpty()) {
                            showEmptyDeckDialog = true
                        } else {
                            navController?.navigate(Screen.MatchGame.createRoute(deckId))
                        }
                    }
                )
            }
            ChimpSectionTitle(text = "Your Cards")
            if (cards.isEmpty()) {
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "This deck is empty.",
                    textAlign = TextAlign.Center
                )
            } else {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(cards, key = { it.id }) { card ->
                        ChimpCardItem(
                            card = card,
                            onClick = {
                                navController?.navigate(Screen.EditCard.createRoute(deckId, card.id))
                            },
                            onDeleteClick = {
                                cardToDelete = card
                            }
                        )
                    }
                }
            }
        }
    }

    if (cardToDelete != null) {
        AlertDialog(
            onDismissRequest = { cardToDelete = null },
            title = { Text("Delete card?") },
            text = { Text("Are you sure you want to delete this card?") },
            confirmButton = {
                Button(
                    onClick = {
                        cardToDelete?.let { card ->
                            Storage.deleteCard(deckId, card.id)
                            deletedCardIds = deletedCardIds + card.id
                        }
                        cardToDelete = null
                    }
                ) {
                    Text("Delete")
                }
            },
            dismissButton = {
                Button(onClick = { cardToDelete = null }) {
                    Text("Cancel")
                }
            }
        )
    }

    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Delete deck?") },
            text = { Text("This will permanently delete ${deck?.name ?: "this deck"}.") },
            confirmButton = {
                Button(
                    onClick = {
                        Storage.deleteDeck(deckId)
                        showDeleteDialog = false
                        navController?.popBackStack()
                    }
                ) {
                    Text("Delete")
                }
            },
            dismissButton = {
                Button(onClick = { showDeleteDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }

    if (showEmptyDeckDialog) {
        AlertDialog(
            onDismissRequest = { showEmptyDeckDialog = false },
            title = { Text("Deck is empty") },
            text = { Text("Add cards to this deck before starting a study session.") },
            confirmButton = {
                Button(onClick = { showEmptyDeckDialog = false }) {
                    Text("OK")
                }
            }
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun DeckInformationScreenPreview() {
    MobdevchimpTheme {
        DeckInformationScreen(deckId = 1)
    }
}
