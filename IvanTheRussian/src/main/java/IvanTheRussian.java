import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;

/**
 * Created by Shaun Sinclair.
 * CGRA 151.
 * Start date: 27/09/2016.
 */

public class IvanTheRussian extends PApplet {

    public static void main(String[] args) {
        main(MethodHandles.lookup().lookupClass().getName());
    }


    static IvanTheRussian instance;
    //base size of all objects(grid size)
    static int size = 40;
    //player(not for hating)
    private Ivan ivan;
    //Images
    private PImage bg;          //background
    private PImage DirtPlat;    //ground(dirt)
    private PImage Spike;
    private PImage MineOn;
    private PImage MineOff;
    private boolean blip;
    private int mineChanger = 0;
    //Objects
    private static boolean keys[] = {false, false, false, false};
    private ArrayList<Blocks> objects = new ArrayList<>();
    private static boolean jumpAllowed = true;

    public void setup() {
        imageMode(CENTER);
        instance = this;
        ivan = new Ivan(new PVector(size, size), false);
        bg = loadImage("Background.jpg");
        ArrayList<PImage> images = new ArrayList<>();
        images.add(DirtPlat = loadImage("Earth.png"));
        images.add(Spike = loadImage("Spikes.png"));
        images.add(MineOn = loadImage("MineOn.png"));
        images.add(MineOff = loadImage("MineOff.png"));
        for (PImage image : images) {
            image.resize(size, size);
        }
        loadLevel(0);
    }

    public void settings() {
        size(600, 600);
    }

    public void draw() {
        bg.resize(width, height);
        background(bg);
        for (Blocks temp : objects) {
            String type = temp.getType();
            switch (type) {
                case "Plat":
                    image(DirtPlat, temp.getPos().x - size / 2, temp.getPos().y + size / 2);
                    break;
                case "Spike":
                    image(Spike, temp.getPos().x - size / 2, temp.getPos().y + size / 2);
                    break;
                case "Mine":
                    mineChanger++;
                    if (mineChanger % 3 == 0) {
                        blip = !blip;
                    }
                    if (blip) image(MineOn, temp.getPos().x - size / 2, temp.getPos().y + size / 2);
                    else image(MineOff, temp.getPos().x - size / 2, temp.getPos().y + size / 2);
                    break;
            }
        }
        displayHealth();
        ivan.move();
        drawPlayer();
    }

    private void drawPlayer() {
        image(ivan.getIvan(), ivan.getPosition().x-size/2, ivan.getPosition().y+size);
    }


    private void displayHealth() {
        int hSize = 20;
        PImage Empty = loadImage("EmptyHeart.png");
        PImage Half = loadImage("HalfHeart.png");
        PImage Full = loadImage("FullHeart.png");
        Empty.resize(hSize, hSize);
        Half.resize(hSize, hSize);
        Full.resize(hSize, hSize);
        int health = ivan.getHealth();
        if (health <= 0) ivan.die();
        for (int i = 0; i < 5; i++, health--) {
            if (health > 1) {
                image(Full, hSize + (i * hSize), hSize);
            } else if (health > 0) {
                image(Half, hSize + (i * hSize), hSize);
            } else {
                image(Empty, hSize + (i * hSize), hSize);
            }
        }
    }

    static void setJumpAllowed(boolean setJumpAllowed) {
        jumpAllowed = setJumpAllowed;
    }

    public void keyPressed() {
        //up
        if (key == 'w' || key == 'W') {
            if (jumpAllowed) {
                keys[0] = true;
                jumpAllowed = false;
            }
        }
        //left
        if (key == 'a' || key == 'A') {
            keys[1] = true;
        }
        //right
        if (key == 'd' || key == 'D') {
            keys[2] = true;
        }
        ///down
        if (key == 's' || key == 'S') {
            keys[3] = true;
        }
        //change ammo
        if (key == 'q' || key == 'Q') {
            ivan.cycleAmmo();
        }
    }

    public void keyReleased() {
        if (key == 'w' || key == 'W') {
            keys[0] = false;
        }
        if (key == 'a' || key == 'A') {
            keys[1] = false;
            ivan.setIvanImages(1);
        }
        if (key == 'd' || key == 'D') {
            keys[2] = false;
            ivan.setIvanImages(0);
        }
        if (key == 's' || key == 'S') {
            keys[3] = false;
            System.out.println("S");
        }
    }

    private void loadLevel(int level) {
        switch (level) {
            //test stuff
            case 0:
                objects.add(new Blocks(new PVector(size * 6, size * 3), "Mine"));
                for (int i = 0; i < 8; i++) {
                    objects.add(new Blocks(new PVector(size * i, size * 4), "Plat"));
                }
                objects.add(new Blocks(new PVector(size * 6, size * 8), "Spike"));
                for (int i = 0; i < 8; i++) {
                    objects.add(new Blocks(new PVector(size * i, size * 9), "Plat"));
                }
                break;
            case 1:
                objects.clear();
                objects.addAll(readLevel(level));
                break;
            case 2:
                objects.clear();
                objects.addAll(readLevel(level));
                break;

        }

    }

    Blocks checkCollide(){
        Blocks x;
        for (Blocks object : objects) {
            x = RectCol.collisionIvan(ivan, object);
            if (x !=null) return x;
        }
        return null;
    }

    static boolean[] getKeys() {
        return keys;
    }

    private ArrayList<Blocks> readLevel(int level) {

        return null;
    }
}

