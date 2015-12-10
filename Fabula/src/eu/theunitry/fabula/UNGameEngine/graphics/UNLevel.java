package eu.theunitry.fabula.UNGameEngine.graphics;

import eu.theunitry.fabula.UNGameEngine.launcher.UNLauncher;
import eu.theunitry.fabula.UNGameEngine.objects.UNButtonListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * All levels extends from this abstract UNLevel class.
 * It inherits from UNGraphicsView, which means that it contains UNGraphicObjects
 */
public abstract class UNLevel extends UNGraphicsView
{
    private boolean playerHasWon = false;
    private String question;
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
                gameScreen.switchPanel(new UNLauncher(gameScreen));
                gameScreen.getMusic().get("song2").stop();
                gameScreen.getMusic().get("intro").play(true);
                gameScreen.getMusic().get("intro").setVolume(0.1);
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

    public boolean hasPlayerWon()
    {
        return playerHasWon;
    }

    public void setPlayerHasWon(boolean playerHasWon)
    {
        this.playerHasWon = playerHasWon;
    }

    public String getQuestion()
    {
        return question;
    }

    public void setQuestion(String question)
    {
        this.question = question;
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
