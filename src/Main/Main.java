package Main;

import Control.MouseClickR;
import Model.Carte;
import Model.ZoneSelection;
import Model.Equipe;
import Model.ThreadDeplacement;
import Vue.Affichage;
import Vue.Redessine;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame maFenetre = new JFrame("projet_vision");
        maFenetre.setResizable(false);

        Carte carte = new Carte();
        Equipe equipe = new Equipe(carte);
        MouseClickR mouseR = new MouseClickR(equipe);
        Affichage panel = new Affichage(equipe, carte);

        Redessine redessine = new Redessine(panel);

        maFenetre.add(panel);
        panel.addMouseListener(mouseR);
        panel.addMouseMotionListener(mouseR);

        redessine.start();
        maFenetre.pack();
        maFenetre.setVisible(true);
    }
}