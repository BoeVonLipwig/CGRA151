package A4Comp;

import processing.core.PApplet;
import processing.core.PVector;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Started by Shaun on 12/09/2016.
 */
public class A4Comp extends PApplet {
    public static void main(String[] args) {
        main(MethodHandles.lookup().lookupClass().getName());
    }

    private ArrayList<PVector> points = new ArrayList<>();
    private ArrayList<PVector> clippingPoly = new ArrayList<>();

    public void setup() {
        int farFromWall = 100;
        clippingPoly.addAll(Arrays.asList(new PVector(farFromWall, 20), new PVector(farFromWall, height - 20), new PVector(width - farFromWall, height - 20), new PVector(width - farFromWall, 20)));
        //setting up random points in the PVector array
        produceRandom(6, points);
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
        stroke(0);
        fill(122);
        for (int i = 0; i < points.size(); i++) {
            vertex(getIndex(points, i).x, getIndex(points, i).y);
        }
        endShape(CLOSE);
        stroke(0, 0, 255);
        fill(0, 255, 0);
        changePoint();
        beginShape();
        for(PVector o : clip()){
            vertex(o.x, o.y);
        }
        endShape(CLOSE);
        stroke(255, 0, 0);
        strokeWeight(1);
        for (PVector point : points) {
            rect(point.x, point.y, 10, 10);
        }
        beginShape();
        strokeWeight(1);
        stroke(0);
        noFill();
        for (int i = 0; i < clippingPoly.size(); i++) {
            vertex(getIndex(clippingPoly, i).x, getIndex(clippingPoly, i).y);
            rect(clippingPoly.get(i).x, clippingPoly.get(i).y, 10, 10);
        }
        strokeWeight(4);
        endShape(CLOSE);
    }

    private ArrayList<PVector> clip() {
        ArrayList<PVector> outputList = new ArrayList<>(points);
        for (int i = 0; i < clippingPoly.size(); i++) {
            ArrayList<PVector> inputList = new ArrayList<>(outputList);
            outputList.clear();
            if (inputList.isEmpty()) return new ArrayList<>();
            PVector S = getIndex(inputList,inputList.size() - 1);
            for (PVector E : inputList) {
                if (isInside(E,i,i+1)) {
                    if (!isInside(S,i,i+1)) {
                        outputList.add(getIntercept(S, E, i, i+1));
                    }
                    outputList.add(E);
                } else if (isInside(S,i, i+1)) {
                    outputList.add(getIntercept(S, E, i, i+1));
                }
                S = E;
            }
        }
        return outputList;
    }

    private boolean isInside(PVector point, int h , int j) {
        //calc difference in y values (don't know why it needs to be made negative)
        float A = -(getIndex(clippingPoly,h).y - getIndex(clippingPoly,j).y);
        //calc difference in x values
        float B = getIndex(clippingPoly,h).x - getIndex(clippingPoly,j).x;
        float C = getIndex(clippingPoly,h).y * getIndex(clippingPoly,j).x - getIndex(clippingPoly,h).x * getIndex(clippingPoly,j).y;
        return (A * point.x + B * point.y + C) > 1;
    }

    private PVector getIntercept(PVector f, PVector s, int h , int j) {
        //Ax+Bx+C=0
        //calc difference in y values (don't know why it needs to be made negative)
        float A = -(getIndex(clippingPoly,h).y - getIndex(clippingPoly,j).y);
        //calc difference in x values
        float B = getIndex(clippingPoly,h).x - getIndex(clippingPoly,j).x;
        float C = getIndex(clippingPoly,h).y * getIndex(clippingPoly,j).x - getIndex(clippingPoly,h).x * getIndex(clippingPoly,j).y;

        float rise = A * f.x + B * f.y + C;

        float run = A * (f.x - s.x) + B * (f.y - s.y);

        float m = rise / run;

        float x = (1 - m) * f.x + m * s.x;
        float y = (1 - m) * f.y + m * s.y;
        return new PVector(x, y);
    }

    private PVector getIndex(ArrayList<PVector> points, int index) {
        if (index < 0) return points.get(points.size()+index);
        return points.get(index % points.size());
    }

    private PVector clickedPoint = null;

    private void changePoint() {
        double dist = Double.MAX_VALUE;
        Point2D mousePt = new Point2D.Float(mouseX, mouseY);
        PVector mouse = new PVector(mouseX, mouseY);
        PVector currentPt = points.get(0);
        for (int i = 0; i < points.size(); i++) {
            Line2D line = new Line2D.Float(getIndex(points, i).x, getIndex(points, i).y, getIndex(points, i + 1).x, getIndex(points, i + 1).y);
            if (line.ptLineDist(mousePt) < dist) {
                currentPt = getIndex(points, i);
                dist = line.ptLineDist(mousePt);
            }
        }
        if (mousePressed && mouseButton == RIGHT && frameCount % 10 == 0) {
            if (keyPressed && keyCode == SHIFT && points.size() - 1 >= 3) points.remove(points.indexOf(currentPt) + 1);
            else points.add(points.indexOf(currentPt) + 1, mouse);
        }
        if (mousePressed && clickedPoint == null) {
            for (PVector point : points) {
                if (mouse.dist(point) < 10) {
                    clickedPoint = point;
                    break;
                }
            }
        }
        if (mousePressed && clickedPoint == null) {
            for (PVector point : clippingPoly) {
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


    private void produceRandom(int num, ArrayList<PVector> points) {
        for (int i = 0; i < num; i++) {
            points.add(new PVector(random(width - 20), random(height - 20)));
        }
    }


}
