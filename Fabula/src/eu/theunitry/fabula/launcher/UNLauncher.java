package eu.theunitry.fabula.launcher;

import eu.theunitry.fabula.graphics.UNColor;
import eu.theunitry.fabula.objects.UNButtonListener;

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
        this.label = new JLabel("Fabula");
        this.startBtn = new JButton("Start");

        this.setStyling();
        
        this.add(label, BorderLayout.CENTER);
        this.add(startBtn, BorderLayout.SOUTH);
    }

    public JPanel getPanel() {
        return this;
    }

    private void setStyling()
    {
        /* Panel styling */
        this.setBackground(color.getPrimaryColor());
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(20, 20, 20, 20));

        /* Label styling */
        label.setForeground(Color.white);
        label.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 40));
        label.setHorizontalAlignment(JLabel.CENTER);

        /* Button styling */
        startBtn.setPreferredSize(new Dimension(200,100));
        startBtn.setBackground(Color.white);
        startBtn.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        startBtn.setForeground(color.getPrimaryColor());
        startBtn.setOpaque(true);
        startBtn.addActionListener(new UNButtonListener());

        /* Reset default styling */
        startBtn.setFocusPainted(false);
        startBtn.setBorderPainted(false);
    }

}
