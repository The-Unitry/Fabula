package eu.theunitry.fabula;

import eu.theunitry.fabula.graphics.UNWindow;
import eu.theunitry.fabula.launcher.UNLauncher;
import eu.theunitry.fabula.objects.UNObject;
import kuusisto.tinysound.*;


public class UNGameScreen extends UNObject
{

    private UNWindow window;
    private UNLauncher launcher;

    public UNGameScreen()
    {
        this.window = new UNWindow("Fabula", 768, 512);
        this.launcher = new UNLauncher();

        TinySound.init();
        Music music = TinySound.loadMusic("audio/song1.wav");
        music.play(true);

        this.window.addPanel(launcher);
        this.window.getFrame().setVisible(true);
    }

}
