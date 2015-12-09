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
        this.loadBackgrounds();
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

        /**
         * Level 1 & 2
         */
        sprites.put("2:1:1", this.getSprite("apple"));
        sprites.put("2:1:2", this.getSprite("basket"));

        /**
         * Level 3
         */
        sprites.put("2:3:1", this.getSprite("levels/3/moon-0"));
        sprites.put("2:3:2", this.getSprite("levels/3/moon-1"));
        sprites.put("2:3:3", this.getSprite("levels/3/rocket-0"));
        sprites.put("2:3:4", this.getSprite("levels/3/rocket-1"));
        sprites.put("2:3:5", this.getSprite("levels/3/rocket-2"));
        sprites.put("2:3:6", this.getSprite("levels/3/rocket-3"));
        sprites.put("2:3:7", this.getSprite("levels/3/ufo-blue"));
        sprites.put("2:3:8", this.getSprite("levels/3/ufo-green"));
        sprites.put("2:3:9", this.getSprite("levels/3/ufo-pink"));
        sprites.put("2:3:10", this.getSprite("levels/3/snowball"));

        /**
         * Level 4
         */
        //Machine
            //State 1
            for(int i = 0; i <= 5; i++) sprites.put("2:4:1" + String.valueOf(i), this.getSprite("levels/4/machine-1-" + String.valueOf(i)));
            //State 2
            for(int i = 0; i <= 5; i++) sprites.put("2:4:2" + String.valueOf(i), this.getSprite("levels/4/machine-2-" + String.valueOf(i)));
            //State 3
            for(int i = 0; i <= 5; i++) sprites.put("2:4:3" + String.valueOf(i), this.getSprite("levels/4/machine-3-" + String.valueOf(i)));
            //State 4
            for(int i = 0; i <= 5; i++) sprites.put("2:4:4" + String.valueOf(i), this.getSprite("levels/4/machine-4-" + String.valueOf(i)));

        //Cable
        for(int i = 1; i <= 6; i++) sprites.put("2:1:5" + String.valueOf(i), this.getSprite("levels/4/cable-" + String.valueOf(i)));
        //Support
            //Support R
            for(int i = 1; i <= 7; i++) sprites.put("1:1:6" + String.valueOf(i), this.getSprite("levels/4/support-0-" + String.valueOf(i)));
            //Support L
            for(int i = 1; i <= 7; i++) sprites.put("1:1:7" + String.valueOf(i), this.getSprite("Levels/4/support-1-" + String.valueOf(i)));
        //Rocket
        for(int i = 0; i <= 4; i++) sprites.put("1:1:8" + String.valueOf(i), this.getSprite("Levels/4/rocket-" + String.valueOf(i)));
        /**
         * Level 5
         */
        sprites.put("2:5:1:1", this.getSprite("levels/5/bird-0"));
        sprites.put("2:5:1:2", this.getSprite("levels/5/bird-1"));
        sprites.put("2:5:1:3", this.getSprite("levels/5/bird-2"));
        sprites.put("2:5:2:1", this.getSprite("levels/5/bucket-0"));
        sprites.put("2:5:2:2", this.getSprite("levels/5/bucket-1"));
        sprites.put("2:5:3:1", this.getSprite("levels/5/camel-0"));
        sprites.put("2:5:3:2", this.getSprite("levels/5/camel-1"));
        sprites.put("2:5:3:3", this.getSprite("levels/5/camel-2"));
        sprites.put("2:5:3:4", this.getSprite("levels/5/camel-3"));
        sprites.put("2:5:3:5", this.getSprite("levels/5/camel-4"));
        sprites.put("2:5:4:1", this.getSprite("levels/5/well-0"));
        sprites.put("2:5:4:2", this.getSprite("levels/5/well-0"));
        sprites.put("2:5:4:3", this.getSprite("levels/5/well-0"));

        /**
         * Level 6
         */
        sprites.put("2:6:1:1", this.getSprite("levels/6/reindeer-0"));
        sprites.put("2:6:1:2", this.getSprite("levels/6/reindeer-1"));
        sprites.put("2:6:1:3", this.getSprite("levels/6/reindeer-2"));
        sprites.put("2:6:2", this.getSprite("levels/6/snow"));
        sprites.put("2:6:3", this.getSprite("levels/6/snowball"));
        sprites.put("2:6:4:1", this.getSprite("levels/6/snowtree"));
        sprites.put("2:6:4:2", this.getSprite("levels/6/snowtree-2"));

        /**
         * Level 7
         */
        sprites.put("2:7:1:1", this.getSprite("levels/7/chest-closed"));
        sprites.put("2:7:1:2", this.getSprite("levels/7/chest-open"));
        sprites.put("2:7:2", this.getSprite("levels/7/coin"));

        /**
         * Level 8
         */
        sprites.put("2:8:1", this.getSprite("levels/8/nugget"));
        sprites.put("2:8:2", this.getSprite("levels/8/pickaxe"));

        /**
         * Level 9
         */
        sprites.put("2:9:1", this.getSprite("levels/9/diamond"));
        sprites.put("2:9:2", this.getSprite("levels/9/shelf"));
        sprites.put("2:9:3:1", this.getSprite("levels/9/weight-1"));
        sprites.put("2:9:3:2", this.getSprite("levels/9/weight-2"));
        sprites.put("2:9:3:3", this.getSprite("levels/9/weight-3"));

        /**
         * Level 10
         */
        sprites.put("2:10:1", this.getSprite("levels/10/acorn"));

        /**
         * Level 11
         */
        sprites.put("2:11:1", this.getSprite("levels/11/apple"));
        sprites.put("2:11:2", this.getSprite("levels/11/banana"));
        sprites.put("2:11:3", this.getSprite("levels/11/cashdesk"));
        sprites.put("2:11:4", this.getSprite("levels/11/grape"));
        sprites.put("2:11:5", this.getSprite("levels/11/orange"));
        sprites.put("2:11:6", this.getSprite("levels/11/pear"));

        /**
         * Level 12
         */
        sprites.put("2:12:1", this.getSprite("levels/12/cage"));
        sprites.put("2:12:2:1", this.getSprite("levels/12/monkey-brown"));
        sprites.put("2:12:2:2", this.getSprite("levels/12/monkey-white"));
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
        final String MINECRAFTIA = "res/fonts/Minecraftia.ttf";
        try
        {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(MINECRAFTIA)));
        }
        catch (IOException|FontFormatException e)
        {
            e.printStackTrace();
        }
    }

    private void loadMusic()
    {
        music.put("intro", TinySound.loadMusic("audio/intro.wav"));
        music.put("song2", TinySound.loadMusic("audio/song2.wav"));         // Wooooohoooooooo! When I feel heavy-metal!
        music.put("avalange", TinySound.loadMusic("audio/avalange.wav"));
    }

    private void loadSound()
    {
        sounds.put("gibberish", TinySound.loadSound("audio/gibberish.wav"));
    }

    public Image getSprite(String folder, String file)
    {
        try {
            return ImageIO.read(new File("res/sprites/" + folder + "/" + file + ".png"));
        } catch (IOException e) {
            System.out.println("UNResourceLoader: Cannot find '" + file + "'");
            e.printStackTrace();
            try {
                return ImageIO.read(new File("res/default.png"));
            } catch (IOException e1) {
                e1.printStackTrace();
                return null;
            }
        }
    }
    private Image getSprite(String file)
    {
        return this.getSprite("", file);
    }

    private static Image getBackground(String file)
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
}
