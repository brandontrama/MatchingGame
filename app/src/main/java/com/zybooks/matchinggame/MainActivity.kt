package com.zybooks.matchinggame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.zybooks.matchinggame.ui.theme.MatchingGameTheme
import com.zybooks.matchinggame.ui.MatchScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MatchingGameTheme {
                MatchScreen()
            }
        }
    }
}
