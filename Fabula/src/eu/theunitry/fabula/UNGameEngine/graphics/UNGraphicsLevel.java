package eu.theunitry.fabula.UNGameEngine.graphics;

import java.util.ArrayList;

/**
 * All levels extends from this abstract UNGraphicsLevel class.
 * It inherits from UNGraphicsView, which means that it contains UNGraphicObjects
 */
public abstract class UNGraphicsLevel extends UNGraphicsView
{
    private boolean playerHasWon = false;
    private String question;
    private ArrayList<String> helpList;
    private int mistakes;

    public UNGraphicsLevel(UNGameScreen gameScreen, boolean hudEnabled)
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
