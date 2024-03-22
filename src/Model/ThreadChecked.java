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
            //si une case n'est pas check, on lance les actions (récolte, combat, etc...) c'est les actions qui verifiront si la case est recoltable par exemple
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
