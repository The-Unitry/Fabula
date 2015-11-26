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

public class Level6 extends UNLevel
{
    private Timer timer;
    private ArrayList<UNGraphicsObject> snowballs, snowballAnswers, trees, reindeers;
    private double imageIndex;
    private JButton button;
    private boolean questionDone, winning;
    private int answer, touch;
    private UNColor color;
    private String lastHelp;
    private ArrayList<JLabel> snowballTexts, answers;
    private double reindeerIndex;

    /**
     * Level 6
     * @param gameScreen
     * @param hudEnabled
     */
    public Level6(UNGameScreen gameScreen, boolean hudEnabled) {
        super(gameScreen, hudEnabled);

        snowballs = new ArrayList<UNGraphicsObject>();
        snowballTexts = new ArrayList<JLabel>();
        answers = new ArrayList<JLabel>();
        snowballAnswers = new ArrayList<UNGraphicsObject>();
        trees = new ArrayList<UNGraphicsObject>();
        reindeers = new ArrayList<UNGraphicsObject>();

        snowballTexts.add(new JLabel(Integer.toString(1 + new Random().nextInt(4))));
        snowballTexts.add(new JLabel(Integer.toString(1 + new Random().nextInt(4))));
        snowballTexts.add(new JLabel(Integer.toString(1 + new Random().nextInt(4))));
        this.answer = Integer.parseInt(snowballTexts.get(0).getText())
                + Integer.parseInt(snowballTexts.get(1).getText())
                + Integer.parseInt(snowballTexts.get(2).getText());

        this.setQuestion("Tel de getallen van de 3 sneeuwballen op");
        this.addHelp("Jammer! Probeer het nog eens, daar komen ze weer");
        this.addHelp("Helaas! Hier komen ze nog een keer langs");
        this.setHelp("Daar komen ze aanrollen");
        this.setBackgroundImage(gameScreen.getBackgrounds().get(0));

        this.winning = false;
        this.questionDone = false;
        this.lastHelp = getHelp();
        this.reindeerIndex = 0;

        snowballs.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), -300, 250, gameScreen.getSprites().get(40), false, 32, 32));
        snowballs.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), -600, 250, gameScreen.getSprites().get(40), false, 32, 32));
        snowballs.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), -900, 250, gameScreen.getSprites().get(40), false, 32, 32));

        for (int i = 0; i < 55; i++)
        {
            trees.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), -20 + i * 15, 280 + new Random().nextInt(50), gameScreen.getSprites().get((new Random().nextInt(2) == 1) ? 41 : 42), false, 13 * 3, 29 * 3));
        }

        reindeers.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), -100, 310, gameScreen.getSprites().get(43), false, 17 * 2, 13 * 2));
        reindeers.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), -50, 330, gameScreen.getSprites().get(43), false, 17 * 2, 13 * 2));
        for (UNGraphicsObject reindeer : reindeers)
        {
            addObject(reindeer);
        }

        snowballAnswers.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), gameScreen.getWindow().getContentWidth() / 3 - 96, 250, gameScreen.getSprites().get(40), false, 96, 96));
        snowballAnswers.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), gameScreen.getWindow().getContentWidth() / 2 - 96, 250, gameScreen.getSprites().get(40), false, 96, 96));
        snowballAnswers.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), gameScreen.getWindow().getContentWidth() - gameScreen.getWindow().getContentWidth() / 3 - 96, 250, gameScreen.getSprites().get(40), false, 96, 96));

        snowballAnswers.get(0).setAngle(new Random().nextInt(360));
        snowballAnswers.get(1).setAngle(new Random().nextInt(360));
        snowballAnswers.get(2).setAngle(new Random().nextInt(360));

        if (new Random().nextInt(2) == 0) {
            answers.add(new JLabel(Integer.toString(answer)));
            answers.add(new JLabel(Integer.toString(answer - 4 + new Random().nextInt(8))));
            answers.add(new JLabel(Integer.toString(answer - 4 + new Random().nextInt(8))));
        } else if (new Random().nextInt(2) == 0) {
            answers.add(new JLabel(Integer.toString(answer - 4 + new Random().nextInt(8))));
            answers.add(new JLabel(Integer.toString(answer)));
            answers.add(new JLabel(Integer.toString(answer - 4 + new Random().nextInt(8))));
        } else {
            answers.add(new JLabel(Integer.toString(answer - 4 + new Random().nextInt(8))));
            answers.add(new JLabel(Integer.toString(answer - 4 + new Random().nextInt(8))));
            answers.add(new JLabel(Integer.toString(answer)));
        }

        this.color = new UNColor();

        for (UNGraphicsObject tree : trees)
        {
            tree.setXOffset(6);
            tree.setYOffset(28);
            addObject(tree);
        }

        for (UNGraphicsObject snowball : snowballs) {
            addObject(snowball);
        }

        for (JLabel snowballText : snowballTexts)
        {
            snowballText.setFont(new Font("Minecraftia", Font.PLAIN, 20));
            snowballText.setForeground(new Color(51, 51, 51));
            snowballText.setHorizontalAlignment(SwingConstants.CENTER);
            add(snowballText);
        }

        for (JLabel possibleAnswer : answers)
        {
            possibleAnswer.setFont(new Font("Minecraftia", Font.PLAIN, 20));
            possibleAnswer.setForeground(new Color(51, 51, 51));
            possibleAnswer.setHorizontalAlignment(SwingConstants.CENTER);
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
                        gameScreen.switchPanel(new Level6(gameScreen, true));
                    } else {
                        gameScreen.switchPanel(new UNLauncher(gameScreen));
                    }
                }
            }
        });

        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!questionDone)
                {
                    for (UNGraphicsObject snowball : snowballs)
                    {
                        snowball.setAngle(snowball.getAngle() + 2);
                        if (new Random().nextInt(10) == 0)
                        {
                            snowball.setWidth(snowball.getWidth() + 1);
                            snowball.setHeight(snowball.getWidth());
                            snowball.setXOffset(snowball.getImage().getWidth(null) / 2);
                            snowball.setY(290 + snowball.getHeight() / 128);
                            snowball.setYOffset(snowball.getImage().getHeight(null) / 2);
                        }
                        if (snowball.getX() < gameScreen.getWindow().getContentWidth() || snowballs.indexOf(snowball) < 2)
                        {
                            snowball.setX(snowball.getX() + 1);
                            for (UNGraphicsObject tree : trees)
                            {
                                if (snowball.getHitbox().intersects(tree.getHitbox()))
                                {
                                    if (tree.getAngle() < 85 + new Random().nextInt(10))
                                    {
                                        tree.setAngle(tree.getAngle() + 2);
                                    }
                                }
                            }
                        }
                        else
                        {
                            questionDone = true;
                            setHelp("Klik op het antwoord");
                            lastHelp = getHelp();

                            addObject(snowballAnswers.get(0));
                            addObject(snowballAnswers.get(1));
                            addObject(snowballAnswers.get(2));

                            for (JLabel possibleAnswer : answers)
                            {
                                add(possibleAnswer);
                                possibleAnswer.setBounds(snowballAnswers.get(answers.indexOf(possibleAnswer)).getX(), snowballAnswers.get(answers.indexOf(possibleAnswer)).getY(), snowballAnswers.get(answers.indexOf(possibleAnswer)).getWidth(), snowballAnswers.get(answers.indexOf(possibleAnswer)).getHeight());
                            }
                        }

                        snowballTexts.get(snowballs.indexOf(snowball)).setBounds(snowball.getX(), snowball.getY(), snowball.getWidth(), 100);
                    }
                    if (reindeerIndex < 2)
                    {
                        reindeerIndex += 0.1;
                    }
                    else
                    {
                        reindeerIndex = 0;
                    }
                    for (UNGraphicsObject reindeer : reindeers)
                    {
                        reindeer.setImage(gameScreen.getSprites().get(43 + (int) Math.round(reindeerIndex)));
                        reindeer.setX(reindeer.getX() + 1);
                    }
                }
                else
                {
                    for (UNGraphicsObject snowball : snowballAnswers)
                    {
                        if (snowball.isMouseClick() && !winning && isHelperDoneTalking())
                        {
                            snowball.setMouseClick(false);
                            if (Integer.valueOf(answers.get(snowballAnswers.indexOf(snowball)).getText()) == answer)
                            {
                                add(button);
                                getHelper().setState(3);
                                setHelp("Mooi hoor! Jij kan goed rekenen");
                                button.setText("Door");
                                winning = true;
                            }
                            else
                            {
                                addMistake();
                                if (getMistakes() < 3)
                                {
                                    getHelper().setState(4);
                                    while (lastHelp == getHelp())
                                    {
                                        setHelp(getHelpList().get(new Random().nextInt(getHelpList().size())));
                                    }
                                    lastHelp = getHelp();
                                    questionDone = false;

                                    removeObject(snowballAnswers.get(0));
                                    removeObject(snowballAnswers.get(1));
                                    removeObject(snowballAnswers.get(2));

                                    for (JLabel possibleAnswer : answers)
                                    {
                                        remove(possibleAnswer);
                                    }
                                    snowballs.get(0).setX(-800);
                                    snowballs.get(1).setX(-1100);
                                    snowballs.get(2).setX(-1400);
                                }
                                else
                                {
                                    getHelper().setState(4);
                                    setHelp("Jammer, het was " + answer + ". Want " + snowballTexts.get(0) + " plus " +
                                            snowballTexts.get(1)  + " plus " + snowballTexts.get(2) + " is " + answer
                                    );
                                    removeObject(snowballAnswers.get(0));
                                    removeObject(snowballAnswers.get(1));
                                    removeObject(snowballAnswers.get(2));

                                    for (JLabel possibleAnswer : answers)
                                    {
                                        remove(possibleAnswer);
                                    }

                                    button.setText("Door");
                                }
                            }
                        }
                    }
                }
            }
        });

        timer.start();
    }
}
