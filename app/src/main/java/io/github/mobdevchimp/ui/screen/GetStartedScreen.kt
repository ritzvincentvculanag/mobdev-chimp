package io.github.mobdevchimp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.mobdevchimp.components.ChimpButton
import io.github.mobdevchimp.ui.theme.MobdevchimpTheme
import io.github.mobdevchimp.ui.theme.macawBlue500
import io.github.mobdevchimp.ui.theme.wolfGray100

@Composable
fun GetStartedScreen(modifier: Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "Chimp",
            textAlign = TextAlign.Center,
            fontSize = MaterialTheme.typography.displayLarge.fontSize,
            fontFamily = MaterialTheme.typography.displayLarge.fontFamily,
            fontWeight = MaterialTheme.typography.displayLarge.fontWeight,
            color = macawBlue500,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Let’s start memorizing! One deck at a time.",
            textAlign = TextAlign.Center,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
            fontWeight = MaterialTheme.typography.bodyMedium.fontWeight,
            color = wolfGray100,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.weight(1f))
        ChimpButton(text = "Get Started", onClick = {})
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun GetStartedScreenPreview() {
    MobdevchimpTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPaddings ->
            GetStartedScreen(modifier = Modifier
                .padding(innerPaddings)
                .padding(16.dp))
        }
    }
}