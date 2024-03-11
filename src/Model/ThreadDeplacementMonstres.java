package Model;

public class ThreadDeplacementMonstres extends Thread{
    private Monstre monstre;
    public int DELAY=50;

    public ThreadDeplacementMonstres(Monstre monstre){
        this.monstre = monstre;
    }

    @Override
    public void run() {

        while (true) {
            if (!monstre.getDoing()) {
                if (monstre.avancement_x > 0) {
                    monstre.avancement_x--;
                    monstre.setX(monstre.getX() + 1);
                }
                if (monstre.avancement_x < 0) {
                    monstre.avancement_x++;
                    monstre.setX(monstre.getX() - 1);
                }
                if (monstre.avancement_y > 0) {
                    monstre.avancement_y--;
                    monstre.setY(monstre.getY() + 1);
                }
                if (monstre.avancement_y < 0) {
                    monstre.avancement_y++;
                    monstre.setY(monstre.getY() - 1);
                }
                if (monstre.avancement_y == 0 && monstre.avancement_x == 0) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    monstre.deplace();
                }
                try {
                    Thread.sleep(DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                //
            }
        }
    }
}
