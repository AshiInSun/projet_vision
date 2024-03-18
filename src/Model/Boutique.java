package Model;

public class Boutique {
    private Carte map;
    private int pos_x;
    private int pos_y;

    private boolean selected = false;


    public Boutique(Carte carte, int x, int y){
        this.map = carte;
        this.pos_x = x;
        this.pos_y = y;
    }

    public int getXBoutique(){
        return pos_x - map.getAvancement_x();
    }

    public int getYBoutique(){
        return pos_y - map.getAvancement_y();
    }
}
