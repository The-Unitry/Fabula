package eu.theunitry.fabula.UNGameEngine.graphics;

import eu.theunitry.fabula.UNGameEngine.launcher.UNResourceLoader;
import eu.theunitry.fabula.helper.Helper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * UNGraphicsView is a JPanel that is used to display UNGraphicObjects,
 * this is also where timers are created.
 */
public class UNGraphicsView extends JPanel
{
    private Image backgroundImage;
    private ArrayList<UNGraphicsObject> objects;
    private ArrayList hud;
    private UNGameScreen gameScreen;
    private boolean hudEnabled, helperDoneTalking;
    private String question, questionDraw, help, helpDraw;
    private Helper helper;
    private Timer timer, timerText, timerQuestion;
    private UNResourceLoader resourceLoader;

    /**
     * UNGraphicsView
     * @param gameScreen
     * @param hudEnabled
     */
    public UNGraphicsView(UNGameScreen gameScreen, boolean hudEnabled)
    {
        this.setGameScreen(gameScreen);
        this.hudEnabled = hudEnabled;
        this.resourceLoader = gameScreen.unResourceLoader;
        objects = new ArrayList<UNGraphicsObject>();
        hud = new ArrayList<UNGraphicsObject>();
        if (hudEnabled)
        {
            timer = new Timer(1, e -> getPanel().repaint());
            timerText = new Timer(100, e -> {
                if (helpDraw.length() < getHelp().length())
                {
                    helpDraw += getHelp().charAt(helpDraw.length());
                    helperDoneTalking = false;
                }
                else
                {
                    if (!helperDoneTalking)
                    {
                        helperDoneTalking = true;
                        getGameScreen().getSounds().get("gibberish").stop();
                    }
                }
            });
            timerQuestion = new Timer(50, e -> {
                if (questionDraw.length() < getQuestion().length())
                {
                    questionDraw += getQuestion().charAt(questionDraw.length());
                }
                else
                {
                    getHelper().setQuestioningDone(true);
                }
            });
            timer.start();
            timerText.start();
            timerQuestion.start();
        }

        MouseHandler mouseHandler = new MouseHandler();
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);
        question = "";
        questionDraw = "";
        help = "";
        helpDraw = "";
        helperDoneTalking = true;
        if (hudEnabled)
        {
            helper = new Helper(gameScreen);
            helper.setImage(resourceLoader.getSprite("tuiltje/idle", "0"));
        }
    }

    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D gd2 = (Graphics2D) g;
        g.drawImage(getBackgroundImage(), 0, 0, 768, 512, this);
        layerObjects(g);
        layerHUD(g);
    }

    public void setBackgroundImage(Image backgroundImage)
    {
        this.backgroundImage = backgroundImage;
    }

    public Image getBackgroundImage()
    {
        return this.backgroundImage;
    }

    public void layerObjects(Graphics g)
    {
        for (UNGraphicsObject object : objects)
        {
            BufferedImage image = (BufferedImage) object.getImage();
            Graphics2D g2d = (Graphics2D) g;
            AffineTransform at = new AffineTransform();

            at.translate(object.getX() + object.getWidth() / 2, object.getY() + object.getHeight() / 2);
            at.rotate(Math.toRadians(object.getAngle()));
            at.scale((double) 1 / image.getWidth() * object.getWidth(), (double) 1 / image.getHeight() * object.getHeight());
            at.translate(-object.getXOffset(), -object.getYOffset());

            g2d.drawImage(image, at, null);
        }
    }

    public void layerHUD(Graphics g)
    {
        if (hudEnabled)
        {
            g.setColor(UNColor.HUD_COLOR);
            g.fillRect(0, 0, 768, 64);
            if (!getHelp().isEmpty())
            {
                g.fillRect(0, 464, 575, 48);
            }

            g.setColor(Color.white);

            g.setFont(new Font("Minecraftia", Font.PLAIN, 18));
            g.drawString("Level " + this.getGameScreen().getLevel() + "/" + this.getGameScreen().getLevelMax(), 11, 38);

            if (!getHelp().isEmpty())
            {
                g.setFont(new Font("Minecraftia", Font.PLAIN, 12));
                g.drawString(this.helpDraw, 11, 492);
            }

            g.setFont(new Font("Minecraftia", Font.PLAIN, 15));
            int stringLen = (int) g.getFontMetrics().getStringBounds(questionDraw, g).getWidth();
            g.drawString(questionDraw, 754 - stringLen, 38);

            g.drawImage(helper.getImage(), 590, 358, helper.getImage().getWidth(this) * 5, helper.getImage().getHeight(this) * 5, this);
        }
    }

    public void setHudEnabled(boolean hudEnabled)
    {
        this.hudEnabled = hudEnabled;
    }

    public void setQuestion(String question)
    {
        this.question = question;
    }

    public String getQuestion()
    {
        return question;
    }

    public void setHelp(String help)
    {
        this.help = help;
        this.helpDraw = "";
        gameScreen.getSounds().get("gibberish").play();
    }

    public String getHelp()
    {
        return help;
    }

    public JPanel getPanel()
    {
        return this;
    }

    public void addObject(UNGraphicsObject object)
    {
        objects.add(object);
    }

    public void removeObject(UNGraphicsObject object)
    {
        objects.remove(object);
    }

    public Helper getHelper()
    {
        return this.helper;
    }

    public boolean isHelperDoneTalking()
    {
        return helperDoneTalking;
    }

    public UNGameScreen getGameScreen() {
        return this.gameScreen;
    }

    public void setGameScreen(UNGameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    public class MouseHandler implements MouseListener, MouseMotionListener
    {
        @Override
        public void mousePressed(MouseEvent e)
        {
            for (UNGraphicsObject object : objects)
            {
                if (e.getX() > object.getX()
                        && e.getY() > object.getY()
                        && e.getX() < object.getX() + object.getWidth()
                        && e.getY() < object.getY() + object.getHeight()
                        )
                {
                    object.setMouseClick(true);

                    if (object.isClickable())
                    {
                        object.setMouseHold(true);

                        object.setMouseXOffset(e.getX() - object.getX());
                        object.setMouseYOffset(e.getY() - object.getY());
                        break;
                    }
                }
            }
        }

        @Override
        public void mouseReleased(MouseEvent e)
        {
            for (UNGraphicsObject object : objects)
            {
                object.setMouseHold(false);
            }
        }

        @Override
        public void mouseDragged(MouseEvent e)
        {
            for (UNGraphicsObject object : objects)
            {
                if (object.getMouseHold())
                {
                    object.setX(e.getX() - object.getMouseXOffset());
                    object.setY(e.getY() - object.getMouseYOffset());
                }
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}

        @Override
        public void mouseMoved(MouseEvent e) {}
    }
}
