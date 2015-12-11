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

        this.setQuestion("Oh nee! Blobby probeert de wereld te vernietigen. Stop hem!");
        this.addHelp("Jammer! Je moet " + need + " appels in de mand stoppen");
        this.addHelp("Helaas! Er moeten " + need + " appels in de mand zitten");
        this.setHelp("Sleep het aantal appels in de mand");
        this.setBackgroundImage(gameScreen.unResourceLoader.backgrounds.get("the-end"));
        gameScreen.getMusic().get("the-end").play(true);
        gameScreen.getMusic().get("the-end").setVolume(0.1);

        this.winning = false;
        this.lastHelp = getHelp();

        this.button = new JButton("Vuren!");

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



        timer = new Timer(1, new ActionListener() {

            int i = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                touch = 0;
                System.out.println(i++);
                winning = (touch == need);
            }
        });

        timer.start();
    }
}
