import processing.core.PVector;

import java.util.Objects;

/**
 * Created by Shaun Sinclair.
 * CGRA 151.
 * Start date: 27/09/2016.
 */
class Blocks {
    private PVector pos;
    private String type;
    private boolean doesDMG;
    private boolean solid;
    private int index;

    Blocks(PVector pos, String type, int index) {
        this.pos = pos;
        this.type = type;
        this.index=index;
        setTypes();
    }

    private void setTypes(){
        doesDMG = Objects.equals(type, "Spike") || Objects.equals(type, "Mine");
        solid = !(type.equals("Mine") || type.equals("FakeEarth") || type.equals("FakeBarrenEarth") || type.equals("FakeWall")||type.equals("Flag"));
    }

    int getIndex(){
        return index;
    }

    boolean isSolid() {
        return solid;
    }

    boolean isDoesDMG() {
        return doesDMG;
    }

    PVector getPos() {
        return pos;
    }

    String getType() {
        return type;
    }

}
