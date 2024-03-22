package Model;

import java.awt.*;
import java.util.ArrayList;

public class Hero {
    public Carte map;
    //si new_move est true, le thread de deplacement doit recalculer le chemin
    private boolean new_move = false;
    //cible est la case ou le hero veut se rendre
    private Point cible;
    private int pos_x;
    private int pos_y;
    //tile_checked est true si le thread de check a vérifié la case (ce thread de check lance les récoltes, les combats, etc...)
    public boolean tile_checked=true;
    //is_doing est true si le hero est en train de faire une action autre que dépalcement
    private boolean is_doing = false;
    //barre_progression est la barre de progression de l'action en cours (récolte, combat, etc...)
    public int barre_progression=0;
    private int id;

    //stats
    private int PV = 100;
    private int AD = 20;

    private int ble=0;

    //getters et setters

    public int getX(){return pos_x;}
    public void incrX(){pos_x+=1;}
    public void incrY(){pos_y+=1;}
    public void decrX(){pos_x-=1;}
    public void decrY(){pos_y-=1;}
    public void setX(int x){pos_x=x;}
    public int getY(){return pos_y;}
    public void setY(int y){pos_y=y;}
    public int getID(){return id;}
    public void setID(int i){this.id = i;}
    public void plusBle(){this.ble++;}
    public int getBle(){return this.ble;}
    public boolean is_doing(){return is_doing;}
    public void setIs_doing(boolean b){is_doing=b;}
    public boolean getNew_move(){return new_move;}
    public void setNew_move(boolean b){new_move=b;}
    public Carte getMap(){return map;}
    public Point getCible(){return cible;}

    public Hero(Carte carte, int id){
        this.id = id;
        this.map = carte;
        this.pos_x = 240;
        this.pos_y = 160;
        //threaad qui check les cases et lance les actions (récolte, combat, etc...)
        ThreadChecked tChecked = new ThreadChecked(this);
        tChecked.start();
    }

    //méthode de déplacement, envoie la cible au thread de déplacement
    public void deplacement(Point cible){
        if(!is_doing) {
            this.cible = cible;
            new_move = true;
        }
    }

    //méthode de récolte, envoie la case à récolter au thread de récolte si la case est récoltable (i.e. type==2)
    public void recolte(){
        int j = pos_x/40;
        int i = pos_y/40;
        int index = i*map.getSizeMap().x + j;
        if(map.getTiles()[index].type==2){
            ThreadRecolte recolte = new ThreadRecolte(this, new Point(i, j));
            recolte.start();
        }
    }

    public void fight(){
        //a faire starfoullah ça va etre chiant de fou
    }

}
