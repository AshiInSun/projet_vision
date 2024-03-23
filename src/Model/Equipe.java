package Model;

import java.awt.*;
import java.util.ArrayList;
import java.awt.Rectangle;

public class Equipe {
    //liste des héros de l'équipe
    private ArrayList<Hero> list_hero = new ArrayList<Hero>();
    private Carte map;

    //position du dernier clic
    private Point posClick;
    //id pour les héros et nn pas de l'équipe
    private int id=0;
    private boolean[] selected;
    private ArrayList<Integer> currentChamp;
    private ZoneSelection zoneSelection;
    private Boutique boutique;

    public ArrayList<Hero> getTeam(){return  list_hero;}
    public void setPosClick(Point p){posClick = p;}
    public Carte getMap(){return map;}

    /** cette méthode est appelée lorsqu'on clic pour demander un déplacement */
    public void deplacement(){
        Point cible = new Point(posClick.x/map.getTILESIZE(), posClick.y/map.getTILESIZE());

        //on verifie qu'on clique pas sur un mur
        if(!(map.getNumMap()[cible.y][cible.x]==1)){
            map.update_chemin(cible);
            if (!list_hero.isEmpty()) {
                for(int i = 0; i<list_hero.size(); i++){
                    if(list_hero.get(i).getSelected()){
                        list_hero.get(i).deplacement(cible);
                    }
                }
            }
        }
    }

    public ArrayList<Hero> getList_hero() {
        return list_hero;
    }


    private int newID(){int temp = id; id++; return temp;}

    public Equipe(Carte carte){
        this.map = carte;
        Hero tim = new Hero(map, newID(),true, 240, 200);
        Hero jhon = new Hero(map, newID(),false,  200, 160);
        boutique  = new Boutique(map);
        list_hero.add(tim);
        list_hero.add(jhon);
        zoneSelection = new ZoneSelection(new Rectangle(0,0,0,0));
    }

    public void SetzoneSelection(ZoneSelection zoneSelection){
        this.zoneSelection = zoneSelection;
    }

    public ZoneSelection getZoneSelection() {
        return zoneSelection;
    }

    public Boutique GetBoutique(){
        return boutique;
    }

}
