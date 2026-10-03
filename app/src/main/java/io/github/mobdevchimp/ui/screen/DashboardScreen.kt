package io.github.mobdevchimp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.mobdevchimp.components.ChimpCardFlip

@Composable
fun DashboardScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        ChimpCardFlip(
            front = { Text("Hi! I'm front!")  },
            back = { Text("Hi! I'm back!") }
        )
    }
}