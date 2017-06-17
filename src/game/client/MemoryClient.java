package game.client;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import game.server.ServerRespond;

import java.io.IOException;

public class MemoryClient extends Thread {
    private Client client = new Client();
    private Player player;

    public MemoryClient() {
        this.client = new Client();
    }

    @Override
    public void run() {
        try {
            Kryo kryo = client.getKryo();
            kryo.register(Player.class);

            client.start();
            client.connect(5000, "localhost", 54555, 54777);
        } catch (IOException e) {
            e.printStackTrace();
        }

        player = new Player();
        client.sendTCP(player);

        client.addListener(new Listener() {
            public void received(Connection connection, Object object) {
                if (object instanceof String) {
                    String res = (String)object;
                    if (res.equals(ServerRespond.CONNECTED)) {
                        System.out.println("Player connected to server");
                    }
                    if (res.equals(ServerRespond.NOT_CONNECTED)) {
                        System.out.println("Player not connected to server");
                    }
                    if (res.equals(ServerRespond.WAITING)) {
                        System.out.println("Waiting for player");
                    }
                }
            }
        });
    }
}
