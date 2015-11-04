package eu.theunitry.fabula;


import eu.theunitry.fabula.graphics.UNPanel;
import eu.theunitry.fabula.graphics.UNWindow;
import eu.theunitry.fabula.objects.UNObject;
import eu.theunitry.fabula.objects.UNTimer;

import javax.swing.*;

public class UNGameScreen extends UNObject
{

    private final UNWindow window;
    private UNTimer gameLoop;
    private UNPanel panel;

    public UNGameScreen() {
        this.window = new UNWindow("Fabula", 768, 512);
        this.gameLoop = new UNTimer(this, 5);
        this.panel = new UNPanel();

        this.window.addPanel(this.panel);
    }

    public JPanel getPanel() {
        return this.panel;
    }

}
