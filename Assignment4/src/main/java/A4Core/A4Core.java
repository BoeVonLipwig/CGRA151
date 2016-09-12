package A4Core;

import processing.core.PApplet;
import processing.core.PVector;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;

/**
 * Started by Shaun on 9/09/2016.
 */
public class A4Core extends PApplet {
    public static void main(String[] args) {
        main(MethodHandles.lookup().lookupClass().getName());
    }

    private PVector[] points = new PVector[6];
    private PVector[] line;

    private ArrayList<PVector> pastPoint = new ArrayList<>();

    public void setup() {
        int farFromWall = 100;
        line = new PVector[]{new PVector(farFromWall+40, 20), new PVector(farFromWall, height - 20)};
        //setting up random points in the PVector array
        produceRandom(points);
        strokeWeight(4);
        rectMode(CENTER);
    }

    public void settings() {
        //setting the size
        int size = 500;
        size(size + 50, size);
    }

    public void draw() {
        clear();
        background(255);
        beginShape();
        strokeWeight(4);
        stroke(0, 0, 255);
        fill(0, 255, 0);
        for (int i = 0; i < points.length + 1; i++) {
            vertex(getIndex(i).x, getIndex(i).y);
        }
        endShape();
        noFill();
        stroke(0);
        line(line[0].x, line[0].y, line[1].x, line[1].y);
        changePoint();
        clip();
        stroke(255, 0, 0);
        strokeWeight(1);
        rect(line[0].x, line[0].y, 10, 10);
        rect(line[1].x, line[1].y, 10, 10);
        for (PVector point : points) {
            rect(point.x, point.y, 10, 10);
        }
    }

    //change to return PVector array
    private void clip() {
        stroke(0);
        strokeWeight(1);
        fill(122, 122, 122);
        if (!pastPoint.isEmpty()) {
            pastPoint.clear();
        }
        for (int i = 0; i < points.length; i++) {
            if (isInside(points[i]) && isInside(getIndex(i+1))) {
                pastPoint.add(points[i]);
            } else {
                //first is in second is out
                if (isInside(points[i])) {
                    pastPoint.add(points[i]);
                    pastPoint.add(getIntercept(points[i], getIndex(i+1)));
                }
                //second is in first is out
                if (isInside(getIndex(i+1))) {
                    pastPoint.add(getIntercept(getIndex(i+1),points[i]));

                }
            }

        }
        strokeWeight(1);
        beginShape();
        //i know i can do this with my get index thing but at this point i no longer care
        for (PVector aPastPoint1 : pastPoint) {
            vertex(aPastPoint1.x, aPastPoint1.y);
        }
        if (!pastPoint.isEmpty()) {
            vertex(pastPoint.get(0).x, pastPoint.get(0).y);
        }
        endShape();
        stroke(255,0,0);
        for (PVector aPastPoint : pastPoint) {
            rect(aPastPoint.x, aPastPoint.y, 10, 10);
        }
    }

    private boolean isInside(PVector point){
        //calc difference in y values (don't know why it needs to be made negative)
        float A = -(line[1].y - line[0].y);
        //calc difference in x values
        float B = line[1].x - line[0].x;
        float C = line[1].y * line[0].x - line[1].x * line[0].y;
        return (A*point.x+B*point.y+C)>1;
    }

    private PVector getIntercept(PVector f, PVector s) {
        //Ax+Bx+C=0
        //calc difference in y values (don't know why it needs to be made negative)
        float A = -(line[1].y - line[0].y);
        //calc difference in x values
        float B = line[1].x - line[0].x;
        float C = line[1].y * line[0].x - line[1].x * line[0].y;

        float rise=A * f.x + B * f.y + C;

        float run = A * (f.x - s.x) + B * (f.y - s.y);

        float m=rise/run;

        float x = (1 - m) * f.x + m * s.x;
        float y = (1 - m) * f.y + m * s.y;
        return new PVector(x, y);
    }

    private PVector getIndex(int index) {
        return points[index % points.length];
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
        if (mousePressed && clickedPoint == null) {
            for (PVector point : line) {
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


    private void produceRandom(PVector[] points) {
        for (int i = 0; i < points.length; i++) {
            points[i] = new PVector(random(width - 20), random(height - 20));
        }
    }


}
