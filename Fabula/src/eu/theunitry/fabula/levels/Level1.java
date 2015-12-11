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
    private boolean winning;
    private int need, touch;
    private String lastHelp;
    public UNGraphicsObject blobby;
    public ArrayList<UNGraphicsObject> bombs;
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

        Random rn = new Random();
        int max = 5;
        int min = 2;
        int num1 = rn.nextInt(max - min + 1) + min;
        int num2 = rn.nextInt(max - min + 1) + min;
        int num3 = rn.nextInt(max - min + 1) + min;

        String question = "(" + String.valueOf(num1) + " + " + String.valueOf(num2) + ") x " + String.valueOf(num3);

        int correctAnswer = (num1 + num2) * num3;

        // Load questions & help texts
        this.setQuestion("De geheime formule is " + question + ". Klik op de juiste bom! Snel!");
        this.addHelp("Jammer! Je moet " + need + " appels in de mand stoppen");
        this.addHelp("Helaas! Er moeten " + need + " appels in de mand zitten");
        this.setHelp("Blobby probeert de wereld te vernietigen. We moeten haar neerhalen!");

        //  Set resources/audio
        this.setBackgroundImage(gameScreen.unResourceLoader.backgrounds.get("the-end"));
        this.blobby = new UNGraphicsObject(gameScreen.getWindow().getFrame(), 320, -50, gameScreen.getSprites().get("2:1:1:1"), false, 38 * 3, 70 * 3);

        this.addObject(blobby);

        gameScreen.getMusic().get("the-end").play(true);
        gameScreen.getMusic().get("the-end").setVolume(0.1);

        // Set variables
        this.winning = false;
        this.lastHelp = getHelp();
        bombs = new ArrayList<>();
        answerLabels = new ArrayList<>();

        answerLabels.add(new JLabel(String.valueOf(correctAnswer - 2)));
        answerLabels.add(new JLabel(String.valueOf(correctAnswer)));
        answerLabels.add(new JLabel(String.valueOf(correctAnswer + 3)));

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
            if (button.getText().equals("Doorgaan"))
            {
                levelDone(5);
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

        this.getPanel().add(button);

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
                    shakeBlobby();
                }
                touch = 0;
                winning = (touch == need);
                if(bombs.get(1).isMouseClick())
                {
                    bombs.get(1).setMouseClick(false);
                    System.out.println("test");
                    timer.stop();
                    die();
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

    public void shakeBlobby()
    {
        if(Math.random() < 0.5)
        {
            blobby.setX(blobby.getX() + 1);
        }
        if(Math.random() < 0.5)
        {
            blobby.setY(blobby.getY() + 1);
        }
        if(Math.random() < 0.5)
        {
            blobby.setX(blobby.getX() - 1);
        }
        if(Math.random() < 0.5)
        {
            blobby.setY(blobby.getY() - 1);
        }
    }
}
