import processing.core.PImage;
import processing.core.PVector;

/**
 * Created by Shaun Sinclair.
 * CGRA 151.
 * Start date: 27/09/2016.
 */
class Boolet {
    private PVector position, original;
    private double speed, allowed = 200;
    private int index;
    //will need to be int if add more ammo types
    private boolean explosive;
    private IvanTheRussian game = IvanTheRussian.instance;
    PImage totallyNotBill=game.loadImage("Boolet.png");

    Boolet(float x, float y, double speed, boolean explosive) {
        position=new PVector(x,y);
        original = position;
        this.speed = speed;
        this.explosive = explosive;
        if (!explosive) {
            allowed = 300;
        } else {
            allowed = 200;
        }
    }

    PVector getPosition() {
        return position;
    }

    double getSpeed() {
        return speed;
    }

    void setIndex(int index){
        this.index=index;
    }

    public void move() {
        if (position.dist(original) > allowed) {
            Ivan.removeBullet(index);
        }
        position.x += speed;
    }

}
