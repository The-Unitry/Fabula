package eu.theunitry.fabula;


import eu.theunitry.fabula.graphics.UNWindow;
import eu.theunitry.fabula.objects.UNObject;

public class UNGameScreen extends UNObject
{

    private final UNWindow window;

    public UNGameScreen() {
        window = new UNWindow("Fabula", 768, 512);
    }

}
