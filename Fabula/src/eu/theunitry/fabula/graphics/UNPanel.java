package eu.theunitry.fabula.graphics;

import eu.theunitry.fabula.UNGameScreen;
import eu.theunitry.fabula.objects.UNHelper;
import eu.theunitry.fabula.objects.UNObject;
import kuusisto.tinysound.TinySound;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class UNPanel extends JPanel
{
    private Image backgroundImage;
    private ArrayList<UNGraphicsObject> objects;
    private ArrayList hud;
    private UNGameScreen gameScreen;
    private boolean hudEnabled, helperDoneTalking;
    private String question, questionDraw, help, helpDraw;
    private UNHelper helper;
    private Timer timer, timerText, timerQuestion;

    public UNPanel(UNGameScreen gameScreen, boolean hudEnabled)
    {
        this.gameScreen = gameScreen;
        this.hudEnabled = hudEnabled;
        objects = new ArrayList<UNGraphicsObject>();
        hud = new ArrayList<UNGraphicsObject>();
        if (hudEnabled)
        {
            timer = new Timer(1, new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    getPanel().repaint();
                }
            });
            timerText = new Timer(100, new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
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
                            gameScreen.getSounds().get(0).stop();
                        }
                    }
                }
            });
            timerQuestion = new Timer(50, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (questionDraw.length() < getQuestion().length())
                    {
                        questionDraw += getQuestion().charAt(questionDraw.length());
                    }
                    else
                    {
                        getHelper().setQuestioningDone(true);
                    }
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
            helper = new UNHelper(gameScreen);
            helper.setImage(gameScreen.getSprites().get(0));
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
            at.translate(-image.getWidth() / 2, -image.getHeight() / 2);

            g2d.drawImage(image, at, null);
        }
    }

    public void layerHUD(Graphics g)
    {
        if (hudEnabled)
        {
            g.setColor(new Color(51, 51, 51));
            g.fillRect(0, 0, 768, 64);
            if (!getHelp().isEmpty())
            {
                g.fillRect(0, 416, 575, 96);
            }

            g.setColor(Color.white);

            g.setFont(new Font("Minecraftia", Font.PLAIN, 18));
            g.drawString("Level " + gameScreen.getLevel() + "/" + gameScreen.getLevelMax(), 11, 38);

            if (!getHelp().isEmpty())
            {
                g.setFont(new Font("Minecraftia", Font.PLAIN, 12));
                g.drawString(this.helpDraw, 11, 455);
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
        gameScreen.getSounds().get(0).play();
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

    public UNHelper getHelper()
    {
        return this.helper;
    }

    public boolean isHelperDoneTalking()
    {
        return helperDoneTalking;
    }

    public class MouseHandler implements MouseListener, MouseMotionListener
    {
        @Override
        public void mousePressed(MouseEvent e)
        {
            for (UNGraphicsObject object : objects)
            {
                if (object.getClickable())
                {
                    if (e.getX() > object.getX() && e.getY() > object.getY() && e.getX() < object.getX() + object.getWidth() && e.getY() < object.getY() + object.getHeight()) {
                        object.setMouseHold(true);

                        object.setXOffset(e.getX() - object.getX());
                        object.setYOffset(e.getY() - object.getY());
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
                    object.setX(e.getX() - object.getXOffset());
                    object.setY(e.getY() - object.getYOffset());
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
