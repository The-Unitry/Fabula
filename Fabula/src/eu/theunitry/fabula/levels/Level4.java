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
 * Level 4
 * Ruben Smit
 */
public class Level4 extends UNLevel
{
    private Timer timer;
    private ArrayList<UNGraphicsObject> rockets, machines, supportsL, supportsR, cables, gems;
    private JButton button;
    private boolean winning, cableRelease, supportRelease, rocketRelease;
    private UNColor color;
    private String lastHelp;
    private ArrayList<JLabel> ufosText, answers;
    private double rocketIndex, machineIndex, supportLIndex, supportRIndex, cableIndex;
    private int machineState, g1, g2, answer, fakeAnswer1, fakeAnswer2, touch;

    /**
     * Level 4
     * @param gameScreen
     * @param hudEnabled
     */
    public Level4(UNGameScreen gameScreen, boolean hudEnabled) {
        super(gameScreen, hudEnabled);

        machines = new ArrayList<>();
        supportsL = new ArrayList<>();
        supportsR = new ArrayList<>();
        rockets = new ArrayList<>();
        cables = new ArrayList<>();
        gems = new ArrayList<>();

        gameScreen.getMusic().get("song2").play(true);
        gameScreen.getMusic().get("song2").setVolume(0.1);

        this.setBackgroundImage(gameScreen.getBackgrounds().get("silo"));

        setPlayerHasWon(false);
        winning = false;
        this.lastHelp = getHelp();

        this.rocketIndex = 0;
        this.machineIndex = 0;
        this.supportLIndex = 1;
        this.supportRIndex = 1;
        this.cableIndex = 1;


        this.g1 = new Random().nextInt(5) + 5;
        this.g2 = new Random().nextInt(4) + 1;
        this.answer = g1 - g2;

        this.setQuestion("Wat is " + g1 +" min " + g2 + "?");
        this.addHelp("Jammer! Probeer het nog eens.");
        this.addHelp("Helaas! Probeer het nog een keer!");
        this.setHelp("Sleep het aantal diamanten naar de machine.");

        this.color = new UNColor();

        machines.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 415, 265, gameScreen.getSprites().get("2:4:0:0"), false, 170 , 200));
        cables.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 208, 358, gameScreen.getSprites().get("2:4:4:1"), false, 205, 105));
        supportsR.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 295, 315, gameScreen.getSprites().get("2:4:5:1"), false, 95, 150));
        supportsL.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 145, 315, gameScreen.getSprites().get("2:4:6:1"), false, 95, 150));
        rockets.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 190, 220, gameScreen.getSprites().get("2:4:7:0"), false, 160,250));

        gems.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 570, 120, gameScreen.getSprites().get("2:9:1"), true, 40, 40));
        gems.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 620, 120, gameScreen.getSprites().get("2:9:1"), true, 40, 40));
        gems.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 670, 120, gameScreen.getSprites().get("2:9:1"), true, 40, 40));

        gems.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 570, 175, gameScreen.getSprites().get("2:9:1"), true, 40, 40));
        gems.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 620, 175, gameScreen.getSprites().get("2:9:1"), true, 40, 40));
        gems.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 670, 175, gameScreen.getSprites().get("2:9:1"), true, 40, 40));

        gems.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 590, 235, gameScreen.getSprites().get("2:9:1"), true, 40, 40));
        gems.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 645, 235, gameScreen.getSprites().get("2:9:1"), true, 40, 40));

        for (UNGraphicsObject rocket : rockets)
        {
            addObject(rocket);
        }
        for (UNGraphicsObject supportL : supportsL)
        {
            addObject(supportL);
        }
        for (UNGraphicsObject supportR : supportsR)
        {
            addObject(supportR);
        }
        for (UNGraphicsObject machine : machines)
        {
            addObject(machine);
        }
        for (UNGraphicsObject cable : cables)
        {
            addObject(cable);
        }
        for (UNGraphicsObject gem : gems)
        {
            addObject(gem);
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

        add(button);
        button.setText("Nakijken");

        machineState = gameScreen.getSubLevel4();

        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (button.getText() == "Doorgaan")
                {
                    if (gameScreen.getSubLevel4() <= 5)
                    {
                        if (winning)
                        {
                            gameScreen.addSubLevel4();
                        }
                        gameScreen.switchPanel(new Level4(gameScreen, true));
                        gameScreen.setSubLevel4(gameScreen.getSubLevel4());
                    }
                }
                else if (button.getText() == "Nakijken"){
                    for (UNGraphicsObject gem : gems) {
                        for (UNGraphicsObject machine : machines){
                            if (machine.getHitbox().intersects(gem.getHitbox())) {
                                touch++;
                            }
                        }
                    }
                    if (touch == answer){
                        getHelper().setState(3);
                        if (gameScreen.getSubLevel4() == 5) {
                            button.setText("Lanceren!");
                            setHelp("Hoera! Nu kunnen we naar de maan.");
                        }
                        else{
                            button.setText("Doorgaan");
                            setHelp("Jij kan goed rekenen!");
                            winning = true;
                        }
                        for (UNGraphicsObject ge : gems) {
                            removeObject(ge);
                        }

                    }
                    else{
                        addMistake();
                        if (getMistakes() > 3) {
                            button.setText("Doorgaan");
                            getHelper().setState(4);
                            if (touch < answer) {
                                setHelp("Bijna, er moest" + ((answer - touch == 1) ? "" : "en") + " nog " + (answer - touch) +
                                                " diamant" + ((answer - touch == 1) ? "" : "en") + " bij. Want " + g1 + " min " +
                                                g2 + " is " + answer + ". Jij had er " + touch + "."
                                );
                            } else {
                                setHelp("Bijna, er moest" + ((touch - answer == 1) ? "" : "en") + " " + (touch - answer) +
                                                " diamant" + ((touch - answer == 1) ? "" : "en") + " af. Want " + g1 + " min " +
                                                g2 + " is " + answer + ". Jij had er " + touch + "."
                                );

                            }

                            for (UNGraphicsObject ge : gems) {
                                ge.setClickable(false);
                            }
                            winning = false;
                        }
                    }
                }
                else{
                    winning = true;
                    setPlayerHasWon(true);
                    remove(button);
                    //button.remove(button);
                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                                @Override
                                public void run() {
                                    levelDone(4);
                                }
                            },
                            4500
                    );
                }

            }
        });

        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (rocketRelease) {
                    if (rocketIndex < 3) {
                        rocketIndex += 0.07;
                    } else {
                        rocketIndex = 1;
                    }
                    for (UNGraphicsObject rocket : rockets) {

                        rocket.setImage(gameScreen.getSprites().get("2:4:7:" + (int) Math.round(rocketIndex)));
                    }
                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                                @Override
                                public void run() {
                                    supportRelease = true;
                                }
                            },
                            500
                    );
                }
                if (machineIndex < 3)
                {
                    machineIndex += 0.1;
                }
                else {
                    machineIndex = 0;
                }

                if (machineState == 5 && winning){
                    machineState = 6;
                }

                for (UNGraphicsObject machine : machines) {
                    switch (machineState) {
                        case 1:
                            machine.setImage(gameScreen.getSprites().get("2:4:" + (int) Math.round(machineIndex) + ":0"));
                            break;
                        case 2:
                            machine.setImage(gameScreen.getSprites().get("2:4:" + (int) Math.round(machineIndex) + ":1"));
                            break;
                        case 3:
                            machine.setImage(gameScreen.getSprites().get("2:4:" + (int) Math.round(machineIndex) + ":2"));
                            break;
                        case 4:
                            machine.setImage(gameScreen.getSprites().get("2:4:" + (int) Math.round(machineIndex) + ":3"));
                            break;
                        case 5:
                            machine.setImage(gameScreen.getSprites().get("2:4:" + (int) Math.round(machineIndex) + ":4"));
                            break;
                        case 6:
                            machine.setImage(gameScreen.getSprites().get("2:4:" + (int) Math.round(machineIndex) + ":5"));
                            cableRelease = true;
                            break;
                    }
                }

                if (cableRelease) {
                    if (cableIndex < 6) {
                        cableIndex += 0.07;
                    }
                    for (UNGraphicsObject cable : cables) {
                        cable.setImage(gameScreen.getSprites().get("2:4:4:" + (int) Math.round(cableIndex)));
                    }
                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                                @Override
                                public void run() {
                                    rocketRelease = true;
                                }
                            },
                            1000
                    );
                }

                if (supportRelease) {
                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                                @Override
                                public void run() {
                                    for (UNGraphicsObject rocket : rockets){
                                        if (rocket.getX() < 200) {
                                            rocket.setY(rocket.getY() - 2);
                                        }
                                        else{
                                            removeObject(rocket);
                                        }
                                    }
                                }
                            },
                            200
                    );
                    if (supportRIndex < 7) {
                        supportRIndex += 0.05;
                    }

                    for (UNGraphicsObject supportR : supportsR) {

                        supportR.setImage(gameScreen.getSprites().get("2:4:5:" + (int) Math.round(supportRIndex)));
                    }
                    if (supportLIndex < 7) {
                        supportLIndex += 0.05;
                    }
                    for (UNGraphicsObject supportL : supportsL) {

                        supportL.setImage(gameScreen.getSprites().get("2:4:6:" + (int) Math.round(supportLIndex)));
                    }
                    gameScreen.resetSubLevel4();
                }
            }
        });
        timer.start();
    }
}
