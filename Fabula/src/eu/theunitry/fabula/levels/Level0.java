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

public class Level0 extends UNLevel {

    private Timer timer;
    private UNGraphicsObject basket;
    private ArrayList<UNGraphicsObject> apples;
    private JButton button;
    private boolean winning;
    private int need, touch;
    private UNColor color;
    private String lastHelp;

    public Level0(UNGameScreen gameScreen, boolean hudEnabled) {
        super(gameScreen, hudEnabled);

        need = 3 + new Random().nextInt(3);

        setQuestion("Vertrek met " + need + " appels in de mand");
        addHelp("Jammer! Je moet " + need + " appels in de mand stoppen");
        addHelp("Helaas! Er moeten " + need + " appels in de mand zitten");
        setHelp("Sleep het aantal appels in de mand");
        setBackgroundImage(gameScreen.getBackgrounds().get(0));

        winning = false;
        lastHelp = getHelp();

        apples = new ArrayList<UNGraphicsObject>();
        color = new UNColor();

        basket = new UNGraphicsObject(gameScreen.getWindow().getFrame(), 600, 200, gameScreen.getSprites().get(39), false, 96, 96);

        for (int i = 0; i < 5; i++){
            apples.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 64 + new Random().nextInt(300), 100 + new Random().nextInt(200), gameScreen.getSprites().get(38), true, 32, 32));
        }

        apples.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 610, 210, gameScreen.getSprites().get(38), true, 32, 32));
        if (new Random().nextInt(2) == 1) {
            apples.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 646, 240, gameScreen.getSprites().get(38), true, 32, 32));
        }

        addObject(basket);
        for (UNGraphicsObject apple : apples) {
            addObject(apple);
        }

        button = new JButton("Vertrek");

        this.setLayout(null);
        button.setBounds(618, 64, 150, 50);
        button.setBackground(new Color(51, 51, 51));
        button.setFont(new Font("Minecraftia", Font.PLAIN, 15));
        button.setForeground(Color.white);
        button.setOpaque(true);

        /* Reset default styling */
        button.setFocusPainted(false);
        button.setBorderPainted(false);

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
                if (getHelperDoneTalking()) {
                    if (winning) {
                        getHelper().setState(3);
                        setHelp("Goed gedaan, dat wordt smikkelen en smullen!");
                        for (UNGraphicsObject apple : apples) {
                            apple.setClickable(false);
                        }
                        //getPanel().remove(button);
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
                                setHelp("Jammer, er moest" + ((need - touch == 1) ? "" : "en") + " nog " + (need - touch) + " appel" + ((need - touch == 1) ? "" : "s")  + " bij. Want " + touch + " plus " + (need - touch)  + " is " + need);
                            } else {
                                setHelp("Jammer, er moest" + ((touch - need == 1) ? "" : "en") + " " + (touch - need) + " appel" + ((touch - need == 1) ? "" : "s")  + " af. Want " + touch + " min " + (touch - need)  + " is " + need);
                            }
                            for (UNGraphicsObject apple : apples) {
                                apple.setClickable(false);
                            }
                            //getPanel().remove(button);
                            button.setText("Door");
                        }
                    }
                }
            }
        });

        getPanel().add(button);

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
