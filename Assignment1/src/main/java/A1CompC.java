import processing.core.PApplet;

import java.lang.invoke.MethodHandles;

/**
 * Created by Shaun on 15/07/2016.
 */
public class A1CompC extends PApplet {
    private int Size = 500;
    private int x=0;
    private int num=500;
    private int dif=70;

    public static void main(String[] args) {
        main(MethodHandles.lookup().lookupClass().getName());
    }

    public void setup() {
        background(140);
    }

    public void settings() {
        size(Size+dif, Size);
    }

    public void draw() {
        float x1, x2,x3, y1, y2, y3;
        while (x<num){
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
