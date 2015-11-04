package eu.theunitry.fabula.graphics;

public class UNEntity extends UNGraphicsObject
{
    //Define variables
    private int velx;
    private int vely;
    private int gravity;

    //Get X velocity
    public int getVelx()
    {
        return this.velx;
    }
    //Set X velocity
    public void setVelx(int velx)
    {
        this.velx = velx;
    }

    //Get Y velocity
    public int getVely()
    {
        return this.vely;
    }

    //Set Y velocity
    public void setVely(int vely)
    {
        this.vely = vely;
    }

    //Get Gravity
    public int getGravity()
    {
        return this.gravity;
    }

    //Set Gravity
    public void setGravity(int gravity)
    {
        this.gravity = gravity;
    }
}
