package eu.theunitry.fabula.levels;

import eu.theunitry.fabula.UNGameEngine.graphics.UNColor;
import eu.theunitry.fabula.UNGameEngine.graphics.UNGameScreen;
import eu.theunitry.fabula.UNGameEngine.graphics.UNGraphicsObject;
import eu.theunitry.fabula.UNGameEngine.graphics.UNLevel;
import eu.theunitry.fabula.UNGameEngine.launcher.UNLauncher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class Level8 extends UNLevel
{
    private Timer timer;
    private JButton button;
    private UNColor color;
    private String lastHelp;
    private int answer, firstInt, secondInt;
    private boolean questionDone, winning;
    private ArrayList<JLabel> nuggetTexts, answers;
    private ArrayList<UNGraphicsObject> nuggets, nuggetAnswers;

    /**
     * Level 8
     * @param gameScreen
     * @param hudEnabled
     */

    public Level8(UNGameScreen gameScreen, boolean hudEnabled)
    {
        super(gameScreen, hudEnabled);

        this.nuggets = new ArrayList<UNGraphicsObject>();
        this.nuggetAnswers = new ArrayList<UNGraphicsObject>();
        this.nuggetTexts = new ArrayList<JLabel>();
        this.answers = new ArrayList<JLabel>();
        this.firstInt = 1 + new Random().nextInt(5);
        this.secondInt = 1 + new Random().nextInt(5);

        this.setQuestion("Hoeveel is " + firstInt + " * " + secondInt + "?");
        this.addHelp("Dit is niet goed! Sleep het houweel naar het goede antwoord.");
        this.addHelp("Helaas, probeer het opnieuw!");
        this.addHelp("Dit is niet juist.");
        this.setBackgroundImage(gameScreen.getBackgrounds().get(0));

        this.winning = false;
        this.questionDone = false;
        this.lastHelp = getHelp();
        this.color = new UNColor();


        this.setLayout(null);
        this.button = new JButton("Vertrek");
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

        this.nuggets.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 350, 350, gameScreen.getSprites().get(43), true, 64, 64));

        for(UNGraphicsObject nugget : nuggets)
        {
            addObject(nugget);
            nugget.setClickable(false);
        }

        this.nuggetTexts.add(new JLabel(Integer.toString(1 + new Random().nextInt(4))));

        this.nuggetAnswers.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), gameScreen.getWindow().getContentWidth() / 3 - 96, 250, gameScreen.getSprites().get(43), false, 96, 96));

        if (new Random().nextInt(2) == 0)
        {
            answers.add(new JLabel(Integer.toString(answer)));
            answers.add(new JLabel(Integer.toString(answer - 4 + new Random().nextInt(8))));
            answers.add(new JLabel(Integer.toString(answer - 4 + new Random().nextInt(8))));
        }
        else if (new Random().nextInt(2) == 0)
        {
            answers.add(new JLabel(Integer.toString(answer - 4 + new Random().nextInt(8))));
            answers.add(new JLabel(Integer.toString(answer)));
            answers.add(new JLabel(Integer.toString(answer - 4 + new Random().nextInt(8))));
        }
        else
        {
            answers.add(new JLabel(Integer.toString(answer - 4 + new Random().nextInt(8))));
            answers.add(new JLabel(Integer.toString(answer - 4 + new Random().nextInt(8))));
            answers.add(new JLabel(Integer.toString(answer)));
        }

        this.answer = Integer.parseInt(nuggetTexts.get(0).getText());

        for (JLabel nuggetText : nuggetTexts)
        {
            nuggetText.setFont(new Font("Minecraftia", Font.PLAIN, 20));
            nuggetText.setForeground(new Color(51, 51, 51));
            nuggetText.setHorizontalAlignment(SwingConstants.CENTER);
            add(nuggetText);
        }

        for (JLabel possibleAnswer : answers)
        {
            possibleAnswer.setFont(new Font("Minecraftia", Font.PLAIN, 20));
            possibleAnswer.setForeground(new Color(51, 51, 51));
            possibleAnswer.setHorizontalAlignment(SwingConstants.CENTER);
        }

        this.button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(button.getText() == "Door")
                {
                    gameScreen.getSounds().get(0).stop();
                    if(gameScreen.getLevel() < gameScreen.getLevelMax())
                    {
                        if(winning)
                        {
                            gameScreen.addLevel();
                        }
                        gameScreen.switchPanel(new Level8(gameScreen, true));
                    }
                    else
                    {
                        gameScreen.switchPanel(new UNLauncher(gameScreen));
                    }
                }
            }
        });

        this.getPanel().add(button);

        timer = new Timer(1, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(!questionDone)
                {
                    for(UNGraphicsObject nugget : nuggets) {
                        questionDone = true;
                        setHelp("Sleep het houweel naar de juist goudklomp.");
                        lastHelp = getHelp();

                        addObject(nuggetAnswers.get(0));
                        addObject(nuggetAnswers.get(0));

                        for (JLabel possibleAnswer : answers) {
                            add(possibleAnswer);
                            possibleAnswer.setBounds(nuggetAnswers.get(answers.indexOf(possibleAnswer)).getX(), nuggetAnswers.get(answers.indexOf(possibleAnswer)).getY(), nuggetAnswers.get(answers.indexOf(possibleAnswer)).getWidth(), nuggetAnswers.get(answers.indexOf(possibleAnswer)).getHeight());
                        }
                        nuggetTexts.get(nuggets.indexOf(nugget)).setBounds(nugget.getX(), 290 - nugget.getHeight() / 128, nugget.getWidth(), 100);
                    }
                }
                else
                {
                    for(UNGraphicsObject nugget : nuggetAnswers)
                    {
                        if(nugget.isMouseClick() && !winning && isHelperDoneTalking())
                        {
                            nugget.setMouseClick(false);
                            if(Integer.valueOf(answers.get(nuggetAnswers.indexOf(nugget)).getText()) == answer)
                            {
                                add(button);
                                getHelper().setState(3);
                                setHelp("Dat is helemaal goed!");
                                button.setText("Door");
                                winning = true;
                            }
                            else
                            {
                                addMistake();
                                if(getMistakes() < 3)
                                {
                                    getHelper().setState(4);
                                    while(lastHelp == getHelp())
                                    {
                                        setHelp(getHelpList().get(new Random().nextInt(getHelpList().size())));
                                    }
                                    lastHelp = getHelp();
                                    questionDone = false;

                                    removeObject(nuggetAnswers.get(0));

                                    for(JLabel possibleAnswer : answers)
                                    {
                                        remove(possibleAnswer);
                                    }

                                    nuggets.get(0).setX(-800);

                                    for(UNGraphicsObject nug : nuggets)
                                    {
                                        nug.setWidth(8);
                                        nug.setHeight(8);
                                    }
                                }
                                else
                                {
                                    getHelper().setState(4);
                                    setHelp("FOUT KUT JONG");
                                    removeObject(nuggetAnswers.get(0));

                                    for(JLabel possibleAnswer : answers)
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