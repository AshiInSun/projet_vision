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
        while (timer>0){
            try {
                Thread.sleep(DELAY);
                timer--;
                hero.barre_progression++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        hero.barre_progression = 0;

        hero.map.recolte(tile.x, tile.y);
        hero.plusBle();
        System.out.println(hero.getBle());
        hero.setIs_doing(false);
    }
}
