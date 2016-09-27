import com.sun.xml.internal.bind.v2.TODO;
import processing.core.PVector;

/**
 * Created by Shaun Sinclair.
 * CGRA 151.
 * Start date: 27/09/2016.
 */
public class Ivan {


    private PVector position;
    int width, height;
    private boolean facingRight;
    private boolean hasExplosive;
    private int health = 10;
    private boolean ammoType;

    Ivan(float x, float y, boolean hasExplosive) {
        position.x = x;
        position.y = y;
        this.hasExplosive = hasExplosive;
    }

    public void move() {
        if (facingRight) {
            position.x++;
        }else{
            position.x--;
        }
    }
    //TODO should movement even be here not in main?
    public void jump(){

    }

    public void takeDmg(int ammount) {
        health -= ammount;
    }

    public void cycleAmmo() {
        if (hasExplosive) {
            ammoType = !ammoType;
        }

    }

    public void Shoot() {
        new Boolet(position.x, position.y, 5, ammoType);
    }
}
