package Control;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Test extends JPanel implements MouseListener, MouseMotionListener {

    private Point pos1 = null;
    private Point pos2 = null;

    public Test(){
        addMouseListener(this);
        addMouseMotionListener(this);
        setSize(300,300);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(pos1 != null && pos2 != null){
            int x = Math.min(pos1.x, pos2.x);
            int y = Math.min(pos1.y, pos2.y);
            int width = Math.abs(pos1.x - pos2.x);
            int height = Math.abs(pos1.y - pos2.y);
            g.drawRect(x, y, width, height);
        }
    }

    public static void main(String args[]){
        JFrame f = new JFrame("test");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new Test());

        new Thread(() -> {
            while(true){
                f.repaint();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        f.pack();
        f.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        pos1 = e.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        pos1 = null;
        // et noter la sélection
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        pos2 = e.getPoint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
