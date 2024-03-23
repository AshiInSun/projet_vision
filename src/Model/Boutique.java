package Model;

import java.awt.*;

public class Boutique {
    private Carte map;
    private boolean selected = false;


    public Boutique(Carte carte){
        this.map = carte;

    }



    public void selectionneB(Point a){
        //on va regarder la hitbox de la boutique qui est de 40 par 40 et changer sa valeur de selected si il est dessus
        int x = a.x/40;
        int y = a.y/40;
        if(map.getNumMap()[y][x] == 5){
            selected = true;
        }else{
            selected = false;
        }
    }
}
