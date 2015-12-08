package eu.theunitry.fabula.helper;

import eu.theunitry.fabula.UNGameEngine.graphics.UNGameScreen;
import eu.theunitry.fabula.UNGameEngine.launcher.UNResourceLoader;
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
    private UNResourceLoader resourceLoader;

    public Helper(UNGameScreen gameScreen) {
        this.gameScreen = gameScreen;
        this.animIdle = new ArrayList<Image>();
        this.animFlapping = new ArrayList<Image>();
        this.animSad = new ArrayList<Image>();
        this.animHappy = new ArrayList<Image>();
        this.animQuestioning = new ArrayList<Image>();
        this.imageIndex = 0;
        this.state = 2;
        this.questioningDone = false;
        this.resourceLoader = new UNResourceLoader();


        // Idle
        for (int i = 0; i <= 5; i++)
        {
            animIdle.add(resourceLoader.getSprite("tuiltje/idle", String.valueOf(i)));
        }

        // Flapping
        for (int i = 0; i <= 5; i++)
        {
            animFlapping.add(resourceLoader.getSprite("tuiltje/flapping", String.valueOf(i)));
        }

        // Sad
        for (int i = 0; i <= 5; i++)
        {
            animSad.add(resourceLoader.getSprite("tuiltje/sad", String.valueOf(i)));
        }

        // Happy
        for (int i = 0; i <= 5; i++)
        {
            animHappy.add(0, resourceLoader.getSprite("tuiltje/happy", String.valueOf(i)));
        }

        // Questioning
        for (int i = 0; i <= 9; i++)
        {
            animQuestioning.add(0, resourceLoader.getSprite("tuiltje/questioning", String.valueOf(i)));
        }

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
