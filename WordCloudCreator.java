import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class WordCloudCreator {   
    private Color[] colors = {Color.white, Color.cyan, Color.green, Color.yellow, Color.orange, Color.red};  //can be moved to enum
    private final static int sizeX = 600;
    private final static int sizeY = 300;
    private static int x;
    private static int y;
    private static boolean first = true;
    private static boolean firstFill = false;
    private static boolean secondFill = false;

    private Point imgSize = new Point(sizeX, sizeY);
    private Point point;
    private Font font;

    private static BufferedImage image= new BufferedImage(sizeX, sizeY, BufferedImage.TYPE_4BYTE_ABGR);
    private ImgPlacement placement = new ImgPlacement();
    private static Graphics graphics = image.getGraphics();
    
    public WordCloudCreator(Word[] words){
        for (int i = 0; i < words.length; i++){
            graphics.setColor(colors[(int)words[i].getWeight()]);
            graphics.setFont(words[i].getFont());
            words[i].setFontMetrics(graphics.getFontMetrics());

            //test collision
            boolean collision = false;
            int fillCheck = 0;

            do{
                if ((firstFill)&&(fillCheck >= 50)){secondFill = true; } //fillCheck = 0; }
                if (fillCheck >= 50){ firstFill = true; fillCheck = 0;}
                words[i].startingPoint = setLocation(words[i], words[0]);
                collision = false;

                for(int j = 0; j < words.length; j++){
                    //System.out.println("I: " + i + ", J: " + j);
                    if (j >= i) break;
                    //System.out.println(words[i].collisionCheck(words[j]));  //should say true

                    if (words[i].collisionCheck(words[j])){
                        fillCheck++;
                        collision = true;
                        //System.out.println("Reloop");
                        break;
                    }
                }
            } while (collision);

            graphics.drawString(words[i].getWord(), (int)words[i].startingPoint.getX(), (int)words[i].startingPoint.getY());//to be changed
        }
    }//constructor

    private Point setLocation(Word w, Word w2){
        if (first){ 
            point = placement.getStartLocation(sizeX, sizeY, w);
            first = false;
            return point;
        } else{
            point = placement.getLocation(sizeX, sizeY, w, w2, firstFill, secondFill);
            return point;
        }
    }//setLocation()

    public static void dispose()
    {
   		graphics.dispose();     
    }//dispose()
    public static void saveImg(String saveAs)
    {
        try {
            ImageIO.write(image, "png", new File(saveAs + ".png")); 
        } catch (IOException e) {
            //TODO: handle exception
            System.out.printf("Error saving file '%s'%n", saveAs + ".png");
        }
    }//saveImg()
}
