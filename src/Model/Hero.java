package Model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class Hero {
    private Carte map;
    private int pos_x;
    private int pos_y;
    public int avancement_x=0;
    public int avancement_y=0;
    private int id;

    private int ble=0;
    private int pierre=0;

    public int getX(){return pos_x - map.getAvancement_x();}
    public void setX(int x){pos_x=x;}
    public int getY(){return pos_y - map.getAvancement_y();}
    public void setY(int y){pos_y=y;}
    public int getID(){return id;}

    public void setID(int i){this.id = i;}



    public Hero(Carte carte, int id){
        this.id = id;
        this.map = carte;
        this.pos_x = 0;
        this.pos_y = 0;
    }

    public int getBle() {
        return ble;
    }

    public int getPierre() {
        return pierre;
    }

    public void deplacement(Point posClick){
        avancement_x = posClick.x-pos_x;
        avancement_y = posClick.y-pos_y;
    }

    public void recolte(){
        int j = pos_x/40;
        int i = pos_y/40;
        int index = i*map.getSizeMap().x + j;
        if(map.getTiles()[index].type==2){
            ble++;
            map.recolte(i, j);
        }
    }

}
