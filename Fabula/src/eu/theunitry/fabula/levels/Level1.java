package eu.theunitry.fabula.levels;


import eu.theunitry.fabula.UNGameEngine.graphics.UNGameScreen;
import eu.theunitry.fabula.UNGameEngine.graphics.UNColor;
import eu.theunitry.fabula.UNGameEngine.graphics.UNLevel;
import eu.theunitry.fabula.UNGameEngine.graphics.UNGraphicsObject;
import eu.theunitry.fabula.UNGameEngine.launcher.UNLauncher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

/**
 * Level 1
 * Maarten Bode
 */
public class Level1 extends UNLevel
{
    private Timer timer;
    private JButton button;
    private boolean winning, hasShieldUp, showExplosion, alarm;
    private int need, touch, xstart, ystart, correctAnswer, clicked;
    private String lastHelp;
    public UNGraphicsObject blobby, shield, explosion, blobbyBomb;
    public ArrayList<UNGraphicsObject> bombs, blobbyHP;
    private ArrayList<Integer> answers;
    private ArrayList<JLabel> answerLabels;

    /**
     * Level 1
     * @param gameScreen
     * @param hudEnabled
     */
    public Level1(UNGameScreen gameScreen, boolean hudEnabled)
    {
        super(gameScreen, hudEnabled);

        gameScreen.stopAudio();

        this.need = 3 + new Random().nextInt(3);

        blobbyHP = new ArrayList<UNGraphicsObject>();
        answerLabels = new ArrayList<>();

        answerLabels.add(new JLabel());
        answerLabels.add(new JLabel());
        answerLabels.add(new JLabel());

        /*Random rn = new Random();
        int max = 5;
        int min = 2;
        int num1 = rn.nextInt(max - min + 1) + min;
        int num2 = rn.nextInt(max - min + 1) + min;
        int num3 = rn.nextInt(max - min + 1) + min;


        String question = "(" + String.valueOf(num1) + " + " + String.valueOf(num2) + ") x " + String.valueOf(num3);

        correctAnswer = (num1 + num2) * num3;*/

        changeQuestion();

        // Load questions & help texts
        //this.setQuestion("Wat is " + question + "?");
        this.addHelp("Jammer! Je moet " + need + " appels in de mand stoppen");
        this.addHelp("Helaas! Er moeten " + need + " appels in de mand zitten");
        this.setHelp("Blobby probeert de wereld te vernietigen. Klik op het juiste antwoord!");

        //  Set resources/audio
        this.setBackgroundImage(gameScreen.unResourceLoader.backgrounds.get("the-end"));
        this.blobby = new UNGraphicsObject(gameScreen.getWindow().getFrame(), 320, -50, gameScreen.getSprites().get("2:1:1:1"), false, 38 * 3, 70 * 3);
        this.shield = new UNGraphicsObject(gameScreen.getWindow().getFrame(), 320, 90, gameScreen.getSprites().get("2:1:5"), false, 38 * 3, 70 * 3);
        this.explosion = new UNGraphicsObject(gameScreen.getWindow().getFrame(), 320, 90, gameScreen.getSprites().get("2:1:6"), false, 36 * 3, 34 * 3);
        this.blobbyBomb = new UNGraphicsObject(gameScreen.getWindow().getFrame(), 160 + new Random().nextInt(320) , -100, gameScreen.getSprites().get("2:1:7"), false, 16 * 3, 28 * 3);
        this.hasShieldUp = false;
        this.showExplosion = false;
        this.clicked = -1;
        this.addObject(blobby);
        this.addObject(blobbyBomb);

        gameScreen.getMusic().get("the-end").play(true);
        gameScreen.getMusic().get("the-end").setVolume(0.1);

        gameScreen.getMusic().get("alarm").setVolume(0.05);

        // Set variables
        this.winning = false;
        this.lastHelp = getHelp();
        this.bombs = new ArrayList<>();
        this.alarm = false;

        for (int i = 0; i < 3; i++)
        {
            blobbyHP.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 32 + (i * 40), 96, gameScreen.getSprites().get("2:1:4:0"), false, 32, 32));
            addObject(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 32 + (i * 40), 96, gameScreen.getSprites().get("2:1:4:1"), false, 32, 32));
            addObject(blobbyHP.get(i));
        }


        for (JLabel label : answerLabels)
        {
            label.setForeground(UNColor.WHITE_COLOR);
            label.setFont(new Font("Minecraftia", Font.PLAIN, 20));
        }

        bombs.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 240, 350, gameScreen.getSprites().get("2:1:2"), false, 64, 64));
        answerLabels.get(0).setBounds(bombs.get(0).getX() + 17, bombs.get(0).getY(), 64, 64);
        this.add(answerLabels.get(0));
        bombs.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 340, 350, gameScreen.getSprites().get("2:1:2"), false, 64, 64));
        answerLabels.get(1).setBounds(bombs.get(1).getX() + 17, bombs.get(1).getY(), 64, 64);
        this.add(answerLabels.get(1));
        bombs.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 440, 350, gameScreen.getSprites().get("2:1:2"), false, 64, 64));
        answerLabels.get(2).setBounds(bombs.get(2).getX() + 17, bombs.get(2).getY(), 64, 64);
        this.add(answerLabels.get(2));



        for (UNGraphicsObject bomb : bombs)
        {
            this.addObject(bomb);
        }

        this.button = new JButton("Vuren!");

        // Standard styling
        this.setLayout(null);

        // Set button styling
        this.button.setBounds(618, 64, 150, 50);
        this.button.setBackground(new Color(51, 51, 51));
        this.button.setFont(new Font("Minecraftia", Font.PLAIN, 15));
        this.button.setForeground(Color.white);
        this.button.setOpaque(true);

        // Makes it look good on Mac
        this.button.setFocusPainted(false);
        this.button.setBorderPainted(false);

        // Speaks for itself
        button.addActionListener(e -> {
            if (button.getText().equals("Opnieuw"))
            {
                levelDone(1);
            }
            if (isHelperDoneTalking()) {
                if (winning) {
                    getHelper().setState(3);
                    setHelp("Goed gedaan, dat wordt smikkelen en smullen!");

                    button.setText("Door");
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
                                    " appel" + ((need - touch == 1) ? "" : "s")  + " bij. Want " + touch + " plus " +
                                    (need - touch)  + " is " + need
                            );
                        } else {
                            setHelp("Jammer, er moest" + ((touch - need == 1) ? "" : "en") + " " + (touch - need) +
                                    " appel" + ((touch - need == 1) ? "" : "s")  + " af. Want " + touch + " min " +
                                    (touch - need)  + " is " + need
                            );
                        }

                        button.setText("Door");
                    }
                }
            }
        });

        timer = new Timer(100, new ActionListener() {
            int i = blobby.getY();
            int frame = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                if(frame < 3)
                {
                    frame++;
                } else {
                    frame = 0;
                }
                blobby.setImage(gameScreen.getSprites().get("2:1:1:" + String.valueOf(frame)));

                if(i <= 90) blobby.setY(i = i + 10);
                if(i >= 90) {
                    if (xstart == 0)
                    {
                        xstart = blobby.getX();
                        ystart = blobby.getY();
                    }
                    shakeBlobby();
                }
                touch = 0;
                winning = (touch == need);
                if (clicked == -1)
                {
                    checkBombClick(0);
                    checkBombClick(1);
                    checkBombClick(2);
                }
                else
                {
                    checkBombClick(clicked);
                    int temp1, temp2;
                    temp1 = clicked - 1;
                    temp2 = clicked + 1;
                    if (temp1 < 0) {temp1 += 3;}
                    if (temp2 < 0) {temp2 += 3;}
                    if (temp1 > 2) {temp1 -= 3;}
                    if (temp2 > 2) {temp2 -= 3;}
                    bombs.get(temp1).setMouseClick(false);
                    bombs.get(temp2).setMouseClick(false);
                }

                /*if (new Random().nextInt(10) == 1 && !alarm)
                {
                    alarm = true;
                    gameScreen.getMusic().get("alarm").play(true);
                }*/

                if (alarm)
                {
                    if (blobbyBomb.getY() < 300)
                    {
                        blobbyBomb.setY(blobbyBomb.getY() + 6);
                        if (blobbyBomb.isMouseClick())
                        {
                            blobbyBomb.setMouseClick(false);
                            blobbyBomb.setY(-100);
                            blobbyBomb.setX(160 + new Random().nextInt(320));
                            gameScreen.getMusic().get("alarm").stop();
                            alarm = false;
                        }
                    }
                    else
                    {
                        gameScreen.getMusic().get("alarm").stop();
                        alarm = false;
                        button.setText("Opnieuw");
                        add(button);
                    }
                }
            }
        });



        timer.start();
    }

    public void die()
    {
        timer = new Timer(200, new ActionListener() {
            int i = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                if(i < 8) {
                    blobby.setImage(getGameScreen().getSprites().get("2:1:3:" + String.valueOf(i)));
                    i++;
                } else {
                    if(i != 9001) {
                        blobby.setHeight(0);
                        blobby.setWidth(0);
                        getGameScreen().switchToCredits();
                        i = 9001;
                    }

                }
            }
        });

        timer.start();
    }

    public void checkBombClick(int bombID)
    {
        if(bombs.get(bombID).isMouseClick())
        {
            if (clicked == -1) {
                clicked = bombID;
                getGameScreen().getSounds().get("swoosh").play(0.1);
            }
            if (bombs.get(bombID).getWidth() > 10 && !hasShieldUp || hasShieldUp && bombs.get(bombID).getWidth() > 20)
            {
                bombs.get(bombID).setX(bombs.get(bombID).getX() + 1 + ((blobby.getX() + blobby.getWidth() / 2) - (bombs.get(bombID).getX() + bombs.get(bombID).getWidth() / 2)) / 10);
                bombs.get(bombID).setY(bombs.get(bombID).getY() - (int) (bombs.get(bombID).getY() / 1.1 - blobby.getY()) / 10);
                bombs.get(bombID).setWidth(bombs.get(bombID).getWidth() - 2);
                bombs.get(bombID).setHeight(bombs.get(bombID).getHeight() - 2);

                if (Integer.parseInt(answerLabels.get(bombID).getText()) != correctAnswer)
                {
                    if (bombs.get(bombID).getWidth() < 32 && !hasShieldUp)
                    {
                        hasShieldUp = true;
                        addObject(shield);
                        getGameScreen().getSounds().get("blocked").play(0.1);
                    }
                }
                else
                {
                    if (bombs.get(bombID).getWidth() < 20 && !showExplosion)
                    {
                        showExplosion = true;
                        explosion.setAngle(new Random().nextInt(360));
                        addObject(explosion);
                        getGameScreen().getSounds().get("explosion").play(0.1);
                    }
                }
            }
            else
            {
                if (Integer.parseInt(answerLabels.get(bombID).getText()) == correctAnswer)
                {
                    removeObject(blobbyHP.get(blobbyHP.size() - 1));
                    blobbyHP.remove(blobbyHP.get(blobbyHP.size() - 1));
                    showExplosion = false;
                    removeObject(explosion);
                }
                else
                {
                    hasShieldUp = false;
                    removeObject(shield);
                }
                changeQuestion();
                bombs.get(bombID).setX(240 + (100 * bombID));
                bombs.get(bombID).setY(350);
                bombs.get(bombID).setWidth(64);
                bombs.get(bombID).setHeight(64);
                bombs.get(bombID).setMouseClick(false);
                clicked = -1;

                if (blobbyHP.size() == 0)
                {
                    timer.stop();
                    die();
                }
            }
        }
    }

    public void shakeBlobby()
    {
        if(Math.random() < 0.5 && blobby.getX() - xstart < 10)
        {
            blobby.setX(blobby.getX() + 1);
        }
        if(Math.random() < 0.5 && blobby.getY() - ystart < 10)
        {
            blobby.setY(blobby.getY() + 1);
        }
        if(Math.random() < 0.5 && blobby.getX() - xstart > -10)
        {
            blobby.setX(blobby.getX() - 1);
        }
        if(Math.random() < 0.5 && blobby.getY() - ystart > -10)
        {
            blobby.setY(blobby.getY() - 1);
        }
    }

    public void changeQuestion()
    {
        Random rn = new Random();
        int max = 5;
        int min = 2;
        int num1 = rn.nextInt(max - min + 1) + min;
        int num2 = rn.nextInt(max - min + 1) + min;
        int num3 = rn.nextInt(max - min + 1) + min;

        boolean plus = rn.nextBoolean();
        String question = "";
        if (plus)
        {
            question = "(" + String.valueOf(num1) + " + " + String.valueOf(num2) + ") x " + String.valueOf(num3);
            correctAnswer = (num1 + num2) * num3;
        }
        else
        {
            num2 = Math.min(num2, num1 - 1);
            question = "(" + String.valueOf(num1) + " - " + String.valueOf(num2) + ") x " + String.valueOf(num3);
            correctAnswer = (num1 - num2) * num3;
        }

        setQuestion("Wat is " + question + "?");
        boolean correctUsed = false;
        int wrongUsed = 0;
        for (int i = 0; i < 3; i++)
        {
            if (i == 2 && !correctUsed)
            {
                answerLabels.get(i).setText(String.valueOf(correctAnswer));
            }
            else
            {
                int randomAnswer = correctAnswer - 3 + rn.nextInt(6);
                while (randomAnswer == correctAnswer && correctUsed || randomAnswer == wrongUsed)
                {
                    randomAnswer = correctAnswer - 3 + rn.nextInt(6);
                }
                if (randomAnswer != correctAnswer)
                {
                    wrongUsed = randomAnswer;
                }
                else
                {
                    correctUsed = true;
                }
                answerLabels.get(i).setText(String.valueOf(randomAnswer));
            }
        }
    }
}
