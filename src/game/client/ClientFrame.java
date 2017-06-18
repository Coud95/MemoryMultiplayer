package game.client;

import game.client.board.BoardPanel;
import game.client.board.InfoPanel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
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

        new Timer(1000, (e) -> {
            boardPanel.board = client.board;
            System.out.println("[client] received board: " + boardPanel.board);

            System.out.println(boardPanel.board.isTurnEnded);
            if (boardPanel.board.isTurnEnded && !boardPanel.board.boardSent) {
                System.out.println("SENDING BOARD TO SERVER");
                client.sendBoard(boardPanel.board);
            }
            if (boardPanel.board.boardSent) {
                boardPanel.updateBoard();
            }
        }).start();
    }

    private void initContainer() {
        boardPanel = new BoardPanel(client.board);
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
