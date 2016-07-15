/**
 * Created by Shaun on 15/07/2016.
 */

import processing.core.PApplet;

import java.lang.invoke.MethodHandles;

public class A1Core extends PApplet {
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

    public void draw() {
        stroke(0);
        strokeWeight(1);
        fill(140,0,140);
        beginShape();
        vertex(340,20);
        vertex(270,35);
        vertex(300,120);
        vertex(380,200);
        vertex(490,50);
        vertex(340,20);
        endShape();
        fill(255,0,0);
        rect(20,20,200,100);
        fill(0,255,0);
        ellipse(120,210,200,100);
        fill(0,0,255);
        triangle(220,280,20,330,120,420);
        stroke(140,140,0);
        strokeWeight(1);
        line(420,300,370,420);
        stroke(0,140,140);
        strokeWeight(3);
        line(460,300,410,420);
        stroke(140,0,140);
        strokeWeight(5);
        line(500,300,450,420);
    }

}