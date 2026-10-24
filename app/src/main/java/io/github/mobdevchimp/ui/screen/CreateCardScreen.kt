package io.github.mobdevchimp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.github.mobdevchimp.components.ChimpButton
import io.github.mobdevchimp.components.ChimpFormField
import io.github.mobdevchimp.components.Screen
import io.github.mobdevchimp.db.Storage
import io.github.mobdevchimp.ui.theme.cardinalRed500

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCardScreen(
    deckId: Int,
    navController: NavController? = null,
    modifier: Modifier = Modifier
) {
    val frontState = rememberTextFieldState("")
    val backState = rememberTextFieldState("")
    var validationError by remember { mutableStateOf<String?>(null) }

    CardEditorLayout(
        title = "Add Card",
        description = "Fill in the details of the card below.",
        frontState = frontState,
        backState = backState,
        modifier = modifier,
        navController = navController,
        actionText = "Save Card",
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
                val card = Storage.addCard(deckId, front, back)
                if (card != null) {
                    navController?.navigate(Screen.CardSaved.createRoute(deckId)) {
                        popUpTo(Screen.AddCard.createRoute(deckId)) { inclusive = true }
                    }
                }
            }
        }
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun CardEditorLayout(
    title: String,
    description: String,
    frontState: androidx.compose.foundation.text.input.TextFieldState,
    backState: androidx.compose.foundation.text.input.TextFieldState,
    modifier: Modifier,
    navController: NavController?,
    actionText: String,
    validationError: String? = null,
    onSave: () -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(title) },
                navigationIcon = {
                    IconButton(onClick = { navController?.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            io.github.mobdevchimp.components.ChimpDescription(text = description)
            ChimpFormField(
                label = "Front (Question)",
                state = frontState,
                placeholder = "Enter question...",
                singleLine = false
            )
            if (validationError != null) {
                io.github.mobdevchimp.components.ChimpSmallText(
                    text = validationError,
                    color = cardinalRed500
                )
            }
            ChimpFormField(
                label = "Back (Answer)",
                state = backState,
                placeholder = "Enter answer...",
                singleLine = false
            )
            Spacer(modifier = Modifier.weight(1f))
            ChimpButton(text = actionText, onClick = onSave)
        }
    }
}
