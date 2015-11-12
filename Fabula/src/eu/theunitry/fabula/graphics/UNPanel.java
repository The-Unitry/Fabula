package eu.theunitry.fabula.graphics;

import eu.theunitry.fabula.UNGameScreen;
import eu.theunitry.fabula.objects.UNObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class UNPanel extends JPanel
{
    private Image backgroundImage;
    private ArrayList<UNGraphicsObject> objects;
    private ArrayList hud;
    private JFrame frame;
    private boolean hudEnabled;
    private String question;

    public UNPanel(JFrame frame)
    {
        this.frame = frame;
        objects = new ArrayList<UNGraphicsObject>();
        hud = new ArrayList<UNGraphicsObject>();

        MouseHandler mouseHandler = new MouseHandler();
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);
        hudEnabled = true;
        question = "";
    }

    @Override
    public void paintComponent(Graphics g)
    {
        g.drawImage(getBackgroundImage(), 0, 0, 768, 512, this);
        layerObjects(g);
        layerHUD(g);
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

    public void layerHUD(Graphics g)
    {
        if (hudEnabled)
        {
            g.fillRect(0, 0, 768, 64);
            g.fillRect(0, 416, 575, 96);
            g.setFont(new Font("Minecraftia", Font.PLAIN, 18));
            g.setColor(Color.white);
            g.drawString("Level 1/5", 11, 38);

            g.setFont(new Font("Minecraftia", Font.PLAIN, 15));
            int stringLen = (int)
                    g.getFontMetrics().getStringBounds(question, g).getWidth();
            g.drawString(question, 754 - stringLen, 38);
            try {
                g.drawImage(ImageIO.read(new File("res/tuiltje.png")), 610, 329, 21 * 5, 29 * 5, this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setHudEnabled(boolean hudEnabled)
    {
        this.hudEnabled = hudEnabled;
    }

    public void setQuestion(String question)
    {
        this.question = question;
    }

    public String getQuestion()
    {
        return question;
    }

    public class MouseHandler implements MouseListener, MouseMotionListener {
        @Override
        public void mousePressed(MouseEvent e)
        {
            for (UNGraphicsObject object : objects) {
                if (e.getX() > object.getX() && e.getY() > object.getY() && e.getX() < object.getX() + object.getWidth() && e.getY() < object.getY() + object.getHeight()) {
                    object.setMouseHold(true);

                    object.setXOffset(e.getX() - object.getX());
                    object.setYOffset(e.getY() - object.getY());
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
                    object.setX(e.getX() - object.getXOffset());
                    object.setY(e.getY() - object.getYOffset());
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
