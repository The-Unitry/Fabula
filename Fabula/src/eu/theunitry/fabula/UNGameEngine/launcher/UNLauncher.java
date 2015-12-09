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
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
    private UNGameScreen gameScreen;

    public UNLauncher(UNGameScreen gameScreen)
    {
        this.label = new JLabel("Fabula");
        this.startBtn = new JButton("Start");
        this.gameScreen = gameScreen;

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

        GridLayout ex = new GridLayout(0,3);

        levelView.setLayout(ex);
        levelView.setBorder(new EmptyBorder(20,212,20,20));

        ArrayList<JButton> levelButtons = new ArrayList<>();


        for(int i = 1; i <= 12; i++) levelButtons.add(new JButton("Level " + i));

        for (JButton btn : levelButtons)
        {
            levelView.add(btn);
            int parsedButtonTextToInt = Integer.parseInt(btn.getText().replaceAll("\\D+",""));
            btn.addActionListener(e -> this.loadLevel(parsedButtonTextToInt));
        }

        this.add(controlView);
        this.add(levelView);
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
         * ControlView styling
         */
        this.controlView.setBounds(0,0,192,512);
        this.controlView.setBackground(UNColor.PRIMARY_COLOR);
        this.controlView.setBorder(new EmptyBorder(20, 20, 20, 20));

        /**
         * LevelView styling
         */
        this.levelView.setBackground(Color.white);
        this.levelView.setBounds(192, 0, 768 - 192, 512);

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
