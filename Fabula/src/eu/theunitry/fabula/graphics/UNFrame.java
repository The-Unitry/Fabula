package eu.theunitry.fabula.graphics;

import eu.theunitry.fabula.objects.UNObject;

import javax.swing.*;

public class UNFrame extends UNObject {

    private final static String NAME = "Fabula";
    private final static int WIDTH = 768;
    private final static int HEIGHT = 512;
    private final JFrame frame;

    public UNFrame() {
        this.frame = new JFrame();

        this.init(this.frame);
        this.frame.setVisible(true);
    }

    public void init(JFrame frame) {
        frame.setTitle(NAME);
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }

    public int getContentWidth() {
        return this.frame.getContentPane().getWidth();
    }

    public int getContentHeight() {
        return this.frame.getContentPane().getHeight();
    }

}
