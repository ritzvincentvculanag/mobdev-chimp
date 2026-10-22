package io.github.mobdevchimp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.github.mobdevchimp.components.ChimpButton
import io.github.mobdevchimp.components.ChimpButtonIcon
import io.github.mobdevchimp.components.ChimpCardContainer
import io.github.mobdevchimp.models.Deck
import androidx.compose.ui.graphics.Color

@Composable
fun DeckStudyScreen(
    deck: Deck,
    onBackClick: () -> Unit
) {
    var cardIndex by remember { mutableIntStateOf(0) }
    var showingAnswer by remember { mutableStateOf(false) }
    val card = deck.cards[cardIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ChimpButtonIcon(
                icon = Icons.Default.ArrowBack,
                contentDescription = "Back to decks",
                onClick = onBackClick
            )
            Text(
                text = deck.name,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineSmall
            )
        }

        Text(
            text = "Card ${cardIndex + 1} of ${deck.cards.size}",
            style = MaterialTheme.typography.bodyMedium
        )

        ChimpCardContainer(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            containerColor = Color(0xFF00241B)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = if (showingAnswer) card.back else card.front,
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = if (showingAnswer) "Answer" else "Question",
                    modifier = Modifier.padding(top = 16.dp),
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }

        ChimpButton(
            text = if (showingAnswer) "Show Question" else "Show Answer",
            onClick = { showingAnswer = !showingAnswer }
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ChimpButton(
                text = "Previous",
                onClick = {
                    if (cardIndex > 0) {
                        cardIndex--
                        showingAnswer = false
                    }
                }
            )
            ChimpButton(
                text = "Next",
                onClick = {
                    if (cardIndex < deck.cards.lastIndex) {
                        cardIndex++
                        showingAnswer = false
                    }
                }
            )
        }
    }
}
