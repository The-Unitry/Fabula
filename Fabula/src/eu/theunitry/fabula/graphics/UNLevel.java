package eu.theunitry.fabula.graphics;

import eu.theunitry.fabula.UNGameScreen;

import javax.swing.*;
import java.util.ArrayList;

public abstract class UNLevel extends UNPanel
{

    private boolean playerHasWon = false;
    private String question;
    private ArrayList<String> mistakeList;

    public UNLevel(UNGameScreen gameScreen, boolean hudEnabled)
    {
        super(gameScreen, hudEnabled);
        this.mistakeList = new ArrayList<>();
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
}





