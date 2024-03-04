package Model;

import java.awt.*;

public class Hitbox {

    public static boolean in(Point point, Point carre, int width, int length){
        return(point.x >= carre.x && point.x <= carre.x+width && point.y >= carre.y && point.y <= carre.y+length);
    }
}
