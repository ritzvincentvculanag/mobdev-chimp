package io.github.mobdevchimp.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.mobdevchimp.ui.theme.MobdevchimpTheme
import io.github.mobdevchimp.ui.theme.macawBlue300
import io.github.mobdevchimp.ui.theme.macawBlue500

@Composable
private fun rememberPressOffset(interactionSource: MutableInteractionSource): Dp {
    val isPressed by interactionSource.collectIsPressedAsState()
    val currentOffset by animateDpAsState(
        label = "AnimationButtonPress",
        targetValue = if (isPressed) 4.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessHigh
        )
    )
    return currentOffset
}

@Composable
fun ChimpButton(
    text: String,
    radius: Dp = 12.dp,
    foreground: Color = macawBlue300,
    background: Color = macawBlue500,
    textColor: Color = Color.White,
    onClick: () -> Unit,
    icon: ImageVector? = null
) {
    val interactionSource = remember { MutableInteractionSource() }
    val currentOffset = rememberPressOffset(interactionSource)
    val width = if (icon != null) {
        Modifier.fillMaxWidth()
    } else {
        Modifier
    }

    Box(
        modifier = Modifier
            .then(width)
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
            modifier = Modifier
                .padding(top = 4.dp)
                .fillMaxWidth()
                .height(48.dp)
        )
        ChimpButtonContainer(
            color = foreground,
            radius = radius,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .offset {
                    IntOffset(
                        x = 0,
                        y = currentOffset.roundToPx()
                    )
                }
        ) {
            Row(
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
                    letterSpacing = 1.2.sp,
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                    fontWeight = FontWeight.Bold,
                    color = textColor
                )
            }
        }
    }
}

@Composable
fun ChimpButtonIcon(
    icon: ImageVector,
    size: Dp = 48.dp,
    radius: Dp = 12.dp,
    foreground: Color = macawBlue300,
    background: Color = macawBlue500,
    iconTint: Color = Color.White,
    contentDescription: String? = null,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val currentOffset = rememberPressOffset(interactionSource)

    Box(
        modifier = Modifier
            .size(width = size, height = size + 4.dp)
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
            modifier = Modifier.padding(top = 4.dp).size(size)
        )
        ChimpButtonContainer(
            color = foreground,
            radius = radius,
            modifier = Modifier
                .size(size)
                .offset {
                    IntOffset (
                        x = 0,
                        y = currentOffset.roundToPx()
                    )
                }
        ) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                tint = iconTint
            )
        }
    }
}

@Composable
private fun ChimpButtonContainer(
    color: Color,
    radius: Dp,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {}
) {
    Box(
        modifier = modifier.background(color = color, shape = RoundedCornerShape(radius)),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

@Preview
@Composable
private fun ChimpButtonPreview() {
    MobdevchimpTheme {
        ChimpButtonIcon(icon = Icons.Default.PlayArrow) { }
    }
}