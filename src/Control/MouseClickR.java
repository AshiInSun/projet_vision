package Control;

import Model.Carte;
import Model.Equipe;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseClickR implements MouseListener {
    private Equipe equipe;

    public MouseClickR(Equipe equipe){this.equipe = equipe;}

    @Override
    public void mouseClicked(MouseEvent e) {
        equipe.setPosClick(MouseInfo.getPointerInfo().getLocation());
        System.out.println("MOUSE////");
        System.out.println(MouseInfo.getPointerInfo().getLocation().x);
        System.out.println(MouseInfo.getPointerInfo().getLocation().y);
        System.out.println("MOUSE----");
        equipe.deplacement();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        equipe.setPosClick(MouseInfo.getPointerInfo().getLocation());
        equipe.deplacement();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
