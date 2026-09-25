package io.github.mobdevchimp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.mobdevchimp.components.ChimpButtonIcon
import io.github.mobdevchimp.components.ChimpCardAnalytics
import io.github.mobdevchimp.ui.theme.MobdevchimpTheme
import io.github.mobdevchimp.ui.theme.eelBlack300
import io.github.mobdevchimp.ui.theme.macawBlue100
import io.github.mobdevchimp.ui.theme.macawBlue200
import io.github.mobdevchimp.ui.theme.macawBlue500
import io.github.mobdevchimp.ui.theme.wolfGray100

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(
                containerColor = macawBlue100
            ) {
                NavigationBarItem(
                    selected = false,
                    onClick = {},
                    icon = { Icon(Icons.Default.Star, contentDescription = "Study") },
                    label = { Text("Study") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = macawBlue500,
                        selectedTextColor = macawBlue500,
                        unselectedIconColor = macawBlue500,
                        unselectedTextColor = macawBlue500,
                        indicatorColor = macawBlue200
                    )
                )
                NavigationBarItem(
                    selected = false,
                    onClick = {},
                    icon = { Icon(Icons.Default.Create, contentDescription = "Create") },
                    label = { Text("Create") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = macawBlue500,
                        selectedTextColor = macawBlue500,
                        unselectedIconColor = macawBlue500,
                        unselectedTextColor = macawBlue500,
                        indicatorColor = macawBlue200
                    )
                )
                NavigationBarItem(
                    selected = true,
                    onClick = {},
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = macawBlue500,
                        selectedTextColor = macawBlue500,
                        unselectedIconColor = macawBlue500,
                        unselectedTextColor = macawBlue500,
                        indicatorColor = macawBlue200
                    )
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .background(macawBlue200)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Spacer(modifier = Modifier.height(4.dp))
                //Profile info and edit button
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = "Arvee D Great",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = eelBlack300
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Joined September 2026",
                            fontSize = 14.sp,
                            color = wolfGray100
                        )
                    }
                    ChimpButtonIcon(icon = Icons.Default.Edit) { }
                }
                Spacer(modifier = Modifier.height(16.dp))
                ChimpCardAnalytics(
                    data = 12,
                    title = "Daily Streak",
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    ChimpCardAnalytics(
                        data = 4,
                        title = "Decks",
                        modifier = Modifier.weight(1f)
                    )
                    ChimpCardAnalytics(
                        data = 28,
                        title = "Cards",
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ProfileScreenPreview(){
    MobdevchimpTheme{
        ProfileScreen()
    }
}
