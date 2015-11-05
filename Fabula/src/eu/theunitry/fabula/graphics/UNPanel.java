package eu.theunitry.fabula.graphics;

import eu.theunitry.fabula.UNGameScreen;
import eu.theunitry.fabula.objects.UNObject;

import javax.swing.*;
import java.awt.*;
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
    }

    @Override
    public void paintComponent(Graphics g)
    {
        g.drawImage(getBackgroundImage(), 0, 0, this);
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

}
