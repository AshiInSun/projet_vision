package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Tile {
    public int type; //0 = herbe, 1 = eau,
    public BufferedImage image;
    public boolean colision = false;
    public int pos_x;
    public int pos_y;

    public Tile(int x, int y, int type){
        pos_x = x;
        pos_y = y;
        this.type = type;
        switch (type){
            case 0:
                try {
                    image = ImageIO.read(getClass().getResourceAsStream("/img/herbe.png"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 1:
                try {
                    image = ImageIO.read(getClass().getResourceAsStream("/img/eau.png"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }
}
