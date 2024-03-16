package Model;

import java.awt.*;

public class ThreadChecked extends Thread{
    private Hero hero;

    private int DELAY=1;

    public ThreadChecked(Hero hero){
        this.hero = hero;
    }

    @Override
    public void run(){

        while (true){
            if(!hero.tile_checked){
                hero.recolte();
                hero.tile_checked = true;
            }
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
