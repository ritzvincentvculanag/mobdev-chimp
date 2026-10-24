package io.github.mobdevchimp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.TouchApp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import io.github.mobdevchimp.components.ChimpCardFlipText
import io.github.mobdevchimp.components.ChimpSmallText
import io.github.mobdevchimp.components.ChimpButton
import io.github.mobdevchimp.components.ChimpButtonOutlined
import io.github.mobdevchimp.components.ChimpCardFlip
import io.github.mobdevchimp.components.ChimpTopBarText
import io.github.mobdevchimp.components.Screen
import io.github.mobdevchimp.db.Storage
import io.github.mobdevchimp.ui.theme.beeYellow100
import io.github.mobdevchimp.ui.theme.beeYellow300
import io.github.mobdevchimp.ui.theme.eelBlack300

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewDeckScreen(
    deckId: Int,
    missedOnly: Boolean = false,
    navController: NavController? = null,
    modifier: Modifier = Modifier
) {
    val deck = Storage.getDecks().find { it.id == deckId }
    val initialCards = remember(deckId, missedOnly) {
        Storage.getReviewCards(deckId, missedOnly)
    }
    val queue = remember(deckId, missedOnly) {
        mutableStateListOf(*initialCards.toTypedArray())
    }
    val missedCards = remember(deckId, missedOnly) { mutableStateListOf<io.github.mobdevchimp.models.Card>() }
    var cardIndex by remember(deckId, missedOnly) { mutableIntStateOf(0) }
    var isFlipped by remember(queue.getOrNull(cardIndex)?.id) { mutableStateOf(false) }
    val card = queue.getOrNull(cardIndex)

    LaunchedEffect(cardIndex, queue.size) {
        isFlipped = false
        if (card == null) {
            Storage.saveSessionResult(deckId, initialCards.size, missedCards.toList())
            navController?.navigate(Screen.SessionSummary.createRoute(deckId, allowReview = true)) {
                popUpTo(Screen.ReviewDeck.createRoute(deckId, missedOnly)) { inclusive = true }
            }
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        ChimpTopBarText(
                            text = deck?.name.toString(),
                            description = "${initialCards.size} ${if (initialCards.size == 1) "card" else "cards"} available"
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController?.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = eelBlack300
                        )
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
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (card != null) {
                Text(
                    text = "${cardIndex + 1} out of ${initialCards.size}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = eelBlack300,
                    textAlign = TextAlign.Center
                )
                key(card.id) {
                    ChimpCardFlip(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        onFlippedChange = { isFlipped = it },
                        frontColor = beeYellow100,
                        backColor = beeYellow300,
                        front = {
                            ChimpCardFlipText(text = card.front)
                        },
                        back = {
                            ChimpCardFlipText(text = card.back)
                        }
                    )
                }
                if (isFlipped) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        ChimpButtonOutlined(
                            text = "Review Again",
                            modifier = Modifier.weight(1f),
                            onClick = {
                                missedCards.add(card)
                                cardIndex++
                            }
                        )
                        ChimpButton(
                            text = "Got It",
                            modifier = Modifier.weight(1f),
                            onClick = { cardIndex++ }
                        )
                    }
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.TouchApp,
                        contentDescription = "Tap to flip",
                        tint = eelBlack300
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    ChimpSmallText(text = "Tap to flip")
                }
            }
        }
    }
}
