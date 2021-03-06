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

    private PVector[] points = new PVector[12];

    public void setup() {
        //setting up random points in the PVector array
        produceRandom();
        //no fill so i dont get white patches between points and such
        noFill();
        //makes it easier to see diffrence between line and curve
        strokeWeight(4);
        rectMode(CENTER);
        background(255);
    }

    public void settings() {
        //setting the size
        int size = 500;
        size(size + 50, size);
    }

    public void draw() {
        clear();
        beginShape();
        strokeWeight(4);
        stroke(0, 0, 255);
        for (int i = 0; i < points.length + 3; i++) {
            curveVertex(vertex(i).x, vertex(i).y);
        }
        endShape();
        stroke(255, 0, 0);
        strokeWeight(1);
        for (int i = 0; i < points.length; i++) {
            //make this nicer and less hard coded
            rect(points[i].x, points[i].y, 10, 10);
            line(vertex(i).x, vertex(i).y, vertex(i + 1).x, vertex(i + 1).y);
        }
        changePoint();
    }

    private PVector clickedPoint = null;

    private void changePoint() {
        PVector mouse = new PVector(mouseX, mouseY);
        if (mousePressed && clickedPoint == null) {
            for (PVector point : points) {
                if (mouse.dist(point) < 10) {
                    clickedPoint = point;
                    break;
                }
            }
        }
        if (mousePressed && clickedPoint != null) {
            clickedPoint.set(mouse);
        } else {
            clickedPoint = null;
        }
    }


    private PVector vertex(int index) {
        return points[index % points.length];
    }

    private void produceRandom() {
        for (int i = 0; i < points.length; i++) {
            points[i] = new PVector(random(width - 20), random(height - 20));
        }
    }
}
