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
    private ArrayList<Object> objects = new ArrayList<>();
    private static boolean jumpAllowed=true;

    public void setup() {
        instance = this;
        ivan = new Ivan(new PVector(size,size), false);
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
        size(600,600);
    }

    public void draw() {
        bg.resize(width, height);
        background(bg);
        for (Object temp : objects) {
            String type = temp.getType();
            switch (type) {
                case "Plat":
                    image(DirtPlat, temp.getPos().x, temp.getPos().y);
                    break;
                case "Spike":
                    image(Spike, temp.getPos().x, temp.getPos().y);
                    break;
                case "Mine":
                    mineChanger++;
                    if (mineChanger % 3 == 0) {
                        blip = !blip;
                    }
                    if (blip) image(MineOn, temp.getPos().x, temp.getPos().y);
                    else image(MineOff, temp.getPos().x, temp.getPos().y);
                    break;
            }
        }
        displayHealth();
        ivan.move();
        drawPlayer();
    }

    private void drawPlayer(){
        image(ivan.getIvan(),ivan.getPosition().x,ivan.getPosition().y);
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

    public void keyPressed(){
        //up
        if (key == 'w' || key == 'W') {
            if(jumpAllowed){
            keys[0] = true;
                jumpAllowed=false;
            }
            System.out.println("W");
        }
        //left
        if (key == 'a' || key == 'A') {
            keys[1] = true;
            System.out.println("A");
        }
        //right
        if (key == 'd' || key == 'D') {
            keys[2] = true;
            System.out.println("D");
        }
        ///down
        if (key == 's' || key == 'S') {
            keys[3] = true;
            System.out.println("S");
        }
        //change ammo
        if (key == 'q' || key == 'Q') {
            ivan.cycleAmmo();
            System.out.println("Q");
        }
    }

    public void keyReleased() {
        if (key == 'w' || key == 'W') {
            keys[0] = false;
        }
        if (key == 'a' || key == 'A') {
            keys[1] = false;
        }
        if (key == 'd' || key == 'D') {
            keys[2] = false;
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
                objects.add(new Object(new PVector(size*6, size * 3), "Mine"));
                for (int i = 0; i < 8; i++) {
                    objects.add(new Object(new PVector(size * i, size * 4), "Plat"));
                }
                objects.add(new Object(new PVector(size*6, size * 8), "Spike"));
                for (int i = 0; i < 8; i++) {
                    objects.add(new Object(new PVector(size*i, size * 9), "Plat"));
                }
                break;
            case 1:
                objects.addAll(readLevel(level));
                break;

        }

    }

    static boolean[] getKeys() {
        return keys;
    }

    private ArrayList<Object> readLevel(int level) {

        return null;
    }
}

