package eu.theunitry.fabula.graphics;

import eu.theunitry.fabula.UNGameScreen;
import eu.theunitry.fabula.objects.UNObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class UNPanel extends JPanel
{
    private Image backgroundImage;
    private ArrayList<UNGraphicsObject> objects;
    private ArrayList hud;

    public UNPanel()
    {
        objects = new ArrayList<UNGraphicsObject>();
        hud = new ArrayList<UNGraphicsObject>();

        MouseHandler mouseHandler = new MouseHandler();
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        g.drawImage(getBackgroundImage(), 0, 0, 768, 512, this);
        layerObjects(g);
        layerHUD();
    }

    public void setBackgroundImage(Image backgroundImage)
    {
        this.backgroundImage = backgroundImage;
    }

    public Image getBackgroundImage()
    {
        return this.backgroundImage;
    }
    public void layerObjects(Graphics g)
    {
        for (UNGraphicsObject object : objects)
        {
            g.drawImage(object.getImage(), object.getX(), object.getY(), this);
        }
    }
    public void layerHUD()
    {
        //TODO: HUDObjects?
    }

    public class MouseHandler implements MouseListener, MouseMotionListener {
        @Override
        public void mousePressed(MouseEvent e)
        {
            for (UNGraphicsObject object : objects) {
                if (e.getX() > object.getX() && e.getY() > object.getY() && e.getX() < object.getX() + object.getWidth() && e.getY() < object.getY() + object.getHeight()) {
                    object.setMouseHold(true);

                    object.setXOffset((int) e.getX() - object.getX());
                    object.setYOffset((int) e.getY() - object.getY());
                }
            }
        }

        @Override
        public void mouseReleased(MouseEvent e)
        {
            for (UNGraphicsObject object : objects) {
                object.setMouseHold(false);
            }
        }

        @Override
        public void mouseDragged(MouseEvent e)
        {
            for (UNGraphicsObject object : objects) {
                if (object.getMouseHold()) {
                    object.setX((int) e.getX() - object.getXOffset());
                    object.setY((int) e.getY() - object.getYOffset());
                }
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}

        @Override
        public void mouseMoved(MouseEvent e) {}
    }
}
