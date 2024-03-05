package Model;

import java.awt.*;

public class Hero {
    public Carte map;
    private int pos_x;
    private int pos_y;
    public int avancement_x=0;
    public int avancement_y=0;
    public boolean is_doing = false;
    public int barre_progression=0;
    private int id;
    //stats
    private int PV = 100;
    private int AD = 20;

    private int ble=0;

    public int getX(){return pos_x - map.getAvancement_x();}
    public void setX(int x){pos_x=x;}
    public int getY(){return pos_y - map.getAvancement_y();}
    public void setY(int y){pos_y=y;}
    public int getID(){return id;}
    public void setID(int i){this.id = i;}
    public void plusBle(){this.ble++;}
    public int getBle(){return this.ble;}



    public Hero(Carte carte, int id){
        this.id = id;
        this.map = carte;
        this.pos_x = 0;
        this.pos_y = 0;
    }

    public void deplacement(Point posClick){
        avancement_x = posClick.x-pos_x; //Alors là ya des sorcelleries
        avancement_y = posClick.y-pos_y-50;
    }


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
