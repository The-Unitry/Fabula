package eu.theunitry.fabula.launcher;

import javax.swing.*;
import java.awt.*;

public class UNLauncher extends JPanel
{

    private JLabel label;

    public UNLauncher()
    {
        label = new JLabel("Fabula");
        this.setBackground(Color.black);
        this.add(label);

    }
}
