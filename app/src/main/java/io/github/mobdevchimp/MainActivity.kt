package io.github.mobdevchimp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.magnifier
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.mobdevchimp.components.ChimpButton
import io.github.mobdevchimp.components.ChimpCardAnalytics
import io.github.mobdevchimp.ui.screen.GetStartedScreen
import io.github.mobdevchimp.ui.theme.MobdevchimpTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobdevchimpTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPaddings ->
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
    }
}