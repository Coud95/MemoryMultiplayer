package game.client.board;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.GridLayout;

public class BoardPanel extends JPanel {
    public BoardPanel() {
        initPanel();
    }

    private void initPanel() {
        setLayout(new GridLayout(4, 5));
        setBorder(BorderFactory.createTitledBorder("Board"));
    }
}
