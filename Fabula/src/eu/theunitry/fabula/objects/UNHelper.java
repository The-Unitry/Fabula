package eu.theunitry.fabula.objects;

import eu.theunitry.fabula.UNGameScreen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class UNHelper
{
    // private UNHud hud;
    private int animationID;
    private boolean loopInfinite;
    private UNTimer animationTimerLoop, animationTimerOnce;
    private UNGameScreen gameScreen;
    private ArrayList<Image> animIdle, animFlapping, animSad, animHappy, animQuestioning;

    public UNHelper(/** UNHud hud **/ UNGameScreen gameScreen)
    {
        // this.hud = hud;
        this.gameScreen = gameScreen;
        animIdle = new ArrayList<Image>();
        animFlapping = new ArrayList<Image>();
        animSad = new ArrayList<Image>();
        animHappy = new ArrayList<Image>();
        animQuestioning = new ArrayList<Image>();
        try {
            //IDLE
            animIdle.add(0, ImageIO.read(new File("res/animations/tuiltje/idle/idle0.png")));
            animIdle.add(1, ImageIO.read(new File("res/animations/tuiltje/idle/idle1.png")));
            animIdle.add(2, ImageIO.read(new File("res/animations/tuiltje/idle/idle2.png")));
            animIdle.add(3, ImageIO.read(new File("res/animations/tuiltje/idle/idle3.png")));
            animIdle.add(4, ImageIO.read(new File("res/animations/tuiltje/idle/idle4.png")));
            animIdle.add(5, ImageIO.read(new File("res/animations/tuiltje/idle/idle5.png")));
            //FLAPPING
            animFlapping.add(0, ImageIO.read(new File("res/animations/tuiltje/flapping/flapping0.png")));
            animFlapping.add(1, ImageIO.read(new File("res/animations/tuiltje/flapping/flapping1.png")));
            animFlapping.add(2, ImageIO.read(new File("res/animations/tuiltje/flapping/flapping2.png")));
            animFlapping.add(3, ImageIO.read(new File("res/animations/tuiltje/flapping/flapping3.png")));
            animFlapping.add(4, ImageIO.read(new File("res/animations/tuiltje/flapping/flapping4.png")));
            animFlapping.add(5, ImageIO.read(new File("res/animations/tuiltje/flapping/flapping5.png")));
            //SAD
            animSad.add(0, ImageIO.read(new File("res/animations/tuiltje/sad/sad0.png")));
            animSad.add(1, ImageIO.read(new File("res/animations/tuiltje/sad/sad1.png")));
            animSad.add(2, ImageIO.read(new File("res/animations/tuiltje/sad/sad2.png")));
            animSad.add(3, ImageIO.read(new File("res/animations/tuiltje/sad/sad3.png")));
            animSad.add(4, ImageIO.read(new File("res/animations/tuiltje/sad/sad4.png")));
            animSad.add(5, ImageIO.read(new File("res/animations/tuiltje/sad/sad5.png")));
            //HAPPY
            animHappy.add(0, ImageIO.read(new File("res/animations/tuiltje/happy/happy0.png")));
            animHappy.add(1, ImageIO.read(new File("res/animations/tuiltje/happy/happy1.png")));
            animHappy.add(2, ImageIO.read(new File("res/animations/tuiltje/happy/happy2.png")));
            animHappy.add(3, ImageIO.read(new File("res/animations/tuiltje/happy/happy3.png")));
            animHappy.add(4, ImageIO.read(new File("res/animations/tuiltje/happy/happy4.png")));
            animHappy.add(5, ImageIO.read(new File("res/animations/tuiltje/happy/happy5.png")));
            animHappy.add(6, ImageIO.read(new File("res/animations/tuiltje/happy/happy6.png")));
            animHappy.add(7, ImageIO.read(new File("res/animations/tuiltje/happy/happy7.png")));
            animHappy.add(8, ImageIO.read(new File("res/animations/tuiltje/happy/happy8.png")));
            animHappy.add(9, ImageIO.read(new File("res/animations/tuiltje/happy/happy9.png")));
            //QUESTIONING
            animQuestioning.add(0, ImageIO.read(new File("res/animations/tuiltje/questioning/questioning0.png")));
            animQuestioning.add(1, ImageIO.read(new File("res/animations/tuiltje/questioning/questioning1.png")));
            animQuestioning.add(2, ImageIO.read(new File("res/animations/tuiltje/questioning/questioning2.png")));
            animQuestioning.add(3, ImageIO.read(new File("res/animations/tuiltje/questioning/questioning3.png")));
            animQuestioning.add(4, ImageIO.read(new File("res/animations/tuiltje/questioning/questioning4.png")));
            animQuestioning.add(5, ImageIO.read(new File("res/animations/tuiltje/questioning/questioning5.png")));
            animQuestioning.add(6, ImageIO.read(new File("res/animations/tuiltje/questioning/questioning6.png")));
            animQuestioning.add(7, ImageIO.read(new File("res/animations/tuiltje/questioning/questioning7.png")));
            animQuestioning.add(8, ImageIO.read(new File("res/animations/tuiltje/questioning/questioning8.png")));
            animQuestioning.add(9, ImageIO.read(new File("res/animations/tuiltje/questioning/questioning9.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
