package io.github.mobdevchimp.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import io.github.mobdevchimp.ui.theme.MobdevchimpTheme
import io.github.mobdevchimp.ui.theme.NunitoFontFamily
import io.github.mobdevchimp.ui.theme.macawBlue300
import io.github.mobdevchimp.ui.theme.wolfGray100


@Composable
fun ChimpTextField(
    state: TextFieldState,
    placeholder: String = "",
    unfocusedBorderColor: Color = wolfGray100,
    focusedBorderColor: Color = macawBlue300,
    backgroundColor: Color = Color.White,
    placeholderColor: Color = wolfGray100,
    borderWidth: Dp = 2.dp,
    fontSize: TextUnit = MaterialTheme.typography.bodyMedium.fontSize,
    cornerRadius: Dp = 12.dp,
    verticalPadding: Dp = 16.dp,
    horizontalPadding: Dp = 16.dp,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    singleLine: Boolean = true
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val currentBorderColor by animateColorAsState(
        targetValue = if (isFocused) focusedBorderColor else unfocusedBorderColor,
        label = "AnimateBorderColor"
    )

    val shape = RoundedCornerShape(cornerRadius)
    val textStyle = TextStyle(
        fontSize = fontSize,
        fontWeight = FontWeight.Bold,
        color = currentBorderColor,
        fontFamily = NunitoFontFamily
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = backgroundColor, shape = shape)
            .border(
                width = borderWidth,
                color = currentBorderColor,
                shape = shape
            )
            .padding(
                horizontal = horizontalPadding,
                vertical = verticalPadding
            ),
        contentAlignment = Alignment.CenterStart
    ) {
        BasicTextField(
            state = state,
            interactionSource = interactionSource,
            textStyle = textStyle,
            cursorBrush = SolidColor(focusedBorderColor),
            lineLimits = if (singleLine) TextFieldLineLimits.SingleLine else TextFieldLineLimits.Default,
            keyboardOptions = keyboardOptions.copy(
                imeAction = if (singleLine) {
                    ImeAction.Next
                } else {
                    keyboardOptions.imeAction
                }
            ),
            modifier = Modifier.fillMaxWidth(),
            decorator = { innerTextField ->
                Box(modifier = Modifier.fillMaxWidth()) {
                    if (state.text.isEmpty()) {
                        Text(
                            text = placeholder,
                            color = placeholderColor,
                            style = textStyle
                        )
                    }
                    innerTextField()
                }
            }
        )
    }
}

@Preview
@Composable
private fun ChimpTextFieldPreview() {
    MobdevchimpTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPaddings ->
            Column(modifier = Modifier.padding(innerPaddings)) {
                val state = rememberTextFieldState(initialText = "")
                ChimpTextField(
                    state = state,
                    placeholder = "Email",
                )
            }
        }
    }
}