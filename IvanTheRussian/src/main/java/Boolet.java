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
    //will need to be int if add more ammo types
    @SuppressWarnings("FieldCanBeLocal")
    private IvanTheRussian game = IvanTheRussian.instance;
    PImage totallyNotBill;

    Boolet(float x, float y, double speed) {
        position=new PVector(x,y);
        this.speed = speed;
        if(speed>=0)totallyNotBill =game.loadImage("BooletRight.png");
        else totallyNotBill =game.loadImage("BooletLeft.png");
        totallyNotBill.resize(25,15);
    }

    PVector getPosition() {
        return position;
    }

    void move() {
        Blocks x;
        if ((x = IvanTheRussian.instance.checkHit(this)) != null) {
            if(x.getType().equals("BreakableWall")||x.getType().equals("Mine")){
                IvanTheRussian.instance.removeObjects(x);
            }
            Ivan.removeBullet(this);
            return;
        }
        position.x += speed;
    }

}
