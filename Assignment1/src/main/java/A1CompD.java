import processing.core.PApplet;

import java.lang.invoke.MethodHandles;

/**
 * Created by Shaun on 17/07/2016.
 */
public class A1CompD extends PApplet {

    public static void main(String[] args) {
        main(MethodHandles.lookup().lookupClass().getName());
    }

    public void setup() {
        background(255);
        ellipseMode(CENTER);
    }

    public void settings() {
        int size = 500;
        size(size, size);
    }

    public void draw() {
        if (mousePressed) {
            fill(255,0,0);
        } else {
            fill(0, 0, 255);
        }
        int SIZE = 50;
        ellipse(mouseX, mouseY, SIZE, SIZE);
    }


}
