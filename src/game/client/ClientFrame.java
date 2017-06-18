package game.client;

import game.client.board.BoardPanel;
import game.client.board.InfoPanel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Dimension;

public class ClientFrame extends JFrame {
    private MemoryClient client;

    private JPanel container;

    private static BoardPanel boardPanel;
    private static InfoPanel infoPanel;

    public ClientFrame() {
        client = new MemoryClient();
        client.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        initFrame();
        initContainer();
    }

    private void initContainer() {
        boardPanel = new BoardPanel(client);
        infoPanel = new InfoPanel();

        container = new JPanel(new BorderLayout());
        container.add(boardPanel, BorderLayout.CENTER);
        container.add(infoPanel, BorderLayout.SOUTH);

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
