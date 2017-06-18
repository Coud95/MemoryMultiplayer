package game.client.board;

import game.server.Board;
import game.server.Card;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class BoardPanel extends JPanel {
    public static final int MAX_BUTTONS = 20;
    public static final int ROWS = 4;
    public static final int COLS = 5;

    public Board board;

    CardButton lastButton;

    public List<CardButton> buttons = new ArrayList<>(MAX_BUTTONS);

    public void updateBoard() {
        System.out.println("[client] updated boardpanel");
        for (Card card : board.cards) {
            if (card.matched) {
                System.out.println("[client] card to deactivate");
                for (CardButton button : buttons) {
                    if (button.card.id == card.id) {
                        System.out.println("[client] Deactivate card");
                        button.setEnabled(!card.matched);
                    }
                }
            }
        }
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (board.isGameStarted) {
                CardButton button = (CardButton)e.getSource();

                if (lastButton == null) {
                    lastButton = button;
                }

                button.showCardImage();

                board.lastCard = button.card;

                final boolean cardPicked = board.isPairOfCardsPicked();

                if (cardPicked && board.isPairFound) {
                    System.out.println("[client] Deactivate buttons");
                    lastButton.setEnabled(!board.firstCard.matched);
                    button.setEnabled(!board.secondCard.matched);
                    lastButton = null;
                    board.firstCard = null;
                    board.secondCard = null;
                    board.isPairFound = true;
                } else if (!board.isPairFound) {
                    System.out.println("[client] HIDE BUTTONS!!");
                    new Timer(100, (event) -> {
                        lastButton.setIcon(null);
                        button.setIcon(null);
                        lastButton = null;
                        board.firstCard = null;
                        board.secondCard = null;
                        board.isPairFound = true;
                    }).start();
                }
            } else {
                System.out.println("[client] GAME IS NOT STARTED, YOU CANT CLICKED ANY BUTTON AT THIS TIME");
            }
        }
    }

    public BoardPanel(Board board) {
        this.board = board;

        initPanel();
//        board.checkButtons(this);
    }

    private void initPanel() {
        setLayout(new GridLayout(ROWS, COLS));
        setBorder(BorderFactory.createTitledBorder("Board"));

        ButtonListener listener = new ButtonListener();

        for (int i = 0; i < MAX_BUTTONS; i++) {
            CardButton button = new CardButton();

            button.setCard(board.cards[i]);
            button.addActionListener(listener);

            buttons.add(button);
            add(button);
        }
    }
}

