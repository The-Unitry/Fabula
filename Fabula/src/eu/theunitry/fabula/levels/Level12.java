package eu.theunitry.fabula.levels;


import eu.theunitry.fabula.UNGameEngine.graphics.UNGameScreen;
import eu.theunitry.fabula.UNGameEngine.graphics.UNColor;
import eu.theunitry.fabula.UNGameEngine.graphics.UNLevel;
import eu.theunitry.fabula.UNGameEngine.graphics.UNGraphicsObject;
import eu.theunitry.fabula.UNGameEngine.launcher.UNLauncher;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Level 12
 * Jeroen Bronkhorst
 */
public class Level12 extends UNLevel
{
    private Timer timer;
    private UNGraphicsObject cage;
    private ArrayList<UNGraphicsObject> monkeys_white, monkeys_brown;
    private JButton button;
    private int need, touch;
    private UNColor color;
    private String lastHelp;

    /**
     * Level 12
     * @param gameScreen
     * @param hudEnabled
     */
    public Level12(UNGameScreen gameScreen, boolean hudEnabled)
    {
        super(gameScreen, hudEnabled);

        this.need = 3 + new Random().nextInt(5);

        this.setQuestion("Stop de aapjes in de kooi met een gewicht van " + need + " kilogram");
        this.addHelp("Jammer! Je moet een totaal gewicht van " + need + " kilogram hebben");
        this.addHelp("Helaas! Er moet een gewicht van " + need + " kilogram in de kooi zitten");
        this.setHelp("Sleep de aapjes in de kooi");
        this.setBackgroundImage(gameScreen.getBackgrounds().get("jungle"));

        this.setPlayerHasWon (false);
        this.lastHelp = getHelp();

        this.monkeys_white = new ArrayList<UNGraphicsObject>();
        this.monkeys_brown = new ArrayList<UNGraphicsObject>();

        this.color = new UNColor();

        this.cage = new UNGraphicsObject(gameScreen.getWindow().getFrame(), 35, 260, gameScreen.unResourceLoader.sprites.get("2:12:1"), false, 128, 128);

        for (int i = 0; i < 5; i++){
            monkeys_white.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 150 +
                new Random().nextInt(360), 315 + new Random().nextInt(1), gameScreen.unResourceLoader.sprites.get("2:12:2:2"), true, 64, 64)
            );
        }

        for (int i = 0; i < 5; i++){
            monkeys_brown.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 150 +
                new Random().nextInt(360), 315 + new Random().nextInt(1), gameScreen.unResourceLoader.sprites.get("2:12:2:1"), true, 64, 64)
            );
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

        button.addActionListener(e -> {
            if (button.getText().equals("Doorgaan")) {
                levelDone(12);
            }
            if (isHelperDoneTalking()) {
                if (hasPlayerWon()) {
                    getHelper().setState(3);
                    setHelp("Goed gedaan! Het past allemaal precies!");
                    for (UNGraphicsObject monkey_white : monkeys_white) {
                        monkey_white.setClickable(false);
                    }
                    for (UNGraphicsObject monkey_brown : monkeys_brown) {
                        monkey_brown.setClickable(false);
                    }
                    button.setText("Doorgaan");
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
                            setHelp("Jammer, je moest nog " + (need - touch) + " kilogram erbij doen. Want " +
                                    (need - touch) + " plus " + touch + " is " + need
                            );
                        } else {
                            setHelp("Jammer, je had er " + (touch - need) + " kilogram teveel bij gedaan. Want " +
                                    touch + " min " + (touch - need) + " is " + need
                            );
                        }

                        for (UNGraphicsObject monkey_white : monkeys_white) {
                            monkey_white.setClickable(false);
                        }
                        for (UNGraphicsObject monkey_brown : monkeys_brown) {
                            monkey_brown.setClickable(false);
                        }

                        button.setText("Doorgaan");
                    }
                }
            }
        });

        this.getPanel().add(button);

        timer = new Timer(1, e -> {
            touch = 0;
            for (UNGraphicsObject monkey_white : monkeys_white) {
                if (monkey_white.getY() < 310 && !monkey_white.getMouseHold()) {
                    monkey_white.setY(monkey_white.getY() + 1);
                }
                if(!monkey_white.getMouseHold() && cage.getHitbox().intersects(monkey_white.getHitbox())) {

                    touch = touch + 2;

                }
            }
            for (UNGraphicsObject monkey_brown : monkeys_brown) {
                if (monkey_brown.getY() < 310 && !monkey_brown.getMouseHold()) {
                    monkey_brown.setY(monkey_brown.getY() + 1);
                }
                if(!monkey_brown.getMouseHold() && cage.getHitbox().intersects(monkey_brown.getHitbox())) {

                    touch = touch + 1;

                }
            }

            setPlayerHasWon(touch == need);

        });

        timer.start();
    }
}
