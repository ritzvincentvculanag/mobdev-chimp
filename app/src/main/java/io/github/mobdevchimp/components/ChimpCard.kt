package io.github.mobdevchimp.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.mobdevchimp.ui.theme.MobdevchimpTheme
import io.github.mobdevchimp.ui.theme.eelBlack100
import io.github.mobdevchimp.ui.theme.macawBlue500

@Composable
fun ChimpCardAnalytics(
    data: Number,
    title: String,
    modifier: Modifier = Modifier
) {
    ChimpCardContainer(modifier = modifier) {
        Text(
            text = data.toString(),
            fontSize = 28.sp,
            fontWeight = FontWeight.ExtraBold,
            color = macawBlue500
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = title.uppercase(),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = eelBlack100
        )
    }
}

@Composable
fun ChimpCardFlip(
    front: @Composable () -> Unit,
    back: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    isFlipped: Boolean? = null,
    onFlip: ((Boolean) -> Unit)? = null,
    durationMillis: Int = 500,
    cameraDistance: Float = 12f
) {

    var internalFlipped by rememberSaveable { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    val flipped = isFlipped ?: internalFlipped

    val density = LocalDensity.current.density
    val rotation by animateFloatAsState(
        label = "AnimationCardFlip",
        targetValue = if (flipped) 180f else 0f,
        animationSpec = tween(
            durationMillis = durationMillis,
            easing = FastOutSlowInEasing
        )
    )

    ChimpCardContainer(
        modifier = modifier
            .graphicsLayer {
                rotationY = rotation
                this.cameraDistance = cameraDistance * density
            }
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = {
                    val newValue = !flipped
                    if (isFlipped == null) internalFlipped = newValue
                    onFlip?.invoke(newValue)
                }
            )
    ) {
        if (rotation <= 90f) {
            Box { front() }
        } else {
            Box(modifier = Modifier.graphicsLayer { rotationY = 180f }) {
                back()
            }
        }
    }
}

@Composable
fun ChimpCardContainer(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .background(
                color = Color.White,
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                width = 2.dp,
                color = Color(0xE5E7EBFF),
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            content()
        }
    }
}

@Preview
@Composable
private fun ChimpCardAnalyticsContainerPreview() {
    MobdevchimpTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPaddings ->
            ChimpCardFlip(
                front = { ChimpCardContainer { Text("Hi! I'm front!") } },
                back = { ChimpCardContainer { Text("Hi! I'm back!") } }
            )
            ChimpCardAnalytics(
                data = 28,
                title = "Day Streak",
                modifier = Modifier
                    .padding(innerPaddings)
                    .padding(16.dp)
            )
        }
    }
}