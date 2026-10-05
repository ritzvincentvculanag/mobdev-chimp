package io.github.mobdevchimp.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import io.github.mobdevchimp.ui.theme.eelBlack300

@Composable
fun ChimpTitle(text: String, ) {
    Text(
        text = text,
        fontSize = MaterialTheme.typography.titleLarge.fontSize,
        fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
        color = eelBlack300
    )
}