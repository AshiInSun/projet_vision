package Main;

import Control.MouseClickR;
import Model.Carte;
import Model.Equipe;
import Model.Inventory;
import Vue.Affichage;
import Vue.InventoryVue;
import Vue.Redessine;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        int width = 1120;
        int height = 680;
        JFrame maFenetre = new JFrame("projet_vision");
        maFenetre.setSize(width, height);
        maFenetre.setLocationRelativeTo(null);
        maFenetre.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        maFenetre.setResizable(false);

        Carte carte = new Carte();
        Equipe equipe = new Equipe(carte);
        Inventory inventaire = new Inventory(equipe);
        MouseClickR mouseR = new MouseClickR(equipe);

        Affichage panel = new Affichage(equipe, carte);
        panel.setBounds(0,0,width,height);
        panel.setOpaque(true);

        InventoryVue inventoryVue = new InventoryVue(inventaire);
        inventoryVue.setBounds(width-200, height-575, 200, 500);

        Redessine redessine = new Redessine(panel, inventoryVue);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1120, 680));
        layeredPane.setBounds(0,0, 1120, 680);
        layeredPane.add(panel, Integer.valueOf(0));
        layeredPane.add(inventoryVue, Integer.valueOf(1));

        maFenetre.add(layeredPane);
        panel.addMouseListener(mouseR);
        redessine.start();
        maFenetre.pack();
        maFenetre.setVisible(true);
    }
}