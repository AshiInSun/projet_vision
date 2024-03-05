package Model;

public class ThreadDeplacementMonstres extends Thread{
    private Monstre monstre;
    public int DELAY=5;

    public ThreadDeplacementMonstres(Monstre monstre){
        this.monstre = monstre;
    }

    @Override
    public void run(){

        while (true){

            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
