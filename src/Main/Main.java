package Main;

import Control.MouseClickR;
import Model.Carte;
import Model.ZoneSelection;
import Model.Equipe;
import Model.Equipe_Monstre;
import Model.ThreadDeplacement;
import Vue.Affichage;
import Vue.Redessine;
import Model.Inventory;
import Vue.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main {
    public static final int WIDTH = 1120;
    public static final int HEIGHT = 680;

    public static void main(String[] args){
        JFrame maFenetre = new JFrame("projet_vision");
        maFenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        maFenetre.setSize(WIDTH, HEIGHT);
        maFenetre.setLocationRelativeTo(null);
        maFenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        maFenetre.setResizable(false);

        Carte carte = new Carte();
        Equipe equipe = new Equipe(carte);
        Equipe_Monstre meute = new Equipe_Monstre(carte);
        Inventory inventaire = new Inventory(equipe);
        MouseClickR mouseR = new MouseClickR(equipe);
        Affichage panel = new Affichage(equipe, carte, meute);

        Affichage panel = new Affichage(equipe, carte);
        panel.setBounds(0,0,WIDTH,HEIGHT);
        panel.setOpaque(true);

        InventoryVue inventoryVue = new InventoryVue(inventaire);
        inventoryVue.setBounds(WIDTH-115, HEIGHT-575, 115, 200);

        Redessine redessine = new Redessine(panel, inventoryVue);


        //carte.update_chemin(new Point(12, 8));
        //ArrayList<Point> test = carte.calcul_chemin(new Point(15, 10));
        //System.out.println(test.toString());

        maFenetre.add(panel);
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1120, 680));
        layeredPane.setBounds(0,0, 1120, 680);
        layeredPane.add(panel, Integer.valueOf(0));
        layeredPane.add(inventoryVue, Integer.valueOf(1));

        maFenetre.add(layeredPane);
        panel.addMouseListener(mouseR);
        panel.addMouseMotionListener(mouseR);

        //test
        /*panel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                carte.changeMap();
            }
        });

         */

        redessine.start();
        maFenetre.pack();
        maFenetre.setVisible(true);
    }
}