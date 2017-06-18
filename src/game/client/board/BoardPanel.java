package game.client.board;

import game.server.Board;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
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

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("KLIKAM SE TE JEBANE PRZYCISKI!!");
            CardButton button = (CardButton)e.getSource();

            if (lastButton == null) {
                lastButton = button;
            }

            button.showCardImage();

            board.lastCard = button.card;

            if (board.isPairOfCardsPicked() && board.isPairFound) {
                System.out.println("LOLOLOLOLOLOL");
                lastButton.setEnabled(!board.firstCard.matched);
                button.setEnabled(!board.secondCard.matched);
                lastButton = null;
                board.firstCard = null;
                board.secondCard = null;
                board.isPairFound = true;
            } else if (!board.isPairFound) {
                System.out.println(" DLACZEGO SIE TU ZERUJE!??!?!");
                lastButton.setIcon(null);
                button.setIcon(null);
                lastButton = null;
                board.firstCard = null;
                board.secondCard = null;
                board.isPairFound = true;
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

