package game.client.board;

import game.server.Board;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

public class BoardPanel extends JPanel {
    public static final int MAX_BUTTONS = 20;
    public static final int ROWS = 4;
    public static final int COLS = 5;

    public Board board;

    public List<CardButton> buttons = new ArrayList<>(MAX_BUTTONS);

    public BoardPanel(Board board) {
        this.board = board;

        initPanel();
    }

    private void initPanel() {
        setLayout(new GridLayout(ROWS, COLS));
        setBorder(BorderFactory.createTitledBorder("Board"));

        for (int i = 0; i < MAX_BUTTONS; i++) {
            CardButton buttonOne = new CardButton();
            buttonOne.setCard(board.cards[i]);
            buttonOne.showCardImage();

            buttons.add(buttonOne);

            add(buttonOne);
        }
    }
}
