/*
  Started by Shaun on 31/07/2016.
 */

import processing.core.PApplet;

import java.lang.invoke.MethodHandles;

public class A2Core extends PApplet {

    public static void main(String[] args) {
        main(MethodHandles.lookup().lookupClass().getName());
    }

    private float xVec = 6, yVec = 0, y = height / 2, x = width / 2, gravVal = 0.1f, grav = gravVal;
    private boolean mouseKey = true;
    private float xR=width/2,yR, Rwid = 40, RHei = 6;

    public void setup() {
        rectMode(CENTER);
    }

    public void settings() {
        int SIZE = 600;
        size(SIZE, SIZE);

    }

    public void draw() {
        clear();

        background(139, 69, 19);
        int diam = 20;
        ellipse(x - xVec, y - yVec, diam - 2, diam - 2);
        int lim = 6;
        if (yVec < lim) {
            yVec += grav;
        }
        x += xVec;
        y += yVec;
        if (y > height - (diam / 2) || y < (diam / 2)) {
            yVec -= 2 * yVec;
        } else if (x > width - (diam / 2) || x < (diam / 2)) {
            xVec -= 2 * xVec;
        }
        rect(xR - Rwid / 2, yR, Rwid, RHei);
        if (mouseKey) {
            if (xR < mouseX) {
                xR += 6;
            } else if (xR > mouseX) {
                xR -= 6;
            }
        } else {
            if (keyPressed) {
                if (key == 'a') {
                    xR--;
                    System.out.println("1");
                } else if (key == 'd') {
                    xR++;
                    System.out.println("2");
                }

            }
        }
    }

    public void mousePressed() {
        mouseKey = !mouseKey;
    }


}
