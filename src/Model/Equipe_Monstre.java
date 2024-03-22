package Model;

import java.awt.*;
import java.util.ArrayList;


public class Equipe_Monstre {
    private ArrayList<Monstre> list_monstre = new ArrayList<Monstre>();
    private Carte map;
    private int id=0;

    public ArrayList<Monstre> getTeam(){return  list_monstre;}

    private int newID(){int temp = id; id++; return temp;}

    public Equipe_Monstre(Carte carte){
        this.map = carte;
        Monstre grubs_gaga = new Monstre(new Point(400, 300), newID(), map);
        Monstre grubs_gougou = new Monstre(new Point(440, 300), newID(), map);
        Monstre grubs_gigi = new Monstre(new Point(400, 340), newID(), map);
        list_monstre.add(grubs_gigi);
        list_monstre.add(grubs_gaga);
        list_monstre.add(grubs_gougou);
        /// boucle for qui fait un thread de deplacement par monstre
        for (int i = 0; i < list_monstre.size(); i++) {
            ThreadDeplacementMonstres threadDeplacementMonstres = new ThreadDeplacementMonstres(list_monstre.get(i));
            threadDeplacementMonstres.start();
        }
    }
}
