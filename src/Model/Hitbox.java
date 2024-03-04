package Model;

import java.awt.*;

//Verifie si le premier Point est dans le rectangle formé du deuxième point (en haut a gauche) et d'une longueur et hauteur
public class Hitbox {
    public boolean in(Point point, Point carre, int width, int length){
        return(point.x > carre.x && point.x < carre.x+width && point.y < carre.y && point.y > carre.y+length);
    }
}
