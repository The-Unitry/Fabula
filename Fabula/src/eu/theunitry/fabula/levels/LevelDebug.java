package eu.theunitry.fabula.levels;

import eu.theunitry.fabula.UNGameEngine.graphics.UNGameScreen;
import eu.theunitry.fabula.UNGameEngine.graphics.UNGraphicsObject;
import eu.theunitry.fabula.UNGameEngine.graphics.UNLevel;
import eu.theunitry.fabula.UNGameEngine.launcher.UNResourceLoader;

public class LevelDebug extends UNLevel
{

    public LevelDebug(UNGameScreen gameScreen, boolean hudEnabled)
    {
        super(gameScreen, hudEnabled);
        UNResourceLoader resourceLoader = new UNResourceLoader();
        UNGraphicsObject basket = new UNGraphicsObject(gameScreen.getWindow().getFrame(), 600, 200, resourceLoader.getSprite("sprites", "apple"), false, 96, 96);
        this.addObject(basket);
    }
}
