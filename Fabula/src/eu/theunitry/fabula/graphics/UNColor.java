package eu.theunitry.fabula.graphics;

import java.awt.*;

public class UNColor
{
    private Color primaryColor;

    public UNColor()
    {
        this.setPrimaryColor(new Color(255,82,44));
    }

    public Color getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(Color primaryColor) {
        this.primaryColor = primaryColor;
    }
}
