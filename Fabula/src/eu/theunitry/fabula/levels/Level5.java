package eu.theunitry.fabula.levels;


import eu.theunitry.fabula.UNGameEngine.graphics.UNGameScreen;
import eu.theunitry.fabula.UNGameEngine.graphics.UNColor;
import eu.theunitry.fabula.UNGameEngine.graphics.UNGraphicsLevel;
import eu.theunitry.fabula.UNGameEngine.graphics.UNGraphicsObject;
import eu.theunitry.fabula.UNGameEngine.launcher.UNLauncher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

/**
 * Level 5
 * Allan de Wit
 */
public class Level5 extends UNGraphicsLevel
{
    private Timer timerWellAnim, timerWellCheck, timerBird;
    private UNGraphicsObject camel1, camel2, camel3, bucket, well;
    private ArrayList<UNGraphicsObject> birds;
    private ArrayList<Integer> birdsFrames;
    private JLabel liter1, liter2, liter3;
    private JButton button;
    private boolean winning;
    private int need, imageIndex1, imageIndex2, imageIndex3, imageIndexWell;
    private String lastHelp;
    private String spriteCamel = "2:5:3:", spriteBucket = "2:5:2:", spriteWell = "2:5:4:", spriteBird = "2:5:1:";

    /**
     * Level 5
     * @param gameScreen
     * @param hudEnabled
     */
    public Level5(UNGameScreen gameScreen, boolean hudEnabled)
    {
        super(gameScreen, hudEnabled);

        this.birdsFrames = new ArrayList<Integer>();
        this.setImageIndex1(new Random().nextInt(3));
        this.setImageIndex2(new Random().nextInt(4));
        this.setImageIndex3(new Random().nextInt(3));
        this.setNeed(this.getImageIndex1() + this.getImageIndex2() + this.getImageIndex3() + 1 + new Random().nextInt(11));
        this.setNeed(Math.min(this.getNeed(), 12));

        this.setQuestion("Vertrek met " + this.getNeed() + " liter in de bakken van de kamelen");
        this.addHelp("Jammer! Je moet " + this.getNeed() + " liter in de bakken stoppen");
        this.addHelp("Helaas! Er moet " + this.getNeed() + " liter in de bakken zitten");
        this.setHelp("Sleep water in de bakken van de kamelen om het getal te verhogen");
        this.setBackgroundImage(this.getGameScreen().getBackgrounds().get("desert"));

        this.setWinning(false);
        this.setLastHelp(getHelp());

        this.setBirds(new ArrayList<UNGraphicsObject>());
        double birdScale;

        for (int i = 0; i < 5; i++)
        {
            birdScale = 0.5 + new Random().nextDouble();
            this.getBirds().add(new UNGraphicsObject(this.getGameScreen().getWindow().getFrame(), new Random().nextInt(this.getGameScreen().getWindow().getContentWidth()), 70 + new Random().nextInt(130), this.getGameScreen().getSprites().get(spriteBird + "0"), false, (int) (15.0 * birdScale), (int) (18.0 * birdScale)));
            this.birdsFrames.add(new Random().nextInt(3));
        }

        for (UNGraphicsObject bird : this.getBirds())
        {
            this.addObject(bird);
        }

        this.setCamel1(new UNGraphicsObject(this.getGameScreen().getWindow().getFrame(), 250, 380, this.getGameScreen().getSprites().get(spriteCamel + "0"), false, 19 * 4, 14 * 4));
        this.setCamel2(new UNGraphicsObject(this.getGameScreen().getWindow().getFrame(), 340, 390, this.getGameScreen().getSprites().get(spriteCamel + "0"), false, 19 * 4, 14 * 4));
        this.setCamel3(new UNGraphicsObject(this.getGameScreen().getWindow().getFrame(), 440, 360, this.getGameScreen().getSprites().get(spriteCamel + "0"), false, 19 * 4, 14 * 4));
        this.setBucket(new UNGraphicsObject(this.getGameScreen().getWindow().getFrame(), 100, 340, this.getGameScreen().getSprites().get(spriteBucket + "0"), true, 8 * 4, 8 * 4));
        this.setWell(new UNGraphicsObject(this.getGameScreen().getWindow().getFrame(), 70, 320, this.getGameScreen().getSprites().get(spriteWell + "0"), false, 22 * 4, 30 * 4));
        this.setImageIndexWell(0);

        this.addObject(this.getCamel1());
        this.addObject(this.getCamel2());
        this.addObject(this.getCamel3());
        this.addObject(this.getBucket());
        this.addObject(this.getWell());

        this.setLiter1(new JLabel(String.valueOf(this.getImageIndex1()) + "l"));
        this.setLiter2(new JLabel(String.valueOf(this.getImageIndex2()) + "l"));
        this.setLiter3(new JLabel(String.valueOf(this.getImageIndex3()) + "l"));
        this.getLiter1().setBounds(268, 354, 100, 100);
        this.getLiter1().setFont(new Font("Minecraftia", Font.PLAIN, 20));
        this.getLiter1().setForeground(new Color(51, 51, 51));
        this.add(this.getLiter1());
        this.getLiter2().setBounds(358, 364, 100, 100);
        this.getLiter2().setFont(new Font("Minecraftia", Font.PLAIN, 20));
        this.getLiter2().setForeground(new Color(51, 51, 51));
        this.add(this.getLiter2());
        this.getLiter3().setBounds(458, 334, 100, 100);
        this.getLiter3().setFont(new Font("Minecraftia", Font.PLAIN, 20));
        this.getLiter3().setForeground(new Color(51, 51, 51));
        this.add(this.getLiter3());

        this.getCamel1().setImage(gameScreen.getSprites().get(spriteCamel + String.valueOf(this.getImageIndex1() + 1)));
        this.getCamel2().setImage(gameScreen.getSprites().get(spriteCamel + String.valueOf(this.getImageIndex2() + 1)));
        this.getCamel3().setImage(gameScreen.getSprites().get(spriteCamel + String.valueOf(this.getImageIndex3() + 1)));

        this.button = new JButton("Vertrek");

        this.setLayout(null);
        this.button.setBounds(618, 64, 150, 50);
        this.button.setBackground(UNColor.HUD_COLOR);
        this.button.setFont(new Font("Minecraftia", Font.PLAIN, 15));
        this.button.setForeground(Color.white);
        this.button.setOpaque(true);

        /**
         * Reset Default Styling
         */
        this.button.setFocusPainted(false);
        this.button.setBorderPainted(false);

        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (Objects.equals(button.getText(), "Door"))
                {
                    getGameScreen().getSounds().get("gibberish").stop();
                    if (getGameScreen().getLevel() < getGameScreen().getLevelMax())
                    {
                        if (isWinning())
                        {
                            getGameScreen().addLevel();
                        }
                        getGameScreen().switchPanel(new Level5(getGameScreen(), true));
                    }
                    else
                    {
                        getGameScreen().switchPanel(new UNLauncher(getGameScreen()));
                    }
                }
                if (isHelperDoneTalking())
                {
                    if (getImageIndex1() + getImageIndex2() + getImageIndex3() == getNeed())
                    {
                        getHelper().setState(3);
                        setHelp("Goed gedaan, we kunnen er weer tegen aan!");
                        setWinning(true);
                        getBucket().setClickable(false);
                        button.setText("Door");
                    }
                    else
                    {
                        addMistake();
                        if (getMistakes() < 3)
                        {
                            getHelper().setState(4);
                            while(getLastHelp() == getHelp())
                            {
                                setHelp(getHelpList().get(new Random().nextInt(getHelpList().size())));
                            }
                            setLastHelp(getHelp());
                        }
                        else
                        {
                            getHelper().setState(4);
                            int current = getImageIndex1() + getImageIndex2() + getImageIndex3();
                            if (current < getNeed())
                            {
                                setHelp("Jammer, er moest nog " + (getNeed() - current) +
                                        " liter bij. Want " + current + " plus " +
                                        (getNeed() - current)  + " is " + getNeed() + "."
                                );
                            }
                            else
                            {
                                setHelp("Jammer, er moest " + (current - getNeed()) +
                                        " liter af. Want " + current + " min " +
                                        (current - getNeed())  + " is " + getNeed() + "."
                                );
                            }

                            button.setText("Door");
                        }
                    }
                }
            }
        });

        this.getPanel().add(button);

        timerWellAnim = new Timer(250, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (getImageIndexWell() <= 2 || getBucket().getImage() == getGameScreen().getSprites().get(spriteBucket + "0"))
                {
                    if (getBucket().getImage() == getGameScreen().getSprites().get(spriteBucket + "1"))
                    {
                        getBucket().setY(getBucket().getY() + 20);
                        setImageIndexWell(getImageIndexWell() + 1);
                    }
                    else
                    {
                        if (getImageIndexWell() > 0)
                        {
                            if (getImageIndexWell() < 4)
                            {
                                getBucket().setY(getBucket().getY() - 20);
                            }
                            setImageIndexWell(getImageIndexWell() - 1);
                        }
                        else
                        {
                            timerWellAnim.stop();
                            getBucket().setClickable(true);
                        }
                    }
                }
                else if (getImageIndexWell() < 5)
                {
                    setImageIndexWell(getImageIndexWell() + 1);
                }
                else
                {
                    setImageIndexWell(getImageIndexWell() - 1);
                    getBucket().setImage(getGameScreen().getSprites().get(spriteBucket + "0"));
                }
                getWell().setImage(getGameScreen().getSprites().get(spriteWell + String.valueOf(Math.min(getImageIndexWell(), 2))));
            }
        });

        timerWellCheck = new Timer(1, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (getBucket().getHitbox().intersects(getWell().getHitbox()))
                {
                    if (getBucket().getImage() == getGameScreen().getSprites().get(spriteBucket + "1") && !timerWellAnim.isRunning() && !getBucket().getMouseHold())
                    {
                        getBucket().setX(100);
                        getBucket().setY(340);
                        getBucket().setMouseHold(false);
                        getBucket().setClickable(false);
                        timerWellAnim.start();
                    }
                }
                if (getBucket().getImage() == getGameScreen().getSprites().get(spriteBucket + "0") && !getBucket().getMouseHold())
                {
                    if (getBucket().getHitbox().intersects(getCamel1().getHitbox()))
                    {
                        if (getImageIndex1() < 4)
                        {
                            setImageIndex1(getImageIndex1() + 1);
                            getCamel1().setImage(getGameScreen().getSprites().get(spriteCamel + String.valueOf(getImageIndex1() + 1)));
                            getBucket().setImage(getGameScreen().getSprites().get(spriteBucket + "1"));
                            getLiter1().setText(String.valueOf(getImageIndex1()) + "l");
                        }
                    }
                    if (getBucket().getHitbox().intersects(getCamel2().getHitbox()))
                    {
                        if (getImageIndex2() < 4)
                        {
                            setImageIndex2(getImageIndex2() + 1);
                            getCamel2().setImage(getGameScreen().getSprites().get(spriteCamel + String.valueOf(getImageIndex2() + 1)));
                            getBucket().setImage(getGameScreen().getSprites().get(spriteBucket + "1"));
                            getLiter2().setText(String.valueOf(getImageIndex2()) + "l");
                        }
                    }
                    if (getBucket().getHitbox().intersects(getCamel3().getHitbox()))
                    {
                        if (getImageIndex3() < 4)
                        {
                            setImageIndex3(getImageIndex3() + 1);
                            getCamel3().setImage(getGameScreen().getSprites().get(spriteCamel + String.valueOf(getImageIndex3() + 1)));
                            getBucket().setImage(getGameScreen().getSprites().get(spriteBucket + "1"));
                            getLiter3().setText(String.valueOf(getImageIndex3()) + "l");
                        }
                    }
                }
                if (getCamel1().isMouseClick())
                {
                    getCamel1().setMouseClick(false);
                    if (!getBucket().getHitbox().intersects(getCamel1().getHitbox()))
                    {
                        if (getImageIndex1() > 0)
                        {
                            setImageIndex1(getImageIndex1() - 1);
                            getCamel1().setImage(getGameScreen().getSprites().get(spriteCamel + String.valueOf(getImageIndex1() + 1)));
                            getLiter1().setText(String.valueOf(getImageIndex1()) + "l");
                        }
                    }
                }
                if (getCamel2().isMouseClick())
                {
                    getCamel2().setMouseClick(false);
                    if (!getBucket().getHitbox().intersects(getCamel2().getHitbox()))
                    {
                        if (getImageIndex2() > 0)
                        {
                            setImageIndex2(getImageIndex2() - 1);
                            getCamel2().setImage(getGameScreen().getSprites().get(spriteCamel + String.valueOf(getImageIndex2() + 1)));
                            getLiter2().setText(String.valueOf(getImageIndex2()) + "l");
                        }
                    }
                }
                if (getCamel3().isMouseClick())
                {
                    getCamel3().setMouseClick(false);
                    if (!getBucket().getHitbox().intersects(getCamel3().getHitbox()))
                    {
                        if (getImageIndex3() > 0)
                        {
                            setImageIndex3(getImageIndex3() - 1);
                            getCamel3().setImage(getGameScreen().getSprites().get(spriteCamel + String.valueOf(getImageIndex3() + 1)));
                            getLiter3().setText(String.valueOf(getImageIndex3()) + "l");
                        }
                    }
                }
            }
        });
        timerWellCheck.start();

        timerBird = new Timer(100, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                for (UNGraphicsObject bird : getBirds())
                {
                    if (birdsFrames.get(getBirds().indexOf(bird)) < 3)
                    {
                        birdsFrames.set(getBirds().indexOf(bird), birdsFrames.get(getBirds().indexOf(bird)) + 1);
                    }
                    else
                    {
                        birdsFrames.set(getBirds().indexOf(bird), 0);
                    }
                    bird.setImage(getGameScreen().getSprites().get(spriteBird + String.valueOf(birdsFrames.get(getBirds().indexOf(bird)))));
                    bird.setX(bird.getX() + Math.max((bird.getWidth() / 10), 1));
                    if (bird.getX() > getGameScreen().getWindow().getContentWidth() + 30)
                    {
                        bird.setX(-30);
                    }
                }
            }
        });
        timerBird.start();
    }

    public UNGraphicsObject getCamel1()
    {
        return this.camel1;
    }

    public void setCamel1(UNGraphicsObject camel1)
    {
        this.camel1 = camel1;
    }

    public UNGraphicsObject getCamel2()
    {
        return this.camel2;
    }

    public void setCamel2(UNGraphicsObject camel2)
    {
        this.camel2 = camel2;
    }

    public UNGraphicsObject getCamel3()
    {
        return this.camel3;
    }

    public void setCamel3(UNGraphicsObject camel3)
    {
        this.camel3 = camel3;
    }

    public UNGraphicsObject getBucket()
    {
        return this.bucket;
    }

    public void setBucket(UNGraphicsObject bucket)
    {
        this.bucket = bucket;
    }

    public UNGraphicsObject getWell()
    {
        return this.well;
    }

    public void setWell(UNGraphicsObject well)
    {
        this.well = well;
    }

    public JLabel getLiter1()
    {
        return this.liter1;
    }

    public void setLiter1(JLabel liter1)
    {
        this.liter1 = liter1;
    }

    public JLabel getLiter2()
    {
        return this.liter2;
    }

    public void setLiter2(JLabel liter2)
    {
        this.liter2 = liter2;
    }

    public JLabel getLiter3()
    {
        return this.liter3;
    }

    public void setLiter3(JLabel liter3)
    {
        this.liter3 = liter3;
    }

    public boolean isWinning()
    {
        return this.winning;
    }

    public void setWinning(boolean winning)
    {
        this.winning = winning;
    }

    public int getNeed()
    {
        return this.need;
    }

    public void setNeed(int need)
    {
        this.need = need;
    }

    public int getImageIndex1()
    {
        return this.imageIndex1;
    }

    public void setImageIndex1(int imageIndex1)
    {
        this.imageIndex1 = imageIndex1;
    }

    public int getImageIndex2()
    {
        return this.imageIndex2;
    }

    public void setImageIndex2(int imageIndex2)
    {
        this.imageIndex2 = imageIndex2;
    }

    public int getImageIndex3()
    {
        return this.imageIndex3;
    }

    public void setImageIndex3(int imageIndex3)
    {
        this.imageIndex3 = imageIndex3;
    }

    public int getImageIndexWell()
    {
        return this.imageIndexWell;
    }

    public void setImageIndexWell(int imageIndexWell)
    {
        this.imageIndexWell = imageIndexWell;
    }

    public String getLastHelp()
    {
        return this.lastHelp;
    }

    public void setLastHelp(String lastHelp)
    {
        this.lastHelp = lastHelp;
    }

    public ArrayList<UNGraphicsObject> getBirds()
    {
        return this.birds;
    }

    public void setBirds(ArrayList<UNGraphicsObject> birds)
    {
        this.birds = birds;
    }
}
