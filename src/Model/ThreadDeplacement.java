package Model;

import Vue.Affichage;

import java.awt.*;
import java.util.ArrayList;

public class ThreadDeplacement extends Thread{
    private Hero hero;
    private ArrayList<Point> chemin;
    private Point cible;

    public int DELAY=5;
    private int avancement_x=0;
    private int avancement_y=0;

    private boolean isMoving = true;

    public void stopMoving(){
        isMoving = false;
    }

    public ThreadDeplacement(Hero hero, Point cible){
        this.hero = hero;
        this.cible = cible;
    }

    @Override
    public void run(){
        int i = 1;
        Point p = new Point(hero.getX()/hero.map.getTILESIZE(), hero.getY()/hero.map.getTILESIZE());
        ArrayList<Point> chemin = hero.map.calcul_chemin(p);
        chemin.add(cible);


        while (i < chemin.size() && isMoving) {
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
            i++;
        }
        hero.tile_checked = false;
    }

}
