import processing.core.PApplet;

import java.lang.invoke.MethodHandles;

/**
 * Created by sdmsi on 17/07/2016.
 */
public class A1CompD extends PApplet {

    int Size = 500;
    int SIZE = 50;

    public static void main(String[] args) {
        main(MethodHandles.lookup().lookupClass().getName());
    }

    public void setup() {
        background(255);
        ellipseMode(CENTER);
    }

    public void settings() {
        size(Size, Size);
    }

    public void draw() {
        if (mousePressed) {
            fill(255,0,0);
        } else {
            fill(0, 0, 255);
        }
        ellipse(mouseX, mouseY, SIZE, SIZE);
    }


}
