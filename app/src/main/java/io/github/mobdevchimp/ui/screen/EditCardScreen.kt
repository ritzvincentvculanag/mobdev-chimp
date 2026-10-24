package io.github.mobdevchimp.ui.screen

import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import io.github.mobdevchimp.db.Storage
import io.github.mobdevchimp.ui.theme.cardinalRed500

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
    var validationError by remember { mutableStateOf<String?>(null) }

    CardEditorLayout(
        title = "Edit Card",
        description = "Update the question and answer.",
        frontState = frontState,
        backState = backState,
        modifier = androidx.compose.ui.Modifier,
        navController = navController,
        actionText = "Save Changes",
        validationError = validationError,
        onSave = {
            val front = frontState.text.toString().trim()
            val back = backState.text.toString().trim()
            validationError = when {
                front.isEmpty() && back.isEmpty() -> "Enter a question and answer."
                front.isEmpty() -> "Enter a question before saving."
                back.isEmpty() -> "Enter an answer before saving."
                else -> null
            }
            if (validationError == null) {
                Storage.updateCard(deckId, cardId, front, back)
                navController?.popBackStack()
            }
        }
    )
}
