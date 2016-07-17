import processing.core.PApplet;

import java.lang.invoke.MethodHandles;

/**
 * Created by sdmsi on 15/07/2016.
 */
public class A1CompB extends PApplet {
    int Size = 500;
    int rows = 15;
    int cols = 15;
    float ranWid;
    float count = 0;
    float spacingBetween = 0;

    public static void main(String[] args) {
        main(MethodHandles.lookup().lookupClass().getName());
    }

    public void setup() {
        background(140);
        noLoop();
    }

    public void settings() {
        size(Size + 50, Size);
    }

    public void draw() {
        float spacingx = (width / cols);
        float spacingy = (height / rows);
        for (int j = 0; j < cols; j++) {
            while (count < width) {
                spacingBetween = random(2, 12);
                ranWid = random(spacingx / 6, spacingx);
                rect(count, spacingy * j, ranWid, spacingy - 2);
                count += ranWid + spacingBetween;
            }
            count = 0;
        }
    }

}
