package eu.theunitry.fabula.levels;

import eu.theunitry.fabula.UNGameEngine.graphics.UNColor;
import eu.theunitry.fabula.UNGameEngine.graphics.UNGameScreen;
import eu.theunitry.fabula.UNGameEngine.graphics.UNLevel;
import eu.theunitry.fabula.UNGameEngine.graphics.UNGraphicsObject;
import eu.theunitry.fabula.UNGameEngine.launcher.UNLauncher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

/**
 * Level 8
 * Jelmer Portegijs
 */
public class Level8 extends UNLevel
{
    private Timer timer;
    private JButton button;
    private UNColor color;
    private String lastHelp;
    private int answer, firstInt, secondInt, fakeAnswer1, fakeAnswer2, fakeAnswer3, fakeAnswer4, newRand;
    private boolean questionDone, buttonClicked;
    private ArrayList<JLabel> nuggetTexts, answers;
    private ArrayList<UNGraphicsObject> nuggets, nuggetAnswers, pickaxes;

    /**
     * Level 8
     * @param gameScreen
     * @param hudEnabled
     */

    public Level8(UNGameScreen gameScreen, boolean hudEnabled)
    {
        super(gameScreen, hudEnabled);

        this.nuggets = new ArrayList<>();
        this.nuggetAnswers = new ArrayList<>();
        this.nuggetTexts = new ArrayList<>();
        this.answers = new ArrayList<>();
        this.firstInt = 1 + new Random().nextInt(9);
        this.secondInt = 1 + new Random().nextInt(4);
        this.pickaxes = new ArrayList<>();
        this.newRand = new Random().nextInt(4) + 1;

        this.setQuestion("Hoeveel is " + firstInt + " * " + secondInt + "?");
        this.addHelp("Dit is niet goed! Sleep het houweel naar het goede antwoord.");
        this.addHelp("Helaas, probeer het opnieuw!");
        this.addHelp("Dit is niet juist. Probeer " + firstInt + " keer het getal" + secondInt + "op te tellen.");
        this.setBackgroundImage(gameScreen.unResourceLoader.backgrounds.get("mine"));

        this.questionDone = false;
        this.buttonClicked = false;
        this.lastHelp = getHelp();
        this.color = new UNColor();

        this.setLayout(null);
        this.button = new JButton("Nakijken");
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

        this.nuggets.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 20, 368, gameScreen.unResourceLoader.sprites.get("2:8:1"), true, 96, 96));
        this.nuggets.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 90, 310, gameScreen.unResourceLoader.sprites.get("2:8:1"), true, 48, 48));
        this.nuggets.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 110, 240, gameScreen.unResourceLoader.sprites.get("2:8:1"), true, 64, 64));
        this.nuggets.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 220, 240, gameScreen.unResourceLoader.sprites.get("2:8:1"), true, 48, 48));
        this.nuggets.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 245, 380, gameScreen.unResourceLoader.sprites.get("2:8:1"), true, 84, 84));

        for(UNGraphicsObject nugget : nuggets)
        {
            this.addObject(nugget);
            nugget.setClickable(false);
        }

        this.nuggetAnswers.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), gameScreen.getWindow().getContentWidth() / 3 - 235, 368, gameScreen.unResourceLoader.sprites.get("2:8:1"), false, 96, 96));
        this.nuggetAnswers.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), gameScreen.getWindow().getContentWidth() / 3 - 165, 310, gameScreen.unResourceLoader.sprites.get("2:8:1"), false, 48, 48));
        this.nuggetAnswers.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), gameScreen.getWindow().getContentWidth() / 3 - 148, 240, gameScreen.unResourceLoader.sprites.get("2:8:1"), false, 64, 64));
        this.nuggetAnswers.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), gameScreen.getWindow().getContentWidth() / 3 - 38, 240, gameScreen.unResourceLoader.sprites.get("2:8:1"), false, 48, 48));
        this.nuggetAnswers.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), gameScreen.getWindow().getContentWidth() / 3 - 12, 380, gameScreen.unResourceLoader.sprites.get("2:8:1"), false, 84, 84));

        this.nuggetTexts.add(new JLabel(Integer.toString(1 + new Random().nextInt(4))));
        this.nuggetTexts.add(new JLabel(Integer.toString(1 + new Random().nextInt(4))));
        this.nuggetTexts.add(new JLabel(Integer.toString(1 + new Random().nextInt(4))));
        this.nuggetTexts.add(new JLabel(Integer.toString(1 + new Random().nextInt(4))));
        this.nuggetTexts.add(new JLabel(Integer.toString(1 + new Random().nextInt(4))));
        this.answer = Integer.parseInt(this.nuggetTexts.get(0).getText())
                        + Integer.parseInt(this.nuggetTexts.get(1).getText())
                        + Integer.parseInt(this.nuggetTexts.get(2).getText())
                        + Integer.parseInt(this.nuggetTexts.get(3).getText())
                        + Integer.parseInt(this.nuggetTexts.get(4).getText());

        this.answer = this.firstInt * this.secondInt;
        this.fakeAnswer1 = this.answer + new Random().nextInt(4) + 1;
        this.fakeAnswer2 = this.answer - new Random().nextInt(5) + 1;
        this.fakeAnswer3 = this.answer - new Random().nextInt(1) + 1;
        this.fakeAnswer4 = this.answer - new Random().nextInt(3) + 1;

        if (newRand == 1)
        {
            this.answers.add(new JLabel(Integer.toString(answer)));
            this.answers.add(new JLabel(Integer.toString(fakeAnswer3)));
            this.answers.add(new JLabel(Integer.toString(fakeAnswer2)));
            this.answers.add(new JLabel(Integer.toString(fakeAnswer1)));
            this.answers.add(new JLabel(Integer.toString(fakeAnswer4)));
        }
        else if (newRand == 2)
        {
            this.answers.add(new JLabel(Integer.toString(fakeAnswer4)));
            this.answers.add(new JLabel(Integer.toString(fakeAnswer2)));
            this.answers.add(new JLabel(Integer.toString(answer)));
            this.answers.add(new JLabel(Integer.toString(fakeAnswer3)));
            this.answers.add(new JLabel(Integer.toString(fakeAnswer1)));
        }
        else if (newRand == 3)
        {
            this.answers.add(new JLabel(Integer.toString(fakeAnswer2)));
            this.answers.add(new JLabel(Integer.toString(fakeAnswer3)));
            this.answers.add(new JLabel(Integer.toString(fakeAnswer1)));
            this.answers.add(new JLabel(Integer.toString(fakeAnswer4)));
            this.answers.add(new JLabel(Integer.toString(answer)));
        }
        else if (newRand == 4)
        {
            this.answers.add(new JLabel(Integer.toString(fakeAnswer4)));
            this.answers.add(new JLabel(Integer.toString(fakeAnswer3)));
            this.answers.add(new JLabel(Integer.toString(answer)));
            this.answers.add(new JLabel(Integer.toString(fakeAnswer1)));
            this.answers.add(new JLabel(Integer.toString(fakeAnswer2)));
        }
        else
        {
            this.answers.add(new JLabel(Integer.toString(fakeAnswer2)));
            this.answers.add(new JLabel(Integer.toString(fakeAnswer1)));
            this.answers.add(new JLabel(Integer.toString(answer)));
            this.answers.add(new JLabel(Integer.toString(fakeAnswer4)));
            this.answers.add(new JLabel(Integer.toString(fakeAnswer3)));
        }

        this.pickaxes.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 250, 300, gameScreen.unResourceLoader.sprites.get("2:8:2"), true, 56, 56));

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

        this.button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                buttonClicked = true;
                if(button.getText() == "Doorgaan")
                {
                   levelDone(8);
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
                    questionDone = true;
                    setHelp("Sleep het houweel naar het juiste goudklomp.");
                    lastHelp = getHelp();

                    addObject(nuggetAnswers.get(0));
                    addObject(nuggetAnswers.get(1));
                    addObject(nuggetAnswers.get(2));
                    addObject(nuggetAnswers.get(3));
                    addObject(nuggetAnswers.get(4));

                    for (JLabel possibleAnswer : answers)
                    {
                        add(possibleAnswer);
                        possibleAnswer.setBounds(
                            nuggetAnswers.get(answers.indexOf(possibleAnswer)).getX(),
                            nuggetAnswers.get(answers.indexOf(possibleAnswer)).getY(),
                            nuggetAnswers.get(answers.indexOf(possibleAnswer)).getWidth(),
                            nuggetAnswers.get(answers.indexOf(possibleAnswer)).getHeight()
                        );
                    }

                    for(UNGraphicsObject pickaxe : pickaxes)
                    {
                        addObject(pickaxe);
                    }
                }
                else
                {
                    if(buttonClicked) {
                        for(UNGraphicsObject nugget : nuggetAnswers)
                        {
                            for(UNGraphicsObject pickaxe : pickaxes)
                            {
                                if(!hasPlayerWon() && isHelperDoneTalking())
                                {
                                    if(pickaxe.getHitbox().intersects(nugget.getHitbox()) && buttonClicked)
                                    {
                                        if (Integer.valueOf(answers.get(nuggetAnswers.indexOf(nugget)).getText()) == answer) {
                                            add(button);
                                            getHelper().setState(3);
                                            setHelp("Goed gedaan! Dit is het juiste antwoord!");
                                            button.setText("Doorgaan");
                                            buttonClicked = false;
                                            setPlayerHasWon(true);
                                            pickaxe.setClickable(false);
                                        }
                                        else
                                        {
                                            button.setText("Doorgaan");
                                            buttonClicked = false;
                                            pickaxe.setClickable(false);
                                            getHelper().setState(4);
                                            setHelp("Dit is niet juist, probeer het nog eens.");
                                        }
                                    }
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