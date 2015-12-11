package eu.theunitry.fabula.UNGameEngine.graphics;

import eu.theunitry.fabula.UNGameEngine.graphics.UNGameScreen;
import eu.theunitry.fabula.UNGameEngine.graphics.UNColor;
import eu.theunitry.fabula.UNGameEngine.graphics.UNView;
import eu.theunitry.fabula.UNGameEngine.launcher.UNLevelLoader;
import eu.theunitry.fabula.UNGameEngine.objects.UNButtonListener;
import kuusisto.tinysound.Music;
import kuusisto.tinysound.Sound;
import kuusisto.tinysound.TinySound;

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
 * UNCreditsViews is the main menu which loads levels & settings.
 */
public class UNCreditsView extends JPanel
{
    private JLabel label;
    private JButton stopBtn;
    private UNView creditsView;
    private UNLevelLoader levelLoader;
    private UNGameScreen gameScreen;
    private ArrayList<JLabel> labels;

    public UNCreditsView(UNGameScreen gameScreen)
    {
        labels = new ArrayList<>();

        labels.add(new JLabel("Gemaakt door:"));
        labels.add(new JLabel("Jeroen"));
        labels.add(new JLabel("Allan"));
        labels.add(new JLabel("Wesley"));
        labels.add(new JLabel("Jelmer"));
        labels.add(new JLabel("Ruben"));
        labels.add(new JLabel("Maarten"));
        labels.add(new JLabel("Een beetje Omar"));
        labels.add(new JLabel(""));

        this.stopBtn = new JButton("Afsluiten");
        this.gameScreen = gameScreen;

        this.gameScreen.stopAudio();

        this.creditsView = new UNView();

        this.setStyling();

        for (JLabel lbl : labels)
        {
            lbl.setForeground(Color.white);
            lbl.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
            lbl.setHorizontalAlignment(JLabel.CENTER);
            creditsView.add(lbl);
        }

        creditsView.add(stopBtn, BorderLayout.SOUTH);

        this.add(creditsView);
        Sound creditsMusic = TinySound.loadSound("audio/credits.wav");
        creditsMusic.play();

        stopBtn.addActionListener(new UNButtonListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                creditsMusic.stop();
                gameScreen.switchToLauncher();

            }
        });
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
        this.creditsView.setLayout(new GridLayout(0,1));
        this.creditsView.setBackground(UNColor.BLACK_COLOR);
        this.creditsView.setBorder(new EmptyBorder(20, 20, 20, 20));

        /**
         * Label Styling
         */


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
}
