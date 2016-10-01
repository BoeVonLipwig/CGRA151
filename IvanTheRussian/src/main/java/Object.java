import processing.core.PVector;

/**
 * Created by Shaun Sinclair.
 * CGRA 151.
 * Start date: 27/09/2016.
 */
class Object {
    private PVector pos;
    private String type;

    Object(PVector pos, String type) {
        this.pos = pos;
        this.type = type;
    }

    PVector getPos() {
        return pos;
    }

    String getType() {
        return type;
    }

}
