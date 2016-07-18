package A1CompC;

import processing.core.PApplet;

import java.lang.invoke.MethodHandles;

/**
 * Created by Shaun on 15/07/2016.
 */
public class A1CompC extends PApplet {

    public static void main(String[] args) {
        main(MethodHandles.lookup().lookupClass().getName());
    }

    private int x=0;
    private int dif=70;

    public void setup() {
        background(140);
    }

    public void settings() {
        int size = 500;
        size(size +dif, size);
    }

    public void draw() {
        float x1, x2,x3, y1, y2, y3;
        int num = 500;
        while (x< num){
            x1=random(0,width);
            y1=random(0,height);
            x2=x1+(random(-dif,dif));
            y2=y1+(random(-dif,dif));
            x3=x2+(random(-dif,dif));
            y3=y2+(random(-dif,dif));
            triangle(x1, y1, x2, y2, x3, y3);
            x++;
        }
    }

}
