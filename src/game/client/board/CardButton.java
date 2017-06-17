package game.client.board;

import game.Assets;
import game.server.Card;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Image;

public class CardButton extends JButton {
    private Card card;
    private Image cardImg;

    public CardButton() {
        initCardButton();
    }

    private void initCardButton() {
    }

    public void setCard(Card card) {
        this.card = card;
        System.out.println("Is card null? " + card);
        cardImg = Assets.getCardImage(card.id);
        System.out.println("Is cardImg null? " + cardImg);
    }

    public void showCardImage() {
        setIcon(new ImageIcon(cardImg));
    }
}
