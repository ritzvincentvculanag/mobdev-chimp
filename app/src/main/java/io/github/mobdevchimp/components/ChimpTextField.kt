package io.github.mobdevchimp.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import io.github.mobdevchimp.ui.theme.MobdevchimpTheme
import io.github.mobdevchimp.ui.theme.macawBlue300
import io.github.mobdevchimp.ui.theme.macawBlue500
import io.github.mobdevchimp.ui.theme.wolfGray100
import io.github.mobdevchimp.ui.theme.wolfGray300


@Composable
fun ChimpTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    placeholder: String = "",
    unfocusedBorderColor: Color = wolfGray100,
    unfocusedShadowColor: Color = wolfGray100,
    focusedBorderColor: Color = macawBlue300,
    focusedShadowColor: Color = macawBlue500,
    backgroundColor: Color = Color.White,
    textColor: Color = wolfGray300,
    placeholderColor: Color = wolfGray100,
    borderWidth: Dp = 2.dp,
    fontSize: TextUnit = MaterialTheme.typography.bodySmall.fontSize,
    cornerRadius: Dp = 12.dp,
    offset: Dp = 4.dp,
    verticalPadding: Dp = 16.dp,
    horizontalPadding: Dp = 16.dp,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = true
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