package com.zybooks.matchinggame.ui.theme.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class MyCard(
    val id: Int = 0,
    val name: String,
    val isFlipped: Boolean = false,
    val isMatched: Boolean = false
)
data class GameState(
    val cards: List<MyCard> = emptyList(),
    val score: Int = 0
)

class MatchViewModel : ViewModel() {
    private val cardNames = listOf("One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight",
                                    "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight").shuffled()

    private val _gameState = MutableStateFlow(
        GameState(cards = cardNames.map { MyCard(name = it) })
    )

    val gameState: StateFlow<GameState> =_gameState.asStateFlow()

    fun onCardClicked(card: MyCard) {  }
}