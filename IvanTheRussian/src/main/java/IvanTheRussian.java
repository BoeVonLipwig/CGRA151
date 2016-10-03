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
    //Objects
    private ArrayList<Object> objects = new ArrayList<>();

    public void setup() {
        instance =this;
        ivan = new Ivan(height - size, size, false);
        bg = loadImage("Background.jpg");
        ArrayList<PImage> images=new ArrayList<>();
        images.add(DirtPlat = loadImage("Earth.png"));
        images.add(Spike = loadImage("Spikes.png"));
        images.add(  MineOn= loadImage("MineOn.png"));
        images.add(  MineOff= loadImage("MineOff.png"));
        for (PImage image : images) {
            image.resize(size,size);
        }
        loadLevel(0);
    }



    public void settings() {
        width = 600;
        height = 600;
        size(width, height);
    }

    public void draw() {
        bg.resize(width, height);
        background(bg);
        rect(ivan.position.x, ivan.position.y, ivan.width, ivan.height);
        if (keyPressed) {
            switch (key) {
                case 'w':
                    ivan.jump();
                    break;
                case 'd':
                    ivan.accl.x = ivan.moveSpeed;
                    break;
                case 'a':
                    ivan.accl.x = -ivan.moveSpeed;
                    break;
            }
            ivan.move();
        } else ivan.accl.x = 0;
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
                    blip=!blip;
                    if(blip) image(MineOn, temp.getPos().x, temp.getPos().y);
                    else image(MineOff, temp.getPos().x, temp.getPos().y);
                    break;
                case "Head":
                    image(ivan.head, temp.getPos().x, temp.getPos().y);
                    break;
            }
        }
    }


    private void loadLevel(int level) {
        switch (level){
            //test stuff
            case 0:
                objects.add(new Object(new PVector(width / 2, size*3), "Mine"));
                objects.add(new Object(new PVector(width / 2, size*4), "Plat"));
                objects.add(new Object(new PVector(width / 2, size*8), "Spike"));
                objects.add(new Object(new PVector(width / 2, size*9), "Plat"));
                objects.add(new Object(new PVector(width / 2, size*11), "Head"));
                break;
            case 1:


        }

    }
}
