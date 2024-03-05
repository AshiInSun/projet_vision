package Vue;

import Model.Carte;
import Model.Equipe;
import Model.Tile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Affichage extends JPanel {

    private Equipe list_hero;
    private Carte carte;

    public Affichage(Equipe list_hero, Carte carte){
        this.carte = carte;
        this.list_hero = list_hero;
        setPreferredSize(new Dimension(1120, 680));
    }

    @Override
    public void paint(Graphics g){

        Graphics2D g2d = (Graphics2D) g;


        super.paint(g);

        for (int i = 0; i < list_hero.getTeam().size() ; i++) {
            g.fillOval(list_hero.getTeam().get(i).getX(), list_hero.getTeam().get(i).getY(), 20, 20);
        }

        for (int i = 0; i < carte.getTiles().length; i++) {
            Tile current = carte.getTiles()[i];
            if(current!=null){
                g.drawImage(current.image, current.pos_x, current.pos_y,40, 40, null);
            }
        }

        for (int i = 0; i < list_hero.getTeam().size() ; i++) {
            g.setColor(Color.BLACK);
            g.fillOval(list_hero.getTeam().get(i).getX(), list_hero.getTeam().get(i).getY(), 20, 20);
            if(list_hero.getTeam().get(i).is_doing){
                g.setColor(Color.GREEN);
                g.fillRect(list_hero.getTeam().get(i).getX()-5, list_hero.getTeam().get(i).getY()-20, (list_hero.getTeam().get(i).barre_progression)/58, 10);//valeur un peu random mais tkt ça marche bien
            }
        }
    }
}