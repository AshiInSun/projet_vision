package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Tile {
    public int type; //0 = herbe, 1 = eau, 2 = blé, 3 = blé coupé, 4 = blé en phase de repousse
    //6 = herbe avec eau sur la gauche 7 = ilot relié a gauche
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
            case 2:
                try {
                    image = ImageIO.read(getClass().getResourceAsStream("/img/ble.png"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 3:
                try {
                    image = ImageIO.read(getClass().getResourceAsStream("/img/ble_coupe.png"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 4:
                try {
                    image = ImageIO.read(getClass().getResourceAsStream("/img/ble_repousse.png"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 6:
                try {
                    image = ImageIO.read(getClass().getResourceAsStream("/img/herbe_eauG.png"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 7:
                try {
                    image = ImageIO.read(getClass().getResourceAsStream("/img/eau_herbeG.png"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 8:
                try {
                    image = ImageIO.read(getClass().getResourceAsStream("/img/coin_hautG.png"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }
}
