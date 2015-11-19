package eu.theunitry.fabula.graphics;

import eu.theunitry.fabula.UNGameScreen;

import javax.swing.*;
import java.util.ArrayList;

public abstract class UNLevel extends UNPanel
{
    private boolean playerHasWon = false;
    private String question;
    private ArrayList<String> helpList;
    private int mistakes;

    public UNLevel(UNGameScreen gameScreen, boolean hudEnabled)
    {
        super(gameScreen, hudEnabled);
        this.helpList = new ArrayList<String>();
        this.mistakes = 0;
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





