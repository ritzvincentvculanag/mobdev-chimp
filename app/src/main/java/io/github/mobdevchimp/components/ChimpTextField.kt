package io.github.mobdevchimp.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.mobdevchimp.ui.theme.MobdevchimpTheme

@Composable
fun ChimpTextField(
    value: String,
    onValueChange: String,
    placeholder: String = "",
    isError: Boolean = false,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions,
    cornerRadius: Dp = 12.dp,
) {

}

@Preview
@Composable
private fun ChimpTextFieldPreview() {
    MobdevchimpTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPaddings ->
            Column(modifier = Modifier.padding(innerPaddings)) {
            }
        }
    }
}