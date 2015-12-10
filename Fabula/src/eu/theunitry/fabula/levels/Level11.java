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

/**
 * Level 11.
 * Jeroen Bronkhorst
 */

public class Level11 extends UNLevel
{
    private Timer timer;
    private UNGraphicsObject cashdesk;
    private ArrayList<UNGraphicsObject> apples, pears, grapes, oranges, bananas;
    private JButton button;
    private int need, touch;
    private UNColor color;
    private String lastHelp;

    /**
     * Level 11
     * @param gameScreen
     * @param hudEnabled
     */

    public Level11(UNGameScreen gameScreen, boolean hudEnabled)
    {
        super(gameScreen, hudEnabled);

        this.need = 3 + new Random().nextInt(10);

        this.setQuestion("Koop voor " + need + " euro aan fruit");
        this.addHelp("Jammer! Je moet voor " + need + " euro aan fruit kopen");
        this.addHelp("Helaas! Je moet voor " + need + " euro aan fruit inslaan");
        this.setHelp("Sleep het aantal fruit wat je kan betalen naar de kassa");
        this.setBackgroundImage(gameScreen.unResourceLoader.backgrounds.get("supermarket"));

        this.setPlayerHasWon (false);
        this.lastHelp = getHelp();

        this.apples = new ArrayList<>();
        this.pears = new ArrayList<>();
        this.grapes = new ArrayList<>();
        this.oranges = new ArrayList<>();
        this.bananas = new ArrayList<>();

        this.color = new UNColor();

        this.cashdesk = new UNGraphicsObject(gameScreen.getWindow().getFrame(), 5, 280, gameScreen.unResourceLoader.sprites.get("2:11:3"), false, 270, 102);

        this.addObject(cashdesk);

        for (int i = 0; i < 5; i++){
            apples.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 65 +
                    new Random().nextInt(50), 80 + new Random().nextInt(80), gameScreen.unResourceLoader.sprites.get("2:11:1"), true, 32, 32)
            );
        }

        for (int i = 0; i < 5; i++){
            pears.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 165 +
                    new Random().nextInt(50), 80 + new Random().nextInt(80), gameScreen.unResourceLoader.sprites.get("2:11:6"), true, 32, 32)
            );
        }

        for (int i = 0; i < 5; i++){
            grapes.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 470 +
                    new Random().nextInt(180), 70 + new Random().nextInt(45), gameScreen.unResourceLoader.sprites.get("2:11:4"), true, 32, 32)
            );
        }

        for (int i = 0; i < 5; i++){
            oranges.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 680 +
                    new Random().nextInt(30), 120 + new Random().nextInt(130), gameScreen.unResourceLoader.sprites.get("2:11:5"), true, 32, 32)
            );
        }

        for (int i = 0; i < 5; i++){
            bananas.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 430 +
                    new Random().nextInt(170), 180 + new Random().nextInt(50), gameScreen.unResourceLoader.sprites.get("2:11:2"), true, 32, 32)
            );
        }

        for (UNGraphicsObject apple : apples) {
            addObject(apple);
        }

        for (UNGraphicsObject pear : pears) {
            addObject(pear);
        }

        for (UNGraphicsObject grape : grapes) {
            addObject(grape);
        }

        for (UNGraphicsObject orange : oranges) {
            addObject(orange);
        }

        for (UNGraphicsObject banana : bananas) {
            addObject(banana);
        }

        this.button = new JButton("Betaal");

        this.setLayout(null);
        this.button.setBounds(275, 64, 150, 50);
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
                if (button.getText().equals("Doorgaan")) {
                    levelDone(11);
                }
                if (isHelperDoneTalking()) {
                    if (hasPlayerWon()) {
                        getHelper().setState(3);
                        setHelp("Puik rekenwerk! Ze zien er vers uit!");
                        for (UNGraphicsObject apple : apples) {
                            apple.setClickable(false);
                        }
                        for (UNGraphicsObject pear : pears) {
                            pear.setClickable(false);
                        }
                        for (UNGraphicsObject grape : grapes) {
                            grape.setClickable(false);
                        }
                        for (UNGraphicsObject orange : oranges) {
                            orange.setClickable(false);
                        }
                        for (UNGraphicsObject banana : bananas) {
                            banana.setClickable(false);
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
                                setHelp("Jammer, je moest nog " + (need - touch) + " euro aan fruit kopen. Want " +
                                        (need - touch) + " plus " + touch + " is " + need
                                );
                            } else {
                                setHelp("Jammer, je had " + (touch - need) + " euro aan fruit teveel gekocht. Want " +
                                        touch + " min " + (touch - need) + " is " + need
                                );
                            }

                            for (UNGraphicsObject apple : apples) {
                                apple.setClickable(false);
                            }
                            for (UNGraphicsObject pear : pears) {
                                pear.setClickable(false);
                            }
                            for (UNGraphicsObject grape : grapes) {
                                grape.setClickable(false);
                            }
                            for (UNGraphicsObject orange : oranges) {
                                orange.setClickable(false);
                            }
                            for (UNGraphicsObject banana : bananas) {
                                banana.setClickable(false);
                            }

                            button.setText("Doorgaan");
                        }
                    }
                }
            }
        });

        this.getPanel().add(button);

        timer = new Timer(1, e -> {
            touch = 0;
            for (UNGraphicsObject apple : apples) {
                if (cashdesk.getHitbox().intersects(apple.getHitbox())) {
                    touch++;
                }
            }
            for (UNGraphicsObject pear : pears) {
                if (cashdesk.getHitbox().intersects(pear.getHitbox())) {
                    touch = touch + 3;
                }
            }
            for (UNGraphicsObject grape : grapes) {
                if (cashdesk.getHitbox().intersects(grape.getHitbox())) {
                    touch = touch + 4;
                }
            }
            for (UNGraphicsObject orange : oranges) {
                if (cashdesk.getHitbox().intersects(orange.getHitbox())) {
                    touch = touch + 4;
                }
            }
            for (UNGraphicsObject banana : bananas) {
                if (cashdesk.getHitbox().intersects(banana.getHitbox())) {
                    touch = touch + 2;
                }
            }

            setPlayerHasWon(touch == need);

        });

        timer.start();
    }
}
