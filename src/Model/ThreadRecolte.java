package Model;

import java.awt.*;

public class ThreadRecolte extends Thread{
    private Hero hero;
    private Point tile;
    private int timer = 2000;
    private int DELAY=1;

    public ThreadRecolte(Hero hero, Point tile){
        this.hero = hero;
        this.tile = tile;
    }

    @Override
    public void run(){
        hero.setIs_doing(true);
        //le timer correspond a la longueur de la barre de progression à remplir
        while (timer>0){
            try {
                Thread.sleep(DELAY);
                timer--;
                hero.barre_progression++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //on a fini de récolter, on remet la barre de progression à 0
        hero.barre_progression = 0;
        //on lance la méthode de récolte (la case change, thread de repousse)
        hero.map.recolte(tile.x, tile.y);
        hero.plusBle();
        System.out.println(hero.getBle());
        hero.setIs_doing(false);
    }
}
