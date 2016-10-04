import processing.core.PImage;
import processing.core.PVector;

import java.util.ArrayList;

/**
 * Created by Shaun Sinclair.
 * CGRA 151.
 * Start date: 27/09/2016.
 */
class Ivan {

    private PVector position, acceleration;
    private int ivanHeight =IvanTheRussian.size*2;
    private boolean facingRight = true;
    private boolean hasExplosive;
    private int health = 10;
    private boolean ammoType;
    private static ArrayList<Boolet> bullets;
    private PImage ivan;

    Ivan(PVector position, boolean hasExplosive) {
        this.position = position;
        acceleration = new PVector(0,0);
        bullets = new ArrayList<>();
        IvanTheRussian game = IvanTheRussian.instance;
        this.hasExplosive = hasExplosive;
        ivan = game.loadImage("Ivan2.png");
        int width = IvanTheRussian.size * 2;
        ivan.resize(width, ivanHeight);
    }

    void move() {
        facingRight = acceleration.x >= 0;
        boolean[] keys=IvanTheRussian.getKeys();
        double grav = 0.981;
        acceleration.y += grav;
        if(position.y > 600-ivanHeight){
            acceleration.y=0;
            position.y= 600-ivanHeight;
        }
//            die();
//            return;
        if(keys[0]){
            acceleration.y-=10;
            keys[0]=false;
        }
        if(keys[1]){
            acceleration.x-=5;
        }
        if(keys[2]){
            acceleration.x+=5;
        }
        if(keys[3]){
            acceleration.y+=1;
        }
        position.add(acceleration);
        acceleration.x=0;
        acceleration.limit(20);
    }

    private void takeDmg(int amount) {
        health -= amount;
        if (health < 0) die();
        else if (health > 10) health = 10;
    }

    public void heal(int amount) {
        takeDmg(-amount);
    }

    void cycleAmmo() {
        if (hasExplosive) {
            ammoType = !ammoType;
        }

    }

    int getHealth() {
        return health;
    }

    static void removeBullet(int index) {
        bullets.remove(index);
    }

    public void Shoot() {
        double speed = -5;
        if (facingRight) speed *= -1;
        Boolet cur = new Boolet(position.x, position.y, speed, ammoType);
        cur.setIndex(bullets.size());
        bullets.add(cur);
    }

    PVector getPosition() {
        return position;
    }

    PVector getAcl() {
        return acceleration;
    }

    PImage getIvan(){
        return ivan;
    }

    void die() {

    }
}
