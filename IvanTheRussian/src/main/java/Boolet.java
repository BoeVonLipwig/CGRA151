import processing.core.PVector;

/**
 * Created by Shaun Sinclair.
 * CGRA 151.
 * Start date: 27/09/2016.
 */
public class Boolet {
    private PVector position, original;
    private double speed, allowed = 200;
    //will need to be int if add more ammo types
    private boolean explosive;

    Boolet(float x, float y, double speed, boolean explosive) {
        position.x = x;
        position.y = y;
        original = position;
        this.speed = speed;
        this.explosive = explosive;
        if (!explosive) {
            allowed = 300;
        } else {
            allowed = 200;
        }
    }


    public void move() {
        if (position.dist(original) > allowed) {
            trigger();
        }
        position.x += speed;
    }

    public void trigger() {
        if (explosive) {

        } else {

        }
    }
}
