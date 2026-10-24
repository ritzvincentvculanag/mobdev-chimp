package io.github.mobdevchimp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.clickable
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.mobdevchimp.ui.theme.MobdevchimpTheme
import io.github.mobdevchimp.ui.theme.cardinalRed300
import io.github.mobdevchimp.models.Card
import io.github.mobdevchimp.ui.theme.eelBlack300
import io.github.mobdevchimp.ui.theme.wolfGray300

@Composable
fun ChimpCardItem(
    card: Card,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    onDeleteClick: () -> Unit = {}
) {
    ChimpCardContainer(modifier = modifier.clickable(onClick = onClick)) {
        Row {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(
                    space = 8.dp,
                    alignment = Alignment.CenterVertically
                )
            ) {
                ChimpBodyText(
                    text = card.front,
                    fontWeight = FontWeight.Bold,
                    color = eelBlack300
                )
                ChimpBodyText(
                    text = card.back,
                    fontSize = 14.sp,
                    color = wolfGray300
                )
            }
            IconButton(onClick = onDeleteClick) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete card",
                    tint = cardinalRed300
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ChimpCardItemPreview() {
    MobdevchimpTheme {
        ChimpCardItem(
            card = Card(
                id = 1,
                deckId = 1,
                front = "Card front/question",
                back = "Card back/answer"
            )
        )
    }
}