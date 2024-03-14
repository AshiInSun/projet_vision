package Main;

import Control.MouseClickR;
import Model.Carte;
import Model.Equipe;
import Model.Equipe_Monstre;
import Model.ThreadDeplacement;
import Vue.Affichage;
import Vue.Redessine;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        JFrame maFenetre = new JFrame("projet_vision");
        maFenetre.setResizable(false);

        Carte carte = new Carte();
        Equipe equipe = new Equipe(carte);
        Equipe_Monstre meute = new Equipe_Monstre(carte);
        MouseClickR mouseR = new MouseClickR(equipe);
        Affichage panel = new Affichage(equipe, carte, meute);

        Redessine redessine = new Redessine(panel);

        maFenetre.add(panel);
        panel.addMouseListener(mouseR);

        redessine.start();
        maFenetre.pack();
        maFenetre.setVisible(true);
    }
}