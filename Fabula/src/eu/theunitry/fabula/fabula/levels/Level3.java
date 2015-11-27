package eu.theunitry.fabula.Fabula.levels;


import eu.theunitry.fabula.UNGameEngine.graphics.UNGameScreen;
import eu.theunitry.fabula.UNGameEngine.graphics.UNColor;
import eu.theunitry.fabula.UNGameEngine.graphics.UNGraphicsObject;
import eu.theunitry.fabula.UNGameEngine.graphics.UNLevel;
import eu.theunitry.fabula.UNGameEngine.objects.UNTimer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Level3 extends UNLevel
{
    private Timer timer;
    private UNGraphicsObject moon, moon2, ufoGreen, ufoBlue, ufoPink, rocket;
    private ArrayList<Image> rocketIdle, rocketBurst;
    private JButton button;
    private Timer animationTimerLoop;
    private UNTimer animationTimerOnce;
    private boolean winning;
    private int num1, num2, answer, imageIndex, state;
    private UNColor color;
    private String lastHelp;
    private Image image;

    /**
     * Level 3
     * @param gameScreen
     * @param hudEnabled
     */

    public Level3(UNGameScreen gameScreen, boolean hudEnabled)
    {
        super(gameScreen, hudEnabled);

        this.num1 = 1 + new Random().nextInt(8);
        this.num2 = 1 + new Random().nextInt(8);
        this.answer = num1 + num2;

        this.setQuestion("Wat is " + num1 + " + " + num2 + "?");
        this.addHelp("Jammer! Je moet op de UFO klikken met het goede antwoord");
        this.addHelp("Helaas! Klik op de UFO met het goede antwoord!");
        this.setHelp("Klik op de UFO met het goede antwoord!");
        this.setBackgroundImage(gameScreen.getBackgrounds().get(2));

        this.winning = false;
        this.lastHelp = getHelp();

        this.color = new UNColor();

        this.moon = new UNGraphicsObject(gameScreen.getWindow().getFrame(), 300, 100, gameScreen.getSprites().get(40), false, 96, 96);
        this.rocket = new UNGraphicsObject(gameScreen.getWindow().getFrame(), 450, 250, gameScreen.getSprites().get(42), false, 120, 120);

        this.rocketIdle = new ArrayList<Image>();
        this.rocketBurst = new ArrayList<Image>();

        //UFO
        this.ufoBlue = new UNGraphicsObject(gameScreen.getWindow().getFrame(), 20, 250, gameScreen.getSprites().get(46), false, 76, 66);
        this.ufoGreen = new UNGraphicsObject(gameScreen.getWindow().getFrame(), 120, 150, gameScreen.getSprites().get(47), false, 76, 66);
        this.ufoPink = new UNGraphicsObject(gameScreen.getWindow().getFrame(), 220, 250, gameScreen.getSprites().get(48), false, 76, 66);

        this.addObject(moon);
        this.addObject(rocket);
        this.addObject(ufoBlue);
        this.addObject(ufoGreen);
        this.addObject(ufoPink);

        this.button = new JButton("Nakijken");

        this.setLayout(null);
        this.button.setBounds(618, 64, 150, 50);
        this.button.setBackground(new Color(51, 51, 51));
        this.button.setFont(new Font("Minecraftia", Font.PLAIN, 15));
        this.button.setForeground(Color.white);
        this.button.setOpaque(true);

        /**
         * Reset Default Styling
         */

        this.button.setFocusPainted(false);
        this.button.setBorderPainted(false);

        this.getPanel().add(button);
    }
}