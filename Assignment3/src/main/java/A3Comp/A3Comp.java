package A3Comp;

import processing.core.PApplet;
import processing.core.PVector;

import java.lang.invoke.MethodHandles;

/**
 * Started by Shaun on 15/08/2016.
 */
public class A3Comp extends PApplet {

    public static void main(String[] args) {
        main(MethodHandles.lookup().lookupClass().getName());
    }

    private PVector[] points = new PVector[12];
    private int curPoint = 0;
    private float incrament=0;

    public void setup() {
        //setting up random points in the PVector array
        produceRandom();
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
        //no fill so i dont get white patches between points and such
        noFill();
        background(255);
        beginShape();
        strokeWeight(4);
        stroke(0, 0, 255);
        for (int i = 0; i < points.length + 3; i++) {
            curveVertex(getVertex(i).x, getVertex(i).y);
        }
        endShape();
        stroke(255, 0, 0);
        strokeWeight(0.5f);
        for (int i = 0; i < points.length; i++) {
            rect(points[i].x, points[i].y, 10, 10);
            line(getVertex(i).x, getVertex(i).y, getVertex(i + 1).x, getVertex(i + 1).y);
        }
        changePoint();
        fill(0,255,0);
        drawArrow();
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


    private PVector getVertex(int index) {
        return points[index % points.length];
    }

    private void drawArrow(){
        float x=curvePoint(getVertex(curPoint).x,getVertex(curPoint+1).x ,getVertex(curPoint+2).x,getVertex(curPoint+3).x,incrament);
        float y=curvePoint(getVertex(curPoint).y,getVertex(curPoint+1).y ,getVertex(curPoint+2).y,getVertex(curPoint+3).y,incrament);
        float xTangent=curveTangent(getVertex(curPoint).x,getVertex(curPoint+1).x ,getVertex(curPoint+2).x,getVertex(curPoint+3).x,incrament);
        float yTangent=curveTangent(getVertex(curPoint).y,getVertex(curPoint+1).y ,getVertex(curPoint+2).y,getVertex(curPoint+3).y,incrament);
        translate(x,y);
        rotate(atan2(yTangent,xTangent));
        scale(0.6f);
        beginShape();
        vertex(0,0);
        vertex(-10,-10);
        vertex(30,0);
        vertex(-10,10);
        endShape(CLOSE);
        incrament+=0.05;
        if (incrament >= 1) {
            curPoint++;
            incrament = 0;
            if (curPoint >=points.length) curPoint = 0;
        }
    }

    private void produceRandom() {
        for (int i = 0; i < points.length; i++) {
            points[i] = new PVector(random(20,width - 20), random(20,height - 20));
        }

    }

}
