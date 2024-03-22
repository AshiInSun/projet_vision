package Model;

import java.awt.*;
import java.util.ArrayList;

public class Equipe {
    //liste des héros de l'équipe
    private ArrayList<Hero> list_hero = new ArrayList<Hero>();
    private Carte map;

    //position du dernier clic
    private Point posClick;
    //id pour les héros et nn pas de l'équipe
    private int id=0;

    public ArrayList<Hero> getTeam(){return  list_hero;}
    public void setPosClick(Point p){posClick = p;}
    public Carte getMap(){return map;}

    /** cette méthode est appelée lorsqu'on clic pour demander un déplacement */
    public void deplacement(){
        Point cible = new Point(posClick.x/map.getTILESIZE(), posClick.y/map.getTILESIZE());
        map.update_chemin(cible);

        if (!list_hero.isEmpty()) {
                // sur un seul héros pour l'instant
                list_hero.get(0).deplacement(cible);
        }
    }



    private int newID(){int temp = id; id++; return temp;}

    public Equipe(Carte carte){
        this.map = carte;
        Hero tim = new Hero(map, newID());
        list_hero.add(tim);
        ///Il faudra faire un thread de deplacement par héro
        ThreadDeplacement tDeplacement = new ThreadDeplacement(list_hero.get(0));
        tDeplacement.start();
    }
}
