package eu.theunitry.fabula.objects;

import eu.theunitry.fabula.UNGameScreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class UNTimer
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
