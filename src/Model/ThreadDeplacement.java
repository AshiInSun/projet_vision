package Model;

import Vue.Affichage;

public class ThreadDeplacement extends Thread{
    private Hero hero;
    public int DELAY=5;

    public ThreadDeplacement(Hero hero){
        this.hero = hero;
    }

    @Override
    public void run(){
        while (true){
                if(hero.avancement_x>0){
                    hero.avancement_x--;
                    hero.setX(hero.getX()+1);
                }
                if(hero.avancement_x<0){
                    hero.avancement_x++;
                    hero.setX(hero.getX()-1);
                }
                if(hero.avancement_y>0){
                    hero.avancement_y--;
                    hero.setY(hero.getY()+1);
                }
                if(hero.avancement_y<0){
                    hero.avancement_y++;
                    hero.setY(hero.getY()-1);
                }
                if(hero.avancement_y==0&&hero.avancement_x==0&& !hero.is_doing){
                    hero.recolte();
                    hero.fight();
                }
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
