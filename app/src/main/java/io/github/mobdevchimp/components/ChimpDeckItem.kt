package io.github.mobdevchimp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.mobdevchimp.ui.theme.MobdevchimpTheme
import io.github.mobdevchimp.ui.theme.eelBlack100
import io.github.mobdevchimp.ui.theme.eelBlack300

@Composable
private fun ChimpDeckItemContent(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(
            text = title,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
            color = eelBlack300
        )
        Text(
            text = subtitle,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            fontWeight = MaterialTheme.typography.bodyMedium.fontWeight,
            color = eelBlack100
        )
    }
}

@Composable
fun ChimpDeckItem(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    ChimpCardContainer {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ChimpDeckItemContent(title = title, subtitle = subtitle)
            ChimpButtonIcon(icon = Icons.Default.PlayArrow)
        }
    }
}

@Preview
@Composable
private fun ChimpDeckItemPreview() {
    MobdevchimpTheme {
        ChimpDeckItem(title = "Biology Chapter 4", subtitle = "12 Cards")
    }
}