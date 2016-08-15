package A3Core;

import processing.core.PApplet;
import processing.core.PVector;

import java.lang.invoke.MethodHandles;

/**
 * Started by Shaun on 7/08/2016.
 */
public class A3Core extends PApplet {
    public static void main(String[] args) {
        main(MethodHandles.lookup().lookupClass().getName());
    }

    private PVector[] points= new PVector[12];
    private PVector mouse=new PVector(mouseX,mouseY);

    public void setup() {
        produceRandom();
        noFill();
        strokeWeight(2);
        rectMode(CENTER);
        background(255);
    }

    public void settings() {
        int size = 500;
        size(size + 50, size);
    }

    public void draw() {
        beginShape();
        stroke(0,0,255);
        for (int i = 0; i < points.length+3; i++) {
            curveVertex(points[i%points.length].x,points[i%points.length].y);
        }
        endShape();
        stroke(255,0,0);
        for (int i = 0; i < points.length; i++) {
            rect(points[i].x,points[i].y,10,10);
            //set i properly
            line(points[i].x,points[i].y,points[i-1].x,points[i+-1].y);
        }
        mouse=new PVector(mouseX,mouseY);
        if(mousePressed){

        }
    }
    private void produceRandom(){
        for (int i = 0; i < points.length; i++) {
            points[i]=new PVector(random(width-20),random(height-20));
        }
    }
}
