package eu.theunitry.fabula.launcher;

import eu.theunitry.fabula.UNGameScreen;
import eu.theunitry.fabula.graphics.UNColor;
import eu.theunitry.fabula.graphics.UNPanel;
import eu.theunitry.fabula.objects.UNButtonListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class UNLauncher extends JPanel
{

    private UNGameScreen gameScreen;
    private JLabel label;
    private JButton startBtn;
    private UNColor color;

    public UNLauncher(UNGameScreen gameScreen)
    {
        this.gameScreen = gameScreen;
        this.color = new UNColor();
        this.label = new JLabel("Fabula");
        this.startBtn = new JButton("Start");

        this.setStyling();
        startBtn.addActionListener(new UNButtonListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                UNPanel panel = new UNPanel(gameScreen.getWindow().getFrame());
                gameScreen.toggleMusic(1, true);
                try {
                    panel.setBackgroundImage(ImageIO.read(new File("res/backgrounds/forest.png")));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                gameScreen.switchPanel(panel);
            }
        });
        
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

        /* Reset default styling */
        startBtn.setFocusPainted(false);
        startBtn.setBorderPainted(false);
    }

}
