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

        this.need = 1 + new Random().nextInt(10);
        this.need2 = 1 + new Random().nextInt(10);

        this.setQuestion("Hoeveel is " + need + " plus " + need2 + "?");
        this.addHelp("Jammer! Je moet " + (need + need2) + " muntjes in de schatkist stoppen");
        this.addHelp("Helaas! Er moeten " + (need + need2) + " muntjes in de schatkist zitten");
        this.setHelp("Sleep het juiste aantal muntjes in de schatkist!");
        this.setBackgroundImage(gameScreen.getBackgrounds().get(0));

        this.lastHelp = getHelp();

        this.coins = new ArrayList<UNGraphicsObject>();
        this.color = new UNColor();

        this.chest = new UNGraphicsObject(gameScreen.getWindow().getFrame(), 479, 360, gameScreen.getSprites().get(41), false, 96, 96);
        this.chest.setHitbox(0, 60, 96, 1);

        for (int i = 0; i < 20; i++){
            coins.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 64 +
                new Random().nextInt(380), -100 - new Random().nextInt(150), gameScreen.getSprites().get(42), false, 32, 32)
            );
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
                            if (touch < need + need2) {
                                setHelp("Jammer, er moest" + ((need + need2 - touch == 1) ? "" : "en") + " nog " + (need + need2 - touch) +
                                        " muntje" + ((need + need2 - touch == 1) ? "" : "s")  + " bij. Want " + touch + " plus " +
                                        (need + need2 - touch)  + " is " + (need + need2)
                                );
                            } else {
                                setHelp("Jammer, er moest" + ((touch - need + need2 == 1) ? "" : "en") + " " + (touch - need + need2) +
                                        " muntje" + ((touch - need + need2 == 1) ? "" : "s")  + " af. Want " + touch + " min " +
                                        (touch - need + need2)  + " is " + (need + need2)
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

        timer = new Timer(15, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                touch = 0;
                for (UNGraphicsObject coin : coins) {
                    if (chest.getHitbox().intersects(coin.getHitbox())) {
                        touch++;
                    }
                }
                winning = (touch == (need + need2));

                for(UNGraphicsObject coin : coins)
                {
                    if(!coin.getMouseHold() && !chest.getHitbox(true).intersects(coin.getHitbox())) {
                        coin.setY(coin.getY() + 1);
                    }
                        if(coin.getY() > 64)
                        {
                            coin.setClickable(true);
                        }
                }
            }
        });

        timer.start();
    }
}
