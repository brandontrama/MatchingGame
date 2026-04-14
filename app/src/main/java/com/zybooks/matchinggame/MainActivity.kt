package com.zybooks.matchinggame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zybooks.matchinggame.ui.GameOverScreen
import com.zybooks.matchinggame.ui.MatchScreen
import com.zybooks.matchinggame.ui.WelcomeScreen
import com.zybooks.matchinggame.ui.theme.MatchingGameTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MatchingGameTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MatchingGameApp()
                }
            }
        }
    }
}

@Composable
fun MatchingGameApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "welcome"
    ) {
        composable("welcome") {
            WelcomeScreen(
                onStartGame = {
                    navController.navigate("match") {
                        popUpTo("welcome") { inclusive = true }
                    }
                }
            )
        }
        composable("match") {
            MatchScreen(
                onEndGame = {
                    navController.navigate("welcome") {
                        popUpTo("match") { inclusive = true }
                    }
                },
                onGameOver = {
                    navController.navigate("gameOver") {
                        popUpTo("match") { inclusive = true }
                    }
                }
            )
        }
        composable("gameOver") {
            GameOverScreen(
                onBack = {
                    navController.navigate("welcome") {
                        popUpTo("gameOver") { inclusive = true }
                    }
                },
                onRestart = {
                    navController.navigate("match") {
                        popUpTo("gameOver") { inclusive = true }
                    }
                }
            )
        }
    }
}
