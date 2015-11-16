package eu.theunitry.fabula;


import eu.theunitry.fabula.graphics.UNColor;
import eu.theunitry.fabula.graphics.UNGraphicsObject;
import eu.theunitry.fabula.graphics.UNLevel;

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
    private int touch;
    private UNColor color;

    public Level0(UNGameScreen gameScreen, boolean hudEnabled) {
        super(gameScreen, hudEnabled);

        setQuestion("Vertrek met 3 appels in de mand");
        addHelp("Jammer! Je moet 3 appels in de mand stoppen");
        setBackgroundImage(gameScreen.getBackgrounds().get(0));

        winning = false;

        apples = new ArrayList<UNGraphicsObject>();
        color = new UNColor();

        basket = new UNGraphicsObject(gameScreen.getWindow().getFrame(), 600, 200, gameScreen.getSprites().get(39), false, 64, 64);

        apples.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 100, 100, gameScreen.getSprites().get(38), true, 32, 32));
        apples.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 140, 190, gameScreen.getSprites().get(38), true, 32, 32));
        apples.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 20, 240, gameScreen.getSprites().get(38), true, 32, 32));
        apples.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 300, 300, gameScreen.getSprites().get(38), true, 32, 32));
        apples.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 610, 210, gameScreen.getSprites().get(38), true, 32, 32));

        addObject(basket);
        for (UNGraphicsObject apple : apples) {
            addObject(apple);
        }

        button = new JButton("Vertrek");

        //button.setPreferredSize(new Dimension(150, 50));
        //button.setLocation(606, 64);
        button.setLayout(null);
        button.setBounds(606, 64, 150, 50);
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
                if (getHelperDoneTalking()) {
                    if (winning) {
                        getHelper().setState(3);
                        setHelp("Goed gedaan! Dat wordt smikkelen en smullen");
                        for (UNGraphicsObject apple : apples) {
                            apple.setClickable(false);
                        }
                        getPanel().remove(button);
                    } else {
                        addMistake();
                        if (getMistakes() < 3) {
                            getHelper().setState(4);
                            setHelp(getHelpList().get(new Random().nextInt(getHelpList().size())));
                        } else {
                            getHelper().setState(4);
                            if (touch < 3) {
                                setHelp("Jammer, er moest" + ((3 - touch == 1) ? "" : "en") + " nog " + (3 - touch) + " appel" + ((3 - touch == 1) ? "" : "s")  + " bij. Want " + touch + " plus " + (3 - touch)  + " is 3");
                            } else {
                                setHelp("Jammer, er moest" + ((touch - 3 == 1) ? "" : "en") + " " + (touch - 3) + " appel" + ((touch - 3 == 1) ? "" : "s")  + " af. Want " + touch + " min " + (touch - 3)  + " is 3");
                            }
                            for (UNGraphicsObject apple : apples) {
                                apple.setClickable(false);
                            }
                            getPanel().remove(button);
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
                winning = (touch == 3);
            }
        });
        timer.start();
    }
}
