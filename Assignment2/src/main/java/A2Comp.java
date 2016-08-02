import processing.core.PApplet;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;

/**
 * Started by Shaun on 2/08/2016.
 */
public class A2Comp extends PApplet {

    private ArrayList<Block> blocks = new ArrayList();
    public static void main(String[] args) {
        main(MethodHandles.lookup().lookupClass().getName());
    }

    private float x = 6;
    private float y = 0;
    private float ballY = height / 2;
    private float ballX = width / 2;
    private boolean mouseKey = true;
    private float batWid = 100;
    private float batHei = 20;

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
    public void draw(){

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
//            if (hp == 3) {
//                fill(0, 255, 0);
//            } else if (hp == 2) {
//                fill(255, 255, 0);
//            } else if (hp == 1) {
//                fill(255, 0, 0);
//            }
            rect(posX, posY, wid, hei);
        }
    }
}
