/**
 * Created by Shaun Sinclair.
 * CGRA 151.
 * Start date: 27/09/2016.
 */
public class RectCol {
    private double size=IvanTheRussian.size;

    public int collisionIvan(Ivan ivan, double rectX, double rectY){
        double x=ivan.getPosition().x;
        double y=ivan.getPosition().y;
        int left = 1,right =2, noCollide=5;
        //0=top not used here 1=left 2=right 3=bottom not used here
        if(ivan.getAcl().x>0&&x>=rectX&&y>rectY&&y<rectY+size){
            return left;
        }else if(ivan.getAcl().x<0&&x<=rectX+size&&y>rectY&&y<rectY+size){
            return right;
        }
        return noCollide;
    }


    public int collisionBullet(Boolet bullet, double rectX, double rectY){
        double x=bullet.getPosition().x;
        double y=bullet.getPosition().y;
        int left = 1,right =2, noCollide=5;
        //0=top not used here 1=left 2=right 3=bottom not used here
        if(bullet.getSpeed()>0&&x>=rectX&&y>rectY&&y<rectY+size){
            return left;
        }else if(bullet.getSpeed()<0&&x<=rectX+size&&y>rectY&&y<rectY+size){
            return right;
        }
        return noCollide;
    }
}
