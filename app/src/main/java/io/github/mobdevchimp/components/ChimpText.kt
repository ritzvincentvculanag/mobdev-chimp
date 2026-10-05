package io.github.mobdevchimp.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import io.github.mobdevchimp.ui.theme.eelBlack300
import io.github.mobdevchimp.ui.theme.wolfGray100

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
        color = wolfGray100,
        letterSpacing = 1.2.sp
    )
}