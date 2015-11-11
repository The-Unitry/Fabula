package eu.theunitry.fabula.objects;

public class UNHelper
{
    // private UNHud hud;
    private int animationID;
    private boolean loopInfinite;

    public UNHelper(/** UNHud hud **/)
    {
        // this.hud = hud;
    }

    public void animateHelper(int animationID, boolean loopInfinite)
    {
        if (loopInfinite){
            //Run selected animation over and over again, until .
            while (loopInfinite)
            {
                switch (animationID){
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                }
            }
        }
        else {
            //Run animation one time.
            switch (animationID) {
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    break;
            }
        }
    }
}
