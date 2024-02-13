package Model;

import java.awt.*;
import java.util.ArrayList;

public class Equipe {
    private ArrayList<Hero> list_hero = new ArrayList<Hero>();
    private Carte map;
    private Point posClick;
    private int id=0;
    private boolean[] selected;
    private ArrayList<Integer> currentChamp;

    public ArrayList<Hero> getTeam(){return  list_hero;}
    public void setPosClick(Point p){posClick = p;}

    public void deplacement(){
        if (!list_hero.isEmpty()) {
            list_hero.get(0).deplacement(posClick);
        }
    }


    private int newID(){int temp = id; id++; return temp;}

    public Equipe(Carte carte){
        this.map = carte;
        Hero tim = new Hero(map, newID());
        list_hero.add(tim);
        ///Il faudra faire un thread de deplacement par connard
        ThreadDeplacement tDeplacement = new ThreadDeplacement(list_hero.get(0));
        tDeplacement.start();
    }
}
