/**
 * Created by Shaun Sinclair.
 * CGRA 151.
 * Start date: 27/09/2016.
 */
class RectCol {
    private static double size = IvanTheRussian.size;

    static Blocks collisionIvan(Ivan ivan, Blocks rect) {
        double rectX = rect.getPos().x;
        double rectY = rect.getPos().y;
        double ivanX = ivan.getPosition().x;
        double ivanY = ivan.getPosition().y;
        if (!rect.getType().equals("Mine")&&!rect.getType().equals("Flag")) {
            if (!rect.isSolid()) return null;
        }
        if (ivanX + size > rectX && ivanX < rectX + size && ivanY + size * 2 > rectY && ivanY < rectY + size) {
            return rect;
        }
        return null;
    }


    static Blocks collisionBoolet(Boolet bullet, Blocks rect) {
        double rectX = rect.getPos().x;
        double rectY = rect.getPos().y;
        double BooletX = bullet.getPosition().x;
        double BooletY = bullet.getPosition().y;
        if (!rect.isSolid()) return null;
        if (BooletX + size * 2 > rectX && BooletX < rectX + size && BooletY + size > rectY && BooletY < rectY + size) {
            return rect;
        }
        return null;
    }

}
