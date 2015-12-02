package eu.theunitry.fabula.UNGameEngine.objects;

import eu.theunitry.fabula.UNGameEngine.graphics.UNGameScreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 * This is an extension of the Timer class. We use this
 * to add time to the UNGameScreen. It makes it very easy to add
 * a timer to objects.
 */
public class UNTimer extends UNObject
{
    private final Timer gameLoop;
    private final UNGameScreen gameScreen;

    public UNTimer(UNGameScreen gameScreen, int ms) {
        this.gameScreen = gameScreen;
        this.gameLoop = new Timer(ms, new GameLoop());
        this.gameLoop.start();
    }

    public void start() {
        this.gameLoop.start();
    }

    public void stop() {
        this.gameLoop.stop();
    }

    public class GameLoop implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    public UNGameScreen getGameScreen()
    {
        return this.gameScreen;
    }
}
