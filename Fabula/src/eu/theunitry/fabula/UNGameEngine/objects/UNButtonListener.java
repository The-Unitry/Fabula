package eu.theunitry.fabula.UNGameEngine.objects;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * UNButtonListener is used for adding an action
 * to when a button is pressed.
 */
public class UNButtonListener extends UNObject implements ActionListener
{
    private JButton buttonPressed;

    @Override
    public void actionPerformed(ActionEvent e)
    {
        this.setButtonPressed((JButton) e.getSource());
    }

    public JButton isButtonPressed()
    {
        return buttonPressed;
    }

    public void setButtonPressed(JButton buttonPressed)
    {
        this.buttonPressed = buttonPressed;
    }
}
