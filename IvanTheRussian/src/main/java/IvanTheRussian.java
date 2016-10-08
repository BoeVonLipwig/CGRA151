import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
    //Solid blocks
    private PImage bg;          //background
    private PImage Earth;       //ground(dirt)
    private PImage BarrenEarth;
    private PImage Wall;
    private PImage Spike;
    private PImage MineOn;
    private PImage MineOff;
    //nonSolid blocks
    //I know they have the same sprite but this makes it easier to understand
    private PImage FakeEarth;
    //unused yet
//    private PImage FakeWall;
//    private PImage FakeBarrenEarth;
    private boolean blip;
    private int mineChanger = 0;
    //Objects
    private static boolean keys[] = {false, false, false, false};
    private ArrayList<Blocks> objects = new ArrayList<>();
    private static boolean jumpAllowed = true, fireAllowed=true;

    public void setup() {
        imageMode(CENTER);
        instance = this;
        ivan = new Ivan(new PVector(size, size), false);
        bg = loadImage("Background.jpg");
        ArrayList<PImage> images = new ArrayList<>();
        images.add(Wall = loadImage("Wall.png"));
        images.add(Earth = loadImage("Earth.png"));
        images.add(BarrenEarth=loadImage("BarrenEarth.png"));
        images.add(Spike = loadImage("Spikes.png"));
        images.add(MineOn = loadImage("MineOn.png"));
        images.add(MineOff = loadImage("MineOff.png"));
//        images.add(FakeWall = loadImage("FakeWall.png"));
        images.add(FakeEarth = loadImage("FakeEarth.png"));
//        images.add(FakeBarrenEarth=loadImage("FakeBarrenEarth.png"));
        for (PImage image : images) {
            image.resize(size, size);
        }
        loadLevel(0);
    }

    public void settings() {
        size(1200, 600);
    }

    public void draw() {
        bg.resize(width, height);
        background(bg);
        for (Blocks temp : objects) {
            String type = temp.getType();
            switch (type) {
                case "Earth":
                    image(Earth, temp.getPos().x - size / 2, temp.getPos().y + size / 2);
                    break;
                case "FakeEarth":
                    image(FakeEarth, temp.getPos().x - size / 2, temp.getPos().y + size / 2);
                    break;
                case "BarrenEarth":
                    image(BarrenEarth, temp.getPos().x - size / 2, temp.getPos().y + size / 2);
                    break;
                case "Wall":
                    image(Wall, temp.getPos().x - size / 2, temp.getPos().y + size / 2);
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
        drawBoolet();
    }

    private void drawPlayer() {
        if (ivan.getIvanRorL()) image(ivan.getIvan(), ivan.getPosition().x, ivan.getPosition().y + size);
        else image(ivan.getIvan(), ivan.getPosition().x - size, ivan.getPosition().y + size);
    }

    private void drawBoolet(){
        ArrayList<Boolet> boolets = Ivan.getBullets();
        for (Boolet boolet:boolets){
            boolet.move();
            image(boolet.totallyNotBill,boolet.getPosition().x,boolet.getPosition().y);
        }
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
        }if (key == 'e' || key == 'E') {
            ivan.shoot();
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
        }
        if (key == 'e' || key == 'E') {
            fireAllowed=true;
        }
    }


    Blocks checkCollide() {
        Blocks x;
        for (Blocks object : objects) {
            x = RectCol.collisionIvan(ivan, object);
            if (x != null) return x;
        }
        return null;
    }

    static boolean[] getKeys() {
        return keys;
    }

    private void loadLevel(int level) {
        ivan.heal(10);
        switch (level) {
            //test stuff
            case 0:
                objects.addAll(readLevel("levelSize.png"));
                break;
            case 1:
                objects.clear();
                objects.addAll(readLevel("level1.png"));
                break;
            case 2:
                objects.clear();
                objects.addAll(readLevel("level2.png"));
                break;

        }

    }

    private ArrayList<Blocks> readLevel(String level) {
        Color color;
//        Color
        ArrayList<Blocks> toReturn = new ArrayList<>();
        try {
            BufferedImage image = ImageIO.read(new File(level));
            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y <image.getHeight(); y++) {
                    color = new Color(image.getRGB(x, y));
                    if (!color.equals(Color.white)) {
                        System.out.println(color);
                    }
                    x++;
                    //dint use color names because my ide puts the colors in the margin for me which makes it easier to see
                    if (color.equals(new Color(0,0,0))) {
                        toReturn.add(new Blocks(new PVector(size * x, size * y), "Wall"));
                    }else if(color.equals(new Color(255,0,0))){
                        toReturn.add(new Blocks(new PVector(size * x, size * y), "Mine"));
                    }else if(color.equals(new Color(0,255,0))){
                        toReturn.add(new Blocks(new PVector(size * x, size * y), "Earth"));
                    }else if(color.equals(new Color(130,127,0))){
                        toReturn.add(new Blocks(new PVector(size * x, size * y), "BarrenEarth"));
                    }else if(color.equals(new Color(76,76,76))){
                        toReturn.add(new Blocks(new PVector(size * x, size * y), "Spike"));
                    }else if(color.equals(new Color(0,0,255))){
                        toReturn.add(new Blocks(new PVector(size * x, size * y), "FakeEarth"));
                    }else if(color.equals(new Color(255,255,23))){
                        toReturn.add(new Blocks(new PVector(size * x, size * y), "Flag"));
                    }else if(color.equals(new Color(127,0,127))){
                        ivan.setPos(x,y);
                    }
                    x--;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        toReturn.add(new Blocks(new PVector(2, 2), "Earth"));
        return toReturn;
    }
}

