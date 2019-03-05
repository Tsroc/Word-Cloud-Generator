import java.awt.Point;

public class ImgPlacement{
    public Point getStartLocation(int sizeX, int sizeY, Word word) {
        int x = (sizeX / 2) - (word.getImgWidth() / 2);
        int y = (sizeY / 2) + (word.getImgHeight() / 2);

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

    public Point getLocation(int sizeX, int sizeY, Word w, Word w2, boolean firstFill, boolean secondFill){
        int x, tempX;
        int y, tempY;
        Point point;
        java.util.Random rnd = new java.util.Random();

        if (!firstFill){
            if (w.getImgWidth() <= w2.getImgWidth())
                tempX = rnd.nextInt(w2.getImgWidth() - w.getImgWidth());
            else
                tempX = (int)w2.startingPoint.getX();
            tempY = rnd.nextInt(w2.getImgHeight()*2);
            x = tempX + (int)w2.startingPoint.getX();
            y = (tempY >= w2.getImgHeight())? (int)(w2.startingPoint.getY() + (tempY - w2.getImgHeight())): (int)(w2.startingPoint.getY() - w2.getImgHeight() - tempY/2);//((int)w2.startingPoint.getY() - w2.getImgHeight()) : y = 0;

        } else if (!secondFill){
            tempX = rnd.nextInt(w2.getImgWidth() + (w2.getImgHeight() * 3) - w.getImgWidth());
            x = sizeX/2 - w2.getImgWidth()/2 - (w2.getImgHeight() *3)/2 + tempX;
            if (tempX > (int)(w2.getImgHeight() * 1.5)|| (tempX < (int)(w2.getImgHeight() * 1.5) + w2.getImgWidth())){
                //y can be anything
                tempY = rnd.nextInt(w2.getImgHeight() * 4);
                y = sizeY/2 +w2.getImgHeight()*2 - tempY;
            } else {
                tempY = rnd.nextInt(w2.getImgHeight()*3); 
                y = (tempY >= (w2.getImgWidth() + w2.getImgHeight() * 3) / 2)? (int)(w2.startingPoint.getX() + w2.getImgWidth() + tempY/2): (int)(w2.startingPoint.getY() - (w2.getImgHeight() * 1.5 - tempY/2));//((int)w2.startingPoint.getY() - w2.getImgHeight()) : y = 0;
            }
        } else {
            x = rnd.nextInt(sizeX - w.getImgWidth());
            y = rnd.nextInt(sizeY - w.getImgHeight());
            y += w.getImgHeight();
        }
        point = new Point(x, y);
        return point;
    }
}