package Model;

import java.awt.*;

public class Inventory {
    private Item ble = new Item(ItemType.BLE);
    private Equipe equipe;

    public Inventory(Equipe equipe) {
        this.equipe = equipe;
    }

    public int getBle() {
        return ble.getQuantity();
    }

    public Equipe getEquipe() {
        return equipe;
    }
    public Image getBleImage() {
        return ble.getImage();
    }

    public void updateBle(){
        int total = 0;
        for (Hero h : this.equipe.getList_hero()) {
            total += h.getBle();
        }
        this.ble.setQuantity(total);
    }

}