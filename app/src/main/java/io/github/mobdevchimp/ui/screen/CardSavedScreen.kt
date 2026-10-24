package io.github.mobdevchimp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.github.mobdevchimp.components.Screen
import io.github.mobdevchimp.components.ChimpButton
import io.github.mobdevchimp.components.ChimpButtonOutlined
import io.github.mobdevchimp.ui.theme.MobdevchimpTheme
import io.github.mobdevchimp.ui.theme.macawBlue100
import io.github.mobdevchimp.ui.theme.macawBlue500

@Composable
fun CardSavedScreen(
    deckId: Int,
    navController: NavController? = null,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier
                .size(88.dp)
                .background(macawBlue100, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Card saved",
                tint = macawBlue500,
                modifier = Modifier.size(48.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Card saved!",
            style = MaterialTheme.typography.headlineLarge,
            color = macawBlue500,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.weight(1f))
        ChimpButton(
            text = "Add Another Card",
            onClick = {
                navController?.navigate(Screen.AddCard.createRoute(deckId)) {
                    popUpTo(Screen.CardSaved.createRoute(deckId)) { inclusive = true }
                }
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        ChimpButtonOutlined(
            text = "Done",
            onClick = {
                navController?.navigate(Screen.DeckInformation.createRoute(deckId)) {
                    popUpTo(Screen.CardSaved.createRoute(deckId)) { inclusive = true }
                }
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CardSavedScreenPreview() {
    MobdevchimpTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPaddings ->
            CardSavedScreen(deckId = 1, modifier = Modifier.padding(innerPaddings))
        }
    }
}
