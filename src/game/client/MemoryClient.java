package game.client;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import game.server.*;

import java.io.IOException;

public class MemoryClient extends Thread {
    private Client client = new Client();
    private Player player;

    public Board board;

    public MemoryClient() {
        this.client = new Client();
    }

    @Override
    public void run() {
        try {
            Kryo kryo = client.getKryo();
            kryo.register(Player.class);
            kryo.register(Request.class);

            kryo.register(Board.class);
            kryo.register(Card.class);
            kryo.register(Card[].class);

            client.start();
            client.connect(5000, "localhost", 54555, 54777);
        } catch (IOException e) {
            e.printStackTrace();
        }

        player = new Player();
        client.sendTCP(player);

        sendRequest(Request.GET_BOARD);

        client.addListener(new Listener() {
            public void received(Connection connection, Object object) {
                if (object instanceof String) {
                    String res = (String)object;
                    System.out.println(res);
                    if (res.equals(ServerRespond.CONNECTED)) {
                        System.out.println("[client] Player connected to server");
                    }
                    if (res.equals(ServerRespond.NOT_CONNECTED)) {
                        System.out.println("[client] Player not connected to server");
                    }
                    if (res.equals(ServerRespond.WAITING)) {
                        System.out.println("[client] Waiting for player");
                    }
                    if (res.equals(ServerRespond.SEND_BOARD)) {
                        System.out.println("[client] Receive updated board");
                    }
                }
                if (object instanceof Board) {
                    board = ((Board)object);
                    System.out.println("[client] get board from server | " + board);
                }
            }
        });
    }

    private void sendRequest(String request) {
        Request req = new Request();
        req.text = request;
        client.sendTCP(req);
    }

    public void sendBoard(Board board) {
        client.sendTCP(board);
    }
}
