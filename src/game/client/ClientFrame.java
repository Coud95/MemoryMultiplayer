package game.client;

import game.Assets;
import game.client.board.BoardPanel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Dimension;

public class ClientFrame extends JFrame {
    private MemoryClient client;

    private JPanel container;

    private BoardPanel boardPanel;

    public ClientFrame() {
        Assets.load();
        initFrame();

        initContainer();

        client = new MemoryClient();

        client.start();
    }

    private void initContainer() {
        container = new JPanel(new BorderLayout());

        boardPanel = new BoardPanel();
        container.add(boardPanel);

        setContentPane(container);
    }

    private void initFrame() {
        Dimension dim = new Dimension(500, 500);
        setMinimumSize(dim);
        setMaximumSize(dim);
        setPreferredSize(dim);

        setTitle("MEMORY GAME");

        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
    }

    public void start() {
        setVisible(true);
    }
}
