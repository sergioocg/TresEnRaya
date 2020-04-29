package juego;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class Resources {
    public static BufferedImage[] arrayFichas;

    static {
        arrayFichas = new BufferedImage[2];
        arrayFichas[0] = loadImage("res/vader.png");
        arrayFichas[1] = loadImage("res/luke.png");
    }

    private static BufferedImage loadImage(String imagePath) {
        try {
            return ImageIO.read(new FileInputStream(imagePath));
        } catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
