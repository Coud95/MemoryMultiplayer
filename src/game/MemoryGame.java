package game;

import game.client.ClientFrame;

import javax.swing.SwingUtilities;

public class MemoryGame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ClientFrame frame = new ClientFrame();
            frame.start();
        });
    }
}
