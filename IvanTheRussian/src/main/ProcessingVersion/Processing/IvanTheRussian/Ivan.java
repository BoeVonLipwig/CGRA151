    import processing.core.PImage;
    import processing.core.PVector;
    
    import java.util.ArrayList;
    
    /**
     * Created by Shaun Sinclair.
     * CGRA 151.
     * Start date: 27/09/2016.
     */
    class Ivan {
         //golabal vars
        private PVector position, acceleration;
        private int size = IvanTheRussian.size, ivanHeight = size * 2, width = size * 2, health = 10, count = 0;
        private boolean spriteChanger, dead = false;
        private static ArrayList<Boolet> bullets;
        private PImage ivan;
        private PImage[] ivanImages = new PImage[4];
        private int frameCountOld = IvanTheRussian.instance.frameCount;
        //constructor
        Ivan(PVector position) {
            this.position = position;
            acceleration = new PVector(0, 0);
            bullets = new ArrayList<>();
            //game instance
            IvanTheRussian game = IvanTheRussian.instance;
            //array of ivan images to be swiched between for walk cycles
            ivanImages[0] = game.loadImage("IvanRight.png");
            ivanImages[1] = game.loadImage("IvanLeft.png");
            ivanImages[2] = game.loadImage("IvanRunningRight.png");
            ivanImages[3] = game.loadImage("IvanRunningLeft.png");
            //resizes all ivan images
            for (int i = 0; i < ivanImages.length; i++) {
                if (i <= 1) {
                    ivanImages[i].resize(width, ivanHeight);
                } else {
                    ivanImages[i].resize((int) ((width / 100) * 93.36), ivanHeight);
                }
            }
            //sets starting image
            ivan = ivanImages[0];
        }
        //for changing ivans current image
        void setIvanImages(int index) {
            ivan = ivanImages[index];
        }
        //returns bullets arrayList
        static ArrayList<Boolet> getBullets() {
            return bullets;
        }
        //vars
        private boolean jumpAllowed = false;
        private int jumpHeight =10;
        private double grav = 0.4;
        
        void move() {
          //if not dead move(seems like a good rule for life)
            if (!dead) {
                //gets pressed keys
                boolean[] keys = IvanTheRussian.getKeys();
                //applies gravity
                acceleration.y += grav;
                //jumps if can 
                if (keys[0] && jumpAllowed) {
                    acceleration.y -= jumpHeight;
                    keys[0] = false;
                }
                //moves left 
                if (keys[1]) {
                    acceleration.x -= 5;
                    count++;
                    //walk cycle(swaps between 2 images every 10 cycles)
                    if (count % 10 == 0) {
                        count = 0;
                        spriteChanger = !spriteChanger;
                    }
                    //sets image
                    if (spriteChanger) ivan = ivanImages[3];
                    else ivan = ivanImages[1];
                }
                //same as abve but reversed
                if (keys[2]) {
                    acceleration.x += 5;
                    count++;
                    //figured no need really to use a second count so this is same one 
                    if (count % 10 == 0) {
                        count = 0;
                        spriteChanger = !spriteChanger;
                    }
                    if (spriteChanger) ivan = ivanImages[2];
                    else ivan = ivanImages[0];
                }
                //movve down faster if you want i guess
                if (keys[3]) {
                    acceleration.y += 1;
                }
                //var
                Blocks x;
                //moves on x axis
                position.x += acceleration.x;
                //checks colltion
                if ((x = IvanTheRussian.instance.checkCollide()) != null) {
                  //if hits something check if it does damage and if so respawn with less health  
                  if (x.isDoesDMG()) {
                        position = IvanTheRussian.instance.startPoint.copy();
                        takeDmg(1);
                    }
                    //checks if has collided with flag
                    if (x.getType().equals("Flag")) {
                        IvanTheRussian.instance.WIN();
                    }
                    if (x.isSolid()) {
                    //moves back if collided
                        if (acceleration.x > 0) {
                            position.x = x.getPos().x - width / 2;
                        } else {
                            position.x = x.getPos().x + width / 2;
                        }
                    }
                }
                //same as above but for y
                position.y += acceleration.y;
                if ((x = IvanTheRussian.instance.checkCollide()) != null) {
                    if (x.isDoesDMG()) {
                        position = IvanTheRussian.instance.startPoint.copy();
                        takeDmg(1);
                    }
                    if (x.getType().equals("Flag")) {
                        IvanTheRussian.instance.WIN();
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
                //makes it so that you stop one you realese te move keys
                acceleration.x = 0;
                //stops from just keeping on accelerating
                acceleration.limit(20);
            }
        }
    //getters and setters
        boolean facingRight() {
            return ivan.equals(ivanImages[0]) || ivan.equals(ivanImages[2]);
        }
    
        int getJumpHeight() {
            return jumpHeight;
        }
    
        void setJumpHeight(int amount) {
            this.jumpHeight = amount;
        }
        
    //returns if dead
        boolean checkHealth() {
            if (health <= 0) {
                dead = true;
                return true;
            }
            return false;
        }
    //deals dmg to ivan
        void takeDmg(int amount) {
            health -= amount;
            if (health > 10) health = 10;
        }
    //setters
        void setPos(PVector set) {
            position = set;
        }
    
        void heal(int amount) {
            takeDmg(-amount);
        }
    
        int getHealth() {
            return health;
        }
    //removes boolets
        static void removeBullet(Boolet c) {
            bullets.remove(c);
        }
    //shhots
        void shoot() {
          //checks if can shoot yet based on frame timer  
          if (IvanTheRussian.instance.frameCount - frameCountOld > (IvanTheRussian.instance.frameRate / 3) * 2) {
            //setts speed based on faced direction    
            double speed = 10;
                if (!facingRight()) speed = -10;
                //constructs the bullet
                Boolet cur = new Boolet(position.x, position.y, speed);
                //adds it to the array
                bullets.add(cur);
                //resets frame clock
                frameCountOld = IvanTheRussian.instance.frameCount;
            }
        }
    //getters
        PVector getPosition() {
            return position;
        }
    
        PImage getIvan() {
            return ivan;
        }
    
    }