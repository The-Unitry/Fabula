package eu.theunitry.fabula.graphics;

public class UNEntity extends UNGraphicsObject
{

    private int velx;
    private int vely;
    private int gravity;

    public int getVelx()
    {
        return this.velx;
    }

    public void setVelx(int velx)
    {
        this.velx = velx;
    }

    public int getVely()
    {
        return this.vely;
    }

    public void setVely(int vely)
    {
        this.vely = vely;
    }

    public int getGravity()
    {
        return this.gravity;
    }

    public void setGravity(int gravity)
    {
        this.gravity = gravity;
    }

    public String showBubble(String text)
    {
        return text;
    }
}
