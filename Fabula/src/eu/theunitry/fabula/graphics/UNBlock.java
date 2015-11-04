package eu.theunitry.fabula.graphics;

public class UNBlock extends UNGraphicsObject {

    private boolean solid;
    public boolean attribute;

    public boolean getSolid() {
        return this.solid;
    }

    public void setSolid(boolean solid) {
        this.solid = solid;
    }

    public boolean getAttribute() {
        return this.attribute;
    }

    public void setAttribute(boolean attribute) {
        this.attribute = attribute;
    }
}
