package Model;

import java.awt.*;
import java.util.ArrayList;
import java.awt.Rectangle;

public class Equipe {
    private ArrayList<Hero> list_hero = new ArrayList<Hero>();
    private Carte map;
    private Point posClick;
    private int id=0;
    private boolean[] selected;
    private ArrayList<Integer> currentChamp;
    private ZoneSelection zoneSelection;
    private Boutique boutique;

    public ArrayList<Hero> getTeam(){return  list_hero;}
    public void setPosClick(Point p){posClick = p;}

    public void deplacement(){
        if (!list_hero.isEmpty()) {
            for(int i = 0; i<list_hero.size(); i++){
                if(list_hero.get(i).getSelected()){
                    list_hero.get(i).deplacement(posClick);
                }
            }
        }
    }



    private int newID(){int temp = id; id++; return temp;}

    public Equipe(Carte carte){
        this.map = carte;
        Hero tim = new Hero(map, newID(),true, 0, 0);
        Hero jhon = new Hero(map, newID(),false,  20, 20);
        boutique  = new Boutique(map);
        list_hero.add(tim);
        list_hero.add(jhon);
        zoneSelection = new ZoneSelection(new Rectangle(0,0,0,0));
        ///Il faudra faire un thread de deplacement par connard
//        ThreadDeplacement tDeplacement = new ThreadDeplacement(list_hero.get(0));
//        tDeplacement.start();
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
