package eu.theunitry.fabula.UNGameEngine.launcher;

import kuusisto.tinysound.Music;
import kuusisto.tinysound.Sound;
import kuusisto.tinysound.TinySound;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * UNResourceLoader
 * This class is used for loading all resources. It should be replaced later with
 * a more dynamic way of loading, since we are repeating ourselves a lot here.
 */
public class UNResourceLoader
{
    public Map<String, Music> music;
    public Map<String, Sound> sounds;
    public Map<String, Image> backgrounds;
    public Map<String, Image> sprites;

    public UNResourceLoader()
    {
        music = new HashMap<>();
        sounds = new HashMap<>();
        sprites = new HashMap<>();
        backgrounds = new HashMap<>();

        this.loadSprites();
        this.loadFonts();
        this.loadMusic();
        this.loadSound();
    }

    private void loadSprites()
    {
        /**
         * Tuiltje
         */

        // Flapping
        for(int i = 0; i <= 5; i++) sprites.put("1:1:" + String.valueOf(i), this.getSprite("tuiltje/flapping/" + String.valueOf(i)));

        // Happy
        for(int i = 0; i <= 9; i++) sprites.put("1:2:" + String.valueOf(i), this.getSprite("tuiltje/happy/" + String.valueOf(i)));

        // Idle
        for(int i = 0; i <= 5; i++) sprites.put("1:3:" + String.valueOf(i), this.getSprite("tuiltje/idle/" + String.valueOf(i)));

        // Questioning
        for(int i = 0; i <= 9; i++) sprites.put("1:4:" + String.valueOf(i), this.getSprite("tuiltje/questioning/" + String.valueOf(i)));

        // Sad
        for(int i = 0; i <= 5; i++) sprites.put("1:5:" + String.valueOf(i), this.getSprite("tuiltje/sad/" + String.valueOf(i)));
    }

    private void loadBackgrounds()
    {
        backgrounds.put("cave", getBackground("cave"));
        backgrounds.put("desert", getBackground("desert"));
        backgrounds.put("forest", getBackground("forest"));
        backgrounds.put("forest_background", getBackground("forest_background"));
        backgrounds.put("jungle", getBackground("jungle"));
        backgrounds.put("mine", getBackground("mine"));
        backgrounds.put("moon", getBackground("moon"));
        backgrounds.put("snow", getBackground("snow"));
        backgrounds.put("space", getBackground("space"));
        backgrounds.put("splash", getBackground("splash"));
        backgrounds.put("splash_unitry", getBackground("splash_unitry"));
        backgrounds.put("supermarket", getBackground("supermarket"));
        backgrounds.put("underwater", getBackground("underwater"));
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
        music.put("intro", TinySound.loadMusic("audio/intro.wav"));
        music.put("song2", TinySound.loadMusic("audio/song2.wav"));         // Wooooohoooooooo!!!
        music.put("avalange", TinySound.loadMusic("audio/avalange.wav"));
    }

    private void loadSound()
    {
        sounds.put("gibberish", TinySound.loadSound("audio/gibberish.wav"));
    }
//
//    private void loadSoundEffects()
//    {
//        sounds.add(0, TinySound.loadSound("audio/gibberish.wav"));
//    }

    public Image getSprite(String folder, String file)
    {
        try {
            return ImageIO.read(new File("res/sprites/" + folder + "/" + file + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
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
//
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
//
//    public Map<String, Image> tuiltje;
//
//    private void loadSprites()
//    {
//        tuiltje = new HashMap<String, Image>();
//
//        for(int i = 0; i <= 9; i++) tuiltje.put("happy_" + String.valueOf(i), this.getSprite("tuiltje/happy/" + String.valueOf(i)));
//    }


}
