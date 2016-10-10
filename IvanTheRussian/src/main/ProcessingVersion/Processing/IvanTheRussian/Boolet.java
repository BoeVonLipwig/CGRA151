import processing.core.PImage;
import processing.core.PVector;

/**
 * Created by Shaun Sinclair.
 * CGRA 151.
 * Start date: 27/09/2016.
 */
class Boolet {
  //global vars
    private PVector position;
    private double speed;
    //will need to be int if add more ammo types
    @SuppressWarnings("FieldCanBeLocal")
    private IvanTheRussian game = IvanTheRussian.instance;
    PImage totallyNotBill;
    //constructor
    Boolet(float x, float y, double speed) {
        position=new PVector(x,y);
        this.speed = speed;
        //sets image based on firing direction
        if(speed>=0)totallyNotBill =game.loadImage("BooletRight.png");
        else totallyNotBill =game.loadImage("BooletLeft.png");
        //resizes image
        totallyNotBill.resize(25,15);
    }
//moves and deals with breaking objects
    void move() {
        Blocks x;
        if ((x = IvanTheRussian.instance.checkHit(this)) != null) {
            if(x.getType().equals("BreakableWall")){
              //destroys breakable wall if hits it
                IvanTheRussian.instance.removeObjects(x);
            }
            //removes boolet if it hits something
            Ivan.removeBullet(this);
            return;
        }
        //moves
        position.x += speed;
    }

//getter for pos
    PVector getPosition() {
        return position;
    }
}