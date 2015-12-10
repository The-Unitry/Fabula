package eu.theunitry.fabula.UNGameEngine.launcher;

import eu.theunitry.fabula.UNGameEngine.graphics.UNGameScreen;
import eu.theunitry.fabula.UNGameEngine.graphics.UNColor;
import eu.theunitry.fabula.UNGameEngine.graphics.UNView;
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
                gameScreen.setAdventure(true);
                loadLevel(1 + new Random().nextInt(11));
            }
        });

        ArrayList<ImageIcon> levelList= new ArrayList<>();

        for (int i = 1; i <= 12; i++) levelList.add(new ImageIcon("res/levels/" + String.valueOf(i) + ".png"));;

        this.controlView.setLayout(new BorderLayout());
        this.levelView.setLayout(new BorderLayout());

        this.controlView.add(label, BorderLayout.NORTH);
        this.controlView.add(startBtn, BorderLayout.SOUTH);

        levelView.setLayout(new GridLayout(0,3));
        levelView.setBorder(new EmptyBorder(20,212,20,20));

        ArrayList<JButton> levelButtons = new ArrayList<>();

        for(int i = 0; i <= 11; i++) levelButtons.add(new JButton("Level " + i));

        for (JButton btn : levelButtons)
        {
            btn.setBorderPainted(false);
            btn.setBackground(Color.white);
            int parsedButtonTextToInt = Integer.parseInt(btn.getText().replaceAll("\\D+",""));
            btn.setText("");
            btn.setIcon(levelList.get(parsedButtonTextToInt));
            levelView.add(btn);
            gameScreen.setAdventure(false);

            int levelLoaded = 0;
            switch (parsedButtonTextToInt)
            {
                case 1: levelLoaded = 7; break;
                case 2: levelLoaded = 11; break;
                case 3: levelLoaded = 12; break;
                case 4: levelLoaded = 3; break;
                case 5: levelLoaded = 5; break;
                case 6: levelLoaded = 6; break;
                case 7: levelLoaded = 9; break;
                case 8: levelLoaded = 4; break;
                case 9: levelLoaded = 10; break;
                case 10: levelLoaded = 8; break;
                case 11: levelLoaded = 1; break;
            }

            final int finalLevelLoaded = levelLoaded;
            btn.addActionListener(e -> this.loadLevel(finalLevelLoaded));

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

    public UNLevelLoader getLevelLoader() {
        return this.levelLoader;
    }

    public void setLevelLoader(UNLevelLoader levelLoader) {
        this.levelLoader = levelLoader;
    }
}
