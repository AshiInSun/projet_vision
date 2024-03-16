package Model;

import java.awt.*;
import java.util.ArrayList;

public class Equipe {
    private ArrayList<Hero> list_hero = new ArrayList<Hero>();
    private Carte map;
    private Point posClick;
    private int id=37;
    private boolean[] selected;
    private ArrayList<Integer> currentChamp;

    public ArrayList<Hero> getTeam(){return  list_hero;}
    public void setPosClick(Point p){posClick = p;}
    public Carte getMap(){return map;}

    public void deplacement(){
        map.update_chemin(new Point(posClick.x/map.getTILESIZE(), posClick.y/map.getTILESIZE()));

        if (!list_hero.isEmpty()) {
            if (!list_hero.get(0).is_doing()){
                Point cible = new Point(posClick.x/map.getTILESIZE(), posClick.y/map.getTILESIZE());
                ArrayList<Point> chemin = map.calcul_chemin(new Point(list_hero.get(0).getX()/map.getTILESIZE(), list_hero.get(0).getY()/map.getTILESIZE()));
                chemin.add(cible);
                list_hero.get(0).deplacement(chemin);
            }
        }
    }


    private int newID(){int temp = id; id++; return temp;}

    public Equipe(Carte carte){
        this.map = carte;
        Hero tim = new Hero(map, newID());
        list_hero.add(tim);
        ///Il faudra faire un thread de deplacement par connard
        ///ThreadDeplacement tDeplacement = new ThreadDeplacement(list_hero.get(0));
        ///tDeplacement.start();
    }
}
