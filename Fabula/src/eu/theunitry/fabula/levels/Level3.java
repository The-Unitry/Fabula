package eu.theunitry.fabula.levels;


import eu.theunitry.fabula.UNGameEngine.graphics.UNGameScreen;
import eu.theunitry.fabula.UNGameEngine.graphics.UNColor;
import eu.theunitry.fabula.UNGameEngine.graphics.UNGraphicsLevel;
import eu.theunitry.fabula.UNGameEngine.graphics.UNGraphicsObject;
import eu.theunitry.fabula.UNGameEngine.launcher.UNLauncher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class Level3 extends UNGraphicsLevel
{
    private Timer timer;
    private ArrayList<UNGraphicsObject> ufos, ufoAnswers, stars, rockets;
    private UNGraphicsObject moon;
    private JButton button;
    private boolean questionDone, winning, rotationDone;
    private int answer, fakeAnswer1, fakeAnswer2, touch, g1, g2, g3, randomUnity;
    private UNColor color;
    private String lastHelp;
    private ArrayList<JLabel> ufosText, answers;
    private double rocketIndex;

    /**
     * Level 3
     * @param gameScreen
     * @param hudEnabled
     */
    public Level3(UNGameScreen gameScreen, boolean hudEnabled) {
        super(gameScreen, hudEnabled);

        ufos = new ArrayList<UNGraphicsObject>();
        ufosText = new ArrayList<JLabel>();
        answers = new ArrayList<JLabel>();
        ufoAnswers = new ArrayList<UNGraphicsObject>();
        stars = new ArrayList<UNGraphicsObject>();
        rockets = new ArrayList<UNGraphicsObject>();

        gameScreen.getMusic().get(1).play(true);
        gameScreen.getMusic().get(1).setVolume(0.1);

        this.randomUnity = new Random().nextInt(4);
        if (this.randomUnity == 1){
            this.moon = new UNGraphicsObject(gameScreen.getWindow().getFrame(), gameScreen.getWindow().getContentWidth() / 2 - 100, 50, gameScreen.getSprites().get(46), false, 96, 96);
        }
        else{
            this.moon = new UNGraphicsObject(gameScreen.getWindow().getFrame(), gameScreen.getWindow().getContentWidth() / 2 - 100, 50, gameScreen.getSprites().get(45), false, 96, 96);
        }

        this.addObject(moon);

        ufosText.add(new JLabel(Integer.toString(1 + new Random().nextInt(4))));
        ufosText.add(new JLabel(Integer.toString(1 + new Random().nextInt(4))));
        ufosText.add(new JLabel(Integer.toString(1 + new Random().nextInt(4))));

        this.setBackgroundImage(gameScreen.getBackgrounds().get(2));

        this.winning = false;
        this.questionDone = false;
        this.lastHelp = getHelp();
        this.rocketIndex = 0;

        ufos.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), -300, 250, gameScreen.getSprites().get(47), false, 32, 32));
        ufos.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), -600, 250, gameScreen.getSprites().get(48), false, 32, 32));
        ufos.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), -900, 250, gameScreen.getSprites().get(49), false, 32, 32));

        for (int i = 0; i < 1; i++)
        {
            stars.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), new Random().nextInt(gameScreen.getWindow().getContentWidth()), 0, gameScreen.getSprites().get(50), false, 7, 7));
        }


        ufoAnswers.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), gameScreen.getWindow().getContentWidth() / 3 - 246, 330, gameScreen.getSprites().get(47), false, 96, 96));
        ufoAnswers.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), gameScreen.getWindow().getContentWidth() / 2 - 276, 226, gameScreen.getSprites().get(48), false, 96, 96));
        ufoAnswers.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), gameScreen.getWindow().getContentWidth() - gameScreen.getWindow().getContentWidth() / 3 - 306, 330, gameScreen.getSprites().get(49), false, 96, 96));

        this.g1 = new Random().nextInt(4) + 1;
        this.g2 = new Random().nextInt(4) + 1;
        this.g3 = new Random().nextInt(4) + 1;
        this.answer = this.g1 + this.g2 + this.g3;
        this.fakeAnswer1 = this.answer + new Random().nextInt(4) + 1;
        this.fakeAnswer2 = this.answer - new Random().nextInt(4) - 1;

        int randInt = new Random().nextInt(2) + 1;
        if (randInt == 1){
            this.answers.add(new JLabel(Integer.toString(answer)));
            this.answers.add(new JLabel(Integer.toString(fakeAnswer1)));
            this.answers.add(new JLabel(Integer.toString(fakeAnswer2)));
        }
        else if (randInt == 2){
            this.answers.add(new JLabel(Integer.toString(fakeAnswer1)));
            this.answers.add(new JLabel(Integer.toString(answer)));
            this.answers.add(new JLabel(Integer.toString(fakeAnswer2)));
        }
        else if (randInt == 3){
            this.answers.add(new JLabel(Integer.toString(fakeAnswer2)));
            this.answers.add(new JLabel(Integer.toString(fakeAnswer1)));
            this.answers.add(new JLabel(Integer.toString(answer)));
        }

        this.setQuestion("Wat is " + this.g1 + " plus " + this.g2  + " plus " + this.g3 + " ?");
        this.addHelp("Jammer! Probeer het nog eens");
        this.addHelp("Helaas! Probeer het nog een keer!");
        this.setHelp("Klik op de UFO met het goede antwoord.");

        this.color = new UNColor();

        for (UNGraphicsObject star : stars)
        {
            addObject(star);
        }

        for (UNGraphicsObject ufo : ufos) {
            addObject(ufo);
        }

        for (JLabel ufoText : ufosText)
        {
            ufoText.setFont(new Font("Minecraftia", Font.PLAIN, 20));
            ufoText.setForeground(new Color(51, 51, 51));
            ufoText.setHorizontalAlignment(SwingConstants.CENTER);
            add(ufoText);
        }

        for (JLabel possibleAnswer : answers)
        {
            possibleAnswer.setFont(new Font("Minecraftia", Font.PLAIN, 20));
            possibleAnswer.setForeground(new Color(51, 51, 51));
            possibleAnswer.setHorizontalAlignment(SwingConstants.CENTER);
        }

        rockets.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 520, 300, gameScreen.getSprites().get(41), false, 120 , 120));

        for (UNGraphicsObject rocket : rockets)
        {
            addObject(rocket);
        }

        this.button = new JButton("Nakijken");

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

        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (button.getText() == "Doorgaan")
                {
                    gameScreen.getSounds().get(0).stop();
                    if (gameScreen.getLevel() < gameScreen.getLevelMax())
                    {
                        if (winning)
                        {
                            gameScreen.addLevel();
                        }
                        gameScreen.switchPanel(new Level3(gameScreen, true));
                    } else
                    {
                        gameScreen.switchPanel(new UNLauncher(gameScreen));
                    }
                }
            }
        });

        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (UNGraphicsObject star : stars)
                {
                    star.setY(star.getY() + 2);
                    star.setX(star.getX() - 2);

                    if (star.getY() > gameScreen.getWindow().getContentHeight())
                    {
                        star.setY(star.getY() - gameScreen.getWindow().getContentHeight());
                    }
                    if (star.getX() < 0)
                    {
                        star.setX(star.getX() + gameScreen.getWindow().getContentWidth());
                    }
                }

                if (!questionDone)
                {
                    for (UNGraphicsObject ufo : ufos)
                    {

                        if (new Random().nextInt(10) == 0)
                        {
                            ufo.setWidth(ufo.getWidth() + 1);
                            ufo.setHeight(ufo.getWidth());
                            ufo.setXOffset(ufo.getImage().getWidth(null) / 2);
                            ufo.setY(290 - ufo.getHeight() / 256);
                            ufo.setYOffset(ufo.getImage().getHeight(null) / 2);
                        }
                        else
                        {
                            gameScreen.getMusic().get(1).stop();
                            gameScreen.getMusic().get(0).play(true);
                            questionDone = true;
                            lastHelp = getHelp();

                            addObject(ufoAnswers.get(0));
                            addObject(ufoAnswers.get(1));
                            addObject(ufoAnswers.get(2));

                            for (JLabel possibleAnswer : answers)
                            {
                                add(possibleAnswer);
                                possibleAnswer.setForeground(Color.white);
                                possibleAnswer.setBounds(ufoAnswers.get(answers.indexOf(possibleAnswer)).getX(), ufoAnswers.get(answers.indexOf(possibleAnswer)).getY() - 7, ufoAnswers.get(answers.indexOf(possibleAnswer)).getWidth(), ufoAnswers.get(answers.indexOf(possibleAnswer)).getHeight());
                            }
                        }

                        ufosText.get(ufos.indexOf(ufo)).setBounds(ufo.getX(), 290 - ufo.getHeight() / 128, ufo.getWidth(), 100);
                    }

                }
                else
                {
                    for (UNGraphicsObject ufoAnswer : ufoAnswers)
                    {
                        if (ufoAnswer.isMouseClick() && !winning && isHelperDoneTalking())
                        {
                            ufoAnswer.setMouseClick(false);
                            if (Integer.valueOf(answers.get(ufoAnswers.indexOf(ufoAnswer)).getText()) == answer)
                            {
                                add(button);
                                getHelper().setState(3);
                                setHelp("Mooi hoor! Jij kan goed rekenen");
                                button.setText("Doorgaan");
                                winning = true;
                            }
                            else
                            {
                                addMistake();
                                if (getMistakes() < 2)
                                {
                                    getHelper().setState(4);
                                    while (lastHelp == getHelp())
                                    {
                                        setHelp(getHelpList().get(new Random().nextInt(getHelpList().size())));
                                    }
                                    lastHelp = getHelp();
                                    questionDone = false;

                                    removeObject(ufoAnswers.get(0));
                                    removeObject(ufoAnswers.get(1));
                                    removeObject(ufoAnswers.get(2));

                                    for (JLabel possibleAnswer : answers)
                                    {
                                        remove(possibleAnswer);
                                    }
                                    gameScreen.getMusic().get(1).play(true);
                                    ufos.get(0).setX(-800);
                                    ufos.get(1).setX(-1100);
                                    ufos.get(2).setX(-1400);
                                    for (UNGraphicsObject snow : ufos) {
                                        snow.setWidth(8);
                                        snow.setHeight(8);
                                    }
                                }
                                else
                                {
                                    getHelper().setState(4);
                                    setHelp("Jammer, het was " + answer + ". Want " + g1 + " plus " +
                                                    g2  + " plus " + g3 + " is " + answer
                                    );
                                    removeObject(ufoAnswers.get(0));
                                    removeObject(ufoAnswers.get(1));
                                    removeObject(ufoAnswers.get(2));

                                    add(button);
                                    button.setText("Doorgaan");
                                }
                            }
                        }
                    }
                }
                if (rocketIndex < 2)
                {
                    rocketIndex += 0.1;
                }
                else {
                    rocketIndex = 0;
                }

                if (winning == true){

                    for (UNGraphicsObject ufo : ufoAnswers){
                        ufo.setAngle(ufo.getAngle() + 1);
                        ufo.setX(ufo.getX() - 1);
                    }

                    for (JLabel possibleAnswer : answers)
                    {
                        remove(possibleAnswer);
                    }

                    for (UNGraphicsObject rocket : rockets) {
                        if (rotationDone = true) {
                            rocket.setImage(gameScreen.getSprites().get(42 + (int) Math.round(rocketIndex)));
                        }

                        if (rocket.getWidth() != 20 && rocket.getWidth() != 20) {
                            rocket.setWidth(rocket.getWidth() - 1);
                            rocket.setHeight(rocket.getHeight() - 1);
                        }

                        if (rocket.getHitbox().intersects(moon.getHitbox())) {
                            if (rocket.getAngle() <= 190) {
                                rocket.setAngle(rocket.getAngle() + 1);
                            }
                        } else {
                            rocket.setX(rocket.getX() - 1);
                            rocket.setY(rocket.getY() - 1);
                        }

                        if (rocket.getAngle() >= 190) {
                            if (rocket.getY() >= 133) {
                                rocket.setX(rocket.getX() - 1);
                                rocket.setY(rocket.getY() - 1);
                            } else {
                                rotationDone = false;
                                rocket.setImage(gameScreen.getSprites().get(41));
                            }
                        }
                    }
                }
            }
        });
        timer.start();
    }
}
