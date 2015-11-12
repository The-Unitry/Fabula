package eu.theunitry.fabula.launcher;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class UNLauncher extends JPanel
{

    private JLabel label;
    private JButton startBtn;

    public UNLauncher()
    {
        this.setBackground(new Color(255,82,44));
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(10, 10, 10, 10));

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
        startBtn.setForeground(Color.white);


        this.add(label, BorderLayout.CENTER);
        this.add(startBtn, BorderLayout.SOUTH);
    }

    public JPanel getPanel() {
        return this;
    }

}
