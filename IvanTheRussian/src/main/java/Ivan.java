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
    private int size = IvanTheRussian.size, ivanHeight = size * 2, width = size * 2,health = 10, count = 0;;
    private boolean facingRight = true;
    private boolean hasExplosive;
    private boolean ammoType, spriteChanger;
    private static ArrayList<Boolet> bullets;
    private PImage ivan;
    private PImage[] ivanImages = {null, null, null, null};

    Ivan(PVector position, boolean hasExplosive) {
        this.position = position;
        acceleration = new PVector(0, 0);
        bullets = new ArrayList<>();
        IvanTheRussian game = IvanTheRussian.instance;
        this.hasExplosive = hasExplosive;
        ivanImages[0] = game.loadImage("IvanRight.png");
        ivanImages[1] = game.loadImage("IvanLeft.png");
        ivanImages[2] = game.loadImage("IvanRunningRight.png");
        ivanImages[3] = game.loadImage("IvanRunningLeft.png");
        for (int i = 0; i < ivanImages.length; i++) {
            if (i <= 1) {
                ivanImages[i].resize(width, ivanHeight);
            } else {
                ivanImages[i].resize((int) ((width / 100) * 93.36), ivanHeight);
            }
        }
        ivan = ivanImages[0];
    }

    public void setIvanImages(int index) {
        ivan=ivanImages[index];
    }

    void move() {
        facingRight = acceleration.x >= 0;
        boolean[] keys = IvanTheRussian.getKeys();
        double grav = 0.4;
        acceleration.y += grav;
        if (position.y > 600 - ivanHeight) {
            acceleration.y = 0;
            position.y = 600 - ivanHeight;
            IvanTheRussian.setJumpAllowed(true);
        }

//            die();
//            return;
        if (keys[0]) {
            acceleration.y -= 10;
            keys[0] = false;
        }
        if (keys[1]) {
            acceleration.x -= 5;
            count++;
            if (count % 3 == 0) {
                count = 0;
                spriteChanger = !spriteChanger;
            }
            if (spriteChanger) ivan = ivanImages[3];
            else ivan = ivanImages[1];
        }
        if (keys[2]) {
            acceleration.x += 5;
            count++;
            if (count % 3 == 0) {
                count = 0;
                spriteChanger = !spriteChanger;
            }
            if (spriteChanger) ivan = ivanImages[2];
            else ivan = ivanImages[0];
        }
        if (keys[3]) {
            acceleration.y += 1;
        }
        position.x += acceleration.x;
        Blocks x;
        if ((x = IvanTheRussian.instance.checkCollide()) != null) {
            if (acceleration.x > 0) {
                position.x = x.getPos().x - width;
            } else {
                position.x = x.getPos().x + width / 2;
            }
        }
        position.y += acceleration.y;
        if ((x = IvanTheRussian.instance.checkCollide()) != null) {
            if (acceleration.y > 0) {
                position.y = x.getPos().y - ivanHeight;
            } else {
                position.y = x.getPos().y + width / 2;
            }
            acceleration.y=0;
        }
        acceleration.x = 0;
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

    PImage getIvan() {
        return ivan;
    }

    void die() {

    }
}
