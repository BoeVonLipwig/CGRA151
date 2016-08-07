package A2Comp;/*
  Started by Shaun on 31/07/2016.
 */

import processing.core.PApplet;
import processing.core.PVector;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;


public class A2Comp extends PApplet {

    public static void main(String[] args) {
        main(MethodHandles.lookup().lookupClass().getName());
    }

    private float xVec = 5;
    private float yVec = 5;
    private float ballX;
    private float ballY;
    private boolean mouseKey = true;
    private float batX;
    private float batY;
    private float batWid = 100;
    private float batHei = 20;
    private float batWRad = batWid / 2;
    private float batHRad = batHei / 2;
    private int diam = 80;
    private PVector velocity = new PVector(xVec, yVec);
    private ArrayList<Block> blocks = new ArrayList();

    public void setup() {
        ballX = batX = width / 2;
        ballY = batY = height / 2;
        int numWide = 5;
        int numHigh = 2;
        for (int i = 0; i < numWide; i++) {
            for (int j = 0; j < numHigh; j++) {
                blocks.add(new Block((batWid * i) + 40, (batHei * j) + 40, batWid, batHei));
            }
        }
    }

    public void settings() {
        int SIZE = 600;
        size(SIZE, SIZE);
    }

    public void draw() {
        clear();
        background(139, 69, 19);
        for (Block block : blocks) {
            block.draw();
        }
        fill(200, 200, 200);
        ballX += velocity.x / 2;
        ballY += velocity.y / 2;
        checkCollision();
        rect(batX = (mouseX - batWRad), batY = (mouseY - batHRad), batWid, batHei);
        ellipse(ballX, ballY, diam, diam);
        ballX += velocity.x / 2;
        ballY += velocity.y / 2;

    }
    //th following method was made with a lot of help from sanjay govind and closely follows his code
    private void checkCollision() {
        int radius = diam / 2;
        if (ballX > width - radius) {
            ballX = width - radius;
            velocity.x *= -1;
        } else if (ballX < radius) {
            velocity.x *= -1;
            ballX = radius;
        }
        if (ballY > height - radius) {
            velocity.y *= -1;
            ballY = height - radius;
        } else if (ballY < radius) {
            velocity.y *= -1;
            ballY = radius;
        }
        float distX = Math.abs(ballX - batX - batWRad);
        float distY = Math.abs(ballY - batY - batHRad);
        if (distX > (batWRad + radius)) {
            return;
        }
        if (distY > (batHRad + radius)) {
            return;
        }

        if (distY <= (batHRad)) {
            velocity.x = -velocity.x;
            if (ballX + radius < batX + batWid) {
                ballX = batX - radius - 1;
            } else {
                ballX = batX + batWid + radius + 1;
            }
            return;
        }
        if (distX <= (batWRad)) {
            velocity.y = -velocity.y;
            if (ballY + radius < batY + batHei) {
                ballY = batY - radius - 1;
            } else {
                ballY = batY + batHei + radius + 1;
            }
            return;
        }

        float dx = distX - batWRad;
        float dy = distY - batHRad;
        if (dx * dx + dy * dy <= sq(radius)) {
            if (ballY + radius < batY + batHei) {
                ballY = batY - radius - 1;
            } else {
                ballY = batY + batHei + radius + 1;
            }
            velocity = velocity.mult(-1);
        }
    }

    public void mousePressed() {
        mouseKey = !mouseKey;
    }

    private class Block {

        private int hp = 3;
        private float posY;
        private float posX;
        private float wid, hei;

        Block(float posX, float posY, float wid, float hei) {
            this.posX = posX;
            this.posY = posY;
            this.wid = wid;
            this.hei = hei;
        }

        public void hit() {
            hp--;
            draw();
        }

        public float getPosX() {
            return posX;
        }

        public float getPosY() {
            return posY;
        }

        void draw() {
            if (hp == 3) {
                fill(0, 255, 0);
            } else if (hp == 2) {
                fill(255, 255, 0);
            } else if (hp == 1) {
                fill(255, 0, 0);
            }
            rect(posX, posY, wid, hei);
        }
    }
}

