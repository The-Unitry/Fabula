package eu.theunitry.fabula.graphics;

import javax.swing.*;

public class UNLevel extends UNPanel {

    private boolean playerHasWon = false;

    public UNLevel(JFrame frame) {
        super(frame);
    }

    public boolean hasPlayerWon()
    {
        return playerHasWon;
    }

    public void setPlayerHasWon(boolean playerHasWon)
    {
        this.playerHasWon = playerHasWon;
    }

}





