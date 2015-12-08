package eu.theunitry.fabula.levels;


import eu.theunitry.fabula.UNGameEngine.graphics.UNGameScreen;
import eu.theunitry.fabula.UNGameEngine.graphics.UNGraphicsLevel;
import eu.theunitry.fabula.UNGameEngine.graphics.UNGraphicsObject;
import eu.theunitry.fabula.UNGameEngine.launcher.UNLauncher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class Level6 extends UNGraphicsLevel
{
    private Timer timer;
    private ArrayList<UNGraphicsObject> snowballs, snowballAnswers, trees, reindeers, snow;
    private double imageIndex;
    private JButton button;
    private boolean questionDone, winning;
    private int answer, touch;
    private String lastHelp;
    private ArrayList<JLabel> snowballTexts, answers;
    private double reindeerIndex;
    private int spriteSnowball = 45, spriteTree = 41, spriteReindeer = 54, spriteSnowflake = 57;

    /**
     * Level 6
     * @param gameScreen
     * @param hudEnabled
     */
    public Level6(UNGameScreen gameScreen, boolean hudEnabled) {
        super(gameScreen, hudEnabled);

        this.setSnowballs(new ArrayList<UNGraphicsObject>());
        this.setSnowballTexts(new ArrayList<JLabel>());
        this.setAnswers(new ArrayList<JLabel>());
        this.setSnowballAnswers(new ArrayList<UNGraphicsObject>());
        this.setTrees(new ArrayList<UNGraphicsObject>());
        this.setReindeers(new ArrayList<UNGraphicsObject>());
        this.setSnow(new ArrayList<UNGraphicsObject>());

        this.getGameScreen().getMusic().get(2).play(true);
        this.getGameScreen().getMusic().get(2).setVolume(0.1);

        this.getSnowballTexts().add(new JLabel(Integer.toString(1 + new Random().nextInt(4))));
        this.getSnowballTexts().add(new JLabel(Integer.toString(1 + new Random().nextInt(4))));
        this.getSnowballTexts().add(new JLabel(Integer.toString(1 + new Random().nextInt(4))));
        this.setAnswer(Integer.parseInt(this.getSnowballTexts().get(0).getText())
                + Integer.parseInt(this.getSnowballTexts().get(1).getText())
                + Integer.parseInt(this.getSnowballTexts().get(2).getText()));

        this.setQuestion("Tel de getallen van de 3 sneeuwballen op");
        this.addHelp("Jammer! Probeer het nog eens, daar komen ze weer");
        this.addHelp("Helaas! Hier komen ze nog een keer langs");
        this.setHelp("Daar komen ze aanrollen");
        this.setBackgroundImage(this.getGameScreen().getBackgrounds().get(1));

        this.setWinning(false);
        this.setQuestionDone(false);
        this.setLastHelp(getHelp());
        this.setReindeerIndex(0);

        this.getSnowballs().add(new UNGraphicsObject(this.getGameScreen().getWindow().getFrame(), -300, 250, this.getGameScreen().getSprites().get(spriteSnowball), false, 32, 32));
        this.getSnowballs().add(new UNGraphicsObject(this.getGameScreen().getWindow().getFrame(), -600, 250, this.getGameScreen().getSprites().get(spriteSnowball), false, 32, 32));
        this.getSnowballs().add(new UNGraphicsObject(this.getGameScreen().getWindow().getFrame(), -900, 250, this.getGameScreen().getSprites().get(spriteSnowball), false, 32, 32));

        for (int i = 0; i < 55; i++)
        {
            this.getTrees().add(new UNGraphicsObject(this.getGameScreen().getWindow().getFrame(), -20 + i * 15, 280 + new Random().nextInt(50), this.getGameScreen().getSprites().get((new Random().nextInt(2) == 1) ? spriteTree : (spriteTree + 1)), false, 13 * 3, 29 * 3));
        }

        for (int i = 0; i < 100; i++)
        {
            this.getSnow().add(new UNGraphicsObject(this.getGameScreen().getWindow().getFrame(), new Random().nextInt(this.getGameScreen().getWindow().getContentWidth()), new Random().nextInt(this.getGameScreen().getWindow().getContentHeight()), this.getGameScreen().getSprites().get(spriteSnowflake), false, 5, 5));
        }

        this.getReindeers().add(new UNGraphicsObject(this.getGameScreen().getWindow().getFrame(), -100, 310, this.getGameScreen().getSprites().get(spriteReindeer), false, 17 * 2, 13 * 2));
        this.getReindeers().add(new UNGraphicsObject(this.getGameScreen().getWindow().getFrame(), -50, 330, this.getGameScreen().getSprites().get(spriteReindeer), false, 17 * 2, 13 * 2));

        for (UNGraphicsObject reindeer : this.getReindeers())
        {
            this.addObject(reindeer);
        }

        this.getSnowballAnswers().add(new UNGraphicsObject(this.getGameScreen().getWindow().getFrame(), this.getGameScreen().getWindow().getContentWidth() / 3 - 96, 250, this.getGameScreen().getSprites().get(spriteSnowball), false, 96, 96));
        this.getSnowballAnswers().add(new UNGraphicsObject(this.getGameScreen().getWindow().getFrame(), this.getGameScreen().getWindow().getContentWidth() / 2 - 96, 250, this.getGameScreen().getSprites().get(spriteSnowball), false, 96, 96));
        this.getSnowballAnswers().add(new UNGraphicsObject(this.getGameScreen().getWindow().getFrame(), this.getGameScreen().getWindow().getContentWidth() - this.getGameScreen().getWindow().getContentWidth() / 3 - 96, 250, this.getGameScreen().getSprites().get(spriteSnowball), false, 96, 96));

        this.getSnowballAnswers().get(0).setAngle(new Random().nextInt(360));
        this.getSnowballAnswers().get(1).setAngle(new Random().nextInt(360));
        this.getSnowballAnswers().get(2).setAngle(new Random().nextInt(360));

        if (new Random().nextInt(2) == 0) {
            this.getAnswers().add(new JLabel(Integer.toString(this.getAnswer())));
            this.getAnswers().add(new JLabel(Integer.toString(this.getAnswer() - 4 + new Random().nextInt(8))));
            this.getAnswers().add(new JLabel(Integer.toString(this.getAnswer() - 4 + new Random().nextInt(8))));
        } else if (new Random().nextInt(2) == 0) {
            this.getAnswers().add(new JLabel(Integer.toString(this.getAnswer() - 4 + new Random().nextInt(8))));
            this.getAnswers().add(new JLabel(Integer.toString(this.getAnswer())));
            this.getAnswers().add(new JLabel(Integer.toString(this.getAnswer() - 4 + new Random().nextInt(8))));
        } else {
            this.getAnswers().add(new JLabel(Integer.toString(this.getAnswer() - 4 + new Random().nextInt(8))));
            this.getAnswers().add(new JLabel(Integer.toString(this.getAnswer() - 4 + new Random().nextInt(8))));
            this.getAnswers().add(new JLabel(Integer.toString(this.getAnswer())));
        }

        for (UNGraphicsObject tree : this.getTrees())
        {
            tree.setXOffset(6);
            tree.setYOffset(28);
            addObject(tree);
        }

        for (UNGraphicsObject snowball : this.getSnowballs()) {
            addObject(snowball);
        }

        for (JLabel snowballText : this.getSnowballTexts())
        {
            snowballText.setFont(new Font("Minecraftia", Font.PLAIN, 20));
            snowballText.setForeground(new Color(51, 51, 51));
            snowballText.setHorizontalAlignment(SwingConstants.CENTER);
            add(snowballText);
        }

        for (JLabel possibleAnswer : this.getAnswers())
        {
            possibleAnswer.setFont(new Font("Minecraftia", Font.PLAIN, 20));
            possibleAnswer.setForeground(new Color(51, 51, 51));
            possibleAnswer.setHorizontalAlignment(SwingConstants.CENTER);
        }

        for (UNGraphicsObject snowflake : this.getSnow())
        {
            addObject(snowflake);
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
                    getGameScreen().getSounds().get(0).stop();
                    if (getGameScreen().getLevel() < getGameScreen().getLevelMax()) {
                        if (isWinning()) {
                            getGameScreen().addLevel();
                        }
                        getGameScreen().switchPanel(new Level6(getGameScreen(), true));
                    } else {
                        getGameScreen().switchPanel(new UNLauncher(getGameScreen()));
                    }
                }
            }
        });

        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                snowflakeUpdate();

                if (!isQuestionDone())
                {
                    for (UNGraphicsObject snowball : getSnowballs())
                    {
                        snowball.setAngle(snowball.getAngle() + 2);
                        if (new Random().nextInt(10) == 0)
                        {
                            snowball.setWidth(snowball.getWidth() + 1);
                            snowball.setHeight(snowball.getWidth());
                            snowball.setXOffset(snowball.getImage().getWidth(null) / 2);
                            snowball.setY(290 - snowball.getHeight() / 256);
                            snowball.setYOffset(snowball.getImage().getHeight(null) / 2);
                        }
                        if (snowball.getX() < getGameScreen().getWindow().getContentWidth() || getSnowballs().indexOf(snowball) < 2)
                        {
                            snowball.setX(snowball.getX() + 1);
                            for (UNGraphicsObject tree : getTrees())
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
                            getGameScreen().getMusic().get(2).stop();
                            getGameScreen().getMusic().get(1).play(true);
                            setQuestionDone(true);
                            setHelp("Klik op het antwoord");
                            setLastHelp(getHelp());

                            addObject(getSnowballAnswers().get(0));
                            addObject(getSnowballAnswers().get(1));
                            addObject(getSnowballAnswers().get(2));

                            for (JLabel possibleAnswer : getAnswers())
                            {
                                add(possibleAnswer);
                                possibleAnswer.setBounds(getSnowballAnswers().get(getAnswers().indexOf(possibleAnswer)).getX(), getSnowballAnswers().get(getAnswers().indexOf(possibleAnswer)).getY(), getSnowballAnswers().get(getAnswers().indexOf(possibleAnswer)).getWidth(), getSnowballAnswers().get(getAnswers().indexOf(possibleAnswer)).getHeight());
                            }
                        }

                        getSnowballTexts().get(getSnowballs().indexOf(snowball)).setBounds(snowball.getX(), 290 - snowball.getHeight() / 128, snowball.getWidth(), 100);
                    }
                    if (getReindeerIndex() < 2)
                    {
                        setReindeerIndex(getReindeerIndex() + 0.1);
                    }
                    else
                    {
                        setReindeerIndex(0);
                    }
                    for (UNGraphicsObject reindeer : getReindeers())
                    {
                        reindeer.setImage(getGameScreen().getSprites().get(spriteReindeer + (int) Math.round(getReindeerIndex())));
                        reindeer.setX(reindeer.getX() + 1);
                    }
                }
                else
                {
                    for (UNGraphicsObject snowball : getSnowballAnswers())
                    {
                        if (snowball.isMouseClick() && !isWinning() && isHelperDoneTalking())
                        {
                            snowball.setMouseClick(false);
                            if (Integer.valueOf(getAnswers().get(getSnowballAnswers().indexOf(snowball)).getText()) == getAnswer())
                            {
                                add(button);
                                getHelper().setState(3);
                                setHelp("Mooi hoor! Jij kan goed rekenen!");
                                button.setText("Door");
                                setWinning(true);
                            }
                            else
                            {
                                addMistake();
                                if (getMistakes() < 3)
                                {
                                    getHelper().setState(4);
                                    while (getLastHelp() == getHelp())
                                    {
                                        setHelp(getHelpList().get(new Random().nextInt(getHelpList().size())));
                                    }
                                    setLastHelp(getHelp());
                                    setQuestionDone(false);

                                    removeObject(getSnowballAnswers().get(0));
                                    removeObject(getSnowballAnswers().get(1));
                                    removeObject(getSnowballAnswers().get(2));

                                    for (JLabel possibleAnswer : getAnswers())
                                    {
                                        remove(possibleAnswer);
                                    }
                                    getGameScreen().getMusic().get(2).play(true);
                                    getSnowballs().get(0).setX(-800);
                                    getSnowballs().get(1).setX(-1100);
                                    getSnowballs().get(2).setX(-1400);
                                    for (UNGraphicsObject snow : getSnowballs()) {
                                        snow.setWidth(8);
                                        snow.setHeight(8);
                                    }
                                }
                                else
                                {
                                    getHelper().setState(4);
                                    setHelp("Jammer, het was " + getAnswer() + ". Want " + getSnowballAnswers().get(0) + " plus " +
                                            getSnowballTexts().get(1)  + " plus " + getSnowballAnswers().get(2) + " is " + getAnswer()
                                    );
                                    removeObject(getSnowballAnswers().get(0));
                                    removeObject(getSnowballAnswers().get(1));
                                    removeObject(getSnowballAnswers().get(2));

                                    for (JLabel possibleAnswer : getAnswers())
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

    public void snowflakeUpdate()
    {
        for (UNGraphicsObject snowflake : this.getSnow())
        {
            snowflake.setY(snowflake.getY() + 1);
            if (new Random().nextInt(2) == 0) {
                snowflake.setX(snowflake.getX() - 1);
            }
            if (snowflake.getY() > this.getGameScreen().getWindow().getContentHeight())
            {
                snowflake.setY(snowflake.getY() - this.getGameScreen().getWindow().getContentHeight());
            }
            if (snowflake.getX() < 0)
            {
                snowflake.setX(snowflake.getX() + this.getGameScreen().getWindow().getContentWidth());
            }
        }
    }

    public ArrayList<UNGraphicsObject> getSnowballs() {
        return this.snowballs;
    }

    public void setSnowballs(ArrayList<UNGraphicsObject> snowballs) {
        this.snowballs = snowballs;
    }

    public ArrayList<UNGraphicsObject> getSnowballAnswers() {
        return this.snowballAnswers;
    }

    public void setSnowballAnswers(ArrayList<UNGraphicsObject> snowballAnswers) {
        this.snowballAnswers = snowballAnswers;
    }

    public ArrayList<UNGraphicsObject> getTrees() {
        return this.trees;
    }

    public void setTrees(ArrayList<UNGraphicsObject> trees) {
        this.trees = trees;
    }

    public ArrayList<UNGraphicsObject> getReindeers() {
        return this.reindeers;
    }

    public void setReindeers(ArrayList<UNGraphicsObject> reindeers) {
        this.reindeers = reindeers;
    }

    public ArrayList<UNGraphicsObject> getSnow() {
        return this.snow;
    }

    public void setSnow(ArrayList<UNGraphicsObject> snow) {
        this.snow = snow;
    }

    public double getImageIndex() {
        return this.imageIndex;
    }

    public void setImageIndex(double imageIndex) {
        this.imageIndex = imageIndex;
    }

    public boolean isQuestionDone() {
        return this.questionDone;
    }

    public void setQuestionDone(boolean questionDone) {
        this.questionDone = questionDone;
    }

    public boolean isWinning() {
        return this.winning;
    }

    public void setWinning(boolean winning) {
        this.winning = winning;
    }

    public int getAnswer() {
        return this.answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int getTouch() {
        return this.touch;
    }

    public void setTouch(int touch) {
        this.touch = touch;
    }

    public String getLastHelp() {
        return this.lastHelp;
    }

    public void setLastHelp(String lastHelp) {
        this.lastHelp = lastHelp;
    }

    public double getReindeerIndex() {
        return this.reindeerIndex;
    }

    public void setReindeerIndex(double reindeerIndex) {
        this.reindeerIndex = reindeerIndex;
    }

    public ArrayList<JLabel> getSnowballTexts() {
        return this.snowballTexts;
    }

    public void setSnowballTexts(ArrayList<JLabel> snowballTexts) {
        this.snowballTexts = snowballTexts;
    }

    public ArrayList<JLabel> getAnswers() {
        return this.answers;
    }

    public void setAnswers(ArrayList<JLabel> answers) {
        this.answers = answers;
    }
}
