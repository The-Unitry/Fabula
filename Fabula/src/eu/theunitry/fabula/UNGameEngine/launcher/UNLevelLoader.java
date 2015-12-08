package eu.theunitry.fabula.UNGameEngine.launcher;

import eu.theunitry.fabula.levels.*;
import eu.theunitry.fabula.UNGameEngine.graphics.UNGameScreen;
import eu.theunitry.fabula.UNGameEngine.graphics.UNView;

/**
 * This class is responsible for loading all levels. There is one variable
 * that controls all levels. This way it is very easy to load a level.
 */
public class UNLevelLoader
{
    /**
     * !!!!!!!!!!!!!!!!
     * DO NOT EDIT THIS
     * WHILE COMMITTING
     * !!!!!!!!!!!!!!!!
     */
    private int currentLevel = 1;      // The current level, this is temporary because the progress will be saved locally.
    private UNGameScreen gameScreen;

    public UNLevelLoader(UNGameScreen gameScreen)
    {
        this.gameScreen = gameScreen;
        this.gameScreen.resetProgress();

        this.gameScreen.switchMusic(1, true);
        this.gameScreen.getMusic().get(1).setVolume(0.1);

        this.loadLevel(this.currentLevel);
    }

    private void loadLevel(int level)
    {
        switch (this.currentLevel)
        {
            case 1:
                UNView panel1 = new Level1(gameScreen, true);
                gameScreen.switchPanel(panel1);
                break;
            case 2:
                UNView panel2 = new Level2(gameScreen, true);
                gameScreen.switchPanel(panel2);
                break;
            case 3:
                UNView panel3 = new Level3(gameScreen, true);
                gameScreen.switchPanel(panel3);
                break;
            case 4:
                //UNView panel4 = new Level4(gameScreen, true);
                //gameScreen.switchPanel(panel4);
                break;
            case 5:
                UNView panel5 = new Level5(gameScreen, true);
                gameScreen.switchPanel(panel5);
                break;
            case 6:
                UNView panel6 = new Level6(gameScreen, true);
                gameScreen.switchPanel(panel6);
                break;
            case 7:
                UNView panel7 = new Level7(gameScreen, true);
                gameScreen.switchPanel(panel7);
                break;
            case 8:
                UNView panel8 = new Level8(gameScreen, true);
                gameScreen.switchPanel(panel8);
                break;
            case 9:
                UNView panel9 = new Level9(gameScreen, true);
                gameScreen.switchPanel(panel9);
                break;
            case 10:
                UNView panel10 = new Level13(gameScreen, true);
                gameScreen.switchPanel(panel10);
                break;
            case 11:
                UNView panel11 = new Level11(gameScreen, true);
                gameScreen.switchPanel(panel11);
                break;
            case 12:
                UNView panel12 = new Level12(gameScreen, true);
                gameScreen.switchPanel(panel12);
                break;
            default:
                break;
        }
    }
}
