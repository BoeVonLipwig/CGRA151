import processing.core.PApplet;

import java.lang.invoke.MethodHandles;

/**
 * Created by Shaun on 17/07/2016.
 */
public class A1Chal extends PApplet {
    private float x = 20;
    private float y = 20;
    private float xVec = 2;
    private float yVec = 2;
    private int red = 140, green = 0, blue = 140;
    private boolean upNotSide = true;
    private double grav = 0.1;

    public static void main(String[] args) {
        main(MethodHandles.lookup().lookupClass().getName());
    }

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
        if (grav > 0&& xVec < 8 && xVec > -8 || grav < 0 && yVec > -8 && yVec < 8) {
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
            println("fucking what");
            if (key == 'S') {
                println("S");
                upNotSide = true;
                grav = 0.1;
            } else if (key == 'W') {
                println("W");
                upNotSide = true;
                grav = -0.1;
            } else if (key == 'A') {
                println("A");
                upNotSide = false;
                grav = -0.1;
            } else if (key == 'D') {
                println("D");
                upNotSide = false;
                grav = 0.1;
            }
        }
    }


    public void changeCol() {
        red = (int) random(0, 255);
        green = (int) random(0, 255);
        blue = (int) random(0, 255);
    }
}