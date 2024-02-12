package Model;

import java.awt.*;

public class Hero {
    private Carte map;
    private int pos_x;
    private int pos_y;
    public int avancement_x=0;
    public int avancement_y=0;
    private int id;

    public int getX(){return pos_x - map.getAvancement_x();}
    public void setX(int x){pos_x=x;}
    public int getY(){return pos_y - map.getAvancement_y();}
    public void setY(int y){pos_y=y;}
    public int getID(){return id;}

    public void setID(int i){this.id = i;}

    public void deplacement(Point posClick){
        avancement_x = posClick.x-7-pos_x; //Alors là ya des sorcelleries
        avancement_y = posClick.y-30-pos_y;
        System.out.println(pos_x);
        System.out.println(pos_y);
    }

    public Hero(Carte carte, int id){
        this.id = id;
        this.map = carte;
        this.pos_x = 0;
        this.pos_y = 0;
    }

}
