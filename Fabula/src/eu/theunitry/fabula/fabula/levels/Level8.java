package eu.theunitry.fabula.Fabula.levels;


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

public class Level8 extends UNLevel
{
    private Timer timer;
    private UNGraphicsObject chest;
    private ArrayList<UNGraphicsObject> nuggets;
    private JButton button;
    private boolean winning;
    private int need, touch;
    private UNColor color;
    private String lastHelp;

    /**
     * Level 0
     * @param gameScreen
     * @param hudEnabled
     */
    public Level8(UNGameScreen gameScreen, boolean hudEnabled)
    {
        super(gameScreen, hudEnabled);

        this.need = 3 + new Random().nextInt(3);

        this.setQuestion("Stop " + need + " muntjes in de schatkist");
        this.addHelp("Jammer! Je moet " + need + " muntjes in de schatkist stoppen");
        this.addHelp("Helaas! Er moeten " + need + " muntjes in de schatkist zitten");
        this.setHelp("Sleep het aantal muntjes in de schatkist");
        this.setBackgroundImage(gameScreen.getBackgrounds().get(4));

        this.winning = false;
        this.lastHelp = getHelp();

        this.nuggets = new ArrayList<UNGraphicsObject>();
        this.color = new UNColor();

        this.chest = new UNGraphicsObject(gameScreen.getWindow().getFrame(), 625, 233, gameScreen.getSprites().get(50), false, 96, 96);

        for (int i = 0; i < 5; i++){
            nuggets.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 64 +
                    new Random().nextInt(300), 100 + new Random().nextInt(200), gameScreen.getSprites()
                    .get(52), true, 32, 32)
            );
        }

        this.nuggets.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 610, 210, gameScreen.getSprites().get(52), true, 32, 32));
        if (new Random().nextInt(2) == 1) {
            nuggets.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 646, 240, gameScreen.getSprites().get(52), true, 32, 32));
        }

        this.addObject(chest);
        for (UNGraphicsObject apple : nuggets) {
            addObject(apple);
        }

        this.button = new JButton("Vertrek");

        this.setLayout(null);
        this.button.setBounds(618, 64, 150, 50);
        this.button.setBackground(new Color(51, 51, 51, 230));
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
                        gameScreen.switchPanel(new Level8(gameScreen, true));
                    } else {
                        gameScreen.switchPanel(new UNLauncher(gameScreen));
                    }
                }
                if (isHelperDoneTalking()) {
                    if (winning) {
                        getHelper().setState(3);
                        setHelp("Goed gedaan, nu zijn we rijk!");
                        for (UNGraphicsObject apple : nuggets) {
                            apple.setClickable(false);
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
                                        " muntje" + ((need - touch == 1) ? "" : "s")  + " bij. Want " + touch + " plus " +
                                        (need - touch)  + " is " + need
                                );
                            } else {
                                setHelp("Jammer, er moest" + ((touch - need == 1) ? "" : "en") + " " + (touch - need) +
                                        " muntje" + ((touch - need == 1) ? "" : "s")  + " af. Want " + touch + " min " +
                                        (touch - need)  + " is " + need
                                );
                            }

                            for (UNGraphicsObject apple : nuggets)
                            {
                                apple.setClickable(false);
                            }

                            button.setText("Door");
                        }
                    }
                }
            }
        });

        this.getPanel().add(button);

        timer = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                touch = 0;
                for (UNGraphicsObject apple : nuggets) {
                    if (chest.getHitbox().intersects(apple.getHitbox())) {
                        touch++;
                    }
                }
                winning = (touch == need);
            }
        });

        timer.start();
    }
}
