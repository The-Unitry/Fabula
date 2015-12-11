package eu.theunitry.fabula.UNGameEngine.graphics;

import eu.theunitry.fabula.UNGameEngine.graphics.UNGameScreen;
import eu.theunitry.fabula.UNGameEngine.graphics.UNColor;
import eu.theunitry.fabula.UNGameEngine.graphics.UNView;
import eu.theunitry.fabula.UNGameEngine.launcher.UNLevelLoader;
import eu.theunitry.fabula.UNGameEngine.objects.UNButtonListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * UNLauncher
 * UNLauncher is the main menu which loads levels & settings.
 */
public class UNCreditsView extends JPanel
{
    private JLabel label;
    private JButton stopBtn;
    private UNView creditsView;
    private UNLevelLoader levelLoader;
    private UNGameScreen gameScreen;

    public UNCreditsView(UNGameScreen gameScreen)
    {
        this.label = new JLabel("Fabula");
        this.stopBtn = new JButton("Klaar");
        this.gameScreen = gameScreen;

        gameScreen.stopAudio();

        this.creditsView = new UNView();

        this.setStyling();

        creditsView.add(stopBtn, BorderLayout.SOUTH);
        creditsView.add(label, BorderLayout.NORTH);

        stopBtn.addActionListener(new UNButtonListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });

        this.add(creditsView);
    }

    private void loadLevel(int level)
    {
        levelLoader = new UNLevelLoader(this.gameScreen);
        levelLoader.loadLevel(level);
    }

    public JPanel getPanel() {
        return this;
    }

    private void setStyling()
    {
        /**
         * Panel Styling
         */
        this.setLayout(new BorderLayout());

        /**
         * Credits styling
         */
        this.creditsView.setBackground(UNColor.BLACK_COLOR);
        this.creditsView.setBorder(new EmptyBorder(20, 20, 20, 20));

        /**
         * Label Styling
         */
        this.label.setForeground(Color.white);
        this.label.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 40));
        this.label.setHorizontalAlignment(JLabel.CENTER);

        /**
         * Button Styling
         */
        this.stopBtn.setPreferredSize(new Dimension(180,60));
        this.stopBtn.setBackground(Color.white);
        this.stopBtn.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        this.stopBtn.setForeground(UNColor.BLACK_COLOR);
        this.stopBtn.setOpaque(true);

        /**
         * Default Styling Reset
         */
        this.stopBtn.setFocusPainted(false);
        this.stopBtn.setBorderPainted(false);
    }

    public UNLevelLoader getLevelLoader() {
        return this.levelLoader;
    }

    public void setLevelLoader(UNLevelLoader levelLoader) {
        this.levelLoader = levelLoader;
    }
}
