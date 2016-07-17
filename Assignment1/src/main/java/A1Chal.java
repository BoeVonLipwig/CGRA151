import processing.core.PApplet;

import java.lang.invoke.MethodHandles;

/**
 * Created by sdmsi on 17/07/2016.
 */
public class A1Chal extends PApplet {
    int Size = 500;
    int Diam = 20;
    float x = 20;
    float y = 20;
    float xVec = 2;
    float yVec = 2;
    int red = 140, green = 0, blue = 140;
    boolean upNotSide = true;
    double grav = 0.1;

    public static void main(String[] args) {
        main(MethodHandles.lookup().lookupClass().getName());
    }

    public void setup() {
        background(0);
    }

    public void settings() {
        size(Size, Size);
    }

    public void draw() {
        println("Try not to hit the edge!!!");
        clear();
        fill(red, green, blue);
        ellipse(x, y, Diam, Diam);
        if (xVec < 8 && xVec > -8 && yVec > -8 && yVec < 8) {
            if (upNotSide) {
                yVec += grav;
            } else {
                xVec += grav;
            }
        }
        x += xVec;
        y += yVec;
        if (y >= height - Diam / 2) {
            changeCol();
            yVec -= 2 * yVec;
        } else if (y <= 0 + Diam / 2) {
            yVec -= 2 * yVec;
            changeCol();
        }
        if (x >= width - Diam / 2) {
            xVec -= 2 * xVec;
            changeCol();
        } else if (x <= 0 + Diam / 2) {
            xVec -= 2 * xVec;
            changeCol();
        }
        if (keyPressed == true) {
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


    public void changeCol() {
        red = (int) random(0, 255);
        green = (int) random(0, 255);
        blue = (int) random(0, 255);
    }
}