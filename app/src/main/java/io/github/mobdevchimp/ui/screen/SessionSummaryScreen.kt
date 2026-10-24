package io.github.mobdevchimp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import io.github.mobdevchimp.components.ChimpButton
import io.github.mobdevchimp.components.ChimpButtonOutlined
import io.github.mobdevchimp.components.Screen
import io.github.mobdevchimp.db.Storage
import io.github.mobdevchimp.ui.theme.beeYellow400

@Composable
fun SessionSummaryScreen(
    deckId: Int,
    navController: NavController? = null,
    modifier: Modifier = Modifier
) {
    val result = Storage.getSessionResult(deckId)
    val missedCount = result?.missedCards?.size ?: 0
    val masteredCount = (result?.totalCards ?: 0) - missedCount

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = Icons.Default.EmojiEvents,
            contentDescription = "Session complete",
            tint = beeYellow400,
            modifier = Modifier.size(96.dp)
        )
        Text(
            text = "Session Complete!",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(text = "Cards mastered: $masteredCount")
        Text(text = "Cards missed: $missedCount")
        Spacer(modifier = Modifier.weight(1f))
        ChimpButton(
            text = "Back to Dashboard",
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                navController?.let { controller ->
                    if (!controller.popBackStack(Screen.Dashboard.route, false)) {
                        controller.navigate(Screen.Dashboard.route)
                    }
                }
            }
        )
        if (missedCount > 0) {
            ChimpButtonOutlined(
                text = "Review Missed Cards",
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    navController?.navigate(Screen.ReviewDeck.createRoute(deckId, missedOnly = true))
                }
            )
        }
    }
}
