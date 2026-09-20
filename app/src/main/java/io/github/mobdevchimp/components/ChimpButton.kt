package io.github.mobdevchimp.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.mobdevchimp.ui.theme.MobdevchimpTheme
import io.github.mobdevchimp.ui.theme.macawBlue300
import io.github.mobdevchimp.ui.theme.macawBlue500

@Composable
fun ChimpButton(
    text: String, radius: Dp = 12.dp,
    foreground: Color = macawBlue300,
    background: Color = macawBlue500,
    textColor: Color = Color.White,
    onClick: () -> Unit,
    icon: ImageVector? = null
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val currentOffset by animateDpAsState(
        label = "AnimationButtonPress",
        targetValue = if (isPressed) 4.dp else 0.dp
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp + 4.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick
            ),
        contentAlignment = Alignment.TopCenter
    ) {
        ChimpButtonContainer(
            color = background,
            radius = radius,
            modifier = Modifier.padding(top = 4.dp)
        )
        ChimpButtonContainer(
            color = foreground,
            radius = radius,
            modifier = Modifier.offset(y = currentOffset)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                if (icon != null) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Text(
                    text = text.uppercase(),
                    textAlign = TextAlign.Center,
                    letterSpacing = 2.sp,
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                    fontWeight = MaterialTheme.typography.bodyMedium.fontWeight,
                    color = textColor
                )
            }
        }
    }
}

@Composable
private fun ChimpButtonContainer(
    color: Color,
    radius: Dp,
    modifier: Modifier = Modifier,
    buttonText: @Composable () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(
                color = color,
                shape = RoundedCornerShape(radius)
            ),
        contentAlignment = Alignment.Center
    ) {
        buttonText()
    }
}

@Preview
@Composable
private fun ChimpButtonPreview() {
    MobdevchimpTheme {
        ChimpButton(text = "Hello", onClick = {})
    }
}