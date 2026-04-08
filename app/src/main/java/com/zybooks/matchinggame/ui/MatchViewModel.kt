package com.zybooks.matchinggame.ui

import androidx.lifecycle.ViewModel
import com.zybooks.matchinggame.data.CardDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class MyCard(
    val id: Int,
    val name: String,
    val isFlipped: Boolean = false,
    val isMatched: Boolean = false
)
data class GameState(
    val cards: List<MyCard> = emptyList(),
    val score: Int = 0
)

class MatchViewModel : ViewModel() {
    private val _gameState = MutableStateFlow(
        GameState(cards = CardDataSource().loadCards().shuffled())
    )

    val gameState: StateFlow<GameState> =_gameState.asStateFlow()

    fun onCardClicked(card: MyCard) {  }
}