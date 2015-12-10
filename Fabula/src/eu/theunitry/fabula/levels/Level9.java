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
 * Level 9
 * Wesley Meijer
 */
public class Level9 extends UNLevel
{
    private Timer timer;
    private UNGraphicsObject plank;
    private UNGraphicsObject diamond;
    private ArrayList<UNGraphicsObject> weights;
    private JButton button;
    private int need, touch;
    private UNColor color;
    private String lastHelp;

    /**
     * Level 9
     * @param gameScreen
     * @param hudEnabled
     */
    public Level9(UNGameScreen gameScreen, boolean hudEnabled)
    {
        super(gameScreen, hudEnabled);

        this.need = 10 - new Random().nextInt(8);

        this.setQuestion("De diamant weegt " + need + " kilo. 1 gewichtje weegt 1 kilo.");
        this.addHelp("Jammer! Je moet " + need + " gewichtjes op de plank hebben.");
        this.addHelp("Helaas! Er moeten " + need + " gewichtjes op de plank staan.");
        this.addHelp("Net niet goed, weet je zeker dat er " + need + " op de plank staan?");
        this.setHelp("Zorg dat de diamant evenveel weegt als de gewichtjes op de plank.");
        this.setBackgroundImage(gameScreen.unResourceLoader.backgrounds.get("cave"));

        this.setPlayerHasWon(false);
        this.lastHelp = getHelp();

        this.weights = new ArrayList<UNGraphicsObject>();
        this.color = new UNColor();

        this.plank = new UNGraphicsObject(gameScreen.getWindow().getFrame(), 565, 280, gameScreen.unResourceLoader.sprites.get("2:9:2"), false, 160, 32);
        this.plank.setHitbox(0, 0, 160, 1);

        this.diamond = new UNGraphicsObject(gameScreen.getWindow().getFrame(), 342, 285, gameScreen.unResourceLoader.sprites.get("2:9:1"), false, 32, 32);

        for (int i = 0; i <10; i++) {
            weights.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 550 +
                    new Random().nextInt(150), 250, gameScreen.unResourceLoader.sprites.get("2:9:3:1"), true, 32, 32));
        }


        this.addObject(plank);
        for (UNGraphicsObject weight : weights) {
            addObject(weight);
        }

        this.addObject(diamond);

        this.button = new JButton("Wegen");

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
                levelDone(9);
            }
            if (isHelperDoneTalking()) {
                if (hasPlayerWon()) {
                    getHelper().setState(3);
                    setHelp("Goed gedaan, de diamant is van ons!!!");
                    for (UNGraphicsObject weight : weights) {
                        weight.setClickable(false);
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
                            setHelp("Jammer, er moest" + ((need - touch == 1) ? "" : "en") + " nog " + (need - touch) +
                                    " gewichtjes" + ((need - touch == 1) ? "" : "s")  + " bij. Want " + touch + " plus " +
                                    (need - touch)  + " is " + need
                            );
                        } else {
                            setHelp("Jammer, er moest" + ((touch - need == 1) ? "" : "en") + " " + (touch - need) +
                                    " gewicht" + ((touch - need == 1) ? "" : "jes")  + " af. Want " + touch + " min " +
                                    (touch - need)  + " is " + need
                            );
                        }

                        for (UNGraphicsObject weight : weights)
                        {
                            weight.setClickable(false);
                        }

                        button.setText("Doorgaan");
                    }
                }
            }
        });

        this.getPanel().add(button);

        timer = new Timer(1, e -> {
            touch = 0;
            for (UNGraphicsObject weight : weights) {
                if (plank.getHitbox().intersects(weight.getHitbox())) {
                    touch++;
                }
            }
            setPlayerHasWon((touch == need));

            for(UNGraphicsObject weight : weights)
            {
                if(!weight.getMouseHold() && !plank.getHitbox(true).intersects(weight.getHitbox())) {
                    weight.setY(weight.getY() + 1);
                }
            }
        });

        timer.start();
    }
}
