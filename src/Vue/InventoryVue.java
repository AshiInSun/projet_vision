package Vue;

import Model.Inventory;
import Model.PanelRound;

import javax.swing.*;
import java.awt.*;

public class InventoryVue extends PanelRound {

    private Inventory inventory;
    private JLabel bleLabel;

    private int round = 25;
    public Inventory getInventory() {
        return inventory;
    }

    public InventoryVue(Inventory inventory){
        this.inventory = inventory;
        this.setLayout(new GridLayout(10, 1));

        bleLabel = new JLabel("Blé : " + inventory.getBle());
        bleLabel.setFont(new Font("Arial", Font.BOLD, 15));
        bleLabel.setForeground(Color.WHITE);
        bleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        this.setRoundTopLeft(round);
        this.setRoundTopRight(0);
        this.setRoundBottomLeft(round);
        this.setRoundBottomRight(0);
        this.add(bleLabel);
        this.setBackground(new Color(0, 0, 0, 200));
        this.setBounds(0, 0, 100, 100);
    }

    public void updateBleQuantity() {
        bleLabel.setText("Ble : " + inventory.getBle());
    }

}
