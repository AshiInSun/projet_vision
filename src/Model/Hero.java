package Model;

import java.awt.*;

public class Hero {
    private Carte map;
    private int pos_x;
    private int pos_y;
    public int avancement_x=0;
    public int avancement_y=0;
    private int id;
    private boolean selected = false;

    private int ble=0;

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

    public Hero(Carte carte, int id, boolean b,int  x, int y){
        this.id = id;
        this.map = carte;
        this.pos_x = x;
        this.pos_y = y;
        this.selected = b;
        ThreadDeplacement tDeplacement = new ThreadDeplacement(this);
        tDeplacement.start();
    }

    public void deplacement(Point posClick){
        avancement_x = posClick.x-pos_x; //Alors là ya des sorcelleries
        avancement_y = posClick.y-pos_y;
    }
    public void recolte(){
        int j = pos_x/40;
        int i = pos_y/40;
        int index = i*map.getSizeMap().x + j;
        if(map.getTiles()[index].type==2){
            ble++;
            System.out.println(ble);
            map.recolte(i, j);
        }
    }

    public void setSelected(boolean b){
        selected = b;
    }

    public boolean getSelected(){
        return selected;
    }

    public void selectionne(Point p){
//        System.out.println(p.x);
//        System.out.println(p.y);
//        System.out.println(pos_x);
//        System.out.println(pos_y);
//        System.out.println(Hitbox.in(p, new Point(pos_x, pos_y), -30, -30));
        if(Hitbox.in(p, new Point(pos_x, pos_y), 20, 20)){
            selected = true;
        }else{
            selected = false;
        }
    }

    public void selectionneZone(Point p1, Point p2){
        selected = false;
        if(p1.x<p2.x){
            if(p1.y<p2.y){
                if(Hitbox.in(new Point(pos_x, pos_y), p1, p2.x-p1.x, p2.y-p1.y)){
                    selected = true;
                }
            }else{
                if(Hitbox.in(new Point(pos_x, pos_y), new Point(p1.x, p2.y), p2.x-p1.x, p1.y-p2.y)){
                    selected = true;
                }
            }
        }else{
            if(p1.y<p2.y){
                if(Hitbox.in(new Point(pos_x, pos_y), new Point(p2.x, p1.y), p1.x-p2.x, p2.y-p1.y)){
                    selected = true;
                }
            }else{
                if(Hitbox.in(new Point(pos_x, pos_y), p2, p1.x-p2.x, p1.y-p2.y)){
                    selected = true;
                }
            }
        }
    }
}
