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
            backgrounds.add(5, ImageIO.read(new File(prefix_bg + "mine.png")));

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
            sprites.add(51, ImageIO.read(new File(prefix_sprites + "chest_closed.png")));
            sprites.add(52, ImageIO.read(new File(prefix_sprites + "chest_open.png")));
            sprites.add(53, ImageIO.read(new File(prefix_sprites + "coin.png")));

            /**
             * Level 8
             */
            sprites.add(54, ImageIO.read(new File(prefix_sprites + "nugget.png")));
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

            sprites.add(62, ImageIO.read(new File("res/sprites/Level4/Cable_1.png")));
            sprites.add(63, ImageIO.read(new File("res/sprites/Level4/Cable_2.png")));
            sprites.add(64, ImageIO.read(new File("res/sprites/Level4/Cable_3.png")));
            sprites.add(65, ImageIO.read(new File("res/sprites/Level4/Cable_4.png")));
            sprites.add(66, ImageIO.read(new File("res/sprites/Level4/Cable_5.png")));
            sprites.add(67, ImageIO.read(new File("res/sprites/Level4/Cable_6.png")));

            /**
             * Level 4
             */
            sprites.add(68, ImageIO.read(new File("res/sprites/Level4/machine_1_0.png")));
            sprites.add(69, ImageIO.read(new File("res/sprites/Level4/machine_2_0.png")));
            sprites.add(70, ImageIO.read(new File("res/sprites/Level4/machine_3_0.png")));
            sprites.add(71, ImageIO.read(new File("res/sprites/Level4/machine_4_0.png")));
            sprites.add(72, ImageIO.read(new File("res/sprites/Level4/machine_1_1.png")));
            sprites.add(73, ImageIO.read(new File("res/sprites/Level4/machine_2_1.png")));
            sprites.add(74, ImageIO.read(new File("res/sprites/Level4/machine_3_1.png")));
            sprites.add(75, ImageIO.read(new File("res/sprites/Level4/machine_4_1.png")));
            sprites.add(76, ImageIO.read(new File("res/sprites/Level4/machine_1_2.png")));
            sprites.add(77, ImageIO.read(new File("res/sprites/Level4/machine_2_2.png")));
            sprites.add(78, ImageIO.read(new File("res/sprites/Level4/machine_3_2.png")));
            sprites.add(79, ImageIO.read(new File("res/sprites/Level4/machine_4_2.png")));
            sprites.add(80, ImageIO.read(new File("res/sprites/Level4/machine_1_3.png")));
            sprites.add(81, ImageIO.read(new File("res/sprites/Level4/machine_2_3.png")));
            sprites.add(82, ImageIO.read(new File("res/sprites/Level4/machine_3_3.png")));
            sprites.add(83, ImageIO.read(new File("res/sprites/Level4/machine_4_3.png")));
            sprites.add(84, ImageIO.read(new File("res/sprites/Level4/machine_1_4.png")));
            sprites.add(85, ImageIO.read(new File("res/sprites/Level4/machine_2_4.png")));
            sprites.add(86, ImageIO.read(new File("res/sprites/Level4/machine_3_4.png")));
            sprites.add(87, ImageIO.read(new File("res/sprites/Level4/machine_4_4.png")));
            sprites.add(88, ImageIO.read(new File("res/sprites/Level4/machine_1_5.png")));
            sprites.add(89, ImageIO.read(new File("res/sprites/Level4/machine_2_5.png")));
            sprites.add(90, ImageIO.read(new File("res/sprites/Level4/machine_3_5.png")));
            sprites.add(91, ImageIO.read(new File("res/sprites/Level4/machine_4_5.png")));

            sprites.add(92, ImageIO.read(new File("res/sprites/Level4/Support_0_1.png")));
            sprites.add(93, ImageIO.read(new File("res/sprites/Level4/Support_0_2.png")));
            sprites.add(94, ImageIO.read(new File("res/sprites/Level4/Support_0_3.png")));
            sprites.add(95, ImageIO.read(new File("res/sprites/Level4/Support_0_4.png")));
            sprites.add(96, ImageIO.read(new File("res/sprites/Level4/Support_0_5.png")));
            sprites.add(97, ImageIO.read(new File("res/sprites/Level4/Support_0_6.png")));
            sprites.add(98, ImageIO.read(new File("res/sprites/Level4/Support_0_7.png")));
            sprites.add(99, ImageIO.read(new File("res/sprites/Level4/Support_1_1.png")));
            sprites.add(100, ImageIO.read(new File("res/sprites/Level4/Support_1_2.png")));
            sprites.add(101, ImageIO.read(new File("res/sprites/Level4/Support_1_3.png")));
            sprites.add(102, ImageIO.read(new File("res/sprites/Level4/Support_1_4.png")));
            sprites.add(103, ImageIO.read(new File("res/sprites/Level4/Support_1_5.png")));
            sprites.add(104, ImageIO.read(new File("res/sprites/Level4/Support_1_6.png")));
            sprites.add(105, ImageIO.read(new File("res/sprites/Level4/Support_1_7.png")));

            sprites.add(106, ImageIO.read(new File("res/sprites/Level4/Rocket_0.png")));
            sprites.add(107, ImageIO.read(new File("res/sprites/Level4/Rocket_1.png")));
            sprites.add(108, ImageIO.read(new File("res/sprites/Level4/Rocket_2.png")));
            sprites.add(109, ImageIO.read(new File("res/sprites/Level4/Rocket_3.png")));
            sprites.add(110, ImageIO.read(new File("res/sprites/Level4/Rocket_4.png")));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
