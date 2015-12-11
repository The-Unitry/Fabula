package eu.theunitry.fabula.levels;


import eu.theunitry.fabula.UNGameEngine.graphics.UNGameScreen;
import eu.theunitry.fabula.UNGameEngine.graphics.UNColor;
import eu.theunitry.fabula.UNGameEngine.graphics.UNLevel;
import eu.theunitry.fabula.UNGameEngine.graphics.UNGraphicsObject;
import eu.theunitry.fabula.UNGameEngine.launcher.UNLauncher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

/**
 * Level 1
 * Maarten Bode
 */
public class Level1 extends UNLevel
{
    private Timer timer;
    private JButton button;
    private boolean winning;
    private int need, touch;
    private String lastHelp;
    public UNGraphicsObject blobby;

    /**
     * Level 1
     * @param gameScreen
     * @param hudEnabled
     */
    public Level1(UNGameScreen gameScreen, boolean hudEnabled)
    {
        super(gameScreen, hudEnabled);

        gameScreen.stopAudio();

        this.need = 3 + new Random().nextInt(3);

        // Load questions & help texts
        this.setQuestion("Oh nee! Blobby probeert de wereld te vernietigen. Stop hem!");
        this.addHelp("Jammer! Je moet " + need + " appels in de mand stoppen");
        this.addHelp("Helaas! Er moeten " + need + " appels in de mand zitten");
        this.setHelp("Om het wapen af te vuren moet je het goede draadje doorknippen");

        //  Set resources/audio
        this.setBackgroundImage(gameScreen.unResourceLoader.backgrounds.get("the-end"));
        this.blobby = new UNGraphicsObject(gameScreen.getWindow().getFrame(), 320, -50, gameScreen.getSprites().get("2:1:1"), false, 38 * 3, 50 * 3);

        this.addObject(blobby);

        gameScreen.getMusic().get("the-end").play(true);
        gameScreen.getMusic().get("the-end").setVolume(0.1);

        // Set variables
        this.winning = false;
        this.lastHelp = getHelp();

        this.button = new JButton("Vuren!");

        // Standard styling
        this.setLayout(null);

        // Set button styling
        this.button.setBounds(618, 64, 150, 50);
        this.button.setBackground(new Color(51, 51, 51));
        this.button.setFont(new Font("Minecraftia", Font.PLAIN, 15));
        this.button.setForeground(Color.white);
        this.button.setOpaque(true);

        // Makes it look good on Mac
        this.button.setFocusPainted(false);
        this.button.setBorderPainted(false);

        // Speaks for itself
        button.addActionListener(e -> {
            if (button.getText().equals("Doorgaan"))
            {
                levelDone(5);
            }
            if (isHelperDoneTalking()) {
                if (winning) {
                    getHelper().setState(3);
                    setHelp("Goed gedaan, dat wordt smikkelen en smullen!");

                    button.setText("Door");
                } else {
                    addMistake();
                    if (getMistakes() < 3) {
                        getHelper().setState(4);
                        while(lastHelp.equals(getHelp())) {
                            setHelp(getHelpList().get(new Random().nextInt(getHelpList().size())));
                        }
                        lastHelp = getHelp();
                    } else {
                        getHelper().setState(4);
                        if (touch < need) {
                            setHelp("Jammer, er moest" + ((need - touch == 1) ? "" : "en") + " nog " + (need - touch) +
                                    " appel" + ((need - touch == 1) ? "" : "s")  + " bij. Want " + touch + " plus " +
                                    (need - touch)  + " is " + need
                            );
                        } else {
                            setHelp("Jammer, er moest" + ((touch - need == 1) ? "" : "en") + " " + (touch - need) +
                                    " appel" + ((touch - need == 1) ? "" : "s")  + " af. Want " + touch + " min " +
                                    (touch - need)  + " is " + need
                            );
                        }

                        button.setText("Door");
                    }
                }
            }
        });

        this.getPanel().add(button);

        timer = new Timer(100, new ActionListener() {
            int i = blobby.getY();
            @Override
            public void actionPerformed(ActionEvent e) {
                if(i <= 100) blobby.setY(i = i + 5);
                if(i >= 100) {
                    shakeBlobby();
                }
                touch = 0;
                winning = (touch == need);
            }
        });

        timer.start();
    }

    public void shakeBlobby()
    {
        if(Math.random() < 0.5)
        {
            blobby.setX(blobby.getX() + 1);
        }
        if(Math.random() < 0.5)
        {
            blobby.setY(blobby.getY() + 1);
        }
        if(Math.random() < 0.5)
        {
            blobby.setX(blobby.getX() - 1);
        }
        if(Math.random() < 0.5)
        {
            blobby.setY(blobby.getY() - 1);
        }
    }
}
