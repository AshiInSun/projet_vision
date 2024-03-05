package Model;

import java.awt.*;
import java.util.ArrayList;

public class Equipe_Monstre {
    private ArrayList<Monstre> list_monstre = new ArrayList<Monstre>();
    private Carte map;
    private Point posClick;
    private int id=0;
    private boolean[] selected;
    private ArrayList<Integer> currentChamp;

    public ArrayList<Monstre> getTeam(){return  list_monstre;}
    public void setPosClick(Point p){posClick = p;}

    public void deplacement(){
        if (!list_monstre.isEmpty()) {
        }
    }


    private int newID(){int temp = id; id++; return temp;}

    public Equipe_Monstre(Carte carte){
        this.map = carte;
        Monstre grubs = new Monstre();
        list_monstre.add(grubs);
    }
}
