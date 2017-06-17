package game.client.board;

import game.Assets;
import game.server.Board;
import game.server.Card;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Image;

public class CardButton extends JButton {
    private Card card;
    private Image cardImg;

    private Board board;

    public CardButton(Board board) {
        this.board = board;
        initCardButton();
    }

    private void initCardButton() {
        setIcon(null);

        addActionListener(e -> {
            CardButton button = (CardButton)e.getSource();
            button.showCardImage();
            board.lastCard = button.card;
            board.checkCards();
        });
    }

    public void setCard(Card card) {
        this.card = card;
        cardImg = Assets.getCardImage(card.id);
    }

    public void showCardImage() {
        setIcon(new ImageIcon(cardImg));
    }
}
