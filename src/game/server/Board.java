package game.server;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Board {
    public Card[] cards;
    private int pairs = 10;

    public Board() {
        cards = new Card[pairs * 2];

        int id = 0;
        for (int i = 0; i < cards.length; i += 2) {
            Card card = new Card(id++);

            cards[i] = card;
            cards[i + 1] = card;
        }

        shuffleCards();
    }

    private void shuffleCards() {
        List<Card> shuffledCards = Arrays.asList(cards);

        Collections.shuffle(shuffledCards);

        cards = (Card[])shuffledCards.toArray();
    }
}
