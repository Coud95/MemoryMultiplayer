package game;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.io.IOException;

public class MemoryClient {
    public static void main(String args[]) throws IOException {
        Client client = new Client();
        client.start();
        client.connect(5000, "localhost", 54555, 54777);
        Kryo kryo = client.getKryo();
        kryo.register(SomeRequest.class);

        SomeRequest request = new SomeRequest();
        request.text = "Here is the request";
        client.sendTCP(request);

        client.addListener(new Listener() {
            public void received (Connection connection, Object object) {
                if (object instanceof SomeRequest) {
                    SomeRequest request = (SomeRequest)object;
                    System.out.println(request.text);

                    SomeResponse response = new SomeResponse();
                    response.text = "Thanks";
                    connection.sendTCP(response);
                }
            }
        });
    }


}
