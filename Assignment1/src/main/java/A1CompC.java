import processing.core.PApplet;

import java.lang.invoke.MethodHandles;

/**
 * Created by sdmsi on 15/07/2016.
 */
public class A1CompC extends PApplet {
    int Size = 500;

    public static void main(String[] args) {
        main(MethodHandles.lookup().lookupClass().getName());
    }

    public void setup() {
        background(255);
    }

    public void settings() {
        size(Size+50, Size);
    }
}
