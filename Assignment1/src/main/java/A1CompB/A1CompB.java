package A1CompB;

import processing.core.PApplet;

import java.lang.invoke.MethodHandles;

/**
 * Created by Shaun on 15/07/2016.
 */
public class A1CompB extends PApplet {

    public static void main(String[] args) {
        main(MethodHandles.lookup().lookupClass().getName());
    }

    private float count = 0;

    public void setup() {
        background(140);
        noLoop();
    }

    public void settings() {
        int size = 500;
        size(size + 50, size);
    }

    public void draw() {
        int cols = 15;
        float spacingx = (width / cols);
        int rows = 15;
        float spacingy = (height / rows);
        for (int j = 0; j < cols; j++) {
            while (count < width) {
                float spacingBetween = random(2, 12);
                float ranWid = random(spacingx / 6, spacingx);
                rect(count, spacingy * j, ranWid, spacingy - 2);
                count += ranWid + spacingBetween;
            }
            count = 0;
        }
    }

}
