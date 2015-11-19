package eu.theunitry.fabula.objects;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UNButtonListener implements ActionListener
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
