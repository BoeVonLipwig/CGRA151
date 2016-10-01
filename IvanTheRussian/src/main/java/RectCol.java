import java.awt.*;

/**
 * Created by Shaun Sinclair.
 * CGRA 151.
 * Start date: 27/09/2016.
 */
public class RectCol {
    double size=IvanTheRussian.size;
    public int collision(Ivan ivan, double rect2x, double rect2y){

        return 0;
    }


    public int collision(Boolet bullet, double rectX, double rectY){
        double x=bullet.getPosition().x;
        double y=bullet.getPosition().y;
        int top =0, left = 1,right =2,bottom =3, noCollide=5;
        //0=top 1=left 2=right 3=bottom
        if(bullet.getSpeed()>0&&x>=rectX&&y>rectY&&y<rectY+size){
            return left;
        }else if(bullet.getSpeed()<0&&x<=rectX+size&&y>rectY&&y<rectY+size){
            return right;
        }
        return noCollide;
    }
}
