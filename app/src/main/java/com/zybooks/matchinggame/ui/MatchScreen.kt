package com.zybooks.matchinggame.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zybooks.matchinggame.R

@Composable
fun MatchScreen(
    viewModel: MatchViewModel = viewModel(),
    onEndGame: () -> Unit,
    onGameOver: () -> Unit
) {
    val gameState by viewModel.gameState.collectAsState()

    LaunchedEffect(gameState.score) {
        if (gameState.score <= -4) {
            onGameOver()
        }
    }

    Scaffold(
        topBar = { TopBar() },
        modifier = Modifier.fillMaxSize().background(Color(0xFF14275D))
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            ScorePanel(score = gameState.score)
            CardGrid(
                cards = gameState.cards,
                onCardClick = { card -> viewModel.onCardClicked(card) }
            )
            gameButtons(
                onEndGame = onEndGame,
                onResetGame = { viewModel.resetGame() }
            )
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
fun CardGrid(cards: List<MyCard>, onCardClick: (MyCard) -> Unit) {
    LazyVerticalGrid(columns = GridCells.Fixed(4)) {
        items(cards) { card ->
            CardItem(card = card, onCardClick = onCardClick)
        }
    }
}

@Composable
fun CardItem(card: MyCard, onCardClick: (MyCard) -> Unit) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .aspectRatio(0.65f)
            .clickable {
                if (!card.isMatched && !card.isFlipped) {
                    onCardClick(card)
                }
            },
        shape = RoundedCornerShape(8.dp),
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(
                    if (card.isFlipped || card.isMatched) Color(0xFFD7D7D7) else Color(0xFF2A2845)
                )
                .padding(4.dp)
        ) {
            if (card.isFlipped) {
                Text(
                    text = card.name,
                    color = Color.Black,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 30.sp
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.card_back),
                    contentDescription = "Card Back",
                    modifier = Modifier.fillMaxSize(),
                    alignment = Alignment.Center
                )
            }
            if (card.isMatched) {
                Text(
                    text = card.name,
                    color = Color(0xFF4E8A2D),
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 30.sp
                )
            }
        }
    }
}

@Composable
fun gameButtons(
    onEndGame: () -> Unit,
    onResetGame: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(onClick = onEndGame) {
            Text(text = "End the Game")
        }
        Button(onClick = onResetGame) {
            Text(text = "Reset the Game")
        }
    }
}