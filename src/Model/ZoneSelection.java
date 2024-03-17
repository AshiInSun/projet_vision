package Model;

import java.awt.*;

public class ZoneSelection {
    private Rectangle selectedZone;

    public ZoneSelection(Rectangle selectedZone){
        this.selectedZone = selectedZone;
        selectedZone.width = 0;
        selectedZone.height = 0;
    }

    public void setSelectedZone(int width, int height){
        selectedZone.width = width;
        selectedZone.height = height;
    }

    public int getXRectangle(){
        return selectedZone.x;
    }

    public int getYRectangle(){
        return selectedZone.y;
    }

    public int getwitdhRectangle(){
        return selectedZone.width;
    }

    public int getheightRectangle(){
        return selectedZone.height;
    }

    public void setXRectangle(int i) {
        selectedZone.x = i;
    }

    public void setYRectangle(int i) {
        selectedZone.y = i;
    }

    public void setWitdhRectangle(int i) {
        selectedZone.width = i;
    }

    public void setHeightRectangle(int i) {
        selectedZone.height = i;
    }
}