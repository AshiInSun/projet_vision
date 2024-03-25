package Model;

import Vue.Affichage;

import java.awt.*;
import java.util.ArrayList;
import java.util.Hashtable;

public class ThreadDeplacement extends Thread{
    private Hero hero;
    //on commence avec un chemin vide

    private ArrayList<Point> chemin = new ArrayList<Point>();
    private Point cible;

    public int DELAY=5;
    private int i=1; //indice du chemin
    private int avancement_x=0;
    private int avancement_y=0;
    private Carte map;
    private int[][] numMap;
    private Point SIZEMAP;
    // pour chaque point, donne la distance la plus courte depuis la case de départ
    private Hashtable<Point,Integer> distance = new Hashtable<Point,Integer>();
    // pour chaque point, donne le point d'avant sur le chemin le plus court depuis la case de départ
    private Hashtable<Point,Point> parent = new Hashtable<Point,Point>();


    public ThreadDeplacement(Hero hero){
        this.hero = hero;
        this.map = hero.getMap();
        this.numMap = map.getNumMap();
        this.SIZEMAP = map.getSizeMap();
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
        ArrayList<Point> res = new ArrayList<Point>();
        // vérifier que cible est accessible (donc elle est dans distance/parent)
        // sinon renvoyer un chemin null
        while (parent.get(cible).x!=-1){
            res.add(cible);
            cible = parent.get(cible);
        }
        // tant que cible.parent != null, prendre cible.parent et lajouter dans le résultat (au début)
        // en partant de la cible, on remonte jusqu'à retomber sur notre case de départ
        return res;
    }



    @Override
    public void run(){

        while(true){
            if(hero.getNew_move()){
                //on recalcule le chemin
                cible = hero.getCible();
                update_chemin(cible);
                chemin = calcul_chemin(new Point(hero.getX()/map.getTILESIZE(), hero.getY()/map.getTILESIZE()));
                chemin.add(new Point(cible.x, cible.y));
                hero.setNew_move(false);
            }

            for (i = 1; i < chemin.size(); i++){

                if(hero.getNew_move()){
                    //on recalcule le chemin
                    break;
                }

                avancement_x = (chemin.get(i).x)*hero.map.getTILESIZE() - hero.getX();
                avancement_y = (chemin.get(i).y)*hero.map.getTILESIZE() - hero.getY();

                while ((avancement_x != 0 || avancement_y != 0)) {
                    if (avancement_x > 0) {
                        hero.incrX();
                        avancement_x -= 1;
                    }
                    if (avancement_x < 0) {
                        hero.decrX();
                        avancement_x += 1;
                    }
                    if (avancement_y > 0) {
                        hero.incrY();
                        avancement_y -= 1;
                    }
                    if (avancement_y < 0) {
                        hero.decrY();
                        avancement_y += 1;
                    }
                    try {
                        Thread.sleep(DELAY);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (i==chemin.size()-1){
                    //on a atteint la cible, on doit vérifier la case
                    hero.tile_checked = false;
                }
            }
            chemin = new ArrayList<Point>();    //on vide le chemin
        }
    }

}
