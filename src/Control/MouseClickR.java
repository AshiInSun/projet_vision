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
        equipe.setPosClick(e.getPoint());
        equipe.deplacement();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        equipe.setPosClick(e.getPoint());
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
