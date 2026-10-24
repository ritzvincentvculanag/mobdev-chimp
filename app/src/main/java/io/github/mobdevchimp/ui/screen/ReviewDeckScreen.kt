package io.github.mobdevchimp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.key
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.github.mobdevchimp.components.ChimpBodyTextMedium
import io.github.mobdevchimp.components.ChimpButton
import io.github.mobdevchimp.components.ChimpButtonOutlined
import io.github.mobdevchimp.components.ChimpCardFlip
import io.github.mobdevchimp.db.Storage

@Composable
@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
fun ReviewDeckScreen(
    deckId: Int,
    navController: NavController? = null,
    modifier: Modifier = Modifier
) {
    val deck = Storage.getDecks().find { it.id == deckId }
    val queue = remember(deckId) { mutableStateListOf(*(deck?.cards.orEmpty().toTypedArray())) }
    var cardIndex by remember(deckId) { mutableIntStateOf(0) }
    val card = queue.getOrNull(cardIndex)

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(deck?.name ?: "Review") },
                navigationIcon = {
                    IconButton(onClick = { navController?.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (card == null) {
                Text("You reviewed all the cards.")
            } else {
                key(card.id) {
                    ChimpCardFlip(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                        front = { ChimpBodyTextMedium(card.front) },
                        back = { ChimpBodyTextMedium(card.back) }
                    )
                }
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    ChimpButtonOutlined(
                        text = "Review",
                        modifier = Modifier.weight(1f),
                        onClick = {
                            val reviewedCard = queue.removeAt(cardIndex)
                            queue.add(reviewedCard)
                            if (cardIndex >= queue.size) cardIndex = 0
                        }
                    )
                    ChimpButton(
                        text = "Got it",
                        modifier = Modifier.weight(1f),
                        onClick = {
                            queue.removeAt(cardIndex)
                            if (cardIndex >= queue.size) cardIndex = 0
                        }
                    )
                }
            }
        }
    }
}
