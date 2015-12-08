package eu.theunitry.fabula.UNGameEngine.launcher;

import kuusisto.tinysound.Music;
import kuusisto.tinysound.Sound;
import kuusisto.tinysound.TinySound;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class is used for loading all resources. It should be replaced later with
 * a more dynamic way of loading, since we are repeating ourselves a lot here.
 */
public class UNResourceLoader
{
    public ArrayList<Music> music;
    public ArrayList<Sound> sounds;
    public ArrayList<Image> sprites;
    public ArrayList<Image> backgrounds;

    public UNResourceLoader()
    {
        music = new ArrayList<>();
        sounds = new ArrayList<>();
        sprites = new ArrayList<>();
        backgrounds = new ArrayList<>();

        this.loadResources();
    }

    private void loadResources()
    {
        this.loadFonts();
        this.loadSoundEffects();
        this.loadMusic();
        this.loadSprites();
    }

    private void loadFonts()
    {
        try
        {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/Minecraftia.ttf")));
        }
        catch (IOException|FontFormatException e)
        {
            e.printStackTrace();
        }
    }

    private void loadMusic()
    {
        music.add(0, TinySound.loadMusic("audio/intro.wav"));
        music.add(1, TinySound.loadMusic("audio/song2.wav"));
        music.add(2, TinySound.loadMusic("audio/avalange.wav"));
    }

    private void loadSoundEffects()
    {
        sounds.add(0, TinySound.loadSound("audio/gibberish.wav"));
    }

    public Image getSprite(String folder, String file)
    {
        try {
            return ImageIO.read(new File("res/sprites/" + folder + "/" + file + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("UNResourceLoader: Could not find " + file);
            try {
                return ImageIO.read(new File("res/default.png"));
            } catch (IOException e1) {
                e1.printStackTrace();
                return null;
            }
        }
    }
    public Image getSprite(String file)
    {
        return this.getSprite("", file);
    }

    public static Image getBackground(String file)
    {
        try {
            return ImageIO.read(new File("res/backgrounds/" + file + ".png"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    private void loadSprites()
    {

    }
}
