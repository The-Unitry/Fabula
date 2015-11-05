package eu.theunitry.fabula.graphics;

import eu.theunitry.fabula.objects.UNObject;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public abstract class UNGraphicsObject extends UNObject implements MouseListener, MouseMotionListener
{

    private int x;
    private int y;
    private Image image;
    private boolean clickable;
    private Rectangle hitbox;
    private boolean mouseHold;
    private int width, height;
    private int xOffset, yOffset;

    public UNGraphicsObject(int x, int y, Image image, boolean clickable, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.image = image;
        this.clickable = clickable;
        this.hitbox = new Rectangle(x, y, width, height);
        this.mouseHold = false;
        this.width = width;
        this.height = height;
    }

    public int getX()
    {
        return this.x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return this.y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public Image getImage()
    {
        return this.image;
    }

    public void setImage(Image image)
    {
        this.image = image;
    }

    public boolean getClickable()
    {
        return this.clickable;
    }

    public void setClickable(boolean clickable)
    {
        this.clickable = clickable;
    }

    public Rectangle getHitbox()
    {
        return this.hitbox;
    }

    public void setHitbox(Rectangle hitbox)
    {
        this.hitbox = hitbox;
    }

    public int getWidth()
    {
        return this.width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public int getHeight()
    {
        return this.height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        Point mousePos = MouseInfo.getPointerInfo().getLocation();
        if(mousePos.getX() > this.getX() && mousePos.getY() > this.getY() && mousePos.getX() < this.getX() + this.getWidth() && mousePos.getY() < this.getY() + this.getHeight())
        {
            mouseHold = true;

            this.xOffset = (int)mousePos.getX() - this.getX();
            this.yOffset = (int)mousePos.getY() - this.getY();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        mouseHold = false;
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        if(mouseHold)
        {
            Point mousePos = MouseInfo.getPointerInfo().getLocation();
            this.setX((int)mousePos.getX() - this.xOffset);
            this.setY((int) mousePos.getY() - this.yOffset);
        }
    }
}
