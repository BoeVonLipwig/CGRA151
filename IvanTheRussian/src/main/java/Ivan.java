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
    private int size = IvanTheRussian.size, ivanHeight = size * 2, width = size * 2, health = 10, count = 0;
    private boolean hasExplosive, dead = false;
    private boolean ammoType, spriteChanger;
    private static ArrayList<Boolet> bullets;
    private PImage ivan;
    private PImage[] ivanImages = new PImage[4];
    private int frameCountOld=IvanTheRussian.instance.frameCount;

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

    void setIvanImages(int index) {
        ivan = ivanImages[index];
    }

    static ArrayList<Boolet> getBullets() {
        return bullets;
    }

    private boolean jumpAllowed = false;

    void move() {
        if (!dead) {
            boolean[] keys = IvanTheRussian.getKeys();
            double grav = 0.4;
            acceleration.y += grav;
//            if (position.y > 600 - ivanHeight) {
//                acceleration.y = 0;
//                position.y = 600 - ivanHeight;
//                jumpAllowed = true;
//            }
            if (keys[0] && jumpAllowed) {
                acceleration.y -= 10;
                keys[0] = false;
            }
            if (keys[1]) {
                acceleration.x -= 5;
                count++;
                if (count % 10 == 0) {
                    count = 0;
                    spriteChanger = !spriteChanger;
                }
                if (spriteChanger) ivan = ivanImages[3];
                else ivan = ivanImages[1];
            }
            if (keys[2]) {
                acceleration.x += 5;
                count++;
                if (count % 10 == 0) {
                    count = 0;
                    spriteChanger = !spriteChanger;
                }
                if (spriteChanger) ivan = ivanImages[2];
                else ivan = ivanImages[0];
            }
            if (keys[3]) {
                acceleration.y += 1;
            }
            Blocks x;
            if ((x = IvanTheRussian.instance.checkCollide()) != null) {
                if (x.getType().equals("Flag")) {
                    IvanTheRussian.instance.WIN(x);
                }
            }
            position.x += acceleration.x;
            if ((x = IvanTheRussian.instance.checkCollide()) != null/*&&!x.isSolid()*/) {
                if (x.isDoesDMG()) {
                    position = IvanTheRussian.instance.startPoint;
                    takeDmg(1);
                }
                if (acceleration.x > 0) {
                    position.x = x.getPos().x - width / 2;
                } else {
                    position.x = x.getPos().x + width / 2;
                }
            }
            position.y += acceleration.y;
            if ((x = IvanTheRussian.instance.checkCollide()) != null/*&&!x.isSolid()*/) {
                if (x.isDoesDMG()) {
                    position = IvanTheRussian.instance.startPoint;
                    takeDmg(1);
                }
                if (x.isSolid()) {
                    if (acceleration.y > 0) {
                        position.y = x.getPos().y - ivanHeight;
                        jumpAllowed = true;
                    } else {
                        position.y = x.getPos().y + width / 2;
                        jumpAllowed = false;
                    }
                    acceleration.y = 0;
                }
            } else {
                jumpAllowed = false;
            }
            acceleration.x = 0;
            acceleration.limit(20);
        }
    }

    boolean facingRight() {
        return ivan.equals(ivanImages[0]) || ivan.equals(ivanImages[2]);
    }

    boolean checkHealth() {
        if (health <= 0) {
            dead = true;
            return true;
        }
        return false;
    }

    private void takeDmg(int amount) {
        health -= amount;
        if (health > 10) health = 10;
    }

    void setPos(PVector set) {
        position = set;
    }

    void heal(int amount) {
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

    static void removeBullet(Boolet c) {
        bullets.remove(c);
    }

    void shoot() {
        if(IvanTheRussian.instance.frameCount-frameCountOld>(IvanTheRussian.instance.frameRate/3)*2) {
            double speed = 10;
            if (!facingRight()) speed = -10;
            Boolet cur = new Boolet(position.x, position.y, speed, ammoType);
            bullets.add(cur);
            frameCountOld=IvanTheRussian.instance.frameCount;
        }
    }

    PVector getPosition() {
        return position;
    }

    PImage getIvan() {
        return ivan;
    }

}