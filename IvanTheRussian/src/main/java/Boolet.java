import processing.core.PImage;
import processing.core.PVector;

/**
 * Created by Shaun Sinclair.
 * CGRA 151.
 * Start date: 27/09/2016.
 */
class Boolet {
    private PVector position;
    private double speed;
    private int index;
    //will need to be int if add more ammo types
    private boolean explosive;
    private IvanTheRussian game = IvanTheRussian.instance;
    PImage totallyNotBill;

    Boolet(float x, float y, double speed, boolean explosive) {
        position=new PVector(x,y);
        this.speed = speed;
        this.explosive = explosive;
        if(speed>=0)totallyNotBill =game.loadImage("BooletRight.png");
        else totallyNotBill =game.loadImage("BooletLeft.png");
        totallyNotBill.resize(25,15);
        double allowed = 20;
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

    void move() {
        Blocks x;
        if ((x = IvanTheRussian.instance.checkHit(this)) != null) {
            if(x.getType().equals("BreakableWall")){
                IvanTheRussian.instance.removeObjects(x.getIndex());
            }
            Ivan.removeBullet(index);
            return;
        }
        position.x += speed;
    }

}
