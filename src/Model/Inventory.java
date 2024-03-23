package Model;

import java.awt.*;
import java.util.ArrayList;

public class Inventory {
    private ArrayList<Item> items;
    private Equipe equipe;

    public Inventory(Equipe equipe) {
        this.equipe = equipe;
        this.items = new ArrayList<Item>();
        this.items.add(new Item(ItemType.BLE));
        this.items.add(new Item(ItemType.PIERRE));
    }

    public int getBle() {
        return items.get(0).getQuantity();
    }

    public int getPierre() {
        return items.get(1).getQuantity();
    }

    public Equipe getEquipe() {
        return equipe;
    }

    //fonction qui actualise la quantité des items
    public void updateInventory() {
        for (int i = 0; i < items.size(); i++) {
            for(int j = 0; j < equipe.getTeam().size(); j++) {
                if (items.get(i).getType() == ItemType.BLE) {
                    items.get(i).setQuantity(equipe.getTeam().get(j).getBle());
                } else if (items.get(i).getType() == ItemType.PIERRE) {
                    items.get(i).setQuantity(equipe.getTeam().get(j).getPierre());
                }
            }
        }
    }

}