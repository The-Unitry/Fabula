package eu.theunitry.fabula;

import eu.theunitry.fabula.graphics.UNPanel;
import eu.theunitry.fabula.graphics.UNWindow;
import eu.theunitry.fabula.launcher.UNLauncher;
import eu.theunitry.fabula.objects.UNObject;
import kuusisto.tinysound.*;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


public class UNGameScreen extends UNObject
{

    private UNWindow window;
    private UNLauncher launcher;
    private UNPanel splash;

    public UNGameScreen()
    {
        this.window = new UNWindow("Fabula", 768, 512);
        this.launcher = new UNLauncher();
        this.splash = new UNPanel();
        try {
            this.splash.setBackgroundImage(ImageIO.read(new File("res/backgrounds/splash_unitry.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.window.addPanel(splash);
        this.window.getFrame().setVisible(true);

        TinySound.init();
        Music music = TinySound.loadMusic("audio/song1.wav");
        music.play(true);

        this.window.removePanel(splash);
        this.window.addPanel(launcher);
        this.window.getFrame().setVisible(true);
    }

}
