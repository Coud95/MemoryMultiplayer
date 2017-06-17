package game;

import game.client.MemoryClient;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.Dimension;

public class ClientFrame extends JFrame {
    private MemoryClient client;

    public ClientFrame() {
        initFrame();
        client = new MemoryClient();

        client.start();
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
