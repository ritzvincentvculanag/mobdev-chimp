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
import io.github.mobdevchimp.components.ChimpButton
import io.github.mobdevchimp.components.ChimpFormField
import io.github.mobdevchimp.components.ChimpScreenHeader
import io.github.mobdevchimp.ui.theme.MobdevchimpTheme

@Composable
fun EditDeckScreen(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val state = rememberTextFieldState("")

        ChimpScreenHeader(
            title = "New Deck",
            description = "Rename your deck"
        )
        ChimpFormField(
            label = "Deck Name",
            state = state,
            placeholder = "e.g. IT Support"
        )
        Spacer(modifier = Modifier.weight(1f))
        ChimpButton(text = "Create")
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun EditDeckScreenPreview() {
    MobdevchimpTheme {
        EditDeckScreen()
    }
}