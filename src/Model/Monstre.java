package Model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Monstre {
    private int id;
    private boolean is_doing = false;
    public Carte map;
    private int tileSize=40;
    private Point center;
    private ArrayList<Point> CASES = new ArrayList<Point>();
    private Point pos;
    public int avancement_x=0;
    public int avancement_y=0;
    private int PV = 50;
    private int AD = 10;

    public Point getPos(){return pos;}
    public void setPos(Point p){pos = p;}
    public void setDoing(boolean b){is_doing = b;}
    public boolean getDoing(){return is_doing;}
    public int getID(){return id;}
    public void setID(int i){this.id = i;}
    public int getPV(){return PV;}
    public void setPV(int pv){PV = pv;}
    public int getAD(){return AD;}
    public void setAD(int ad){AD = ad;}
    public void setX(int x){pos.x = x;}
    public void setY(int y){pos.y = y;}
    public int getX(){return pos.x;}
    public int getY(){return pos.y;}

    public ArrayList<Point> voisins(Point centre){
        ArrayList<Point> res = new ArrayList<>();
        res.add(centre);
        res.add(new Point(centre.x+tileSize, centre.y+tileSize));
        res.add(new Point(centre.x+tileSize, centre.y));
        res.add(new Point(centre.x+tileSize, centre.y-tileSize));
        res.add(new Point(centre.x, centre.y+tileSize));
        res.add(new Point(centre.x, centre.y-tileSize));
        res.add(new Point(centre.x-tileSize, centre.y+tileSize));
        res.add(new Point(centre.x-tileSize, centre.y));
        res.add(new Point(centre.x-tileSize, centre.y-tileSize));
        return res;
    }

    public Monstre(int id){
        this.pos = new Point(0,0);
        this.id = id;
    }


    public Monstre(Point pos, int id, Carte map){
        this.pos = pos;
        this.center = pos;
        this.CASES = voisins(center);
        this.id = id;
        this.map = map;
        this.tileSize = map.getTILESIZE();
    }

    public void deplace(){
        //je veux un random qui choisis une case de la fonction cases()
        Random rand = new Random();
        int n = rand.nextInt(CASES.size());
        Point p = CASES.get(n);
        avancement_x = p.x - pos.x;
        avancement_y = p.y - pos.y;
    }

}
