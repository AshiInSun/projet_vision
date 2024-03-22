package Model;

import Vue.Affichage;

import java.awt.*;
import java.util.ArrayList;

public class ThreadDeplacement extends Thread{
    private Hero hero;
    //on commence avec un chemin vide

    private ArrayList<Point> chemin = new ArrayList<Point>();
    private Point cible;

    public int DELAY=5;
    private int i=1; //indice du chemin
    private int avancement_x=0;
    private int avancement_y=0;


    public ThreadDeplacement(Hero hero){
        this.hero = hero;
    }

    @Override
    public void run(){

        while(true){
            if(hero.getNew_move()){
                //on recalcule le chemin
                cible = hero.getCible();
                chemin = hero.getMap().calcul_chemin(new Point(hero.getX()/hero.getMap().getTILESIZE(), hero.getY()/hero.getMap().getTILESIZE()));
                chemin.add(cible);
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
