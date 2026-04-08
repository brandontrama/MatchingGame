package com.zybooks.matchinggame.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun WelcomeScreen(
    onStartGame: () -> Unit
) {
    Scaffold(
        topBar = { WelcomeTopBar() },
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            WelcomePanel(onStartGame = onStartGame)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomeTopBar() {
    TopAppBar(
        title = { Text("Welcome to the Matching Game!") },
        colors = TopAppBarColors(
            containerColor = Color.DarkGray,
            titleContentColor = Color.White,
            navigationIconContentColor = Color.White,
            actionIconContentColor = Color.White,
            scrolledContainerColor = Color.DarkGray
        ),
        modifier = Modifier
    )
}

@Composable
fun WelcomePanel(
    onStartGame: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            Text("Scoring:")
            Text("Match: 2 points")
            Text("Miss: -1 points")
        }
        Text(
            text = "Click Start to begin!",
            modifier = Modifier.padding(16.dp)
        )

        Button(onClick = onStartGame) {
            Text("Start the Game!")
        }
    }
}