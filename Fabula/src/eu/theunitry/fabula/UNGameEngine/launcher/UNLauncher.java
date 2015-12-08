package eu.theunitry.fabula.UNGameEngine.launcher;

import eu.theunitry.fabula.UNGameEngine.graphics.UNGameScreen;
import eu.theunitry.fabula.UNGameEngine.graphics.UNColor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * UNLauncher is the main menu which loads levels & settings.
 * Later, we will add support to switch between levels.
 */
public class UNLauncher extends JPanel
{
    private JLabel label;
    private JPanel menuItemsView;
    //private JButton startBtn;

    public UNLauncher(UNGameScreen gameScreen)
    {
        this.label = new JLabel("Fabula");
        UNMenuButton btn = new UNMenuButton("Hello world");


        this.setStyling();
//        startBtn.addActionListener(new UNButtonListener(){
//            @Override
//            public void actionPerformed(ActionEvent e)
//            {
//                new UNLevelLoader(gameScreen);
//            }
//        });

        this.add(label, BorderLayout.CENTER);
        this.add(btn, BorderLayout.SOUTH);
    }

    public JPanel getPanel() {
        return this;
    }

    private void setStyling()
    {
        /**
         * Panel Styling
         */
        this.setBackground(UNColor.PRIMARY_COLOR);
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(20, 20, 20, 20));

        /**
         * Label Styling
         */
        this.label.setForeground(Color.white);
        this.label.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 40));
        this.label.setHorizontalAlignment(JLabel.CENTER);
    }

}
