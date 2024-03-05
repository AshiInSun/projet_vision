package Model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Monstre {
    private int id;
    public Carte map;
    private int tileSize;
    private Point center;
    private Point pos;
    public int avancement_x=0;
    public int avancement_y=0;
    private int PV = 50;
    private int AD = 10;

    public Point getPos(){return pos;}

    public Monstre(int id){
        this.pos = new Point(0,0);
        this.id = id;
    }

    public Monstre(Point pos, int id, Carte map){
        this.pos = pos;
        this.id = id;
        this.map = map;
        this.tileSize = map.getTILESIZE();
    }

    public ArrayList<Point> cases(){
        ArrayList<Point> res = new ArrayList<>();
        res.add(center);
        res.add(new Point(center.x+tileSize, center.y+tileSize));
        res.add(new Point(center.x+tileSize, center.y));
        res.add(new Point(center.x+tileSize, center.y-tileSize));
        res.add(new Point(center.x, center.y+tileSize));
        res.add(new Point(center.x, center.y-tileSize));
        res.add(new Point(center.x-tileSize, center.y+tileSize));
        res.add(new Point(center.x-tileSize, center.y));
        res.add(new Point(center.x-tileSize, center.y-tileSize));
        return res;
    }

    public void deplace(){
        //je veux un random qui choisis une case de la fonction cases()
        Random rand = new Random();
        ArrayList<Point> cases = cases();
        int n = rand.nextInt(cases.size());
        Point p = cases.get(n);
        avancement_x = p.x - pos.x;
        avancement_y = p.y - pos.y;
    }

}
