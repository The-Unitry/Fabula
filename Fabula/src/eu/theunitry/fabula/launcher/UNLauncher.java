package eu.theunitry.fabula.launcher;

import javax.swing.*;
import java.awt.*;

public class UNLauncher extends JPanel
{

    private JLabel label;
    private JButton startBtn;

    public UNLauncher()
    {
        this.setBackground(new Color(255,82,44));
        this.setLayout(new BorderLayout());

        label = new JLabel("Fabula");
        label.setForeground(Color.white);
        label.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 40));

        startBtn = new JButton("Start");
        startBtn.setPreferredSize(new Dimension(200,100));

        this.add(label);
        this.add(startBtn, BorderLayout.SOUTH);
    }

    public JPanel getPanel() {
        return this;
    }

}
