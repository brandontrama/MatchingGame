package com.zybooks.matchinggame.ui

import androidx.lifecycle.ViewModel
import com.zybooks.matchinggame.data.CardDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay

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

    val gameState: StateFlow<GameState> = _gameState.asStateFlow()

    private var firstCardIndex: Int? = null
    private var secondCardIndex: Int? = null
    private var isProcessing = false

    fun onCardClicked(clickedCard: MyCard) {
        if (isProcessing) return

        val currentState = _gameState.value
        val clickedIndex = currentState.cards.indexOfFirst { it.id == clickedCard.id }

        if (firstCardIndex == null) {
            flipCard(clickedIndex, true)
            firstCardIndex = clickedIndex
        }
        else if (secondCardIndex == null && clickedIndex != firstCardIndex) {
            flipCard(clickedIndex, true)
            secondCardIndex = clickedIndex

            isProcessing = true
            checkForMatch()
        }
    }

    private fun flipCard(index: Int, isFlipped: Boolean) {
        _gameState.update { currentState ->
            val updatedCards = currentState.cards.toMutableList()
            val card = updatedCards[index]
            updatedCards[index] = card.copy(isFlipped = isFlipped)
            currentState.copy(cards = updatedCards)
        }
    }

    private fun checkForMatch() {
        val currentState = _gameState.value
        val card1 = currentState.cards[firstCardIndex!!]
        val card2 = currentState.cards[secondCardIndex!!]

        if (card1.name == card2.name) {
            _gameState.update { currentState ->
                val updatedCards = currentState.cards.toMutableList()
                updatedCards[firstCardIndex!!] = card1.copy(isMatched = true, isFlipped = true)
                updatedCards[secondCardIndex!!] = card2.copy(isMatched = true, isFlipped = true)
                currentState.copy(
                    cards = updatedCards,
                    score = currentState.score + 2
                )
            }

            firstCardIndex = null
            secondCardIndex = null
            isProcessing = false
        } else {
            MainScope().launch {
                delay(1000)

                _gameState.update { currentState ->
                    val updatedCards = currentState.cards.toMutableList()
                    if (firstCardIndex != null) {
                        val card1 = updatedCards[firstCardIndex!!]
                        updatedCards[firstCardIndex!!] = card1.copy(isFlipped = false)
                    }
                    if (secondCardIndex != null) {
                        val card2 = updatedCards[secondCardIndex!!]
                        updatedCards[secondCardIndex!!] = card2.copy(isFlipped = false)
                    }
                    currentState.copy(
                        cards = updatedCards,
                        score = currentState.score - 1
                    )
                }

                firstCardIndex = null
                secondCardIndex = null
                isProcessing = false
            }
        }
    }

    fun resetGame() {
        _gameState.value = GameState(cards = CardDataSource().loadCards().shuffled(), score = 0)
        firstCardIndex = null
        secondCardIndex = null
        isProcessing = false
    }
}