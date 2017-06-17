package game.client.board;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.GridLayout;

public class BoardPanel extends JPanel {
    public static final int MAX_BUTTONS = 20;
    public static final int ROWS = 4;
    public static final int COLS = 5;

    public BoardPanel() {
        initPanel();
    }

    private void initPanel() {
        setLayout(new GridLayout(ROWS, COLS));
        setBorder(BorderFactory.createTitledBorder("Board"));

        for (int i = 0; i < MAX_BUTTONS; i++) {
            add(new CardButton());
        }
    }
}
