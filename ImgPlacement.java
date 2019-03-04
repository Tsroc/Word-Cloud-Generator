import java.awt.Point;

public class ImgPlacement{
    public Point getStartLocation(int x, int y, Word word) {
        x = (x / 2) - (word.getImgWidth() / 2);
        y = (y / 2) + (word.getImgHeight() / 2);
        //thinking about changing getImgHeight to be a minus value, will figure later.

        return new Point(x, y);
    }

    //seems to be working correctly
    public static boolean inBoundsCheck(Point size, Point startPoint, Word w){
        if((startPoint.getX() < 0) || (startPoint.getX() + w.getImgWidth()) > size.getX()){
            return false;
        }
        if((startPoint.getY() + w.getImgHeight() < 0) || (startPoint.getY()  > size.getY())){
            return false;
        }

        return true;
    }
}