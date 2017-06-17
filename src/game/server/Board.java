package game.server;

public class Board {
    public Card[] cards;
    private int pairs = 10;

    public Board() {
        cards = new Card[pairs * 2];

        for (int i = 0; i < cards.length; i++) {
            Card card = new Card(i);

            cards[i] = card;
            cards[i] = card;
        }
    }
}
