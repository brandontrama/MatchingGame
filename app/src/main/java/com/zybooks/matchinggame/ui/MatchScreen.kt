package com.zybooks.matchinggame.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MatchScreen(
    viewModel: MatchViewModel = viewModel()
) {
    val gameState by viewModel.gameState.collectAsState()

    Scaffold(
        topBar = { TopBar() },
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            ScorePanel(score = gameState.score)
            CardGrid(cards = gameState.cards)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    TopAppBar(
        title = { Text("Matching Game") },
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
fun ScorePanel(score: Int = 0) {
    Text(
        text = "Score: $score",
        fontSize = 18.sp,
        textAlign = TextAlign.End,
        lineHeight = 30.sp,
        modifier = Modifier.fillMaxWidth()
                            .background(Color(0xFFFFF7C0))
                            .padding(16.dp)
    )
}

@Composable
fun CardGrid(cards: List<MyCard>) {
    LazyVerticalGrid(columns = GridCells.Fixed(4)) {
        items(cards) { card ->
            CardItem(card = card)
        }
    }
}

@Composable
fun CardItem(card: MyCard) {
    Card(
        modifier = Modifier.padding(4.dp).aspectRatio(0.65f),
        shape = RoundedCornerShape(8.dp),
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color(0xFF42405A))
                .padding(4.dp)
                .size(80.dp))
        {
            Text(
                text = card.name,
                color = Color.White,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                lineHeight = 30.sp)
        }
    }
}