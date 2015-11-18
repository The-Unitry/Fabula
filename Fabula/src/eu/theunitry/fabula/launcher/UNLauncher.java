package eu.theunitry.fabula.launcher;

import eu.theunitry.fabula.levels.Level0;
import eu.theunitry.fabula.UNGameScreen;
import eu.theunitry.fabula.graphics.UNColor;
import eu.theunitry.fabula.graphics.UNPanel;
import eu.theunitry.fabula.objects.UNButtonListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class UNLauncher extends JPanel
{

    private UNGameScreen gameScreen;
    private JLabel label;
    private JButton startBtn;

    public UNLauncher(UNGameScreen gameScreen)
    {
        this.gameScreen = gameScreen;
        this.label = new JLabel("Fabula");
        this.startBtn = new JButton("Start");

        this.setStyling();
        startBtn.addActionListener(new UNButtonListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                gameScreen.resetProgress();
                UNPanel panel = new Level0(gameScreen, true);
                gameScreen.switchMusic(1, true);
                gameScreen.getMusic().get(1).setVolume(0.1);

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
        this.setBackground(UNColor.PRIMARY_COLOR);
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
        startBtn.setForeground(UNColor.PRIMARY_COLOR);
        startBtn.setOpaque(true);

        /* Reset default styling */
        startBtn.setFocusPainted(false);
        startBtn.setBorderPainted(false);
    }

}
