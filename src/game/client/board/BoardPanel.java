package game.client.board;

import game.client.MemoryClient;
import game.server.Card;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class BoardPanel extends JPanel {
    public static final int MAX_BUTTONS = 20;
    public static final int ROWS = 4;
    public static final int COLS = 5;

    private MemoryClient client;

    CardButton lastButton;

    public JPanel buttonsPanel = new JPanel();
    public List<CardButton> buttons = new ArrayList<>(MAX_BUTTONS);
    public JButton endTurnButton;

    public void updateBoard() {
        System.out.println("[client] updated boardpanel");
        for (Card card : client.board.cards) {
            if (card.matched) {
                System.out.println("[client] card to deactivate");
                for (CardButton button : buttons) {
                    if (button.card.id == card.id) {
                        System.out.println("[client] Deactivate card");
                        button.setEnabled(!card.matched);
                    }
                }
            }
        }
    }

    public BoardPanel(MemoryClient client) {
        this.client = client;

        initPanel();
//        board.checkButtons(this);
    }

    private void initPanel() {
        buttonsPanel.setLayout(new GridLayout(ROWS, COLS));

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Board"));

        ButtonListener listener = new ButtonListener();

        for (int i = 0; i < MAX_BUTTONS; i++) {
            CardButton button = new CardButton();

            button.setCard(client.board.cards[i]);
            button.addActionListener(listener);

            buttons.add(button);
            buttonsPanel.add(button);
        }

        endTurnButton = new JButton("END TURN");
        endTurnButton.addActionListener(new EndTurnListener());

        add(buttonsPanel, BorderLayout.CENTER);
        add(endTurnButton, BorderLayout.SOUTH);
    }

    private class EndTurnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(client);
            System.out.println(client.board);
            System.out.println("[client] SENDING BOARD TO SERVER");
            client.sendBoard(client.board);
        }
    }

    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (client.board.isGameStarted) {
                updateBoard();

                CardButton button = (CardButton)e.getSource();

                if (lastButton == null) {
                    lastButton = button;
                }

                button.showCardImage();

                client.board.lastCard = button.card;

                final boolean cardPicked = client.board.isPairOfCardsPicked();

                if (cardPicked && client.board.isPairFound) {
                    System.out.println("[client] Deactivate buttons");
                    lastButton.setEnabled(!client.board.firstCard.matched);
                    button.setEnabled(!client.board.secondCard.matched);
                    lastButton = null;
                    client.board.firstCard = null;
                    client.board.secondCard = null;
                    client.board.isPairFound = true;
                } else if (!client.board.isPairFound) {
                    System.out.println("[client] HIDE BUTTONS!!");
                    new Timer(100, (event) -> {
                        lastButton.setIcon(null);
                        button.setIcon(null);
                        lastButton = null;
                        client.board.firstCard = null;
                        client.board.secondCard = null;
                        client.board.isPairFound = true;
                    }).start();
                }
            } else {
                System.out.println("[client] GAME IS NOT STARTED, YOU CANT CLICKED ANY BUTTON AT THIS TIME");
            }
        }
    }
}

