package Model;

import java.awt.*;

//MATHIAS LA MIEUX FAIT CA C DE LA MERDE
public class Hitbox {
    public boolean in(Point point, Point carre, int width, int length){
        return(point.x > carre.x && point.x < carre.x+width && point.y < carre.y && point.y > carre.y+length);
    }
}
