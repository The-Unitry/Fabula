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

public class Level12 extends UNLevel
{
    private Timer timer;
    private UNGraphicsObject cage;
    private ArrayList<UNGraphicsObject> monkeys_white, monkeys_brown;
    private JButton button;
    private boolean winning;
    private int need, touch;
    private UNColor color;
    private String lastHelp;

    /**
     * Level 11
     * @param gameScreen
     * @param hudEnabled
     */
    public Level12(UNGameScreen gameScreen, boolean hudEnabled)
    {
        super(gameScreen, hudEnabled);

        this.need = 3 + new Random().nextInt(5);

        this.setQuestion("Stop de aapjes in de kooi met een totaal gewicht van " + need + " kilogram");
        this.addHelp("Jammer! Je moet een totaal gewicht van " + need + " kilogram hebben");
        this.addHelp("Helaas! Er moet een gewicht van " + need + " kilogram in de kooi zitten");
        this.setHelp("Sleep de aapjes in de kooi");
        this.setBackgroundImage(gameScreen.getBackgrounds().get(0));

        this.winning = false;
        this.lastHelp = getHelp();

        this.monkeys_white = new ArrayList<UNGraphicsObject>();
        this.monkeys_brown = new ArrayList<UNGraphicsObject>();

        this.color = new UNColor();

        this.cage = new UNGraphicsObject(gameScreen.getWindow().getFrame(), 600, 200, gameScreen.getSprites().get(39), false, 96, 96);

        for (int i = 0; i < 5; i++){
            monkeys_white.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 64 +
                    new Random().nextInt(300), 100 + new Random().nextInt(200), gameScreen.getSprites()
                    .get(38), true, 32, 32)
            );
        }

        for (int i = 0; i < 5; i++){
            monkeys_brown.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 64 +
                    new Random().nextInt(300), 100 + new Random().nextInt(200), gameScreen.getSprites()
                    .get(38), true, 32, 32)
            );
        }

        this.monkeys_white.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 610, 210, gameScreen.getSprites().get(38), true, 32, 32));
        if (new Random().nextInt(2) == 1) {
            monkeys_white.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 646, 240, gameScreen.getSprites().get(38), true, 32, 32));
        }

        this.monkeys_brown.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 610, 210, gameScreen.getSprites().get(38), true, 32, 32));
        if (new Random().nextInt(2) == 1) {
            monkeys_brown.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 646, 240, gameScreen.getSprites().get(38), true, 32, 32));
        }

        for (UNGraphicsObject monkey_white : monkeys_white) {
            addObject(monkey_white);
        }

        for (UNGraphicsObject monkey_brown : monkeys_brown) {
            addObject(monkey_brown);
        }

        this.addObject(cage);

        this.button = new JButton("Klaar");

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
                        setHelp("Goed gedaan! Het past allemaal precies!");
                        for (UNGraphicsObject monkey_white : monkeys_white) {
                            monkey_white.setClickable(false);
                        }
                        for (UNGraphicsObject monkey_brown : monkeys_brown) {
                            monkey_brown.setClickable(false);
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
                                setHelp("Jammer, je moest nog een gewicht van " + (need - touch) + " erbij doen. Want " +
                                        (need - touch) + " plus " + touch + " is " + need
                                );
                            } else {
                                setHelp("Jammer, je had een gewicht van " + (touch - need) + " teveel bij gedaan. Want " +
                                        touch + " min " + (touch - need) + " is " + need
                                );
                            }

                            for (UNGraphicsObject monkey_white : monkeys_white) {
                                monkey_white.setClickable(false);
                            }
                            for (UNGraphicsObject monkey_brown : monkeys_brown) {
                                monkey_brown.setClickable(false);
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
                for (UNGraphicsObject monkey_white : monkeys_white) {
                    if (cage.getHitbox().intersects(monkey_white.getHitbox())) {
                        touch = touch + 1;
                    }
                }
                for (UNGraphicsObject monkey_brown : monkeys_brown) {
                    if (cage.getHitbox().intersects(monkey_brown.getHitbox())) {
                        touch = touch + 2;
                    }
                }
                winning = (touch == need);
            }
        });

        timer.start();
    }
}
