import com.sun.xml.internal.bind.v2.TODO;
import processing.core.PVector;

import java.awt.*;

/**
 * Created by Shaun Sinclair.
 * CGRA 151.
 * Start date: 27/09/2016.
 */
public class Obstacle {


    private PVector pos;
    private String type;
    Image display;


    Obstacle(float x, float y, String type) {
        pos.x = x;
        pos.y = y;
        this.type = type;
    }

//    TODO create images for traps
    public void pickImage() {
        switch (type) {
            case "Spike":
                //display=;
                break;
        }
    }

    public void draw(){

    }
}
