package eu.theunitry.fabula.UNGameEngine.launcher;

import eu.theunitry.fabula.UNGameEngine.graphics.UNColor;

import javax.swing.*;
import java.awt.*;

/**
 * This button is only supposed to be used in the main menu. It is
 * responsible for commanding the UNLevelLoader to load a level. This will
 * eventually inherit from UNButton.
 */
public class UNMenuButton extends JButton
{
    public UNMenuButton(String title)
    {
        /**
         * Button Styling
         */
        this.setPreferredSize(new Dimension(200,100));
        this.setBackground(Color.white);
        this.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        this.setForeground(UNColor.PRIMARY_COLOR);
        this.setOpaque(true);

        /**
         * Set text
         */
        this.setText(title);

        /**
         * Default Styling Reset
         */
        this.setFocusPainted(false);
        this.setBorderPainted(false);
    }
}
