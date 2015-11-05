package eu.theunitry.fabula;


import eu.theunitry.fabula.graphics.UNPanel;
import eu.theunitry.fabula.graphics.UNWindow;
import eu.theunitry.fabula.objects.UNObject;
import eu.theunitry.fabula.objects.UNTimer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UNGameScreen extends UNObject
{

    private final UNWindow window;
    private UNTimer gameLoop;
    private UNPanel panel_menu;

    public UNGameScreen() {
        this.window = new UNWindow("Fabula", 768, 512);
        this.gameLoop = new UNTimer(this, 5) {
            class GameLoop implements ActionListener {

                @Override
                public void actionPerformed(ActionEvent e) {
                    getGameScreen().getPanel().revalidate();
                }

            }
        };

        this.panel_menu = new UNPanel() {
            public void panel0()
            {

            }
        };

        this.window.addPanel(this.panel_menu);
    }

    public JPanel getPanel() {
        return this.panel_menu;
    }

}
