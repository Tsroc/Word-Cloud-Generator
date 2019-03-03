import java.awt.Point;

public class ImgPlacement{
    public Point getStartLocation(int x, int y, Word word) {
        x = (x / 2) - (word.getImgWidth() / 2);
        y = (y / 2) + (word.getImgHeight() / 2);
        //thinking about changing getImgHeight to be a minus value, will figure later.

        return new Point(x, y);
    }
}