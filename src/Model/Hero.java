package Model;

import java.awt.*;
import java.util.ArrayList;

public class Hero {
    public Carte map;
    private boolean new_move = false;
    private int pos_x;
    private int pos_y;
    boolean is_moving;
    public boolean tile_checked=true;
    int id_thread=0;
    public int avancement_x=0;
    public int avancement_y=0;
    private boolean is_doing = false;
    public int barre_progression=0;
    private int id;
    //stats
    private int PV = 100;
    private int AD = 20;

    private int ble=0;

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
    public void setIs_moving(boolean b){is_moving=b;}
    public boolean is_doing(){return is_doing;}
    public void setIs_doing(boolean b){is_doing=b;}
    public boolean getNew_move(){return new_move;}
    public void setNew_move(boolean b){new_move=b;}

    public Hero(Carte carte, int id){
        this.id = id;
        this.map = carte;
        this.pos_x = 240;
        this.pos_y = 160;
        ThreadChecked tChecked = new ThreadChecked(this);
        tChecked.start();
    }

    public void deplacement(ArrayList<Point> chemin){
        if(!is_doing && !is_moving) {
            ThreadDeplacement tDeplacement = new ThreadDeplacement(this, chemin, id_thread);
            id_thread++;
            tDeplacement.start();
        }
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
