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

    //Taille de la carte
    private static final Point SIZEMAP = new Point(28, 17);
    //Tableau de toutes les tiles, dans l'ordre (ie tiles[30] = la tile en (1,2) (x*28 + y))
    private Inventory inventaire;
    private Tile[] tiles;
    //version en tableau de carte_data.txt
    private int numMap[][];
    // pour chaque point, donne la distance la plus courte depuis la case de départ
    private Hashtable<Point,Integer> distance = new Hashtable<Point,Integer>();
    // pour chaque point, donne le point d'avant sur le chemin le plus court depuis la case de départ
    private Hashtable<Point,Point> parent = new Hashtable<Point,Point>();
//taille d'une tile (carré)
    private int TILESIZE = 40;
    private ArrayList<String> mapList = new ArrayList<String>();


    public Point getSizeMap(){return SIZEMAP;}
    public int[][] getNumMap(){return numMap;}
    public Tile[] getTiles(){return tiles;}
    public int getTILESIZE(){return TILESIZE;}



    //fonction qui convertit le fichier carte_data.txt en tableau de int
    public void loadMap(String map){
        InputStream is = getClass().getResourceAsStream(map);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        int [][] numMap = new int[SIZEMAP.y][SIZEMAP.x];
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
        this.numMap = numMap;
    }

    //fonction qui charge les tiles en fonction du tableau de int
    public void loadTile(){
        int x; int y=0; int index=0;
        Tile[] tiles = new Tile[numMap.length*numMap[0].length];
        for (int i = 0; i < numMap.length; i++) {
            x=0;
            for (int j = 0; j < numMap[i].length; j++) {
                tiles[index]= new Tile(x, y, numMap[i][j]);
                index++;
                x+=40;
            }
            y+=40;
        }
        this.tiles = tiles;
    }

    public Carte(){
        mapList.add("/img/carte_data.txt");
        mapList.add("/img/carte_data2.txt");
        loadMap(mapList.get(0));
        loadTile();
//        tiles = new Tile[10];
//        tiles[0] = new Tile(0, 0, 1);
//        tiles[1] = new Tile(40, 0, 0);

        }
    //fonction qui renvoie les voisisn d'un point (i.e. les points accessibles depuis ce point (i.e. pas des murs))
    public ArrayList<Point> voisins (Point p, ArrayList<Point> deja_vu){
        ArrayList<Point> res = new ArrayList<Point>();
        if(p.x!=0 && numMap[p.y][p.x-1]!=1){
            Point p2 = new Point(p.x-1, p.y);
            if (!deja_vu.contains(p2)) {
                res.add(p2);
            }

        }
        if(p.x!=SIZEMAP.x && numMap[p.y][p.x+1]!=1){
            Point p2 = new Point(p.x+1, p.y);
            if (!deja_vu.contains(p2)) {
                res.add(p2);
            }
        }
        if(p.y!= 0 && numMap[p.y-1][p.x]!=1){
            Point p2 = new Point(p.x, p.y-1);
            if (!deja_vu.contains(p2)) {
                res.add(p2);
            }
        }
        if(p.y!= SIZEMAP.y && numMap[p.y+1][p.x]!=1){
            Point p2 = new Point(p.x, p.y+1);
            if (!deja_vu.contains(p2)) {
                res.add(p2);
            }
        }

        //on test si les if de limites ont vraiment une importance dans l'implémentation des voisins diagonaux
        if(p.x!=0 && p.y!=0 && numMap[p.y-1][p.x-1]!=1){
            Point p2 = new Point(p.x-1, p.y-1);
            if (!deja_vu.contains(p2)) {
                res.add(p2);
            }
        }
        if(p.x!=SIZEMAP.x && p.y!=0 && numMap[p.y-1][p.x+1]!=1){
            Point p2 = new Point(p.x+1, p.y-1);
            if (!deja_vu.contains(p2)) {
                res.add(p2);
            }
        }
        if(p.x!=0 && p.y!=SIZEMAP.y && numMap[p.y+1][p.x-1]!=1){
            Point p2 = new Point(p.x-1, p.y+1);
            if (!deja_vu.contains(p2)) {
                res.add(p2);
            }
        }
        if(p.x!=SIZEMAP.x && p.y!=SIZEMAP.y && numMap[p.y+1][p.x+1]!=1){
            Point p2 = new Point(p.x+1, p.y+1);
            if (!deja_vu.contains(p2)) {
                res.add(p2);
            }
        }

        return res;
    }

    //fonction qui renvoie le point le plus proche de depart dans a_voir (djistrka)

    public Point pop_meilleur(ArrayList<Point> avoir){
        Point res = avoir.get(0);
        int d = distance.get(res);
        for (int i = 1; i < avoir.size(); i++) {
            Point p = avoir.get(i);
            if(distance.get(p)<d){
                res = p;
                d = distance.get(res);
            }
        }
        return res;
    }


    //fonction qui met à jour les tableaux distance et parent pour un depart donné (djistrka) (dans le code on remplace le depart par le point cliqué, + pratique)
    public void update_chemin(Point depart) {
        distance = new Hashtable<Point,Integer>();
        distance.put(depart,0);
        parent = new Hashtable<Point,Point>();
        parent.put(depart,new Point(-1, -1));

        ArrayList<Point> a_voir = new ArrayList<Point>();
        a_voir.add(depart);

        ArrayList<Point> deja_vu = new ArrayList<Point>();

        while (!a_voir.isEmpty()) {
            Point p = pop_meilleur(a_voir);
            a_voir.remove(p);
            deja_vu.add(p);
            ArrayList<Point> voisins_possibles = voisins(p,deja_vu);
            int d = distance.get(p)+1;
            for(Point v : voisins_possibles) {
                if (distance.containsKey(v)) {
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

    //fonction qui renvoie le chemin le plus court, dans le code on met le point de départ. le point d'arrivé est celui mis dans la précédente update_chemin() (djistrka)

    public ArrayList<Point> calcul_chemin(Point cible) {
        // vérifier que cible est accessible (donc elle est dans distance/parent)
        // sinon renvoyer un chemin null

        ArrayList<Point> res = new ArrayList<Point>();
        while (parent.get(cible).x!=-1){
            res.add(cible);
            cible = parent.get(cible);
        }
        // tant que cible.parent != null, prendre cible.parent et lajouter dans le résultat (au début)
        // en partant de la cible, on remonte jusqu'à retomber sur notre case de départ
        return res;
    }

    //fonction qui recolte le blé, on met l'index de la tile à récolter

    public void recolte(int i, int j){
        int index = SIZEMAP.x*(i) + j;
        tiles[index] = new Tile(tiles[index].pos_x, tiles[index].pos_y, 3);
        ThreadRepousse threadRepousse = new ThreadRepousse(this, index);
        threadRepousse.start();
    }

    public void changeMap(){
        loadMap(mapList.get(1));
        loadTile();
    }
}
