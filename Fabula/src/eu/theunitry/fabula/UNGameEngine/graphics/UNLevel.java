package eu.theunitry.fabula.UNGameEngine.graphics;

import eu.theunitry.fabula.UNGameEngine.launcher.UNLauncher;
import eu.theunitry.fabula.UNGameEngine.objects.UNButtonListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;

/**
 * All levels extends from this abstract UNLevel class.
 * It inherits from UNGraphicsView, which means that it contains UNGraphicObjects
 */
public abstract class UNLevel extends UNGraphicsView
{
    private boolean playerHasWon = false;
    private ArrayList<String> helpList;
    private int mistakes;

    public UNLevel(UNGameScreen gameScreen, boolean hudEnabled)
    {
        super(gameScreen, hudEnabled);
        this.helpList = new ArrayList<>();
        this.mistakes = 0;
        JButton btn = new JButton("<");
        this.setButtonStyling(btn);
        btn.addActionListener(new UNButtonListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                super.actionPerformed(e);
                gameScreen.switchToLauncher();
            }
        });
        super.add(btn);
    }

    private void setButtonStyling(JButton btn)
    {
        btn.setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));
        btn.setBackground(UNColor.HUD_COLOR);
        btn.setBorderPainted(false);
        btn.setForeground(UNColor.WHITE_COLOR);
        btn.setOpaque(true);
        btn.setBounds(0,0,64,64);
    }

    public void levelDone(int currentLevel)
    {
        getGameScreen().getSounds().get("gibberish").stop();
        if (getGameScreen().getLevel() < getGameScreen().getLevelMax())
        {
            if (this.hasPlayerWon())
            {
                if (this.getGameScreen().isAdventure())
                {
                    this.getGameScreen().addLevel();
                    int newLevel = 3 + new Random().nextInt(9);
                    while (newLevel == currentLevel) {
                        newLevel = 3 + new Random().nextInt(9);
                    }
                    this.getGameScreen().getLauncher().getLevelLoader().switchToLevel(newLevel);
                }
                else
                {
                    getGameScreen().switchToLauncher();
                }
            }
            else
            {
                this.getGameScreen().getLauncher().getLevelLoader().switchToLevel(currentLevel);
            }
        }
        else
        {
            getGameScreen().setAdventure(false);
            this.getGameScreen().getLauncher().getLevelLoader().switchToLevel(1);
            //getGameScreen().switchToLauncher();
        }
    }

    public boolean hasPlayerWon()
    {
        return playerHasWon;
    }

    public void setPlayerHasWon(boolean playerHasWon)
    {
        this.playerHasWon = playerHasWon;
    }

    public void addHelp(String help)
    {
        helpList.add(help);
    }

    public ArrayList<String> getHelpList()
    {
        return helpList;
    }

    public void addMistake()
    {
        this.mistakes++;
    }

    public int getMistakes()
    {
        return mistakes;
    }
}
