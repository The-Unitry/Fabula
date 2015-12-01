package eu.theunitry.fabula.UNGameEngine.launcher;

import eu.theunitry.fabula.Fabula.levels.*;
import eu.theunitry.fabula.UNGameEngine.graphics.UNGameScreen;
import eu.theunitry.fabula.UNGameEngine.graphics.UNView;

public class UNLevelLoader
{
    public UNLevelLoader(int level, UNGameScreen gameScreen)
    {
        gameScreen.resetProgress();
        UNView panel = new Level1(gameScreen, true);
        gameScreen.switchMusic(1, true);
        gameScreen.getMusic().get(1).setVolume(0.1);

        gameScreen.switchPanel(panel);
    }
}
