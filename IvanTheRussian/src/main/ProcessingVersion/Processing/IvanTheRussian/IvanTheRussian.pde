import processing.sound.*;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;
import processing.core.PVector;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Shaun Sinclair.
 * CGRA 151.
 * Start date: 27/09/2016.
 */
    //global vars(theres just a few)
    static IvanTheRussian instance;
    //base size of all objects(grid size)
    static int size = 40;
    private Blocks flag;
    //player(not for hating)
    private Ivan ivan;
    private PImage Earth;       //ground(dirt)
    private PImage BarrenEarth;
    private PImage BreakableWall;
    private PImage Wall;
    private PImage Spike;
    private PImage MineOn;
    private PImage MineOff;
    //nonSolid blocks
    //I know they have the same sprite but this makes it easier to understand
    private PImage FakeWall;
    private PImage FakeEarth;
    private PImage FakeBarrenEarth;
    private boolean credits = false;
    private int levelWidth;
    private int levelHeight;
    @SuppressWarnings("FieldCanBeLocal")
    private int level=1,flagIndex=0;
    private boolean blip;
    private boolean won = false;
    PVector startPoint;
    private PShape backgroundShape;
    private int mineChanger = 0;
    //Objects
    private static boolean keys[] = {false, false, false, false};
    private ArrayList<Blocks> objects = new ArrayList<Blocks>();
    private ArrayList<PImage> flags = new ArrayList<PImage>();
    private static boolean fireAllowed = true;
    SoundFile creditsSound;
    boolean creditMusic=false;
    SoundFile deathSound;
    boolean deathMusic = false;
    SoundFile playSound;
    boolean play=false;

    public void setup() {
        imageMode(CENTER);
        instance = this;
        ivan = new Ivan(new PVector(size, size));
        ArrayList<PImage> images = new ArrayList<PImage>();
        flags.add(loadImage("Goal.png"));
        flags.add(loadImage("HalfChangedFlag.png"));
        flags.add(loadImage("END.png"));
        images.add(Wall = loadImage("Wall.png"));
        images.add(Earth = loadImage("Earth.png"));
        images.add(Spike = loadImage("Spikes.png"));
        images.add(MineOn = loadImage("MineOn.png"));
        images.add(MineOff = loadImage("MineOff.png"));
        images.add(FakeWall = loadImage("FakeWall.png"));
        images.add(FakeEarth = loadImage("FakeEarth.png"));
        images.add(BarrenEarth = loadImage("BarrenEarth.png"));
        images.add(FakeBarrenEarth = loadImage("FakeBarrenEarth.png"));
        BreakableWall = loadImage("BreakableWall.png");
        BreakableWall.resize(size, size * 2);
        for (PImage image : images) {
            image.resize(size, size);
        }
        for (PImage image : flags) {
            image.resize(size * 2, size * 3);
        }
        loadLevel();
        backgroundShape = createShape();
        backgroundShape.beginShape(QUAD);
        PImage bg = loadImage("background.png");
        bg.resize(width, height);
        backgroundShape.texture(bg);
        backgroundShape.textureMode(NORMAL);
        backgroundShape.vertex(0, 0, 0, 0);
        backgroundShape.vertex(width, 0, 1, 0);
        backgroundShape.vertex(width, height, 1, 1);
        backgroundShape.vertex(0, height, 0, 1);
        backgroundShape.endShape();
    }

    public void settings() {
        size(800, 600, P2D);
        fullScreen();
    }
    
    
    private void death() {
        //clears objects
        objects.clear();
        //only runs this once cos boolean value
        if(!deathMusic){
             playSound.stop();
          //gets and plays sound file 
        SoundFile deathSound =new SoundFile( this , "deathMusic.mp3" );
        deathSound.loop();
        deathMusic=true;
        }
        //shows death screen
        image(loadImage("Death.png"),0,40);
    }

    public void draw() {
        clear();
        if(!play){
          playSound =new SoundFile( this , "playMusic.mp3" );
          playSound.loop();
          play=true;
        }
        if(credits) {
            if(!creditMusic){
             playSound.amp(0.01);
            SoundFile winSound =new SoundFile( this , "winMusic.mp3" );
            winSound.loop();
            }
            creditMusic=true;
           imageMode(CORNER);
           image(loadImage("Credits.png"),0,40);
           return;
        }
        if (won) {
            doWin();
        }
        if(deathMusic){
         //playSound.stop();
        }
          
        if (ivan.checkHealth()) {
            imageMode(CORNER);
            death();
            return;
        }
        //changes image mode
        imageMode(CENTER);
        //background
        shape(backgroundShape);
        //pushes matrix
        pushMatrix();
        //translates based on the layer excpept when to near a boundary
        translate(-Math.min(Math.max(ivan.getPosition().x - width / 2, 0), levelWidth - width),
                -Math.min(Math.max(ivan.getPosition().y - height / 2, 0), levelHeight - height));
                //looping over every object in the objects array
        for (Blocks temp : objects) {
          //returns a string stating what the object is
            String type = temp.getType();
            switch (type) {
              //draws an image based off what type of object it is
                case "Earth":
                    image(Earth, temp.getPos().x - size / 2, temp.getPos().y + size / 2);
                    break;
                case "FakeEarth":
                    image(FakeEarth, temp.getPos().x - size / 2, temp.getPos().y + size / 2);
                    break;
                case "BarrenEarth":
                    image(BarrenEarth, temp.getPos().x - size / 2, temp.getPos().y + size / 2);
                    break;
                case "FakeBarrenEarth":
                    image(FakeBarrenEarth, temp.getPos().x - size / 2, temp.getPos().y + size / 2);
                    break;
                case "Wall":
                    image(Wall, temp.getPos().x - size / 2, temp.getPos().y + size / 2);
                    break;
                case "FakeWall":
                    image(FakeWall, temp.getPos().x - size / 2, temp.getPos().y + size / 2);
                    break;
                case "Spike":
                    image(Spike, temp.getPos().x - size / 2, temp.getPos().y + size / 2);
                    break;
                case "Mine":
                //makes mine flicker
                    mineChanger++;
                    if (mineChanger % 3 == 0) {
                        blip = !blip;
                    }
                    if (blip) image(MineOn, temp.getPos().x - size / 2, temp.getPos().y + size / 2);
                    else image(MineOff, temp.getPos().x - size / 2, temp.getPos().y + size / 2);
                    break;
                case "BreakableWall":
                    image(BreakableWall, temp.getPos().x - size / 2, temp.getPos().y + size);
                    break;
            }
        }
        //move so long as game is not won(freezes ivan while game is won)
        if (!won)
        ivan.move();
        //draws player
        drawPlayer();
        //draws and move boolets
        drawBoolet();
        //draws flag
        drawFlag(flagIndex);
        popMatrix();
        // is after pop atrix as it needs to follow the screen
        displayHealth();
    }

    private void drawFlag(int flagWanted){
      //avoids null pointer errors
        if (flag == null) return;
        //gets the right image for flag
        PImage img =flags.get(flagWanted);
        //draws the flag image
        image(img,flag.getPos().x,flag.getPos().y);
    }

    private void drawPlayer() {
      //draws ivan based on what direction he is facing as the images point in diffrent directions
        if (ivan.facingRight()) image(ivan.getIvan(), ivan.getPosition().x, ivan.getPosition().y + size);
        else image(ivan.getIvan(), ivan.getPosition().x - size, ivan.getPosition().y + size);
    }

    private void drawBoolet() {
        //loops through boolets drawing then moving them
        ArrayList<Boolet> boolets = Ivan.getBullets();
        for (Boolet boolet : new ArrayList<Boolet>(boolets)) {
            image(boolet.totallyNotBill, boolet.getPosition().x, boolet.getPosition().y + 10);
            boolet.move();
        }
    }

