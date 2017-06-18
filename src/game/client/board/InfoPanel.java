package game.client.board;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;

public class InfoPanel extends JPanel {
    public JLabel player1Label, player2Label;
    public JLabel player1Points, player2Points;

    public InfoPanel() {
        initInfoPanel();
    }

    private void initInfoPanel() {
        initLabels();

        setBorder(BorderFactory.createTitledBorder("INFO"));
        setLayout(new GridLayout(1, 4));
        add(player1Label);
        add(player1Points);
        add(player2Label);
        add(player2Points);
    }

    private void initLabels() {
        player1Label = new JLabel("Player 1 points");
        player2Label = new JLabel("Player 2 points");

        player1Points = new JLabel("0");
        player2Points = new JLabel("0");
    }
}
