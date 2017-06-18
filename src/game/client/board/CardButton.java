package game.client.board;

import game.Assets;
import game.server.Card;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Image;

public class CardButton extends JButton {
    public Card card;
    private Image cardImg;

    public CardButton() {
        initCardButton();
    }

    private void initCardButton() {
        setIcon(null);
    }

    public void setCard(Card card) {
        this.card = card;
        cardImg = Assets.getCardImage(card.id);
    }

    public void showCardImage() {
        setIcon(new ImageIcon(cardImg));
    }
}
