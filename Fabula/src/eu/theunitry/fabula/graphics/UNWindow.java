package eu.theunitry.fabula.graphics;

import eu.theunitry.fabula.objects.UNObject;

import javax.swing.*;

public class UNWindow extends UNObject
{

    private final String TITLE;
    private final int WIDTH;
    private final int HEIGHT;
    private final JFrame frame;

    /**
     * UN Window
     * @param title     Title that will be displayed in dock
     * @param width     Width
     * @param height    Height
     */
    public UNWindow(String title, int width, int height) {
        this.TITLE = title;
        this.WIDTH = width;
        this.HEIGHT = height;
        this.frame = new JFrame();

        this.init();
        this.frame.setVisible(true);
    }

    public void init() {
        this.frame.setTitle(this.TITLE);
        this.frame.setSize(this.WIDTH, this.HEIGHT);
        this.frame.setDefaultCloseOperation(this.frame.EXIT_ON_CLOSE);
        this.frame.setResizable(false);
        this.frame.setUndecorated(true);
        this.frame.setLocationRelativeTo(null);
    }

    public void addPanel(JPanel panel) {
        this.frame.add(panel);
    }

    public void removePanel(JPanel panel) {
        this.frame.remove(panel);
    }

    public JFrame getFrame() {
        return this.frame;
    }

    public int getContentWidth() {
        return this.frame.getContentPane().getWidth();
    }

    public int getContentHeight() {
        return this.frame.getContentPane().getHeight();
    }

}
