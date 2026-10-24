package io.github.mobdevchimp.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
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
import io.github.mobdevchimp.components.ChimpBodyText
import io.github.mobdevchimp.components.ChimpCardContainer
import io.github.mobdevchimp.components.ChimpTopBarText
import io.github.mobdevchimp.components.Screen
import io.github.mobdevchimp.db.Storage
import io.github.mobdevchimp.models.Card
import io.github.mobdevchimp.ui.theme.cardinalRed200
import io.github.mobdevchimp.ui.theme.cardinalRed500
import io.github.mobdevchimp.ui.theme.eelBlack300
import io.github.mobdevchimp.ui.theme.featherGreen200
import io.github.mobdevchimp.ui.theme.featherGreen500
import io.github.mobdevchimp.ui.theme.macawBlue100
import io.github.mobdevchimp.ui.theme.macawBlue500

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchGameScreen(
    deckId: Int,
    navController: NavController? = null,
    modifier: Modifier = Modifier
) {
    val deck = Storage.getDecks().find { it.id == deckId }
    val cards = remember(deckId) { deck?.cards.orEmpty() }
    val batches = remember(cards) {
        cards.chunked(if (cards.size <= 5) cards.size.coerceAtLeast(1) else 4)
    }
    val questionResults = remember(deckId) { mutableStateMapOf<Int, Boolean>() }
    val answerResults = remember(deckId) { mutableStateMapOf<Int, Boolean>() }
    val missedCards = remember(deckId) { mutableStateMapOf<Int, Card>() }
    var batchIndex by remember(deckId) { mutableIntStateOf(0) }
    var selectedQuestionId by remember(deckId) { mutableStateOf<Int?>(null) }
    var selectedAnswerId by remember(deckId) { mutableStateOf<Int?>(null) }
    val batch = batches.getOrNull(batchIndex).orEmpty()
    val answerCards = remember(batch) { batch.shuffled() }

    fun selectQuestion(card: Card) {
        if (card.id !in questionResults) {
            selectedQuestionId = card.id
        }
    }

    fun selectAnswer(card: Card) {
        if (card.id !in answerResults) {
            selectedAnswerId = card.id
        }
    }

    LaunchedEffect(selectedQuestionId, selectedAnswerId, batchIndex) {
        val questionId = selectedQuestionId
        val answerId = selectedAnswerId
        if (questionId != null && answerId != null) {
            val isMatch = questionId == answerId
            questionResults[questionId] = isMatch
            answerResults[answerId] = isMatch
            if (!isMatch) {
                batch.firstOrNull { it.id == questionId }?.let { missedCards[it.id] = it }
            }
            selectedQuestionId = null
            selectedAnswerId = null
        }
    }

    LaunchedEffect(batchIndex, questionResults.size, answerResults.size) {
        if (batch.isNotEmpty() && batch.all {
                it.id in questionResults && it.id in answerResults
            }) {
            if (batchIndex == batches.lastIndex) {
                Storage.saveSessionResult(
                    deckId = deckId,
                    totalCards = cards.size,
                    missedCards = cards.filter { it.id in missedCards }
                )
                navController?.navigate(
                    Screen.SessionSummary.createRoute(deckId, allowReview = false)
                ) {
                    popUpTo(Screen.MatchGame.createRoute(deckId)) { inclusive = true }
                }
            } else {
                batchIndex++
                selectedQuestionId = null
                selectedAnswerId = null
            }
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    ChimpTopBarText(
                        text = deck?.name ?: "Match Game",
                        description = "${cards.size} ${if (cards.size == 1) "card" else "cards"} available"
                    )
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
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Batch ${batchIndex + 1} out of ${batches.size}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = eelBlack300
            )
            ChimpBodyText(
                text = "Select a question and its matching answer.",
                textAlign = TextAlign.Center
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "QUESTIONS",
                    modifier = Modifier.weight(1f),
                    fontWeight = FontWeight.Bold,
                    color = eelBlack300,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "ANSWERS",
                    modifier = Modifier.weight(1f),
                    fontWeight = FontWeight.Bold,
                    color = eelBlack300,
                    textAlign = TextAlign.Center
                )
            }
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                MatchColumn(
                    cards = batch,
                    isQuestion = true,
                    modifier = Modifier.weight(1f),
                    selectedCardId = selectedQuestionId,
                    canSelect = selectedQuestionId == null && selectedAnswerId == null,
                    resultCards = questionResults,
                    onCardSelected = ::selectQuestion
                )
                MatchColumn(
                    cards = answerCards,
                    isQuestion = false,
                    modifier = Modifier.weight(1f),
                    selectedCardId = selectedAnswerId,
                    canSelect = selectedQuestionId != null,
                    resultCards = answerResults,
                    onCardSelected = ::selectAnswer
                )
            }
        }
    }
}

@Composable
private fun MatchColumn(
    cards: List<Card>,
    isQuestion: Boolean,
    modifier: Modifier,
    selectedCardId: Int?,
    canSelect: Boolean,
    resultCards: Map<Int, Boolean>,
    onCardSelected: (Card) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(cards, key = { it.id }) { card ->
            val result = resultCards[card.id]
            val isSelected = card.id == selectedCardId
            val cardModifier = Modifier
                .fillMaxWidth()
                .height(112.dp)
                .clickable(
                    enabled = result == null && canSelect,
                    onClick = { onCardSelected(card) }
                )
            if (result == null && !isSelected) {
                ChimpCardContainer(modifier = cardModifier) {
                    MatchCardText(card = card, isQuestion = isQuestion)
                }
            } else if (result == null) {
                ChimpCardContainer(
                    modifier = cardModifier,
                    containerColor = macawBlue100,
                    borderColor = macawBlue500
                ) {
                    MatchCardText(card = card, isQuestion = isQuestion)
                }
            } else {
                ChimpCardContainer(
                    modifier = cardModifier,
                    containerColor = if (result) featherGreen200 else cardinalRed200,
                    borderColor = if (result) featherGreen500 else cardinalRed500
                ) {
                    MatchCardText(card = card, isQuestion = isQuestion)
                }
            }
        }
    }
}

@Composable
private fun MatchCardText(card: Card, isQuestion: Boolean) {
    ChimpBodyText(
        text = if (isQuestion) card.front else card.back,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth(),
        fontSize = 12.sp
    )
}
