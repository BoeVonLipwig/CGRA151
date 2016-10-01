import processing.core.PVector;

import java.util.ArrayList;

/**
 * Created by Shaun Sinclair.
 * CGRA 151.
 * Start date: 27/09/2016.
 */
class Ivan {


    PVector position=new PVector(),accl=new PVector();
    int width, height, moveSpeed=10;
    private boolean facingRight = true;
    private boolean hasExplosive;
    private int health = 10;
    private boolean ammoType;
    private static ArrayList<Boolet> bullets;

    Ivan(float x, float y, boolean hasExplosive) {
        position.x = x;
        position.y = y;
        this.hasExplosive = hasExplosive;
    }

    void draw() {
    }

    void move() {
        double grav = -2;
        accl.y += grav;
        facingRight = accl.x >= 0;
        position.x += accl.x;
        position.y += accl.y;
    }

    void jump() {
        accl.y += 20;
    }

    public void takeDmg(int amount) {
        health -= amount;
    }

    public void cycleAmmo() {
        if (hasExplosive) {
            ammoType = !ammoType;
        }

    }

    static void removeBullet(int index) {
        bullets.remove(index);
    }

    public void Shoot() {
        double speed=-5;
        if (facingRight) speed *=-1;
        Boolet cur=new Boolet(position.x, position.y, speed, ammoType);
        cur.setIndex(bullets.size());
        bullets.add(cur);
    }
}
