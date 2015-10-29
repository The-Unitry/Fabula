package eu.theunitry.fabula.graphics;

import eu.theunitry.fabula.objects.UNObject;

import javax.swing.*;
import java.awt.*;

public class UNPanel extends UNObject
{

    private final JPanel panel;

    public UNPanel() {
        this.panel = new JPanel();
    }

    public void paintComponent(Graphics g) {

    }

    public JPanel getPanel() {
        return this.panel;
    }

}
