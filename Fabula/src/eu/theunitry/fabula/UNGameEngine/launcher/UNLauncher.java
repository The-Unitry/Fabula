package eu.theunitry.fabula.UNGameEngine.launcher;

import eu.theunitry.fabula.UNGameEngine.graphics.UNGameScreen;
import eu.theunitry.fabula.UNGameEngine.graphics.UNColor;
import eu.theunitry.fabula.UNGameEngine.graphics.UNView;
import eu.theunitry.fabula.UNGameEngine.objects.UNButtonListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * UNLauncher
 * UNLauncher is the main menu which loads levels & settings.
 * Later, we will add support to switch between levels.
 */
public class UNLauncher extends JPanel
{
    private JLabel label;
    private JButton startBtn;
    private UNView controlView, levelView;
    private UNLevelLoader levelLoader;

    public UNLauncher(UNGameScreen gameScreen)
    {
        this.label = new JLabel("Fabula");
        this.startBtn = new JButton("Start");

        this.controlView = new UNView();
        this.levelView = new UNView();

        this.setStyling();

        startBtn.addActionListener(new UNButtonListener(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                levelLoader = new UNLevelLoader(gameScreen);
            }
        });

        this.controlView.setLayout(new BorderLayout());
        this.levelView.setLayout(new BorderLayout());

        this.controlView.add(label, BorderLayout.NORTH);
        this.controlView.add(startBtn, BorderLayout.SOUTH);

        this.add(controlView);
        this.add(levelView);
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
         * ControlView styling
         */
        this.controlView.setBounds(0,0,192,512);
        this.controlView.setBackground(UNColor.PRIMARY_COLOR);
        this.controlView.setBorder(new EmptyBorder(20, 20, 20, 20));

        /**
         * LevelView styling
         */
        this.levelView.setBackground(Color.white);
        this.levelView.setBounds(controlView.getWidth(), 0, 768 - controlView.getWidth(), 512);

        /**
         * Label Styling
         */
        this.label.setForeground(Color.white);
        this.label.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 40));
        this.label.setHorizontalAlignment(JLabel.CENTER);

        /**
         * Button Styling
         */
        this.startBtn.setPreferredSize(new Dimension(180,60));
        this.startBtn.setBackground(Color.white);
        this.startBtn.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        this.startBtn.setForeground(UNColor.PRIMARY_COLOR);
        this.startBtn.setOpaque(true);

        /**
         * Default Styling Reset
         */
        this.startBtn.setFocusPainted(false);
        this.startBtn.setBorderPainted(false);
    }
}
