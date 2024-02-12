package Vue;

public class Redessine extends Thread{
    private Affichage affichage;
    public int DELAY=1;

    public Redessine(Affichage affichage){
        this.affichage = affichage;
    }

    @Override
    public void run(){
        while (true){
            affichage.revalidate();
            affichage.repaint();
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
