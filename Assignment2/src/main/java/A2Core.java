/*
  Started by Shaun on 31/07/2016.
 */

import processing.core.PApplet;

import java.lang.invoke.MethodHandles;

public class A2Core extends PApplet {

    public static void main(String[] args) {
        main(MethodHandles.lookup().lookupClass().getName());
    }

    private float xVec = 6, yVec = 0, y = height / 2, x = width / 2, gravVal = 0.1f, grav = gravVal, xR;

    public void setup() {
    }

    public void settings() {
        int SIZE = 600;
        size(SIZE, SIZE);
        rectMode(CENTER);
    }

    public void draw() {
        clear();
        background(139, 69, 19);
        float yR = height - 40, Rwid = 40, RHei = 6;
        int diam = 20;
        ellipse(x - xVec, y - yVec, diam - 2, diam - 2);
        rect(mouseX - Rwid / 2, yR, Rwid, RHei);
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
    }
    if(keyPressed){
        if (key == 'a') {
            xR--;
        } else if (key == 'd') {
            xR++;
        }

    }


}
