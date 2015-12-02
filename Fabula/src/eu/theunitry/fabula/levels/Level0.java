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

public class Level0 extends UNLevel
{
    private Timer timer;
    private UNGraphicsObject basket;
    private ArrayList<UNGraphicsObject> apples;
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
    public Level0(UNGameScreen gameScreen, boolean hudEnabled)
    {
        super(gameScreen, hudEnabled);

        this.need = 3 + new Random().nextInt(3);

        this.setQuestion("Vertrek met " + need + " appels in de mand");
        this.addHelp("Jammer! Je moet " + need + " appels in de mand stoppen");
        this.addHelp("Helaas! Er moeten " + need + " appels in de mand zitten");
        this.setHelp("Sleep het aantal appels in de mand");
        this.setBackgroundImage(gameScreen.getBackgrounds().get(0));

        this.winning = false;
        this.lastHelp = getHelp();

        this.apples = new ArrayList<UNGraphicsObject>();
        this.color = new UNColor();

        this.basket = new UNGraphicsObject(gameScreen.getWindow().getFrame(), 600, 200, gameScreen.getSprites().get(39), false, 96, 96);

        for (int i = 0; i < 5; i++){
            apples.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 64 +
                    new Random().nextInt(300), 100 + new Random().nextInt(200), gameScreen.getSprites()
                    .get(38), true, 32, 32)
            );
        }

        this.apples.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 610, 210, gameScreen.getSprites().get(38), true, 32, 32));
        if (new Random().nextInt(2) == 1) {
            apples.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 646, 240, gameScreen.getSprites().get(38), true, 32, 32));
        }

        this.addObject(basket);
        for (UNGraphicsObject apple : apples) {
            addObject(apple);
        }

        this.button = new JButton("Vertrek");

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
                        gameScreen.switchPanel(new Level0(gameScreen, true));
                    } else {
                        gameScreen.switchPanel(new UNLauncher(gameScreen));
                    }
                }
                if (isHelperDoneTalking()) {
                    if (winning) {
                        getHelper().setState(3);
                        setHelp("Goed gedaan, dat wordt smikkelen en smullen!");
                        for (UNGraphicsObject apple : apples) {
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
                                        " appel" + ((need - touch == 1) ? "" : "s")  + " bij. Want " + touch + " plus " +
                                        (need - touch)  + " is " + need
                                );
                            } else {
                                setHelp("Jammer, er moest" + ((touch - need == 1) ? "" : "en") + " " + (touch - need) +
                                        " appel" + ((touch - need == 1) ? "" : "s")  + " af. Want " + touch + " min " +
                                        (touch - need)  + " is " + need
                                );
                            }

                            for (UNGraphicsObject apple : apples)
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
                for (UNGraphicsObject apple : apples) {
                    if (basket.getHitbox().intersects(apple.getHitbox())) {
                        touch++;
                    }
                }
                winning = (touch == need);
            }
        });

        timer.start();
    }
}
