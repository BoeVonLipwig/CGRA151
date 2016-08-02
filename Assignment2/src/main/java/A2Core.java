/*
  Started by Shaun on 31/07/2016.
 */

import processing.core.PApplet;
import processing.core.PVector;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;

public class A2Core extends PApplet {



    public static void main(String[] args) {
        main(MethodHandles.lookup().lookupClass().getName());
    }

    private float xVec = 6;
    private float yVec = 6;
    private float ballY = height / 2;
    private float ballX = width / 2;
    private boolean mouseKey = true;
    private float batX = width / 2;
    private float batY = width / 2;
    private float batWid = 100;
    private float batHei = 20;
    private PVector velocity = new PVector(xVec, yVec);
    private ArrayList<Block> blocks = new ArrayList();

    public void setup() {
        rectMode(CENTER);
        int numWide = 5;
        int numHigh = 2;
        for (int i = 0; i < numWide; i++) {
            for (int j = 0; j < numHigh; j++) {
                blocks.add(new Block((batWid * i) + batWid, (batHei * j) + 40, batWid, batHei));
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
        int diam = 80;
        int raduis = diam / 2;
        ellipse(ballX - velocity.x, ballY - velocity.y, diam - 2, diam - 2);
        ballX += velocity.x;
        ballY += velocity.y;
        if (ballY > height - (raduis) || ballY < (raduis)) {
            velocity.y -= 2 * velocity.y;
        } else if (ballX > width - (raduis) || ballX < (raduis)) {
            velocity.x -= 2 * velocity.x;
        }
        rect(batX - batWid / 2, batY, batWid, batHei);
        if (mouseKey) {
            batX = mouseX + (batWid / 2);
            batY = mouseY;
        } else {
            if (keyPressed) {
                if (key == 'a') {
                    batX--;
                    System.out.println("1");
                } else if (key == 'd') {
                    batX++;
                    System.out.println("2");
                }

            }
        }
        float distX = Math.abs(ballX - (batX - batWid / 2));
        float distY = Math.abs(ballY - (batY - batHei / 2));
        float dx = distX - batWid / 2;
        float dy = distY - batHei / 2;
        if (distX > (batWid / 2 + raduis)) {
            return;
        }
        if (distY > (batHei / 2 + raduis)) {
            return;
        }
        if (distY <= (batHei / 2)) {
            velocity.x = -velocity.x;
            if (ballX + raduis > batX && batX + raduis < batX + batWid) {
                batX = batX - raduis - 1;
            } else {
                batX = batX + batWid + raduis + 1;
            }
        }
        if (distX <= (batWid / 2)) {
            velocity.y = -velocity.y;
            if (ballX + raduis < batX + batHei) {
                ballX = batX - raduis - 1;
            } else {
                ballX = batX + batHei + raduis + 1;
            }
        }
        if (dx * dx + dy * dy <= raduis * raduis) {
            if (ballY + raduis < batY + batHei) {
                ballY = batY - raduis - 1;
            } else {
                ballY = batY + batHei + raduis + 1;
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
