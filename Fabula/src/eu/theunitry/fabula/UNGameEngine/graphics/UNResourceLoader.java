package eu.theunitry.fabula.UNGameEngine.graphics;

import kuusisto.tinysound.Music;
import kuusisto.tinysound.Sound;
import kuusisto.tinysound.TinySound;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class UNResourceLoader
{
    public ArrayList<Music> music;
    public ArrayList<Sound> sounds;
    public ArrayList<Image> sprites, backgrounds;

    public UNResourceLoader()
    {
        music = new ArrayList<Music>();
        sounds = new ArrayList<Sound>();
        sprites = new ArrayList<Image>();
        backgrounds = new ArrayList<Image>();

        this.loadFonts();
        this.loadMusic();
        this.loadSoundEffects();
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
    }

    private void loadSoundEffects()
    {
        sounds.add(0, TinySound.loadSound("audio/gibberish.wav"));
    }

    private void loadSprites()
    {
        try
        {
            //BACKGROUND PRELOAD
            backgrounds.add(0, ImageIO.read(new File("res/backgrounds/underwater.png")));
            backgrounds.add(1, ImageIO.read(new File("res/backgrounds/moon.png")));
            backgrounds.add(2, ImageIO.read(new File("res/backgrounds/space.png")));
            backgrounds.add(3, ImageIO.read(new File("res/backgrounds/supermarket.png")));

            //SPRITE PRELOAD
            //TUILTJE IDLE
            sprites.add(0, ImageIO.read(new File("res/animations/tuiltje/idle/idle0.png")));
            sprites.add(1, ImageIO.read(new File("res/animations/tuiltje/idle/idle1.png")));
            sprites.add(2, ImageIO.read(new File("res/animations/tuiltje/idle/idle2.png")));
            sprites.add(3, ImageIO.read(new File("res/animations/tuiltje/idle/idle3.png")));
            sprites.add(4, ImageIO.read(new File("res/animations/tuiltje/idle/idle4.png")));
            sprites.add(5, ImageIO.read(new File("res/animations/tuiltje/idle/idle5.png")));
            //TUILTJE FLAPPING
            sprites.add(6, ImageIO.read(new File("res/animations/tuiltje/flapping/flapping0.png")));
            sprites.add(7, ImageIO.read(new File("res/animations/tuiltje/flapping/flapping1.png")));
            sprites.add(8, ImageIO.read(new File("res/animations/tuiltje/flapping/flapping2.png")));
            sprites.add(9, ImageIO.read(new File("res/animations/tuiltje/flapping/flapping3.png")));
            sprites.add(10, ImageIO.read(new File("res/animations/tuiltje/flapping/flapping4.png")));
            sprites.add(11, ImageIO.read(new File("res/animations/tuiltje/flapping/flapping5.png")));
            //TUILTJE SAD
            sprites.add(12, ImageIO.read(new File("res/animations/tuiltje/sad/sad0.png")));
            sprites.add(13, ImageIO.read(new File("res/animations/tuiltje/sad/sad1.png")));
            sprites.add(14, ImageIO.read(new File("res/animations/tuiltje/sad/sad2.png")));
            sprites.add(15, ImageIO.read(new File("res/animations/tuiltje/sad/sad3.png")));
            sprites.add(16, ImageIO.read(new File("res/animations/tuiltje/sad/sad4.png")));
            sprites.add(17, ImageIO.read(new File("res/animations/tuiltje/sad/sad5.png")));
            //TUILTJE HAPPY
            sprites.add(18, ImageIO.read(new File("res/animations/tuiltje/happy/happy0.png")));
            sprites.add(19, ImageIO.read(new File("res/animations/tuiltje/happy/happy1.png")));
            sprites.add(20, ImageIO.read(new File("res/animations/tuiltje/happy/happy2.png")));
            sprites.add(21, ImageIO.read(new File("res/animations/tuiltje/happy/happy3.png")));
            sprites.add(22, ImageIO.read(new File("res/animations/tuiltje/happy/happy4.png")));
            sprites.add(23, ImageIO.read(new File("res/animations/tuiltje/happy/happy5.png")));
            sprites.add(24, ImageIO.read(new File("res/animations/tuiltje/happy/happy6.png")));
            sprites.add(25, ImageIO.read(new File("res/animations/tuiltje/happy/happy7.png")));
            sprites.add(26, ImageIO.read(new File("res/animations/tuiltje/happy/happy8.png")));
            sprites.add(27, ImageIO.read(new File("res/animations/tuiltje/happy/happy9.png")));
            //TUILTJE QUESTIONING
            sprites.add(28, ImageIO.read(new File("res/animations/tuiltje/questioning/questioning0.png")));
            sprites.add(29, ImageIO.read(new File("res/animations/tuiltje/questioning/questioning1.png")));
            sprites.add(30, ImageIO.read(new File("res/animations/tuiltje/questioning/questioning2.png")));
            sprites.add(31, ImageIO.read(new File("res/animations/tuiltje/questioning/questioning3.png")));
            sprites.add(32, ImageIO.read(new File("res/animations/tuiltje/questioning/questioning4.png")));
            sprites.add(33, ImageIO.read(new File("res/animations/tuiltje/questioning/questioning5.png")));
            sprites.add(34, ImageIO.read(new File("res/animations/tuiltje/questioning/questioning6.png")));
            sprites.add(35, ImageIO.read(new File("res/animations/tuiltje/questioning/questioning7.png")));
            sprites.add(36, ImageIO.read(new File("res/animations/tuiltje/questioning/questioning8.png")));
            sprites.add(37, ImageIO.read(new File("res/animations/tuiltje/questioning/questioning9.png")));
            //LEVEL0
            sprites.add(38, ImageIO.read(new File("res/sprites/apple.png")));
            sprites.add(39, ImageIO.read(new File("res/sprites/basket.png")));
            sprites.add(40, ImageIO.read(new File("res/sprites/snowball.png")));
            //LEVEL3
            sprites.add(41, ImageIO.read(new File("res/sprites/rocket0.png")));
            sprites.add(42, ImageIO.read(new File("res/sprites/rocket1.png")));
            sprites.add(43, ImageIO.read(new File("res/sprites/rocket2.png")));
            sprites.add(44, ImageIO.read(new File("res/sprites/rocket3.png")));
            sprites.add(45, ImageIO.read(new File("res/sprites/moon0.png")));
            sprites.add(46, ImageIO.read(new File("res/sprites/moon1.png")));
            sprites.add(47, ImageIO.read(new File("res/sprites/ufoBlue.png")));
            sprites.add(48, ImageIO.read(new File("res/sprites/ufoGreen.png")));
            sprites.add(49, ImageIO.read(new File("res/sprites/ufoPink.png")));
            sprites.add(50, ImageIO.read(new File("res/sprites/snowball.png")));
            //LEVEL7
            sprites.add(51, ImageIO.read(new File("res/sprites/kist_dicht.png")));
            sprites.add(52, ImageIO.read(new File("res/sprites/kist_open.png")));
            sprites.add(53, ImageIO.read(new File("res/sprites/muntje.png")));
            //LEVEL8
            sprites.add(54, ImageIO.read(new File("res/sprites/klomp.png")));
            sprites.add(55, ImageIO.read(new File("res/sprites/pickaxe.png")));
            //LEVEL11
            sprites.add(56, ImageIO.read(new File("res/sprites/appel.png")));
            sprites.add(57, ImageIO.read(new File("res/sprites/peer.png")));
            sprites.add(58, ImageIO.read(new File("res/sprites/druif.png")));
            sprites.add(59, ImageIO.read(new File("res/sprites/sinaasappel.png")));
            sprites.add(60, ImageIO.read(new File("res/sprites/banaan.png")));
            sprites.add(61, ImageIO.read(new File("res/sprites/kassa.png")));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
