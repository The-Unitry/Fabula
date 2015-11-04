package eu.theunitry.fabula.graphics;

import eu.theunitry.fabula.objects.UNObject;

import javax.swing.*;
import java.awt.*;

public class UNPanel extends JPanel
{

    public UNPanel()
    {

    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawRect(10, 10, 20, 20);
    }

    public JPanel getPanel()
    {
        return (JPanel) this;
    }

}
