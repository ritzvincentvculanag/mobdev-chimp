package io.github.mobdevchimp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.mobdevchimp.components.ChimpButton
import io.github.mobdevchimp.components.ChimpFormField
import io.github.mobdevchimp.db.Storage
import io.github.mobdevchimp.ui.theme.MobdevchimpTheme
import io.github.mobdevchimp.components.ChimpSmallText
import io.github.mobdevchimp.ui.theme.cardinalRed500

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun EditDeckScreen(
    deckId: Int? = null,
    navController: NavController? = null,
    modifier: Modifier = Modifier
) {
    val deck = deckId?.let { id -> Storage.getDecks().find { it.id == id } }
    val state = rememberTextFieldState(deck?.name.orEmpty())
    var showValidationError by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text("Edit Deck") },
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
            ChimpFormField(
                label = "Deck Name",
                state = state,
                placeholder = "e.g. IT Support"
            )
            if (showValidationError) {
                ChimpSmallText(
                    text = "Enter a deck name before saving.",
                    color = cardinalRed500
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            ChimpButton(
                text = "Save",
                onClick = {
                    val name = state.text.toString().trim()
                    if (name.isNotEmpty()) {
                        showValidationError = false
                        deckId?.let { Storage.updateDeck(it, name) }
                        navController?.popBackStack()
                    } else {
                        showValidationError = true
                    }
                }
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun EditDeckScreenPreview() {
    MobdevchimpTheme {
        EditDeckScreen()
    }
}