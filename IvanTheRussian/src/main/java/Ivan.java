import processing.core.PImage;
import processing.core.PVector;

import java.util.ArrayList;

/**
 * Created by Shaun Sinclair.
 * CGRA 151.
 * Start date: 27/09/2016.
 */
class Ivan {

    private IvanTheRussian game;
    PVector position,accl;
    int width, height, moveSpeed=10;
    private boolean facingRight = true;
    private boolean hasExplosive;
    private int health = 10;
    private boolean ammoType;
    private static ArrayList<Boolet> bullets;
    PImage head;

    Ivan(float x, float y, boolean hasExplosive) {
        game=IvanTheRussian.instance;
        position.x = x;
        position.y = y;
        this.hasExplosive = hasExplosive;
        head=game.loadImage("Head.png");
        head.resize(IvanTheRussian.size *2, IvanTheRussian.size);
        position=new PVector();
        accl=new PVector();
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
        if(health<0)die();
        else if (health>10)health=10;
    }

    public void heal(int amount){
        takeDmg(-amount);
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

    PVector getPosition() {
        return position;
    }

    PVector getAcl() {
        return accl;
    }

    private void die(){

    }
}
