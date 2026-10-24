package io.github.mobdevchimp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.github.mobdevchimp.components.ChimpButton
import io.github.mobdevchimp.components.Screen
import io.github.mobdevchimp.ui.theme.MobdevchimpTheme
import io.github.mobdevchimp.components.ChimpBodyText
import io.github.mobdevchimp.components.ChimpLargeText

@Composable
fun GetStartedScreen(
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
        ChimpLargeText(text = "Chimp", modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(16.dp))
        ChimpBodyText(
            text = "Let's start memorizing! One deck at a time.",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.weight(1f))
        ChimpButton(
            text = "Get Started",
            onClick = {
                navController?.navigate(Screen.Dashboard.route) {
                    popUpTo(Screen.GetStarted.route) {
                        inclusive = true
                    }
                }
            }
        )
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