/*
  Started by Shaun on 31/07/2016.
 */

import processing.core.PApplet;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;

public class A2Core extends PApplet {

    public A2Core() {
    }

    public static void main(String[] args) {
        main(MethodHandles.lookup().lookupClass().getName());
    }

    private float xVec = 6, yVec = 0, ballY = height / 2, ballX = width / 2, gravVal = 0.1f;
    private boolean mouseKey = true;
    private float batX = width / 2, batY, batWid = 100, batHei = 20;
    private int numWide = 8, numHigh = 2;
    private ArrayList<Block> blocks = new ArrayList();

    public void setup() {
        rectMode(CENTER);
        blocks.add(new Block((20), (20), batWid, batHei));
        for (int i = 1; i < numWide; i++) {
            for (int j = 1; j < numHigh; j++) {
                blocks.add(new Block((20 + batWid) * i, (20 + batHei) * j, batWid, batHei));
            }
        }
    }

    public void settings() {
        int SIZE = 600;
        size(SIZE, SIZE);
    }

    public void draw() {
        clear();
        for (int i = 0; i < blocks.size(); i++) {
            blocks.get(i).draw();
        }
        batY = height - 40;
        background(139, 69, 19);
        int diam = 80;
        int raduis = diam / 2;
        ellipse(ballX - xVec, ballY - yVec, diam - 2, diam - 2);
        int lim = 6;
        if (yVec < lim) {
            yVec += gravVal;
        }
        ballX += xVec;
        ballY += yVec;
        if (ballY > height - (raduis) || ballY < (raduis)) {
            yVec -= 2 * yVec;
        } else if (ballX > width - (raduis) || ballX < (raduis)) {
            xVec -= 2 * xVec;
        }
        rect(batX - batWid / 2, batY, batWid, batHei);
        if (mouseKey) {
            //testing only
            batX = mouseX;
            batY = mouseY;
//            if (batX -20 < mouseX-5) {
//                batX += 6;
//            } else if (batX -20 > mouseX+5) {
//                batX -= 6;
//            }
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
        if (ballY + raduis >= batY - 3 && ballX + raduis > batX - batWid && ballX < batX + batWid - raduis) {
            xVec *= -1;
            yVec *= -1;
        }
    }

    public void mousePressed() {
        mouseKey = !mouseKey;
    }


    public class Block {

        private int hp = 3;
        private float posY;
        private float posX;
        private float wid, hei;

        Block(float posX, float posY, float wid, float hei) {
            this.posX = posX;
            this.posY = posY;
            this.wid = 40;
            this.hei = 40;
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
            System.out.println("H "+hei);
            rect(posX, posY, wid, hei);
            System.out.println("W "+wid);
            fill(200,200,200);
        }
    }
}