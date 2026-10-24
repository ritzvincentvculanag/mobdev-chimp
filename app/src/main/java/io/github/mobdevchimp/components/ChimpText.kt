package io.github.mobdevchimp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.mobdevchimp.ui.theme.eelBlack300
import io.github.mobdevchimp.ui.theme.wolfGray300

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
        color = eelBlack300,
        letterSpacing = 1.2.sp
    )
}

@Composable
fun ChimpBodyText(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 16.sp,
    fontWeight: FontWeight = FontWeight.Medium,
    color: androidx.compose.ui.graphics.Color = wolfGray300,
    textAlign: TextAlign? = null
) {
    Text(
        text = text,
        modifier = modifier,
        fontSize = fontSize,
        fontWeight = fontWeight,
        color = color,
        textAlign = textAlign
    )
}

@Composable
fun ChimpSmallText(
    text: String,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.Medium,
    color: androidx.compose.ui.graphics.Color = wolfGray300,
    letterSpacing: TextUnit = TextUnit.Unspecified
) {
    Text(
        text = text,
        modifier = modifier,
        fontSize = 14.sp,
        fontWeight = fontWeight,
        letterSpacing = letterSpacing,
        color = color
    )
}

@Composable
fun ChimpLargeText(
    text: String,
    modifier: Modifier = Modifier,
    fontWeight: FontWeight = FontWeight.Medium,
    color: androidx.compose.ui.graphics.Color = eelBlack300
) {
    ChimpBodyText(
        text = text,
        modifier = modifier,
        fontSize = 18.sp,
        fontWeight = fontWeight,
        color = color
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
        color = eelBlack300,
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
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        ChimpLargeText(text = text, fontWeight = FontWeight.Bold, color = eelBlack300)
        if (description.isNotEmpty()) {
            ChimpSmallText(text = description, color = wolfGray300)
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
        Text(
            text = title,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = eelBlack300
        )
        if (description.isNotEmpty()) {
            ChimpDescription(text = description)
        }
    }
}
