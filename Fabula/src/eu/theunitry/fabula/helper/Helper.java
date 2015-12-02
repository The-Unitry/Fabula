package eu.theunitry.fabula.helper;

import eu.theunitry.fabula.UNGameEngine.graphics.UNGameScreen;
import eu.theunitry.fabula.UNGameEngine.objects.UNTimer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;

public class Helper
{
    private int animationID;
    private boolean loopInfinite;
    private Timer animationTimerLoop;
    private UNTimer animationTimerOnce;
    private UNGameScreen gameScreen;
    private ArrayList<Image> animIdle, animFlapping, animSad, animHappy, animQuestioning;
    private boolean questioningDone;
    private int imageIndex;
    private Image image;
    private int state;

    public Helper(UNGameScreen gameScreen)
    {
        this.gameScreen = gameScreen;
        this.animIdle = new ArrayList<Image>();
        this.animFlapping = new ArrayList<Image>();
        this.animSad = new ArrayList<Image>();
        this.animHappy = new ArrayList<Image>();
        this.animQuestioning = new ArrayList<Image>();
        this.imageIndex = 0;
        this.state = 2;
        this.questioningDone = false;


        /**
         * Idle
         */
        animIdle.add(0, gameScreen.getSprites().get(0));
        animIdle.add(1, gameScreen.getSprites().get(1));
        animIdle.add(2, gameScreen.getSprites().get(2));
        animIdle.add(3, gameScreen.getSprites().get(3));
        animIdle.add(4, gameScreen.getSprites().get(4));
        animIdle.add(5, gameScreen.getSprites().get(5));

        /**
         * Flapping
         */
        animFlapping.add(0, gameScreen.getSprites().get(6));
        animFlapping.add(1, gameScreen.getSprites().get(7));
        animFlapping.add(2, gameScreen.getSprites().get(8));
        animFlapping.add(3, gameScreen.getSprites().get(9));
        animFlapping.add(4, gameScreen.getSprites().get(10));
        animFlapping.add(5, gameScreen.getSprites().get(11));

        /**
         * Sad
         */
        animSad.add(0, gameScreen.getSprites().get(12));
        animSad.add(1, gameScreen.getSprites().get(13));
        animSad.add(2, gameScreen.getSprites().get(14));
        animSad.add(3, gameScreen.getSprites().get(15));
        animSad.add(4, gameScreen.getSprites().get(16));
        animSad.add(5, gameScreen.getSprites().get(17));

        /**
         * Happy
         */
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

        /**
         * Questioning
         */
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

        animationTimerLoop = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                animate();
            }
        });
        animationTimerLoop.start();
    }

    public void animate() {
        switch (state) {
            case 0:
                setImage(animIdle.get(Math.max(imageIndex, 0)));
                if (imageIndex < animIdle.size() - 1) {
                    imageIndex++;
                } else {
                    imageIndex = -(new Random().nextInt(100));
                }
                if (imageIndex < 0) {
                    if (new Random().nextInt(100) == 0) {
                        imageIndex = 0;
                        state = 1;
                    }
                }
                break;
            case 1:
                setImage(animFlapping.get(imageIndex));
                if (imageIndex < animFlapping.size() - 1) {
                    imageIndex++;
                } else {
                    imageIndex = -(new Random().nextInt(50));
                    state = 0;
                }
                break;
            case 2:
                setImage(animQuestioning.get(imageIndex));
                if (imageIndex < animQuestioning.size() - 1) {
                    if (imageIndex != 5 || questioningDone) {
                        if (imageIndex == 5 && questioningDone) {
                            questioningDone = !questioningDone;
                        }
                        imageIndex++;
                    }
                } else {
                    imageIndex = -(new Random().nextInt(100));
                    state = 0;
                }
                break;
            case 3:
                if (imageIndex < 5) {
                    setImage(animHappy.get(imageIndex));
                } else {
                    setImage(animHappy.get(Math.max(imageIndex - 40, 5)));
                }
                if (imageIndex < animHappy.size() + 40 - 1) {
                    imageIndex++;
                } else {
                    imageIndex = -(new Random().nextInt(50));
                    state = 0;
                }
                break;
            case 4:
                if (imageIndex < 3) {
                    setImage(animSad.get(imageIndex));
                } else {
                    setImage(animSad.get(Math.max(imageIndex - 40, 3)));
                }
                if (imageIndex < animSad.size() + 40 - 1) {
                    imageIndex++;
                } else {
                    imageIndex = -(new Random().nextInt(50));
                    state = 0;
                }
                break;
        }
    }

    public void setState(int state) {
        imageIndex = 0;
        this.state = state;
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

    public void setQuestioningDone(boolean questioningDone)
    {
        this.questioningDone = questioningDone;
    }
}
