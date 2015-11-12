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
            //Run selected animation over and over again, until loopInfinite is no longer true .
            while (loopInfinite)
            {
                switch (animationID){
                    case 0:
                        //Idle animation
                        break;
                    case 1:
                        //Flapping animation
                        break;
                    case 2:
                        //Questioning face
                        break;
                    case 3:
                        //Happy face
                        break;
                    case 4:
                        //Sad face
                        break;
                    default:
                        //Return to default animation
                        break;
                }
            }
        }
        else {
            //Run animation one time.
            switch (animationID){
                case 0:
                    //Idle animation
                    break;
                case 1:
                    //Flapping animation
                    break;
                case 2:
                    //Questioning face
                    break;
                case 3:
                    //Happy face
                    break;
                case 4:
                    //Sad face
                    break;
            }
        }
    }
}
