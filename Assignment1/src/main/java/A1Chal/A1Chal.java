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

    private float x = 20;
    private float y = 20;
    private float xVec = 2;
    private float yVec = 2;
    private int red = 140, green = 0, blue = 140;
    private boolean upNotSide = true;
    private double grav = 0.1;

    public void setup() {
        background(0);
    }

    public void settings() {
        int size = 500;
        println("Try not to hit the edge!!!");
        size(size, size);
    }

    public void draw() {
        clear();
        fill(red, green, blue);
        int diam = 20;
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
        if (y >= height - diam / 2) {
            changeCol();
            yVec -= 2 * yVec;
        } else if (y <= diam / 2) {
            yVec -= 2 * yVec;
            changeCol();
        }
        if (x >= width - diam / 2) {
            xVec -= 2 * xVec;
            changeCol();
        } else if (x <= diam / 2) {
            xVec -= 2 * xVec;
            changeCol();
        }
        if (keyPressed) {
            if (key == 's') {
                upNotSide = true;
                grav = 0.1;
            } else if (key == 'w') {
                upNotSide = true;
                grav = -0.1;
            } else if (key == 'a') {
                upNotSide = false;
                grav = -0.1;
            } else if (key == 'd') {
                upNotSide = false;
                grav = 0.1;
            }
        }
    }


    private void changeCol() {
        red = (int) random(0, 255);
        green = (int) random(0, 255);
        blue = (int) random(0, 255);
    }
}