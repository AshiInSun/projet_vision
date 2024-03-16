package Model;

import Vue.Affichage;

import java.awt.*;
import java.util.ArrayList;

public class ThreadDeplacement extends Thread{
    private Hero hero;
    private ArrayList<Point> chemin;

    public int DELAY=5;
    private int avancement_x=0;
    private int avancement_y=0;
    private int id;

    public ThreadDeplacement(Hero hero, ArrayList<Point> chemin, int id){
        this.hero = hero;
        this.chemin = chemin;
        this.id = id;
    }

    @Override
    public void run(){
        hero.setIs_moving(true);
        for (int i=0; i < chemin.size(); i++) {
            avancement_x = (chemin.get(i).x)*hero.map.getTILESIZE() - hero.getX();
            avancement_y = (chemin.get(i).y)*hero.map.getTILESIZE() - hero.getY();

            while ((avancement_x != 0 || avancement_y != 0)/*&&hero.id_thread==id*/) {
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
        }

        hero.setIs_moving(false);
        hero.tile_checked = false;
    }
}
