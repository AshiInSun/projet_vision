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

import static java.lang.Math.abs;

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
            if(list_hero.getTeam().get(i).getSelected()){
                g.setColor(Color.white);
                g.fillOval(list_hero.getTeam().get(i).getX(), list_hero.getTeam().get(i).getY(), 20, 20);
            }else{
                g.setColor(Color.black);
                g.fillOval(list_hero.getTeam().get(i).getX(), list_hero.getTeam().get(i).getY(), 20, 20);
            }
        }

        if(list_hero.getZoneSelection().getheightRectangle() != 0 || list_hero.getZoneSelection().getwitdhRectangle() != 0){
            /*if(list_hero.getZoneSelection().getwitdhRectangle() < 0){
               list_hero.getZoneSelection().setXRectangle(list_hero.getZoneSelection().getXRectangle() + list_hero.getZoneSelection().getwitdhRectangle());
               list_hero.getZoneSelection().setWitdhRectangle(-list_hero.getZoneSelection().getwitdhRectangle());
            }*/
            g.setColor(Color.red);
            g.drawRect(list_hero.getZoneSelection().getXRectangle(), list_hero.getZoneSelection().getYRectangle(), list_hero.getZoneSelection().getwitdhRectangle(), list_hero.getZoneSelection().getheightRectangle());
        }

    }
}