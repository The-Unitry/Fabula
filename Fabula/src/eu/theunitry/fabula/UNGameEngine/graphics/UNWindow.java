package eu.theunitry.fabula.UNGameEngine.graphics;

import eu.theunitry.fabula.UNGameEngine.objects.UNObject;

import javax.swing.*;

/**
 * UNWindow is our variant of JFrame, which it is based upon.
 */
public class UNWindow extends UNObject
{
    private final String TITLE;
    private final int WIDTH;
    private final int HEIGHT;
    public JFrame frame;

    /**
     * UN Window
     * @param title     Title that will be displayed in title-bar & dock
     * @param width     Width of window
     * @param height    Height of window
     */
    public UNWindow(String title, int width, int height)
    {
        this.TITLE = title;
        this.WIDTH = width;
        this.HEIGHT = height;
        this.frame = new JFrame();
        this.init();
        this.frame.setVisible(true);
    }

    public void init()
    {
        ImageIcon img = new ImageIcon("res/icons/icon.png");
        this.frame.setTitle(this.TITLE);
        this.frame.setSize(this.WIDTH, this.HEIGHT);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setResizable(false);
        this.frame.setLocationRelativeTo(null);
        this.frame.setUndecorated(true);
        this.frame.setIconImage(img.getImage());
    }

    public void addPanel(JPanel panel)
    {
        this.frame.add(panel);
    }

    public void removePanel(JPanel panel)
    {
        this.frame.remove(panel);
    }

    public JFrame getFrame()
    {
        return this.frame;
    }

    public int getContentWidth()
    {
        return this.frame.getContentPane().getWidth();
    }

    public int getContentHeight()
    {
        return this.frame.getContentPane().getHeight();
    }
}
