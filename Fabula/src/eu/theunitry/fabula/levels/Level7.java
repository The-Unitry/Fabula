package eu.theunitry.fabula.levels;


import eu.theunitry.fabula.UNGameScreen;
import eu.theunitry.fabula.graphics.UNColor;
import eu.theunitry.fabula.graphics.UNGraphicsObject;
import eu.theunitry.fabula.graphics.UNLevel;
import eu.theunitry.fabula.launcher.UNLauncher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class Level7 extends UNLevel
{
    private Timer timer;
    private UNGraphicsObject chest;
    private ArrayList<UNGraphicsObject> coins;
    private JButton button;
    private boolean winning;
    private int need, need2, touch;
    private UNColor color;
    private String lastHelp;

    /**
     * Level 0
     * @param gameScreen
     * @param hudEnabled
     */
    public Level7(UNGameScreen gameScreen, boolean hudEnabled)
    {
        super(gameScreen, hudEnabled);

        this.need = 1 + new Random().nextInt(4);
        this.need2 = 1 + new Random().nextInt(10);

        this.setQuestion("Hoeveel is " + need + " keer " + need2 + "?");
        this.addHelp("Jammer! Je moet " + need + " muntjes in de schatkist stoppen");
        this.addHelp("Helaas! Er moeten " + need + " muntjes in de schatkist zitten");
        this.setHelp("Sleep het juiste aantal muntjes in de schatkist!");
        this.setBackgroundImage(gameScreen.getBackgrounds().get(0));

        this.winning = false;
        this.lastHelp = getHelp();

        this.coins = new ArrayList<UNGraphicsObject>();
        this.color = new UNColor();

        // this.chest = new UNGraphicsObject(gameScreen.getWindow().getFrame(), 490, 360, gameScreen.getSprites().get(42), false, 96, 96);

        for (int i = 0; i < 5; i++){
            coins.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 64 +
                new Random().nextInt(300), 100 + new Random().nextInt(200), gameScreen.getSprites().get(42), true, 32, 32)
            );
        }

        if (new Random().nextInt(2) == 1) {
           coins.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 646, 240, gameScreen.getSprites().get(42), true, 32, 32));
        }

        this.addObject(chest);
        for (UNGraphicsObject coin : coins) {
            addObject(coin);
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
                        gameScreen.switchPanel(new Level7(gameScreen, true));
                    } else {
                        gameScreen.switchPanel(new UNLauncher(gameScreen));
                    }
                }
                if (isHelperDoneTalking()) {
                    if (winning) {
                        getHelper().setState(3);
                        setHelp("Goed gedaan, nu zijn we rijk!");
                        for (UNGraphicsObject coin : coins) {
                            coin.setClickable(false);
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

                            for (UNGraphicsObject coin : coins)
                            {
                                coin.setClickable(false);
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
                for (UNGraphicsObject coin : coins) {
                    if (chest.getHitbox().intersects(coin.getHitbox())) {
                        touch++;
                    }
                }
                winning = (touch == need);
            }
        });

        timer.start();
    }
}
