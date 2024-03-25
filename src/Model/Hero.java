package Model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

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
    private boolean selected = false;

    //stats
    private int PV = 100;
    private int AD = 20;

    private int ble=0;
    private int pierre=0;

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
    public int getPierre() {
        return pierre;
    }
    public boolean is_doing(){return is_doing;}
    public void setIs_doing(boolean b){is_doing=b;}
    public boolean getNew_move(){return new_move;}
    public void setNew_move(boolean b){new_move=b;}
    public Carte getMap(){return map;}
    public Point getCible(){return cible;}
    public Point getPos(){return new Point(pos_x, pos_y);}
    public Point getNumPos(){return new Point(pos_x/map.getTILESIZE(), pos_y/map.getTILESIZE());}
    public boolean isSelected(){return selected;}

    public Hero(Carte carte, int id){
        this.id = id;
        this.map = carte;
        this.pos_x = 240;
        this.pos_y = 160;
        //threaad qui check les cases et lance les actions (récolte, combat, etc...)
        ThreadChecked tChecked = new ThreadChecked(this);
        tChecked.start();
    }

    public Hero(Carte carte, int id, boolean b,int  x, int y){
        this.id = id;
        this.map = carte;
        this.pos_x = x;
        this.pos_y = y;
        this.selected = b;
        //thread de deplacement
        ThreadDeplacement tDeplacement = new ThreadDeplacement(this);
        tDeplacement.start();
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
        if(Hitbox.in(p, new Point(pos_x, pos_y), 30, 30)){
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
