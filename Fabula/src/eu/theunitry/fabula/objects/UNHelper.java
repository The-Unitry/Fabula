package eu.theunitry.fabula.objects;

import eu.theunitry.fabula.UNGameScreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UNHelper
{
    // private UNHud hud;
    private int animationID;
    private boolean loopInfinite;
    private UNTimer animationTimerLoop, animationTimerOnce;
    private UNGameScreen gameScreen;

    public UNHelper(/** UNHud hud **/ UNGameScreen gameScreen)
    {
        // this.hud = hud;
        this.gameScreen = gameScreen;
    }

    public void animateHelper(int animationID, boolean loopInfinite)
    {
        if (loopInfinite){
            animationTimerLoop = new UNTimer(gameScreen, 10)
            {
                class GameLoop implements ActionListener {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        animationTimerLoop.start();
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
            };
        }
        else {
            animationTimerOnce = new UNTimer(gameScreen, 10) {
                class GameLoop implements ActionListener {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        animationTimerOnce.start();
                        switch (animationID) {
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
                        animationTimerOnce.stop();
                    }

                }
            };
        }
    }

    public UNGameScreen getGameScreen()
    {
        return this.gameScreen;
    }
}
