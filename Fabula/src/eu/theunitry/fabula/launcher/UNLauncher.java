package eu.theunitry.fabula.launcher;

import javax.swing.*;
import java.awt.*;

public class UNLauncher extends JPanel
{

    private JLabel label;

    public UNLauncher()
    {
        label = new JLabel("Fabula");
        this.setBackground(new Color(255,82,44));
        label.setForeground(Color.white);
        label.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 40));
        this.add(label);

        // TODO: Add buttons & background
    }

    public JPanel getPanel() {
        return this;
    }

}
