package io.github.mobdevchimp.models

data class Deck(
    val id: Int,
    val name: String,
    val cards: List<Card> = listOf()
)
