package Model;

import javax.lang.model.type.NullType;
import javax.swing.*;
import java.awt.*;

public class Item {
    private ItemType type;
    private int quantity;

    public Item(ItemType type) {
        this.type = type;
        this.quantity = 0;
    }

    // getters and setters
    public ItemType getType() {
        return type;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;

    }

    public void incrementQuantity() {
        this.quantity++;
    }
    public void decrementQuantity() {
        this.quantity--;
        if(this.quantity < 0) {
            this.quantity = 0;
        }
    }
}
