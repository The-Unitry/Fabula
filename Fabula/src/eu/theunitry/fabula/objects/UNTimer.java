package eu.theunitry.fabula.objects;

import eu.theunitry.fabula.UNGameScreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class UNTimer
{

    private final Timer gameLoop;
    private final GameLoop actionListener;
    private final UNGameScreen gameScreen;

    public UNTimer(UNGameScreen gameScreen, int ms) {
        this.gameScreen = gameScreen;
        this.actionListener = new GameLoop();
        this.gameLoop = new Timer(ms, this.actionListener);
        this.gameLoop.start();
    }

    public class GameLoop implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }

    }

    public UNGameScreen getGameScreen() {
        return this.gameScreen;
    }

}
