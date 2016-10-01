import processing.core.PApplet;

import java.awt.event.KeyEvent;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;

/**
 * Created by Shaun Sinclair.
 * CGRA 151.
 * Start date: 27/09/2016.
 */

public class Main extends PApplet {
    public static void main(String[] args) {
        main(MethodHandles.lookup().lookupClass().getName());
    }

    public void setup() {
        size(400,400);
        surface.setResizable(true);
    }


    public void settings(){
        size(400,400);
    }

    ArrayList objects=new ArrayList();
    private Ivan ivan = new Ivan(height - 20, 20, false);

    public void draw() {
        rect(ivan.position.x, ivan.position.y, ivan.width, ivan.height);
        if (keyPressed) {
            switch (key){
                case 'w':
                    ivan.jump();
                    break;
                case 'd':
                    ivan.accl.x=ivan.moveSpeed;
                    break;
                case 'a':
                    ivan.accl.x=-ivan.moveSpeed;
                    break;
            }
            ivan.move();
        }else ivan.accl.x=0;
    }
}
