package eu.theunitry.fabula.levels;

import eu.theunitry.fabula.UNGameEngine.graphics.UNGameScreen;
import eu.theunitry.fabula.UNGameEngine.graphics.UNColor;
import eu.theunitry.fabula.UNGameEngine.graphics.UNGraphicsObject;
import eu.theunitry.fabula.UNGameEngine.graphics.UNLevel;
import eu.theunitry.fabula.UNGameEngine.launcher.UNLauncher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class Level13 extends UNLevel
{
    private Timer timer;
    private UNGraphicsObject plank;
    private UNGraphicsObject diamond;
    private ArrayList<UNGraphicsObject> acorn;
    private JButton button;
    private boolean winning;
    private int need, touch, g1, g2;
    private UNColor color;
    private String lastHelp;

    /**
     * Level 10
     * @param gameScreen
     * @param hudEnabled
     */
    public Level13(UNGameScreen gameScreen, boolean hudEnabled)
    {
        super(gameScreen, hudEnabled);

        this.g1 = 1+ new Random().nextInt(5);
        this.g2 = 1+ new Random().nextInt(6);
        this.need = g1 * g2;

        this.setQuestion("hoeveel groepen van " + g1 + " eikeltjes vallen uit de boom");
        this.addHelp("Jammer! Je moet " + need + " gewichtjes op de plank hebben");
        this.addHelp("Helaas! Er moeten " + need + " gewichtjes op de plank staan");
        this.addHelp("net niet goed, weet je zeker dat er " + need + " op de plank staan?");
        this.setHelp("Klik op het juiste getal om te antwoorden");
        this.setBackgroundImage(gameScreen.getBackgrounds().get(3));

        this.winning = false;
        this.lastHelp = getHelp();

        this.acorn = new ArrayList<UNGraphicsObject>();
        this.color = new UNColor();

        for (int i = 0; i < need; i++)
        {
            acorn.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(),50 + new Random().nextInt(400), 10, gameScreen.getSprites().get(46), false, 16, 16));
        }

        //If (button.getText () == "Begin) {



   // }

        acorn.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 557, 180, gameScreen.getSprites().get(46), true, 64, 64));
        acorn.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 557, 250, gameScreen.getSprites().get(46), true, 64, 64));
        acorn.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 557, 320, gameScreen.getSprites().get(46), true, 64, 64));

        this.button = new JButton("begin");
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

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (button.getText() == "Door") {
                    gameScreen.getSounds().get(0).stop();
                    if (gameScreen.getLevel() < gameScreen.getLevelMax()) {
                        if (winning) {
                            gameScreen.addLevel();
                        }
                        gameScreen.switchPanel(new Level9(gameScreen, true));
                    } else {
                        gameScreen.switchPanel(new UNLauncher(gameScreen));
                    }
                }
                if (isHelperDoneTalking()) {
                    if (winning) {
                        getHelper().setState(3);
                        setHelp("Goed gedaan, de diamant is van ons");
                        for (UNGraphicsObject weight : acorn) {
                            weight.setClickable(false);
                        }
                        button.setText("Door");
                    } else {
                        addMistake();
                        if (getMistakes() < 3) {
                            getHelper().setState(4);
                            while(lastHelp == getHelp()) {
                                setHelp(getHelpList().get(new Random().nextInt(getHelpList().size())));
                            }
                            lastHelp = getHelp();
                        } else {
                            getHelper().setState(4);
                            if (touch < need) {
                                setHelp("Jammer, er moest" + ((need - touch == 1) ? "" : "en") + " nog " + (need - touch) +
                                        " gewichtjes" + ((need - touch == 1) ? "" : "s")  + " bij. Want " + touch + " plus " +
                                        (need - touch)  + " is " + need
                                );
                            } else {
                                setHelp("Jammer, er moest" + ((touch - need == 1) ? "" : "en") + " " + (touch - need) +
                                        " gewicht" + ((touch - need == 1) ? "" : "s")  + " af. Want " + touch + " min " +
                                        (touch - need)  + " is " + need
                                );
                            }

                            for (UNGraphicsObject weight : acorn)
                            {
                                weight.setClickable(false);
                            }

                            button.setText("Door");
                        }
                    }
                }
            }
        });

        for (UNGraphicsObject acorns : acorn)
        {
            addObject(acorns);
        }
        this.getPanel().add(button);

        //timer.start();
    }
}
