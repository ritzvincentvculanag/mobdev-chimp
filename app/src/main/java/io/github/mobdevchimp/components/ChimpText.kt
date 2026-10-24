package io.github.mobdevchimp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.mobdevchimp.ui.theme.eelBlack300
import io.github.mobdevchimp.ui.theme.swanGray300
import io.github.mobdevchimp.ui.theme.wolfGray100
import io.github.mobdevchimp.ui.theme.wolfGray300

@Composable
fun ChimpTitle(text: String, ) {
    Text(
        text = text,
        fontSize = MaterialTheme.typography.headlineLarge.fontSize,
        fontWeight = MaterialTheme.typography.headlineLarge.fontWeight,
        color = eelBlack300
    )
}

@Composable
fun ChimpSectionTitle(text: String) {
    Text(
        text = text.uppercase(),
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        color = eelBlack300,
        letterSpacing = 1.2.sp
    )
}

@Composable
fun ChimpBodyText(text: String) {
    Text(
        text = text,
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        color = wolfGray300
    )
}

@Composable
fun ChimpScreenHeader(
    title: String,
    description: String
) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        ChimpTitle(text = title)
        ChimpBodyText(text = description)
    }
}