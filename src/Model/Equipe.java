package Model;

import java.awt.*;
import java.util.ArrayList;
import java.awt.Rectangle;
import java.util.Queue;

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

    public boolean isHeroIn(Point p){
        for(Hero h : list_hero){
            if(h.getNumPos().equals(p)){
                return true;
            }
        }
        return false;
    }

    public boolean isCibleIn(Point p){
        for(Hero h : list_hero){
            if(h.getCible().equals(p)){
                return true;
            }
        }
        return false;
    }

    //n le nombre de héro sélectionné
    public ArrayList<Point> cibles(Point cible, int n){
        ArrayList<Point> file = new ArrayList<Point>();
        ArrayList<Point> res = new ArrayList<Point>();
        ArrayList<Point> dejavu = new ArrayList<Point>();
        dejavu.add(cible);
        file.add(cible);

        while(res.size()<n){
            Point p = file.remove(0);
            res.add(p);
            for (Point point: map.voisins(p, dejavu)) {
                file.add(point);
                dejavu.add(point);
            }
        }

        return res;
    }

    /** cette méthode est appelée lorsqu'on clic pour demander un déplacement */
    public void deplacement(){
        Point cible = new Point(posClick.x/map.getTILESIZE(), posClick.y/map.getTILESIZE());
        //il faut donner a chaque héro une cible différente. vu qu'on ajoute a la main la cible, on va en donner une différente qui fait partie de ses voisins.
        int n = 0;
        ArrayList<Point> cibles = new ArrayList<>();

        //on verifie qu'on clique pas sur un mur ou un autre héro
        if(!(map.getNumMap()[cible.y][cible.x]==1)&&!isHeroIn(cible)){
            if (!list_hero.isEmpty()) {
                for(int i = 0; i<list_hero.size(); i++){
                    if (list_hero.get(i).isSelected()) {
                        n++;
                    }
                }
                cibles = cibles(cible, n);
                for(int i = 0; i<list_hero.size(); i++){
                    if (list_hero.get(i).isSelected()) {
                        list_hero.get(i).deplacement(cibles.remove(0));
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
        Hero mohamed = new Hero(map, newID(),false,  280, 160);
        boutique  = new Boutique(map);
        list_hero.add(tim);
        list_hero.add(jhon);
        list_hero.add(mohamed);
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
