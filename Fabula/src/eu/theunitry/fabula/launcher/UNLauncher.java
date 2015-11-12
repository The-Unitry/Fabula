package eu.theunitry.fabula.launcher;

import eu.theunitry.fabula.graphics.UNColor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class UNLauncher extends JPanel
{

    private JLabel label;
    private JButton startBtn;
    private UNColor color;

    public UNLauncher()
    {
        this.color = new UNColor();

        this.setBackground(color.getPrimaryColor());
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(20, 20, 20, 20));

        label = new JLabel("Fabula");
        label.setForeground(Color.white);
        label.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 40));
        label.setHorizontalAlignment(JLabel.CENTER);

        startBtn = new JButton("Start");
        startBtn.setPreferredSize(new Dimension(200,100));

        /* Remove default styling */
        startBtn.setFocusPainted(false);
        startBtn.setBorderPainted(false);

        startBtn.setBackground(Color.white);
        startBtn.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        startBtn.setForeground(color.getPrimaryColor());


        this.add(label, BorderLayout.CENTER);
        this.add(startBtn, BorderLayout.SOUTH);
    }

    public JPanel getPanel() {
        return this;
    }

}
