package com.zybooks.matchinggame.data

import com.zybooks.matchinggame.ui.MyCard

class CardDataSource {
    private val cardList = listOf(
        MyCard(
            id = 1,
            name = "One",
        ),
        MyCard(
            id = 2,
            name = "One",
        ),
        MyCard(
            id = 3,
            name = "Two",
        ),
        MyCard(
            id = 4,
            name = "Two",
        ),
        MyCard(
            id = 5,
            name = "Three",
        ),
        MyCard(
            id = 6,
            name = "Three",
        ),
        MyCard(
            id = 7,
            name = "Four",
        ),
        MyCard(
            id = 8,
            name = "Four",
        ),
        MyCard(
            id = 9,
            name = "Five",
        ),
        MyCard(
            id = 10,
            name = "Five",
        ),
        MyCard(
            id = 11,
            name = "Six",
        ),
        MyCard(
            id = 12,
            name = "Six",
        ),
        MyCard(
            id = 13,
            name = "Seven",
        ),
        MyCard(
            id = 14,
            name = "Seven",
        ),
        MyCard(
            id = 15,
            name = "Eight",
        ),
        MyCard(
            id = 16,
            name = "Eight",
        )
    )

    fun getCard(id: Int): MyCard? {
        return cardList.find { it.id == id }
    }

    fun loadCards() = cardList
}