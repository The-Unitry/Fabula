package eu.theunitry.fabula.graphics;


import eu.theunitry.fabula.objects.UNObject;

import java.awt.*;

public abstract class UNGraphicsObject extends UNObject
{

    private int x;
    private int y;
    private Image image;
    private boolean clickable;
    private Rectangle hitbox;

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
}