//removes blocks
    void removeObjects(Blocks block) {
        objects.remove(block);
    }

    private void displayHealth() {
        //heart size
        int heartSize = 20;
        //heart images
        PImage Empty = loadImage("EmptyHeart.png");
        PImage Half = loadImage("HalfHeart.png");
        PImage Full = loadImage("FullHeart.png");
        //resizing
        Empty.resize(heartSize, heartSize);
        Half.resize(heartSize, heartSize);
        Full.resize(heartSize, heartSize);
        //gets ivans health
        int health = ivan.getHealth();
        ///adds heart types so that there is 5 hearts with differentamountss full
        for (int i = 0; i < 5; i++, health--) {
            if (health > 1) {
                image(Full, heartSize + (i * heartSize), heartSize);
                health--;
            } else if (health > 0) {
                image(Half, heartSize + (i * heartSize), heartSize);
            } else {
                image(Empty, heartSize + (i * heartSize), heartSize);
            }
        }
    }

    public void keyPressed() {
        //sets array parts to true when key pressed to be read from elsewhere
        //allows muli key presses at the same time
        //up
        if (key == 'w' || key == 'W') {
            keys[0] = true;
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
            ivan.setPos(startPoint.copy());
            ivan.takeDmg(1);
        }
        //shoots
        if (key == 'e' || key == 'E') {
            if (fireAllowed) {
                ivan.shoot();
            }
        }
        //turns on super jump
        if (key == 't' || key == 'T') {
            int x=ivan.getJumpHeight();
            if(x == 10){
                ivan.setJumpHeight(30);
            }else{
                ivan.setJumpHeight(10);
            }
        }
    }

    public void keyReleased() {
        //sets to false
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
                //stops holding down key to spam
                fireAllowed = true;
            }

    }

    Blocks checkHit(Boolet boolet) {
        //loops over all objects and returns the block the boolet collides with
        for (Blocks object : objects) {
            Blocks x = RectCol.collisionBoolet(boolet, object);
            if (x != null) return x;
        }
        //if no colide occurs
        return null;
    }

    Blocks checkCollide() {
        ///same as above but for ivan
        Blocks x;
        for (Blocks object : objects) {
            x = RectCol.collisionIvan(ivan, object);
            if (x != null) return x;
        }
        return null;
    }

    //returns the array of whhat keys are pressed
    static boolean[] getKeys() {
        return keys;
    }

    private void loadLevel() {
        //heals ivan
        ivan.heal(10);
        objects.clear();
        flag = null;
        // clears the objects array the adds all objects for level to it
        switch (level) {
            //test stuff
            case 0:
                objects.addAll(readLevel("test.png"));
                break;
            //tut level
            case 1:
                objects.addAll(readLevel("level1.png"));
                break;
            case 2:
                objects.addAll(readLevel("level2.png"));
                break;
            case 3:
                objects.addAll(readLevel("level3.png"));
                break;
                //roll credits
                // i didnt put music credits in there but they will be on the credits screen at some point
            case 4:
                credits = true;
                break;

        }

    }

    private ArrayList<Blocks> readLevel(String level) {
        //named werid for reasons
        Color lcolor;
        //the arrayList of blocks that will have all the objects added to
        ArrayList<Blocks> toReturn = new ArrayList<Blocks>();
        try {
          //reads image
            BufferedImage image = ImageIO.read(dataFile(level));
            //gets width and height
            levelWidth = image.getWidth() * size;
            levelHeight = image.getHeight() * size;
            //loops through rows
            for (int x = 0; x < image.getWidth(); x++) {
              //loops through collums  
              for (int y = 0; y < image.getHeight(); y++) {
                //gets color of pixle
                    lcolor = new Color(image.getRGB(x, y));
                    x++;
                    //dint use color names because my ide puts the colors in the margin for me which makes it easier to see
                    //adds a block with type based on color and posstion based on posstion
                    if (lcolor.equals(new Color(0, 0, 0))) {
                        toReturn.add(new Blocks(new PVector(size * x, size * y), "Wall"));
                    } else if (lcolor.equals(new Color(23, 255, 83))) {
                        toReturn.add(new Blocks(new PVector(size * x, size * y), "BreakableWall"));
                    } else if (lcolor.equals(new Color(0, 0, 255))) {
                        toReturn.add(new Blocks(new PVector(size * x, size * y), "FakeWall"));
                    } else if (lcolor.equals(new Color(0, 255, 0))) {
                        toReturn.add(new Blocks(new PVector(size * x, size * y), "Earth"));
                    } else if (lcolor.equals(new Color(169, 63, 63))) {
                        toReturn.add(new Blocks(new PVector(size * x, size * y), "FakeEarth"));
                    } else if (lcolor.equals(new Color(130, 127, 0))) {
                        toReturn.add(new Blocks(new PVector(size * x, size * y), "BarrenEarth"));
                    } else if (lcolor.equals(new Color(73, 104, 114))) {
                        toReturn.add(new Blocks(new PVector(size * x, size * y), "FakeBarrenEarth"));
                    } else if (lcolor.equals(new Color(76, 76, 76))) {
                        toReturn.add(new Blocks(new PVector(size * x, size * y), "Spike"));
                    } else if (lcolor.equals(new Color(255, 0, 0))) {
                        toReturn.add(new Blocks(new PVector(size * x, size * y), "Mine"));
                    } else if (lcolor.equals(new Color(255, 255, 23))) {
                        toReturn.add(flag=(new Blocks(new PVector(size * x, (float) (size * (y - 0.5))), "Flag")));
                    } else if (lcolor.equals(new Color(127, 0, 127))) {
                      //setts respawn point and start pouint
                        startPoint = new PVector(size *x,size * y);
                        ivan.setPos(startPoint.copy());
                    }
                    x--;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return toReturn;
    }

    void WIN() {
        won = true;
    }
    private int lastCount = 0;
    private void doWin() {
        //cycles through flag types based on frame rate timer
        if (frameCount - lastCount > frameRate) {
            lastCount = frameCount;
//            changes the flag index it draws from
            flagIndex++;
            if (flagIndex == flags.size()) {
                //goes to next level
                level++;
                //loads next level
                loadLevel();
                //resets won
                won = false;
                //resets flag
                flagIndex = 0;
            }

        }

    }