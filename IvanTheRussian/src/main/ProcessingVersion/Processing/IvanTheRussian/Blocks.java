import processing.core.PVector;

import java.util.Objects;

/**
 * Created by Shaun Sinclair.
 * CGRA 151.
 * Start date: 27/09/2016.
 */
class Blocks {
  //global vars
    private PVector pos;
    private String type;
    private boolean doesDMG;
    private boolean solid;
    //constructor
    Blocks(PVector pos, String type) {
        this.pos = pos;
        this.type = type;
        setTypes();
    }

    //sets booleans based on types
    private void setTypes(){
        doesDMG = Objects.equals(type, "Spike") || Objects.equals(type, "Mine");
        solid = !(type.equals("Mine") || type.equals("FakeEarth") || type.equals("FakeBarrenEarth") || type.equals("FakeWall")||type.equals("Flag"));
    }

    //getters and setters
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