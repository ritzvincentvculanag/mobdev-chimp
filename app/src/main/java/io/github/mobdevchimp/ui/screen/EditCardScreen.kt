package io.github.mobdevchimp.ui.screen

import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import io.github.mobdevchimp.db.Storage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditCardScreen(
    deckId: Int,
    cardId: Int,
    navController: NavController? = null
) {
    val card = remember(deckId, cardId) {
        Storage.getDecks()
            .firstOrNull { it.id == deckId }
            ?.cards
            ?.firstOrNull { it.id == cardId }
    }
    val frontState = rememberTextFieldState(card?.front.orEmpty())
    val backState = rememberTextFieldState(card?.back.orEmpty())

    CardEditorLayout(
        title = "Edit Card",
        description = "Update the question and answer.",
        frontState = frontState,
        backState = backState,
        modifier = androidx.compose.ui.Modifier,
        navController = navController,
        actionText = "Save Changes",
        onSave = {
            Storage.updateCard(
                deckId = deckId,
                cardId = cardId,
                front = frontState.text.toString().trim(),
                back = backState.text.toString().trim()
            )
            navController?.popBackStack()
        }
    )
}
