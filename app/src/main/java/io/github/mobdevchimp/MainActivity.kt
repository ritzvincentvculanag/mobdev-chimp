package io.github.mobdevchimp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.mobdevchimp.ui.screen.GetStartedScreen
import io.github.mobdevchimp.ui.theme.MobdevchimpTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobdevchimpTheme {
                GetStartedScreen(modifier = Modifier.systemBarsPadding().padding(16.dp))
            }
        }
    }
}