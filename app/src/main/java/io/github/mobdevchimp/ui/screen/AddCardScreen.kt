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
fun AddCardScreen(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val state = rememberTextFieldState("")

        ChimpScreenHeader(
            title = "Add Card",
            description = "Fill in the details of the card below."
        )
        ChimpFormField(
            label = "Front (Question)",
            state = state,
            placeholder = "Enter question...",
            singleLine = false
        )
        ChimpFormField(
            label = "Front (Question)",
            state = state,
            placeholder = "Enter question...",
            singleLine = false
        )
        Spacer(modifier = Modifier.weight(1f))
        ChimpButton(text = "Save Card")
    }
}

@Preview(showSystemUi = true)
@Composable
private fun AddCardScreenPreview() {
    MobdevchimpTheme {
        AddCardScreen()
    }
}