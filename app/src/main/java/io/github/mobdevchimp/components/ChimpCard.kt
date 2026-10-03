package io.github.mobdevchimp.components

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.mobdevchimp.ui.theme.MobdevchimpTheme
import io.github.mobdevchimp.ui.theme.eelBlack100
import io.github.mobdevchimp.ui.theme.macawBlue500
import io.github.mobdevchimp.ui.theme.swanGray100
import io.github.mobdevchimp.ui.theme.swanGray300

private object ChimpCardDefaults {
    val borderWidth: Dp = 2.dp
    val borderColor: Color = swanGray300
    val contentPadding: PaddingValues = PaddingValues(16.dp)
    val containerColor: Color = swanGray100
    val shape: Shape = RoundedCornerShape(12.dp)
    val cameraDistance: Dp = 12.dp
    val flipAnimationSpec: AnimationSpec<Float> = tween(
        durationMillis = 500,
        easing = FastOutSlowInEasing
    )
}

@Composable
fun ChimpCardContainer(
    modifier: Modifier = Modifier,
    shape: Shape = ChimpCardDefaults.shape,
    containerColor: Color = ChimpCardDefaults.containerColor,
    borderColor: Color = ChimpCardDefaults.borderColor,
    contentPadding: PaddingValues = ChimpCardDefaults.contentPadding,
    contentAlignment: Alignment = Alignment.Center,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier,
        shape = shape,
        color = containerColor,
        border = BorderStroke(
            width = ChimpCardDefaults.borderWidth,
            color = borderColor
        )
    ) {
        Box(
            modifier = Modifier.padding(contentPadding),
            contentAlignment = contentAlignment
        ) {
            content()
        }
    }
}

@Composable
fun ChimpCardAnalytics(
    value: Number,
    title: String,
    modifier: Modifier = Modifier
) {
    ChimpCardContainer(modifier = modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = value.toString(),
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold,
                color = macawBlue500
            )
            Text(
                text = title.uppercase(),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = eelBlack100
            )
        }
    }
}

@Composable
fun ChimpCardFlip(
    front: @Composable () -> Unit,
    back: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    isFlipped: Boolean? = null,
    onFlip: ((Boolean) -> Unit)? = null,
) {

    var internalFlipped by rememberSaveable { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    val flipped = isFlipped ?: internalFlipped

    val density = LocalDensity.current.density
    val rotation by animateFloatAsState(
        label = "AnimationCardFlip",
        targetValue = if (flipped) 180f else 0f,
        animationSpec = ChimpCardDefaults.flipAnimationSpec
    )

    ChimpCardContainer(
        modifier = modifier
            .graphicsLayer {
                rotationY = rotation
//                cameraDistance = ChimpCardDefaults.cameraDistance * density
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
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) { front() }
        } else {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        rotationY = 180f
                    }
            ) {
                back()
            }
        }
    }
}






@Preview
@Composable
private fun ChimpCardAnalyticsContainerPreview() {
    MobdevchimpTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPaddings ->
            ChimpCardFlip(
                front = { Text("Hi! I'm front!") },
                back = { Text("Hi! I'm back!") }
            )
            ChimpCardAnalytics(
                value = 28,
                title = "Day Streak",
                modifier = Modifier
                    .padding(innerPaddings)
                    .padding(16.dp)
            )
        }
    }
}