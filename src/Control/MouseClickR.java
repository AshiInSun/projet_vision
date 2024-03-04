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
        if(e.getButton() == MouseEvent.BUTTON3) {
            equipe.setPosClick(MouseInfo.getPointerInfo().getLocation());
            equipe.deplacement();
        }else{
            for(int i=0; i<equipe.getTeam().size(); i++){
                Point a = MouseInfo.getPointerInfo().getLocation();
                equipe.getTeam().get(i).selectionne(a);
            }
            System.out.println("Left click");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

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
