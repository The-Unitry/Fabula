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
 * Level 10
 * Wesley Meijer
 */
public class Level10 extends UNLevel
{
    private ArrayList<UNGraphicsObject> acorn, snails;
    private UNGraphicsObject pointer;
    private JButton button;
    private int need, g1, g2, fakeAnswer1, fakeAnswer2, newRand;
    private String lastHelp;
    private ArrayList<JLabel> snailsText, answers;
    private Timer timer;

    /**
     * Level 10
     * @param gameScreen
     * @param hudEnabled
     */
    public Level10(UNGameScreen gameScreen, boolean hudEnabled)
    {
        super(gameScreen, hudEnabled);

        this.snailsText = new ArrayList<>();
        this.answers = new ArrayList<>();
        this.snails = new ArrayList<>();
        this.newRand = new Random().nextInt(2) + 1;

        this.g1 = 1+ new Random().nextInt(4);
        this.g2 = 1+ new Random().nextInt(4);
        this.acorn = new ArrayList<UNGraphicsObject>();
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
        this.lastHelp = getHelp();
        this.setBackgroundImage(gameScreen.unResourceLoader.backgrounds.get("forest-background"));

        this.setPlayerHasWon(false);
        this.lastHelp = getHelp();
        this.snails.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 557, 150, gameScreen.unResourceLoader.sprites.get("2:10:2"), false, 60, 60));
        this.snails.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 557, 220, gameScreen.unResourceLoader.sprites.get("2:10:2"), false, 60, 60));
        this.snails.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 557, 290, gameScreen.unResourceLoader.sprites.get("2:10:2"), false, 60, 60));

        this.pointer = new UNGraphicsObject(gameScreen.getWindow().getFrame(), 342, 285, gameScreen.unResourceLoader.sprites.get("2:10:1"), true, 50, 50);

        this.snailsText.add(new JLabel(Integer.toString(g2)));
        this.snailsText.add(new JLabel(Integer.toString(g2 +1)));
        this.snailsText.add(new JLabel(Integer.toString(g2 -1)));

        for (int i = 0; i < need; i++)
        {
            acorn.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(),50 + new Random().nextInt(400), 65, gameScreen.unResourceLoader.sprites.get("2:10:1"), true, 32, 32));
        }

        for(UNGraphicsObject snail : snails)
        {
            this.addObject(snail);
            snail.setClickable(false);
        }

        this.fakeAnswer1 = this.g2 + 1;
        this.fakeAnswer2 = this.g2 - 1;

        if (newRand == 1)
        {
            this.answers.add(new JLabel(Integer.toString(g2)));
            this.answers.add(new JLabel(Integer.toString(fakeAnswer2)));
            this.answers.add(new JLabel(Integer.toString(fakeAnswer1)));
        }
        else if (newRand == 2)
        {
            this.answers.add(new JLabel(Integer.toString(fakeAnswer1)));
            this.answers.add(new JLabel(Integer.toString(g2)));
            this.answers.add(new JLabel(Integer.toString(fakeAnswer2)));
        }
        else if (newRand == 3) {
            this.answers.add(new JLabel(Integer.toString(fakeAnswer2)));
            this.answers.add(new JLabel(Integer.toString(fakeAnswer1)));
            this.answers.add(new JLabel(Integer.toString(g2)));
        }

        this.button = new JButton("nakijken");
        this.setLayout(null);
        this.button.setBounds(618, 64, 150, 50);
        this.button.setBackground(new Color(51, 51, 51));
        this.button.setFont(new Font("Minecraftia", Font.PLAIN, 15));
        this.button.setForeground(Color.white);
        this.button.setOpaque(true);
        addObject(pointer);

        for (JLabel snailText : snailsText)
        {
            snailText.setFont(new Font("Minecraftia", Font.PLAIN, 20));
            snailText.setForeground(new Color(51, 51, 51));
            snailText.setHorizontalAlignment(SwingConstants.CENTER);
            add(snailText);
        }

        for (JLabel possibleAnswer : answers)
        {
            possibleAnswer.setFont(new Font("Minecraftia", Font.PLAIN, 20));
            possibleAnswer.setForeground(new Color(51, 51, 51));
            possibleAnswer.setHorizontalAlignment(SwingConstants.CENTER);
        }

        /**
         * Reset Default Styling
         */
        this.button.setFocusPainted(false);
        this.button.setBorderPainted(false);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (button.getText() == "Doorgaan") {
                    levelDone(10);
                } else {
                    for (UNGraphicsObject snail : snails) {
                        if (pointer.getHitbox().intersects(snail.getHitbox())) {
                            if (Integer.parseInt(answers.get(snails.indexOf(snail)).getText()) == g2) {
                                setHelp("Goedzo, jij kent je tafels goed!");
                                setPlayerHasWon(true);
                                button.setText("Doorgaan");
                                getHelper().setState(3);
                            } else {
                                addMistake();
                                if (getMistakes() < 3)
                                {
                                    getHelper().setState(4);
                                    while(lastHelp == getHelp())
                                    {
                                        setHelp(getHelpList().get(new Random().nextInt(getHelpList().size())));
                                    }
                                    lastHelp = getHelp();
                                }
                                else
                                {
                                    getHelper().setState(4);
                                    int current = Integer.parseInt(answers.get(snails.indexOf(snail)).getText());
                                    if (current < g2)
                                    {
                                        setHelp("Jammer, er moest nog 1 groepje bij. Want " + g1 + " keer " +
                                                g2  + " is " + need + "."
                                        );
                                    }
                                    else
                                    {
                                        setHelp("Jammer, er moest nog 1 groepje af. Want " + g1 + " keer " +
                                                g2  + " is " + need + "."
                                        );
                                    }

                                    button.setText("Doorgaan");
                                }
                            }
                        }
                    }
                }
            }
        });

        for (UNGraphicsObject acorns : acorn)
        {
            addObject(acorns);
        }

        for (JLabel possibleAnswer : answers)
        {
            add(possibleAnswer);
            possibleAnswer.setForeground(Color.white);
            possibleAnswer.setBounds(snails.get(answers.indexOf(possibleAnswer)).getX() + 8, snails.get(answers.indexOf(possibleAnswer)).getY() - 6, snails.get(answers.indexOf(possibleAnswer)).getWidth(), snails.get(answers.indexOf(possibleAnswer)).getHeight());
        }
        this.getPanel().add(button);

        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (UNGraphicsObject cavia : acorn) {
                    if(!cavia.getMouseHold()) {
                        cavia.setY(cavia.getY() +1);
                    }
                }
            }
        });
        timer.start();
    }
}
