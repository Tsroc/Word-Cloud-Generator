import java.awt.Point;

/*
    Author: Eoin Wilkie
    Class information:
        Parser Interface
        This class is used to determine the placement of the word images on the canvas.
*/
public class ImgPlacement{
    /*
        Used to determine the mid point of the canvas, used for placement of the first word.
    */
    public Point getStartLocation(int sizeX, int sizeY, Word word) {
        //Big-O running time: O(1)
        //Checks 4 variables, and determines the mid point.

        int x = (sizeX / 2) - (word.getImgWidth() / 2);
        int y = (sizeY / 2) + (word.getImgHeight() / 2);
        return new Point(x, y);
    }//getStartLocation()

    /*
        Used to ensure wordplacement does not extend past the visible canvas.
    */
    public static boolean inBoundsCheck(Point size, Point startPoint, Word w){
        //Big-O running time: O(1)
        //Checks some variables from word class, best case 2 checks, worst case 4 checks

        if((startPoint.getX() < 0) || (startPoint.getX() + w.getImgWidth()) > size.getX())
            return false;
        if((startPoint.getY() + w.getImgHeight() < 0) || (startPoint.getY()  > size.getY()))
            return false;
        return true;
    }//inBoundsCheck()

    /*
        Used to place all word images after first.
        Word are placed within areas sorrounding the first word.
        Initially words are placed within an area around he first word, when this area fills the words are placed in an extended area.
        After this second area is filled words are placed anywhere on the remaining canvas.
    */
    public Point getLocation(int sizeX, int sizeY, Word w, Word w2, boolean firstFill, boolean secondFill){
        //Big-O running time: O(n3)? Unsure how to lable this.
        //Horrible code, currently it is placing images at random and not filterning previously selected random.
        //There are some conditions set to ensure it is forced to close
        //better implementation outside of the scope of this project

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
            y = (tempY >= w2.getImgHeight())? (int)(w2.startingPoint.getY() + (tempY - w2.getImgHeight())): (int)(w2.startingPoint.getY() - w2.getImgHeight() - tempY/2);

        } else if (!secondFill){
            tempX = rnd.nextInt(w2.getImgWidth() + (w2.getImgHeight() * 3) - w.getImgWidth());
            x = sizeX/2 - w2.getImgWidth()/2 - (w2.getImgHeight() *3)/2 + tempX;
            if (tempX > (int)(w2.getImgHeight() * 1.5)|| (tempX < (int)(w2.getImgHeight() * 1.5) + w2.getImgWidth())){
                tempY = rnd.nextInt(w2.getImgHeight() * 4);
                y = sizeY/2 +w2.getImgHeight()*2 - tempY;
            } else {
                tempY = rnd.nextInt(w2.getImgHeight()*3); 
                y = (tempY >= (w2.getImgWidth() + w2.getImgHeight() * 3) / 2)? (int)(w2.startingPoint.getX() + w2.getImgWidth() + tempY/2): (int)(w2.startingPoint.getY() - (w2.getImgHeight() * 1.5 - tempY/2));
            }
        } else {
            x = rnd.nextInt(sizeX - w.getImgWidth());
            y = rnd.nextInt(sizeY - w.getImgHeight());
            y += w.getImgHeight();
        }
        point = new Point(x, y);
        return point;
    }//getLocation()
}