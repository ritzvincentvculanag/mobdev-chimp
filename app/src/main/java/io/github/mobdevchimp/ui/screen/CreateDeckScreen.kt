package io.github.mobdevchimp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.github.mobdevchimp.components.ChimpButton
import io.github.mobdevchimp.components.ChimpFormField
import io.github.mobdevchimp.components.ChimpScreenHeader
import io.github.mobdevchimp.components.Screen
import io.github.mobdevchimp.db.Storage
import io.github.mobdevchimp.ui.theme.MobdevchimpTheme

@Composable
fun CreateDeckScreen(
    navController: NavController? = null,
    modifier: Modifier = Modifier
) {
    val state = rememberTextFieldState("")

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        ChimpScreenHeader(
            title = "New Deck",
            description = "Create a focus topic to organize your cards."
        )
        ChimpFormField(
            label = "Deck Name",
            state = state,
            placeholder = "e.g. IT Support"
        )
        Spacer(modifier = Modifier.weight(1f))
        ChimpButton(
            text = "Create",
            onClick = {
                val name = state.text.toString().trim()
                if (name.isNotEmpty()) {
                    val deck = Storage.addDeck(name)
                    navController?.navigate(Screen.DeckInformation.createRoute(deck.id))
                }
            }
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun CreateDeckScreenPreview() {
    MobdevchimpTheme {
        CreateDeckScreen()
    }
}