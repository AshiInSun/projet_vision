package Model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

public class Carte {

    private static final Point SIZEMAP = new Point(28, 17);
    private Tile[] tiles;
    private int numMap[][];
    private int avancement_x=0;
    private int avancement_y=0;
    // pour chaque point, donne la distance la plus courte depuis la case de départ
    private Hashtable<Point,Integer> distance = new Hashtable<Point,Integer>();
    // pour chaque point, donne le point d'avant sur le chemin le plus court depuis la case de départ
    private Hashtable<Point,Point> parent = new Hashtable<Point,Point>();
    private int TILESIZE = 40;


    public int getAvancement_x(){return avancement_x;}
    public int getAvancement_y(){return avancement_y;}
    public Point getSizeMap(){return SIZEMAP;}
    public int[][] getNumMap(){return numMap;}
    public Tile[] getTiles(){return tiles;}
    public int getTILESIZE(){return TILESIZE;}

    public void loadMap(){
        InputStream is = getClass().getResourceAsStream("/img/carte_data.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        for (int i = 0; i < SIZEMAP.y; i++) {
            String line;
            try {
                line = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String num[] = line.split(" ");
            for(int j=0; j<num.length; j++){
                int typ = Integer.parseInt(num[j]);
                numMap[i][j] = typ;
            }
        }
    }

    public void loadTile(){
        int x; int y=0; int index=0;
        tiles = new Tile[numMap.length*numMap[0].length];
        for (int i = 0; i < numMap.length; i++) {
            x=0;
            for (int j = 0; j < numMap[i].length; j++) {
                tiles[index]= new Tile(x, y, numMap[i][j]);
                index++;
                x+=40;
            }
            y+=40;
        }
    }

    public ArrayList<Point> voisins (Point p){
        ArrayList<Point> res = new ArrayList<Point>();
        if(p.x!=0){
            res.add(new Point(p.x-1, p.y));
        }
        if(p.x!=SIZEMAP.x){
            res.add(new Point(p.x+1, p.y));
        }
        if(p.y!= 0){
            res.add(new Point(p.x, p.y-1));
        }
        if(p.y!= SIZEMAP.y){
            res.add(new Point(p.x, p.y+1));
        }

        return res;
    }

    public Point pop_meilleur(ArrayList<Point> avoir){
        Point res = avoir.get(0);
        for (int i = 1; i < avoir.size(); i++) {
            if(distance.get(avoir.get(i))<distance.get(res)){
                res = avoir.get(i);
            }
        }
        return res;
    }



    public void update_chemin(Point depart) {
        distance = new Hashtable<Point,Integer>();
        distance.put(depart,0);
        parent = new Hashtable<Point,Point>();
        parent.put(depart,new Point(-1, -1));

        ArrayList<Point> a_voir = new ArrayList<Point>();
        a_voir.add(depart);

        while (a_voir.size()>0) {
            Point p = pop_meilleur(a_voir);
            a_voir.remove(p);
            ArrayList<Point> voisins_possibles = voisins(p);
            int d = distance.get(p)+1;
            for(Point v : voisins_possibles) {
                if (distance.contains(v)) {
                    if (distance.get(v) > d) {
                        distance.put(v,d);
                        parent.put(v,p);
                    }
                } else {
                    a_voir.add(v);
                    distance.put(v,d);
                    parent.put(v,p);
                }
            }
        }

        // ici, parent et distance sont corrects pour tous les points, par rapport à depart choisi
    }

    public ArrayList<Point> calcul_chemin(Point cible) {
        ArrayList<Point> res = new ArrayList<Point>();
        while (parent.get(cible).x!=-1){
            res.add(cible);
            cible = parent.get(cible);
        }
        // tant que cible.parent != null, prendre cible.parent et lajouter dans le résultat (au début)
        // en partant de la cible, on remonte jusqu'à retomber sur notre case de départ
        return res;
    }

    public Carte(){
        numMap = new int[SIZEMAP.y][SIZEMAP.x];
        loadMap();
        loadTile();
//        tiles = new Tile[10];
//        tiles[0] = new Tile(0, 0, 1);
//        tiles[1] = new Tile(40, 0, 0);

    }

    public void recolte(int i, int j){
        int index = SIZEMAP.x*(i) + j;
        tiles[index] = new Tile(tiles[index].pos_x, tiles[index].pos_y, 3);
        ThreadRepousse threadRepousse = new ThreadRepousse(this, index);
        threadRepousse.start();
    }
}
