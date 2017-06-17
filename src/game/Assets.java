package game;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.FileInputStream;
import java.io.IOException;

public class Assets {
    private static Image[] images = new Image[10];

    public static void load() {
        try {
            images[0] = ImageIO.read(new FileInputStream("pikachu.jpg"));
            images[1] = ImageIO.read(new FileInputStream("bulbasaur.png"));
            images[2] = ImageIO.read(new FileInputStream("squirtle.png"));
            images[3] = ImageIO.read(new FileInputStream("charmander.png"));
            images[4] = ImageIO.read(new FileInputStream("pidgey.png"));
            images[5] = ImageIO.read(new FileInputStream("zubat.png"));
            images[6] = ImageIO.read(new FileInputStream("psyduck.png"));
            images[7] = ImageIO.read(new FileInputStream("abra.jpg"));
            images[8] = ImageIO.read(new FileInputStream("koffing.png"));
            images[9] = ImageIO.read(new FileInputStream("eevee.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Image getCardImage(int id) {
        return images[id];
    }
}
