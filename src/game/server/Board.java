package game.server;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Board {
    public static final int MAX_PAIRS = 10;

    public boolean isGameStarted = false;

    public Card[] cards;

    public boolean isPlayerOneTurn = true;

    public boolean boardSent = false;

    public Card lastCard;
    public Card firstCard;
    public Card secondCard;

    public boolean isTurnEnded = false;

    public boolean isPairFound = true;

    public Board() {
        cards = new Card[MAX_PAIRS * 2];

        int id = 0;
        for (int i = 0; i < cards.length; i += 2) {
            Card card = new Card(id++);

            cards[i] = card;
            cards[i + 1] = card;
        }

//        shuffleCards();
    }

    private void shuffleCards() {
        List<Card> shuffledCards = Arrays.asList(cards);

        Collections.shuffle(shuffledCards);

        cards = (Card[])shuffledCards.toArray();
    }

    public boolean isPairOfCardsPicked() {
        isTurnEnded = false;
        if (firstCard == null && secondCard == null) {
            firstCard = lastCard;
            System.out.println("First card selected! " + firstCard);
        }

        if (firstCard != null && firstCard != lastCard && secondCard == null) {
            secondCard = lastCard;
            System.out.println("Second card selected! " + secondCard);
            endTurn();

            return true;
        }
        return false;
    }

    public void endTurn() {
        isPlayerOneTurn = !isPlayerOneTurn;
        System.out.println((isPlayerOneTurn ? "Player one " : "Player two") + " turn");

        if (firstCard.id == secondCard.id) {
            firstCard.matched = secondCard.matched = true;
            System.out.println("PAIR MATCHED");
            isPairFound = true;
        } else {
            isPairFound = false;
            System.out.println("PAIR NOT MATCHED");
        }

        isTurnEnded = true;
    }
}
