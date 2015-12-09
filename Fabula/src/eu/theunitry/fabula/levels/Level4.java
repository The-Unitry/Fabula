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

/**
 * Level 4
 * Ruben Smit
 */
public class Level4 extends UNGraphicsLevel
{
    private Timer timer;
    private ArrayList<UNGraphicsObject> rockets, machines, supportsL, supportsR, cables, gems;
    private JButton button;
    private boolean winning, cableRelease, supportRelease, rocketRelease;
    private UNColor color;
    private String lastHelp;
    private ArrayList<JLabel> ufosText, answers;
    private double rocketIndex, machineIndex, supportLIndex, supportRIndex, cableIndex;
    private int machineState;

    /**
     * Level 3
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

        this.setBackgroundImage(gameScreen.getBackgrounds().get("space"));

        this.winning = false;
        this.lastHelp = getHelp();

        this.rocketIndex = 0;
        this.machineIndex = 0;
        this.supportLIndex = 1;
        this.supportRIndex = 1;
        this.cableIndex = 1;

        this.setQuestion("Wat is  plus plus ?");
        this.addHelp("Jammer! Probeer het nog eens");
        this.addHelp("Helaas! Probeer het nog een keer!");
        this.setHelp("Klik op de UFO met het goede antwoord.");

        this.color = new UNColor();

        machines.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 400, 265, gameScreen.getSprites().get("2:4:0:1"), false, 170 , 200));
        cables.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 193, 358, gameScreen.getSprites().get("2:4:6:1"), false, 205, 105));
        supportsL.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 130, 315, gameScreen.getSprites().get("2:4:8:1"), false, 95, 150));
        supportsR.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 280, 315, gameScreen.getSprites().get("2:4:7:1"), false, 95, 150));
        rockets.add(new UNGraphicsObject(gameScreen.getWindow().getFrame(), 175, 220, gameScreen.getSprites().get("2:4:9:0"), false, 160,250));

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
        button.setText("Lanceer!");

        for (int i = 0; i < 4; i++){
            gameScreen.addLevel();
        }
        machineState = gameScreen.getLevel();
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (button.getText() == "Doorgaan")
                {
                    gameScreen.getSounds().get("gibberish").stop();
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
                else{
                    winning = true;
                }
            }
        });

        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (rocketRelease) {
                    if (rocketIndex < 2) {
                        rocketIndex += 0.07;
                    } else {
                        rocketIndex = 0;
                    }
                    for (UNGraphicsObject rocket : rockets) {

                        rocket.setImage(gameScreen.getSprites().get("2:4:9:" + (int) Math.round(rocketIndex)));
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
                            machine.setImage(gameScreen.getSprites().get("2:4:0:" + (int) Math.round(machineIndex)));
                            break;
                        case 2:
                            machine.setImage(gameScreen.getSprites().get("2:4:1:" + (int) Math.round(machineIndex)));

                            break;
                        case 3:
                            machine.setImage(gameScreen.getSprites().get("2:4:2:" + (int) Math.round(machineIndex)));
                            break;
                        case 4:
                            machine.setImage(gameScreen.getSprites().get("2:4:3:" + (int) Math.round(machineIndex)));
                            break;
                        case 5:
                            machine.setImage(gameScreen.getSprites().get("2:4:4:" + (int) Math.round(machineIndex)));
                            break;
                        case 6:
                            machine.setImage(gameScreen.getSprites().get("2:4:5:" + (int) Math.round(machineIndex)));
                            cableRelease = true;
                            break;
                    }
                }

                if (cableRelease) {
                    if (cableIndex < 6) {
                        cableIndex += 0.07;
                    }
                    for (UNGraphicsObject cable : cables) {
                        cable.setImage(gameScreen.getSprites().get("2:4:6:" + (int) Math.round(cableIndex)));
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
                                        rocket.setY(rocket.getY() - 2);
                                    }
                                }
                            },
                            200
                    );
                    if (supportRIndex < 7) {
                        supportRIndex += 0.05;
                    }

                    for (UNGraphicsObject supportR : supportsR) {

                        supportR.setImage(gameScreen.getSprites().get("2:4:7:" + (int) Math.round(supportRIndex)));
                    }
                    if (supportLIndex < 7) {
                        supportLIndex += 0.05;
                    }
                    for (UNGraphicsObject supportL : supportsL) {

                        supportL.setImage(gameScreen.getSprites().get("2:4:8:" + (int) Math.round(supportLIndex)));
                    }

                }
            }
        });
        timer.start();
    }
}
