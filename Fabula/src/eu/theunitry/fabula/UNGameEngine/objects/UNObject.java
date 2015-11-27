package eu.theunitry.fabula.UNGameEngine.objects;

/**
 * UNObject is a class all objects in the UNGameEngine should eventually inherit from
 * Currently, the class has no purpose, but when we move away from Swing we
 * except to use it more.
 */
public abstract class UNObject
{
    public void onCreate() {}

    public UNObject()
    {
        this.onCreate();
    }
}
