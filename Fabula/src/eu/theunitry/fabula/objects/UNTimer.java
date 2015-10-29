package eu.theunitry.fabula.objects;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class UNTimer {

    private final Timer gameLoop;
    private final GameLoop actionListener;

    public UNTimer() {
        this.actionListener = new GameLoop();
        this.gameLoop = new Timer(5, this.actionListener);
    }

    public class GameLoop implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }

    }

}
