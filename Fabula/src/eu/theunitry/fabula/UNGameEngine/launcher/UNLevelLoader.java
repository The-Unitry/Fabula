package eu.theunitry.fabula.UNGameEngine.launcher;

import eu.theunitry.fabula.levels.*;
import eu.theunitry.fabula.UNGameEngine.graphics.UNGameScreen;
import eu.theunitry.fabula.UNGameEngine.graphics.UNView;

public class UNLevelLoader
{
    public UNLevelLoader(int level, UNGameScreen gameScreen)
    {
        gameScreen.resetProgress();

        gameScreen.switchMusic(1, true);
        gameScreen.getMusic().get(1).setVolume(0.1);

        switch (level)
        {
            case 1:
                UNView panel = new Level1(gameScreen, true);
                gameScreen.switchPanel(panel);
            default:
                break;
        }
    }
}
