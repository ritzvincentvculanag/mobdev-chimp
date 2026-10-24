package io.github.mobdevchimp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.mobdevchimp.ui.theme.macawBlue700

@Composable
fun ChimpScreenTitle(
    text: String,
    modifier: Modifier = Modifier
) {
    ChimpLargeText(
        text = text,
        modifier = modifier,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun ChimpDescription(
    text: String,
    modifier: Modifier = Modifier
) {
    ChimpBodyText(text = text, modifier = modifier)
}

@Composable
fun ChimpSectionTitle(
    text: String,
    modifier: Modifier = Modifier
) {
    ChimpSmallText(
        text = text.uppercase(),
        modifier = modifier,
        fontWeight = FontWeight.Bold,
        letterSpacing = 1.2.sp
    )
}

@Composable
fun ChimpBodyText(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 16.sp,
    fontWeight: FontWeight = FontWeight.Medium,
    textAlign: TextAlign? = null
) {
    Text(
        text = text,
        modifier = modifier,
        fontSize = fontSize,
        fontWeight = fontWeight,
        color = macawBlue700,
        textAlign = textAlign
    )
}

@Composable
fun ChimpSmallText(
    text: String,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.Medium,
    letterSpacing: TextUnit = TextUnit.Unspecified
) {
    Text(
        text = text,
        modifier = modifier,
        fontSize = 14.sp,
        fontWeight = fontWeight,
        letterSpacing = letterSpacing,
        color = macawBlue700
    )
}

@Composable
fun ChimpLargeText(
    text: String,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.Medium
) {
    ChimpBodyText(
        text = text,
        modifier = modifier,
        fontSize = 18.sp,
        fontWeight = fontWeight
    )
}

@Composable
fun ChimpCardFlipText(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Center
) {
    ChimpBodyText(
        text = text,
        modifier = modifier,
        fontWeight = FontWeight.Bold,
        textAlign = textAlign
    )
}

@Composable
fun ChimpTopBarText(
    text: String,
    description: String = "",
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ChimpLargeText(text = text, fontWeight = FontWeight.Bold)
        if (description.isNotEmpty()) {
            ChimpSmallText(text = description)
        }
    }
}

@Composable
fun ChimpScreenHeader(
    title: String,
    description: String = "",
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ChimpScreenTitle(text = title)
        if (description.isNotEmpty()) {
            ChimpDescription(text = description)
        }
    }
}
