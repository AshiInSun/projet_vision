package Control;

import Model.Carte;
import Model.Equipe;
import Model.ZoneSelection;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import static java.lang.Math.abs;

public class MouseClickR implements MouseListener, MouseMotionListener {
    private Equipe equipe;
    private Rectangle selectedZone;

    public MouseClickR(Equipe equipe){this.equipe = equipe;}

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON3) {
            equipe.setPosClick(e.getPoint());
            equipe.deplacement();
        }else{
            Point a = e.getPoint();
            equipe.GetBoutique().selectionneB(a);
            for(int i=0; i<equipe.getTeam().size(); i++){
                equipe.getTeam().get(i).selectionne(a);
            }
            System.out.println("Left click");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            System.out.println("left click Pressed");
            selectedZone = new Rectangle(e.getX(), e.getY(), 0, 0);
            equipe.SetzoneSelection(new ZoneSelection(selectedZone));
            System.out.println("left click Pressed2");
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        /*if click gauche enfoncé*/
        if(e.getModifiersEx() == MouseEvent.BUTTON1_DOWN_MASK){
            selectedZone.width = e.getX() - selectedZone.x;
            selectedZone.height = e.getY() - selectedZone.y;
            equipe.getZoneSelection().setSelectedZone(selectedZone.width, selectedZone.height);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //nothing here
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {
            System.out.println("left click Released");
            for (int i = 0; i < equipe.getTeam().size(); i++) {
                Point p1 = new Point(equipe.getZoneSelection().getXRectangle(), equipe.getZoneSelection().getYRectangle());
                Point p2 = new Point(equipe.getZoneSelection().getXRectangle() + equipe.getZoneSelection().getwitdhRectangle(), equipe.getZoneSelection().getYRectangle() + equipe.getZoneSelection().getheightRectangle());
                equipe.getTeam().get(i).selectionneZone(p1, p2);
            }
            selectedZone.width = 0;
            selectedZone.height = 0;
            equipe.getZoneSelection().setSelectedZone(selectedZone.width, selectedZone.height);
            System.out.println("left click Released2");
        }

    }


    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
