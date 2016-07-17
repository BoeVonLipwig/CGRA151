import processing.core.PApplet;

import java.lang.invoke.MethodHandles;

/**
 * Created by Shaun on 15/07/2016.
 */
public class A1CompA extends PApplet {

    public static void main(String[] args) {
        main(MethodHandles.lookup().lookupClass().getName());
    }

    public void setup() {
        background(255);
    }

    public void settings() {
        int size = 500;
        size(size, size);
    }

    public void draw() {
        int cols = 15;
        int spacingx = (width / cols);
        int rows = 15;
        int spacingy = (height/ rows);
        for (int j = 0; j< cols; j++) {
            for (int i = 0; i < rows; i++) {
                rect(spacingx * i, spacingy*j,spacingx-2,spacingy-2);
            }
        }
    }

}
