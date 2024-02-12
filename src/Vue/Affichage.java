package Vue;

import Model.Equipe;
import Model.Hero;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Affichage extends JPanel {

    private Equipe list_hero;

    public Affichage(Equipe list_hero){

        this.list_hero = list_hero;
        setPreferredSize(new Dimension(400, 400));
    }

    @Override
    public void paint(Graphics g){

        Graphics2D g2d = (Graphics2D) g;

        super.paint(g);

        for (int i = 0; i < list_hero.getTeam().size() ; i++) {
            g.fillOval(list_hero.getTeam().get(i).getX(), list_hero.getTeam().get(i).getY(), 20, 20);
        }
    }
}