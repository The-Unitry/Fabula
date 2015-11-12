package eu.theunitry.fabula.graphics;

import java.awt.*;

public class UNColor
{
    private Color PRIMARY_COLOR;

    public UNColor()
    {
        this.setPrimaryColor(new Color(255,82,44));
    }


    public Color getPrimaryColor() {
        return PRIMARY_COLOR;
    }

    public void setPrimaryColor(Color primaryColor) {
        PRIMARY_COLOR = primaryColor;
    }
}
