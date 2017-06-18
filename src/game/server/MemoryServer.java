package game.server;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;

public class MemoryServer {
    public static Player playerOne;
    public static Player playerTwo;
    public static int playerCount = 0;

    public static Board board;

    public static void main(String args[]) throws IOException {
        Server server = new Server();

        board = new Board();

        Kryo kryo = server.getKryo();
        kryo.register(Player.class);
        kryo.register(Request.class);

        kryo.register(Board.class);
        kryo.register(Card.class);
        kryo.register(Card[].class);

        server.start();
        server.bind(54555, 54777);

        System.out.println("[server] SERVER STARTED");
        System.out.println("[server] " + (board.isPlayerOneTurn ? "Player ONE" : "Player TWO") + " started");

        server.addListener(new Listener() {
            public void received(Connection connection, Object object) {
                if (object instanceof Player) {
                    if (!board.isGameStarted) {
                        playerCount++;
                        if (playerCount == 1) {
                            playerOne = (Player)object;
                            playerOne.connection = connection;
                            playerOne.name = "PLAYER ONE";

                            System.out.println("[server] " + playerOne.name + " CONNECTED");
                            connection.sendTCP(ServerRespond.CONNECTED);
                            connection.sendTCP(ServerRespond.WAITING);
                            System.out.println("[server] Server is waiting form player two");
                        }
                        if (playerCount == 2) {
                            playerTwo = (Player)object;
                            playerTwo.connection = connection;
                            playerTwo.name = "PLAYER TWO";
                            System.out.println("[server] " + playerTwo.name + " CONNECTED");
                            connection.sendTCP(ServerRespond.CONNECTED);

                            board.isGameStarted = true;
                            System.out.println("[server] LET THE GAME BEGIN");

                            System.out.println("[server] UPDATED BOARD IS SENDING TO PLAYER ONE");
                            connection.sendTCP(board);
                            playerOne.connection.sendTCP(ServerRespond.SEND_BOARD);
                            playerOne.connection.sendTCP(board);
                        }
                    } else {
                        System.out.println("[server] MAX PLAYER!");
                        connection.sendTCP(ServerRespond.NOT_CONNECTED);
                    }
                }
                if (object instanceof Request) {
                    Request req = ((Request)object);
                    if (req.text.equals(Request.GET_BOARD)) {
                        System.out.println("[server] sending board " + board);
                        connection.sendTCP(board);
                    }
                }
                if (object instanceof Board) {
                    System.out.println("[server] " + "UPDATED BOARD RECEIVED");
                    board = (Board)object;
                    if (board.isPlayerOneTurn) {
                        System.out.println("[server] " + "SEND TO PLAYER ONE");
                        playerOne.connection.sendTCP(board);
                    } else if (board.isPlayerTwoTurn) {
                        System.out.println("[server] " + "SEND TO PLAYER TWO");
                        playerTwo.connection.sendTCP(board);
                    }
                }
            }
        });
    }
}
