/**
 * Created by Shaun Sinclair.
 * CGRA 151.
 * Start date: 27/09/2016.
 */
public class RectCol {
    private double size = IvanTheRussian.size;

    public int collisionIvan(Ivan ivan, double rectX, double rectY) {
        double x = ivan.getPosition().x;
        double y = ivan.getPosition().y;
        int left = 1, right = 2, top = 3, bottom = 4, noCollide = 0;
        //0=no collision 1=left 2=right 3=top 4=bottom
        if (ivan.getAcl().y > 0 && y >= rectY && x > rectX && x < rectX + size) {
            IvanTheRussian.setJumpAllowed(true);
            return bottom;
        }
        if (ivan.getAcl().x > 0 && x >= rectX && y > rectY && y < rectY + size) {
            return left;
        }


        if (ivan.getAcl().x < 0 && x <= rectX + size && y > rectY && y < rectY + size) {
            return right;
        }


        if (ivan.getAcl().y < 0 && y <= rectY - size && x > rectX && x < rectX + size) {
            return top;
        }


        return noCollide;
    }


    public int collisionBullet(Boolet bullet, double rectX, double rectY) {
        double x = bullet.getPosition().x;
        double y = bullet.getPosition().y;
        int left = 1, right = 2, noCollide = 0;
        //0=no collision 1=left 2=right 3=bottom not used here
        if (bullet.getSpeed() > 0 && x >= rectX && y > rectY && y < rectY + size) {
            return left;
        } else if (bullet.getSpeed() < 0 && x <= rectX + size && y > rectY && y < rectY + size) {
            return right;
        }
        return noCollide;
    }
}
