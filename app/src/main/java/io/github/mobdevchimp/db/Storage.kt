package io.github.mobdevchimp.db

import io.github.mobdevchimp.models.Card
import io.github.mobdevchimp.models.Deck

class Storage {
    private var decks = mutableListOf<Deck>()
    private val biologyCards: List<Card> = listOf(
        Card(
            id = 1,
            deckId = 1,
            front = "What is the primary function of the rough endoplasmic reticulum?",
            back = "Synthesizing and processing proteins (due to the ribosomes attached to its surface)."
        ),
        Card(
            id = 2,
            deckId = 1,
            front = "What molecule serves as the primary energy currency of the cell?",
            back = "ATP (Adenosine Triphosphate)."
        ),
        Card(
            id = 3,
            deckId = 1,
            front = "Define homeostasis.",
            back = "The process by which an organism maintains a stable internal environment despite external changes."
        ),
        Card(
            id = 4,
            deckId = 1,
            front = "What is the structural difference between arteries and veins regarding blood flow direction?",
            back = "Arteries carry blood away from the heart; veins carry blood toward the heart."
        )
    )
    private val historyCards: List<Card> = listOf(
        Card(
            id = 5,
            deckId = 2,
            front = "What event in 1914 triggered the outbreak of World War I?",
            back = "The assassination of Archduke Franz Ferdinand of Austria."
        ),
        Card(
            id = 6,
            deckId = 2,
            front = "Who was the primary author of the United States Declaration of Independence?",
            back = "Thomas Jefferson."
        ),
        Card(
            id = 7,
            deckId = 2,
            front = "Which ancient civilization built the Machu Picchu citadel?",
            back = "The Inca Empire."
        )
    )
    private val chemistryCards: List<Card> = listOf(
        Card(
            id = 8,
            deckId = 3,
            front = "What type of chemical bond involves the sharing of electron pairs between atoms?",
            back = "Covalent bond."
        ),
        Card(
            id = 9,
            deckId = 3,
            front = "According to the Brønsted-Lowry theory, what is a base?",
            back = "A proton (H+) acceptor."
        ),
        Card(
            id = 10,
            deckId = 3,
            front = "What does a catalyst do to a chemical reaction's activation energy?",
            back = "It lowers the activation energy to speed up the reaction rate without being consumed."
        )
    )

    init {
        buildDecks()
    }

    private fun buildDecks() {
        decks.add(
            Deck(
                id = 1,
                name = "Biology Chapter 4",
                cards = biologyCards
            )
        )
        decks.add(
            Deck(
                id = 2,
                name = "History 101",
                cards = historyCards
            )
        )
        decks.add(
            Deck(
                id = 3,
                name = "Chemistry",
                cards = chemistryCards
            )
        )
    }

    fun getDecks(): List<Deck> = decks
}