package eu.theunitry.fabula.objects;

import eu.theunitry.fabula.UNGameScreen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.Timer;

public class UNHelper
{
    // private UNHud hud;
    private int animationID;
    private boolean loopInfinite;
    private Timer animationTimerLoop;
    private UNTimer animationTimerOnce;
    private UNGameScreen gameScreen;
    private ArrayList<Image> animIdle, animFlapping, animSad, animHappy, animQuestioning;
    private int imageIndex;
    private Image image;

    public UNHelper(/** UNHud hud **/ UNGameScreen gameScreen)
    {
        // this.hud = hud;
        this.gameScreen = gameScreen;
        animIdle = new ArrayList<Image>();
        animFlapping = new ArrayList<Image>();
        animSad = new ArrayList<Image>();
        animHappy = new ArrayList<Image>();
        animQuestioning = new ArrayList<Image>();
        imageIndex = 0;
        //IDLE
        animIdle.add(0, gameScreen.getSprites().get(0));
        animIdle.add(1, gameScreen.getSprites().get(1));
        animIdle.add(2, gameScreen.getSprites().get(2));
        animIdle.add(3, gameScreen.getSprites().get(3));
        animIdle.add(4, gameScreen.getSprites().get(4));
        animIdle.add(5, gameScreen.getSprites().get(5));
        //FLAPPING
        animFlapping.add(0, gameScreen.getSprites().get(6));
        animFlapping.add(1, gameScreen.getSprites().get(7));
        animFlapping.add(2, gameScreen.getSprites().get(8));
        animFlapping.add(3, gameScreen.getSprites().get(9));
        animFlapping.add(4, gameScreen.getSprites().get(10));
        animFlapping.add(5, gameScreen.getSprites().get(11));
        //SAD
        animSad.add(0, gameScreen.getSprites().get(12));
        animSad.add(1, gameScreen.getSprites().get(13));
        animSad.add(2, gameScreen.getSprites().get(14));
        animSad.add(3, gameScreen.getSprites().get(15));
        animSad.add(4, gameScreen.getSprites().get(16));
        animSad.add(5, gameScreen.getSprites().get(17));
        //HAPPY
        animHappy.add(0, gameScreen.getSprites().get(18));
        animHappy.add(1, gameScreen.getSprites().get(19));
        animHappy.add(2, gameScreen.getSprites().get(20));
        animHappy.add(3, gameScreen.getSprites().get(21));
        animHappy.add(4, gameScreen.getSprites().get(22));
        animHappy.add(5, gameScreen.getSprites().get(23));
        animHappy.add(6, gameScreen.getSprites().get(24));
        animHappy.add(7, gameScreen.getSprites().get(25));
        animHappy.add(8, gameScreen.getSprites().get(26));
        animHappy.add(9, gameScreen.getSprites().get(27));
        //QUESTIONING
        animQuestioning.add(0, gameScreen.getSprites().get(28));
        animQuestioning.add(1, gameScreen.getSprites().get(29));
        animQuestioning.add(2, gameScreen.getSprites().get(30));
        animQuestioning.add(3, gameScreen.getSprites().get(31));
        animQuestioning.add(4, gameScreen.getSprites().get(32));
        animQuestioning.add(5, gameScreen.getSprites().get(33));
        animQuestioning.add(6, gameScreen.getSprites().get(34));
        animQuestioning.add(7, gameScreen.getSprites().get(35));
        animQuestioning.add(8, gameScreen.getSprites().get(36));
        animQuestioning.add(9, gameScreen.getSprites().get(37));
    }

    public void animateHelper(int animationID, boolean loopInfinite)
    {
        animationTimerLoop = new Timer(1000, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                switch (animationID){
                    case 0:
                        //Idle animation
                        setImage(animIdle.get(imageIndex));
                        if (imageIndex < animIdle.size() - 1) {
                            imageIndex++;
                        } else {
                            imageIndex = 0;
                        }
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
                if (!loopInfinite)
                {
                    //animationTimerLoop.stop();
                }
            }
        });
        animationTimerLoop.start();
        /*if (loopInfinite){
            animationTimerLoop = new UNTimer(gameScreen, 10)
            {
                class GameLoop implements ActionListener {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        animationTimerLoop.start();
                        switch (animationID){
                            case 0:
                                //Idle animation
                                if (imageIndex < animIdle.size()) {
                                    imageIndex++;
                                } else {
                                    imageIndex = 0;
                                }
                                setImage(animIdle.get(imageIndex));
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
            animationTimerLoop.start();
        }
        else {
            animationTimerOnce = new UNTimer(gameScreen, 10) {
                class GameLoop implements ActionListener {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        animationTimerOnce.stop();
                    }

                }
            };
        }*/
    }

    public void setImage(Image image)
    {
        this.image = image;
    }

    public Image getImage()
    {
        return this.image;
    }

    public UNGameScreen getGameScreen()
    {
        return this.gameScreen;
    }
}
