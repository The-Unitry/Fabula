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

    private void loadSprites()
    {
        try
        {
            String prefix_animations = "res/animations/";

            /**
             * Background
             */
            String prefix_bg = "res/backgrounds/";

            backgrounds.add(0, ImageIO.read(new File(prefix_bg + "underwater.png")));
            backgrounds.add(1, ImageIO.read(new File(prefix_bg + "moon.png")));
            backgrounds.add(2, ImageIO.read(new File(prefix_bg + "space.png")));
            backgrounds.add(3, ImageIO.read(new File(prefix_bg + "supermarket.png")));
            backgrounds.add(4, ImageIO.read(new File(prefix_bg + "jungle.png")));

            /**
             * Tuiltje - Idle
             */
            String prefix_idle = prefix_animations + "tuiltje/idle/";

            sprites.add(0, ImageIO.read(new File(prefix_idle + "idle0.png")));
            sprites.add(1, ImageIO.read(new File(prefix_idle + "idle1.png")));
            sprites.add(2, ImageIO.read(new File(prefix_idle + "idle2.png")));
            sprites.add(3, ImageIO.read(new File(prefix_idle + "idle3.png")));
            sprites.add(4, ImageIO.read(new File(prefix_idle + "idle4.png")));
            sprites.add(5, ImageIO.read(new File(prefix_idle + "idle5.png")));

            /**
             * Tuiltje - Flapping
             */
            String prefix_flap = prefix_animations + "tuiltje/flapping/";

            sprites.add(6, ImageIO.read(new File(prefix_flap + "flapping0.png")));
            sprites.add(7, ImageIO.read(new File(prefix_flap + "flapping1.png")));
            sprites.add(8, ImageIO.read(new File(prefix_flap + "flapping2.png")));
            sprites.add(9, ImageIO.read(new File(prefix_flap + "flapping3.png")));
            sprites.add(10, ImageIO.read(new File(prefix_flap + "flapping4.png")));
            sprites.add(11, ImageIO.read(new File(prefix_flap + "flapping5.png")));

            /**
             * Tuiltje - Sad
             */
            String prefix_sad = prefix_animations + "tuiltje/sad/";

            sprites.add(12, ImageIO.read(new File(prefix_sad + "sad0.png")));
            sprites.add(13, ImageIO.read(new File(prefix_sad + "sad1.png")));
            sprites.add(14, ImageIO.read(new File(prefix_sad + "sad2.png")));
            sprites.add(15, ImageIO.read(new File(prefix_sad + "sad3.png")));
            sprites.add(16, ImageIO.read(new File(prefix_sad + "sad4.png")));
            sprites.add(17, ImageIO.read(new File(prefix_sad + "sad5.png")));

            /**
             * Tuiltje - Happy
             */
            String prefix_happy = prefix_animations + "tuiltje/happy/";

            sprites.add(18, ImageIO.read(new File(prefix_happy + "happy0.png")));
            sprites.add(19, ImageIO.read(new File(prefix_happy + "happy1.png")));
            sprites.add(20, ImageIO.read(new File(prefix_happy + "happy2.png")));
            sprites.add(21, ImageIO.read(new File(prefix_happy + "happy3.png")));
            sprites.add(22, ImageIO.read(new File(prefix_happy + "happy4.png")));
            sprites.add(23, ImageIO.read(new File(prefix_happy + "happy5.png")));
            sprites.add(24, ImageIO.read(new File(prefix_happy + "happy6.png")));
            sprites.add(25, ImageIO.read(new File(prefix_happy + "happy7.png")));
            sprites.add(26, ImageIO.read(new File(prefix_happy + "happy8.png")));
            sprites.add(27, ImageIO.read(new File(prefix_happy + "happy9.png")));

            /**
             * Tuiltje - Questioning
             */

            String prefix_question = prefix_animations + "tuiltje/questioning/";

            sprites.add(28, ImageIO.read(new File(prefix_question + "questioning0.png")));
            sprites.add(29, ImageIO.read(new File(prefix_question + "questioning1.png")));
            sprites.add(30, ImageIO.read(new File(prefix_question + "questioning2.png")));
            sprites.add(31, ImageIO.read(new File(prefix_question + "questioning3.png")));
            sprites.add(32, ImageIO.read(new File(prefix_question + "questioning4.png")));
            sprites.add(33, ImageIO.read(new File(prefix_question + "questioning5.png")));
            sprites.add(34, ImageIO.read(new File(prefix_question + "questioning6.png")));
            sprites.add(35, ImageIO.read(new File(prefix_question + "questioning7.png")));
            sprites.add(36, ImageIO.read(new File(prefix_question + "questioning8.png")));
            sprites.add(37, ImageIO.read(new File(prefix_question + "questioning9.png")));

            /**
             * Levels
             */

            String prefix_sprites = "res/sprites/";

            /**
             * Level 0
             */
            sprites.add(38, ImageIO.read(new File(prefix_sprites + "apple.png")));
            sprites.add(39, ImageIO.read(new File(prefix_sprites + "basket.png")));
            sprites.add(40, ImageIO.read(new File(prefix_sprites + "snowball.png")));

            /**
             * Level 3
             */
            sprites.add(41, ImageIO.read(new File(prefix_sprites + "rocket0.png")));
            sprites.add(42, ImageIO.read(new File(prefix_sprites + "rocket1.png")));
            sprites.add(43, ImageIO.read(new File(prefix_sprites + "rocket2.png")));
            sprites.add(44, ImageIO.read(new File(prefix_sprites + "rocket3.png")));
            sprites.add(45, ImageIO.read(new File(prefix_sprites + "moon0.png")));
            sprites.add(46, ImageIO.read(new File(prefix_sprites + "moon1.png")));
            sprites.add(47, ImageIO.read(new File(prefix_sprites + "ufoBlue.png")));
            sprites.add(48, ImageIO.read(new File(prefix_sprites + "ufoGreen.png")));
            sprites.add(49, ImageIO.read(new File(prefix_sprites + "ufoPink.png")));
            sprites.add(50, ImageIO.read(new File(prefix_sprites + "snowball.png")));

            /**
             * Level 7
             */
            sprites.add(51, ImageIO.read(new File(prefix_sprites + "kist_dicht.png")));
            sprites.add(52, ImageIO.read(new File(prefix_sprites + "kist_open.png")));
            sprites.add(53, ImageIO.read(new File(prefix_sprites + "muntje.png")));

            /**
             * Level 8
             */
            sprites.add(54, ImageIO.read(new File(prefix_sprites + "klomp.png")));
            sprites.add(55, ImageIO.read(new File(prefix_sprites + "pickaxe.png")));

            /**
             * Level 11
             */
            sprites.add(56, ImageIO.read(new File(prefix_sprites + "appel.png")));
            sprites.add(57, ImageIO.read(new File(prefix_sprites + "peer.png")));
            sprites.add(58, ImageIO.read(new File(prefix_sprites + "druif.png")));
            sprites.add(59, ImageIO.read(new File(prefix_sprites + "sinaasappel.png")));
            sprites.add(60, ImageIO.read(new File(prefix_sprites + "banaan.png")));
            sprites.add(61, ImageIO.read(new File(prefix_sprites + "kassa.png")));

            /**
             * Level 12
             */
            sprites.add(62, ImageIO.read(new File(prefix_sprites + "aap_bruin.png")));
            sprites.add(63, ImageIO.read(new File(prefix_sprites + "aap_wit.png")));
            sprites.add(64, ImageIO.read(new File(prefix_sprites + "kooi.png")));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
