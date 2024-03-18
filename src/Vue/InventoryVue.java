package Vue;

import Model.Inventory;
import Model.PanelRound;
import Main.Main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class InventoryVue extends PanelRound {

    private Inventory inventory;
    private int width = WIDTH-200;
    private int height = HEIGHT-575;
    private JLabel bleLabel;

    private int round = 25;
    public Inventory getInventory() {
        return inventory;
    }

    public InventoryVue(Inventory inventory){
        this.inventory = inventory;
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.setLayout(new BorderLayout());
        this.setBounds(width, height, 200, 500);

        JLabel inventoryLabel = new JLabel("   Inventaire");
        inventoryLabel.setFont(new Font("Arial", Font.BOLD, 20));
        inventoryLabel.setForeground(Color.WHITE);
        //inventoryLabel.setHorizontalAlignment(SwingConstants.CENTER);

        bleLabel = new JLabel("Blé : " + inventory.getBle());
        bleLabel.setForeground(Color.WHITE);
        //bleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        this.setRoundTopLeft(round);
        this.setRoundTopRight(0);
        this.setRoundBottomLeft(round);
        this.setRoundBottomRight(0);

        this.add(inventoryLabel, BorderLayout.NORTH);
        this.add(bleLabel, BorderLayout.CENTER);

        this.setBackground(new Color(0, 0, 0, 200));
    }

    //getters width and height
    public int getWIDTH() {
        return width;
    }

    public int getHEIGHT() {
        return height;
    }

    public void updateBleQuantity() {
        bleLabel.setText("Ble : " + inventory.getBle());
    }

}
