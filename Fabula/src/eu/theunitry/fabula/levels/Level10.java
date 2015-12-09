package eu.theunitry.fabula.levels;

import eu.theunitry.fabula.UNGameEngine.graphics.UNGameScreen;
import eu.theunitry.fabula.UNGameEngine.graphics.UNColor;
import eu.theunitry.fabula.UNGameEngine.graphics.UNGraphicsObject;
import eu.theunitry.fabula.UNGameEngine.graphics.UNGraphicsLevel;
import eu.theunitry.fabula.UNGameEngine.launcher.UNLauncher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

/**
 * Level 10
 * Wesley Meijer
 */
public class Level10 extends UNGraphicsLevel
{
    private Timer timer;
    private UNGraphicsObject snail1;
    private UNGraphicsObject snail2;
    private UNGraphicsObject snail3;
    private ArrayList<UNGraphicsObject> acorn;
    private UNGraphicsObject pointer;
    private JButton button;
    private boolean winning;
    private int need, touch, g1, g2;
    private UNColor color;
    private String lastHelp;

    /**
     * Level 10
     * @param gameScreen
     * @param hudEnabled
     */
    public Level10(UNGameScreen gameScreen, boolean hudEnabled)
    {
        super(gameScreen, hudEnabled);

        this.g1 = 1+ new Random().nextInt(4);
        this.g2 = 1+ new Random().nextInt(4);
        this.need = g1 * g2;

        if (g1 == 1)
        {
            this.setQuestion("hoeveel groepen van " + g1 + " eikeltje vallen uit de boom");
        } else {
            this.setQuestion("hoeveel groepen van " + g1 + " eikeltjes vallen uit de boom");
        }
        
        this.addHelp("Jammer! Je moet kijken hoevel groepen van " + g1 + " eikeltjes op de grond liggen");
        this.addHelp("Helaas! gebruik je wel de tafel van " + g1 + " ?");
        this.addHelp("net niet goed, weet je zeker dat je goed hebt getelt?");
        this.setHelp("Sleep het grote eikeltje naar de slak met het juiste antwoord.");
        this.setBackgroundImage(gameScreen.unResourceLoader.backgrounds.get("forest-background"));

        this.winning = false;
        this.lastHelp = getHelp();
        this.pointer = new UNGraphicsObject(gameScreen.getWindow().getFrame(), 342, 285, gameScreen.unResourceLoader.sprites.get("2:10:1"), true, 32, 32);
        this.snail1 = new UNGraphicsObject(gameScreen.getWindow().getFrame(), 557, 200, gameScreen.unResourceLoader.sprites.get("2:10:2"), false, 60, 60);
        this.snail2 = new UNGraphicsObject(gameScreen.getWindow().getFrame(), 557, 270, gameScreen.unResourceLoader.sprites.get("2:10:2"), false, 60, 60);
        this.snail3 = new UNGraphicsObject(gameScreen.getWindow().getFrame(), 557, 340, gameScreen.unResourceLoader.sprites.get("2:10:2"), false, 60, 60);
        this.acorn = new ArrayList<UNGraphicsObject>();
        this.color = new UNColor();

        for (int i = 0; i < need; i++)
        {
            acorn.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(),50 + new Random().nextInt(400), 65, gameScreen.unResourceLoader.sprites.get("2:10:1"), true, 16, 16));
        }

        this.button = new JButton("begin");
        this.setLayout(null);
        this.button.setBounds(618, 64, 150, 50);
        this.button.setBackground(new Color(51, 51, 51));
        this.button.setFont(new Font("Minecraftia", Font.PLAIN, 15));
        this.button.setForeground(Color.white);
        this.button.setOpaque(true);
        addObject(pointer);
        addObject(snail1);
        addObject(snail2);
        addObject(snail3);

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
                        gameScreen.switchPanel(new Level9(gameScreen, true));
                    } else {
                        gameScreen.switchPanel(new UNLauncher(gameScreen));
                    }
                }
                if (isHelperDoneTalking()) {
                    if (winning) {
                        getHelper().setState(3);
                        setHelp("Goed gedaan, jij kent de tafels goed!");
                        for (UNGraphicsObject acorns : acorn) {
                            acorns.setClickable(false);
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
                                        " groep(en)" + ((need - touch == 1) ? "" : "s")  + " bij. Want " + touch + " keer " +
                                        (need - touch)  + " is " + need
                                );
                            } else {
                                setHelp("Jammer, er moest" + ((touch - need == 1) ? "" : "en") + " " + (touch - need) +
                                        " groep(en)" + ((touch - need == 1) ? "" : "s")  + " af. Want " + touch + " keer " +
                                        (touch - need)  + " is " + need
                                );
                            }

                            button.setText("Door");
                        }
                    }
                }
            }
        });

        for (UNGraphicsObject acorns : acorn)
        {
            addObject(acorns);
        }


        timer = new Timer(1, e -> {
            touch = 0;
            for (UNGraphicsObject acorns : acorn) {
                if (snail1.getHitbox().intersects(acorns.getHitbox())) {
                    touch++;
                }
            }
            winning = (touch == need);

            for(UNGraphicsObject acorns : acorn)
            {
                if(!acorns.getMouseHold() && !snail2.getHitbox(true).intersects(acorns.getHitbox())) {
                    acorns.setY(acorns.getY() + 1);
                }
            }
        });

            this.getPanel().add(button);

        timer.start();
    }
}
