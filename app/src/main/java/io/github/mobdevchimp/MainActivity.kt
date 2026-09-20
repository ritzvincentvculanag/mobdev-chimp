package io.github.mobdevchimp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import io.github.mobdevchimp.components.ChimpTextField
import io.github.mobdevchimp.ui.theme.MobdevchimpTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobdevchimpTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPaddings ->
                    Column(modifier = Modifier.padding(innerPaddings)) {
                        val emailState = rememberTextFieldState(initialText = "")
                        val nameState = rememberTextFieldState(initialText = "")
                        ChimpTextField(
                            state = emailState,
                            placeholder = "Email",
                        )
                        ChimpTextField(
                            state = nameState,
                            placeholder = "Email",
                        )
                    }
                }
            }
        }
    }
}