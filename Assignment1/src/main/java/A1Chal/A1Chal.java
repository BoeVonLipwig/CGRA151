package A1Chal;

import processing.core.PApplet;

import java.lang.invoke.MethodHandles;

/**
 * Started by Shaun on 17/07/2016.
 */
public class A1Chal extends PApplet {

    public static void main(String[] args) {
        main(MethodHandles.lookup().lookupClass().getName());
    }

    private float xVec = 0, yVec = 0, y = height / 2, x = width / 2, gravVal = 0.1f, grav = gravVal;//0.1=easy 0.5=medium 1=hard(game doesn't ever actually get difficult)
    private int red = 0, green = 0, blue = 0, redBack = 255, greenBack = 255, blueBack = 255;
    private int count = 0;
    private int SIZE = 600;
    private int time = 400;
    private boolean upNotSide = true;
    private float recWid = SIZE, recHei = SIZE, recX = 0, recY = 0;

    public void setup() {
        strokeWeight(0);
    }

    public void settings() {
        println("Try not to hit the edge!!!");
        size(SIZE, SIZE);
    }

    public void draw() {
        int recSpeed = 10;
        if (count % recSpeed == 0 && recWid > 100 && recHei > 100) {
            recX += 0.5;
            recY += 0.5;
            recWid -= 1;
            recHei -= 1;
        } else if (recWid <= 100 && recHei <= 100) {
            time--;
            if (time == 0) {
                xVec = 0;
                yVec = 0;
                grav = 0;
                gravVal = 0;
                println("winner");
            }
        }
        clear();
        background(redBack, blueBack, greenBack);
        strokeWeight(5);
        noFill();
        rect(recX, recY, recWid, recHei);
        strokeWeight(0);
        int diam = 20;
        fill(red, green, blue, 90);
        ellipse(x - 2 * xVec, y - 2 * yVec, diam - 4, diam - 4);
        ellipse((float) (x - 1.5 * xVec), (float) (y - 1.5 * yVec), diam - 3, diam - 3);
        ellipse(x - xVec, y - yVec, diam - 2, diam - 2);
        ellipse((float) (x - 0.5 * xVec), (float) (y - 0.5 * yVec), diam - 1, diam - 1);
        fill(red, green, blue);
        ellipse(x, y, diam, diam);
        int lim = 6;
        if (grav > 0 && xVec < lim && xVec > -lim || grav < 0 && yVec > -lim && yVec < lim) {
            if (upNotSide) {
                yVec += grav;
            } else {
                xVec += grav;
            }
        }
        x += xVec;
        y += yVec;
        if (y >= (recHei + recY) - diam / 2 || y <= recY + diam / 2) {
            yVec -= 2 * yVec;
            die();
        } else if (x > (recWid + recX) - diam / 2 || x < recX + diam / 2) {
            xVec -= 2 * xVec;
            die();
        }
        if (keyPressed) {
            if (key == 's') {
                upNotSide = true;
                grav = gravVal;
            } else if (key == 'w') {
                upNotSide = true;
                grav = -gravVal;
            } else if (key == 'a') {
                upNotSide = false;
                grav = -gravVal;
            } else if (key == 'd') {
                upNotSide = false;
                grav = gravVal;
            }
        }
        count++;
    }

    private void die() {
        red = (int) random(0, 255);
        redBack = 255 - red;
        green = (int) random(0, 255);
        greenBack = 255 - green;
        blue = (int) random(0, 255);
        blueBack = 255 - blue;
        count = 0;
        count = 0;
        recWid = SIZE;
        recHei = SIZE;
        recX = 0;
        recY = 0;
    }
}