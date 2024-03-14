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

    public ThreadDeplacement(Hero hero, ArrayList<Point> chemin){
        this.hero = hero;
        this.chemin = chemin;
    }

    @Override
    public void run(){
        for (int i = 0; i < chemin.size(); i++) {
            avancement_x = (chemin.get(i).x)*hero.map.getTILESIZE() - hero.getX();
            avancement_y = (chemin.get(i).y)*hero.map.getTILESIZE() - hero.getY();

            for(int j = i; j < chemin.size(); j++){
                System.out.println(chemin.get(j).x + ", " + chemin.get(j).y);
            }
            System.out.println("x : " + hero.getX());
            System.out.println("chemin_x" + (chemin.get(i).x)*hero.map.getTILESIZE());
            System.out.println("y : " + hero.getY());
            System.out.println("chemin_y" + (chemin.get(i).y)*hero.map.getTILESIZE());
            System.out.println("avancement_x : " + avancement_x);
            System.out.println("avancement_y : " + avancement_y);

            while (avancement_x != 0 || avancement_y != 0) {
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
    }
}
