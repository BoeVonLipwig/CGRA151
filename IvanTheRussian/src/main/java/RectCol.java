/**
 * Created by Shaun Sinclair.
 * CGRA 151.
 * Start date: 27/09/2016.
 */
public class RectCol {
    private static double size = IvanTheRussian.size;

    static Blocks collisionIvan(Ivan ivan, Blocks rect) {
        double rectX = rect.getPos().x;
        double rectY = rect.getPos().y;
        double ivanX = ivan.getPosition().x;
        double ivanY = ivan.getPosition().y;
        if (ivanX + size > rectX && ivanX < rectX + size && ivanY + size *2 > rectY && ivanY < rectY + size) {
            IvanTheRussian.setJumpAllowed(true);
            return rect;
        }
        return null;
    }


    public static Blocks collisionBullet(Boolet bullet, Blocks rect) {
        double rectX = rect.getPos().x;
        double rectY = rect.getPos().y;
        double BooletX = bullet.getPosition().x;
        double BooletY = bullet.getPosition().y;
        if (BooletX + size * 2 > rectX && BooletX < rectX + size && BooletY + size > rectY && BooletY < rectY + size) {
            return rect;
        }
        return null;
    }

//    double x = ivan.getPosition().x;
//    double y = ivan.getPosition().y;
//    double rectY=rect.getPos().y,rectX=rect.getPos().x;
//    int left = 1, right = 2, top = 3, bottom = 4, noCollide = 0;
//    //0=no collision 1=left 2=right 3=top 4=bottom
//        if (ivan.getAcl().y > 0 && y >= rectY && x > rectX && x < rectX + size) {
//        IvanTheRussian.setJumpAllowed(true);
//        return rect;
//    }
//        if (ivan.getAcl().x > 0 && x >= rectX && y > rectY && y < rectY + size) {
//        return rect;
//    }
//
//
//        if (ivan.getAcl().x < 0 && x <= rectX + size && y > rectY && y < rectY + size) {
//        return rect;
//    }
//
//
//        if (ivan.getAcl().y < 0 && y <= rectY - size && x > rectX && x < rectX + size) {
//        return rect;
//    }
//        return null;
}
