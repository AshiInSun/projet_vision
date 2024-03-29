package Model;

public class ThreadRepousse extends Thread{
    private Carte carte;
    private int index;
    public int DELAY=5000;

    public ThreadRepousse(Carte carte, int index){
        this.carte = carte;
        this.index = index;
    }

    @Override
    public void run(){
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            carte.getTiles()[index] = new Tile(carte.getTiles()[index].pos_x, carte.getTiles()[index].pos_y, 4);
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            carte.getTiles()[index] = new Tile(carte.getTiles()[index].pos_x, carte.getTiles()[index].pos_y, 2);
    }
}
