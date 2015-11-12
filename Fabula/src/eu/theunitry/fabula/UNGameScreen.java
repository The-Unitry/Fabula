package eu.theunitry.fabula;

import eu.theunitry.fabula.graphics.UNPanel;
import eu.theunitry.fabula.graphics.UNWindow;
import eu.theunitry.fabula.launcher.UNLauncher;
import eu.theunitry.fabula.objects.UNObject;
import kuusisto.tinysound.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class UNGameScreen extends UNObject
{

    private UNWindow window;
    private UNLauncher launcher;
    private UNPanel splash;
    private JPanel currentPanel;
    private ArrayList<Music> music;

    public UNGameScreen()
    {
        this.window = new UNWindow("Fabula", 768, 512);
        this.launcher = new UNLauncher(this);
        this.splash = new UNPanel(this.window.getFrame());
        this.splash.setHudEnabled(false);
        try {
            this.splash.setBackgroundImage(ImageIO.read(new File("res/backgrounds/splash_unitry.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.window.addPanel(splash);
        this.window.getFrame().setVisible(true);

        music = new ArrayList<Music>();
        TinySound.init();
        music.add(0, TinySound.loadMusic("audio/intro.wav"));
        music.add(1, TinySound.loadMusic("audio/song2.wav"));
        music.get(0).play(true);

        this.window.removePanel(splash);
        this.window.addPanel(launcher);
        currentPanel = launcher;
        this.window.getFrame().setVisible(true);
    }

    public void switchPanel(JPanel panel)
    {
        this.window.removePanel(currentPanel);
        this.window.addPanel(panel);
        this.window.getFrame().setVisible(true);
    }

    public UNWindow getWindow()
    {
        return this.window;
    }

    public void toggleMusic(int index, boolean loop)
    {
        music.get(0).stop();
        music.get(index).play(loop);
    }

}
