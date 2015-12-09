package eu.theunitry.fabula.UNGameEngine.launcher;

import eu.theunitry.fabula.UNGameEngine.graphics.UNGraphicsView;
import eu.theunitry.fabula.levels.*;
import eu.theunitry.fabula.UNGameEngine.graphics.UNGameScreen;

/**
 * UNLevelLoader
 * This class is responsible for loading all levels. There is one variable
 * that controls all levels. This way it is very easy to load a level.
 */
public class UNLevelLoader
{
    private int currentLevel = 5;      // The current level, this is temporary because the progress will be saved locally.
    private UNGameScreen gameScreen;

    public UNLevelLoader(UNGameScreen gameScreen)
    {
        this.gameScreen = gameScreen;
        this.gameScreen.resetProgress();

        this.gameScreen.switchMusic("song2", true);
        this.gameScreen.getMusic().get("song2").setVolume(0.1);

        this.loadLevel(this.currentLevel);
    }

    private void loadLevel(int level)
    {
        switch (this.currentLevel)
        {
            case 0:
                UNGraphicsView panel0 = new Level0(gameScreen, true);
                gameScreen.switchPanel(panel0);
                break;
            case 1:
                UNGraphicsView panel1 = new Level1(gameScreen, true);
                gameScreen.switchPanel(panel1);
                break;
            case 2:
                UNGraphicsView panel2 = new Level2(gameScreen, true);
                gameScreen.switchPanel(panel2);
                break;
            case 3:
                UNGraphicsView panel3 = new Level3(gameScreen, true);
                gameScreen.switchPanel(panel3);
                break;
            case 4:
                //UNGraphicsView panel4 = new Level4(gameScreen, true);
                //gameScreen.switchPanel(panel4);
                break;
            case 5:
                UNGraphicsView panel5 = new Level5(gameScreen, true);
                gameScreen.switchPanel(panel5);
                break;
            case 6:
                UNGraphicsView panel6 = new Level6(gameScreen, true);
                gameScreen.switchPanel(panel6);
                break;
            case 7:
                UNGraphicsView panel7 = new Level7(gameScreen, true);
                gameScreen.switchPanel(panel7);
                break;
            case 8:
                UNGraphicsView panel8 = new Level8(gameScreen, true);
                gameScreen.switchPanel(panel8);
                break;
            case 9:
                UNGraphicsView panel9 = new Level9(gameScreen, true);
                gameScreen.switchPanel(panel9);
                break;
            case 10:
                UNGraphicsView panel10 = new Level10(gameScreen, true);
                gameScreen.switchPanel(panel10);
                break;
            case 11:
                UNGraphicsView panel11 = new Level11(gameScreen, true);
                gameScreen.switchPanel(panel11);
                break;
            case 12:
                UNGraphicsView panel12 = new Level12(gameScreen, true);
                gameScreen.switchPanel(panel12);
                break;
            default:
                break;
        }
    }
}
