/**
 * Created by Shaun Sinclair.
 * CGRA 151.
 * Start date: 27/09/2016.
 */
class RectCol {
    private static double size = IvanTheRussian.size;
    //constructor
    static Blocks collisionIvan(Ivan ivan, Blocks rect) {
        double rectX = rect.getPos().x;
        double rectY = rect.getPos().y;
        double ivanX = ivan.getPosition().x;
        double ivanY = ivan.getPosition().y;
        //only check colltions if block is solid(can be collided with) and is not a non solid non standered block
        if (!rect.getType().equals("Mine")&&!rect.getType().equals("Flag")) {
            if (!rect.isSolid()) return null;
        }
        //returns object collided with if on has been collided wth
        if (ivanX + size > rectX && ivanX < rectX + size && ivanY + size * 2 > rectY && ivanY < rectY + size) {
            return rect;
        }
        return null;
    }

    //constructor 
    static Blocks collisionBoolet(Boolet bullet, Blocks rect) {
      //rect and object posstions
        double rectX = rect.getPos().x;
        double rectY = rect.getPos().y;
        double BooletX = bullet.getPosition().x;
        double BooletY = bullet.getPosition().y;
        //only check colltions if block is solid(can be collided with)
        if (!rect.isSolid()) return null;
        //returns object collided with if on has been collided wth
        if (BooletX + size * 2 > rectX && BooletX < rectX + size && BooletY + size > rectY && BooletY < rectY + size) {
            return rect;
        }
        return null;
    }

}