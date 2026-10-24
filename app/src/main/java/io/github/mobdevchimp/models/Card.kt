package io.github.mobdevchimp.models

data class Card(
    val id: Int,
    val deckId: Int,
    val front: String,
    val back: String
)
