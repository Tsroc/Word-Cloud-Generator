/*
Author: Eoin Wilkie
Class information:
    This class generates the word cloud from the Word[] with methods from the ImgPlacement Class.
*/
//*NOTE* Still some work to do here to ensure the program will run under some additional situations which may break it
    //such as when too many words are added to the word cloud.

//ADD PACKAGE HERE

import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;

import javafx.scene.canvas.GraphicsContext;

import java.io.*;
import java.util.Random;

public class WordCloudCreator {   
    //private Color[] colors = {Color.white, Color.cyan, Color.green, Color.yellow, Color.orange, Color.red};  //can be moved to enum
    //private Color[] colors = {new Color(0x151B54), new Color(0x4863A0), new Color(0x488AC7), new Color(0x3BB9FF)};
    private Color[] colors = {new Color(0x50C9CE), new Color(0x72A1E5), new Color(0x9883E5), new Color(0xFCD3DE)};
    private final static int sizeX = 600;
    private final static int sizeY = 300;
    private static int x;
    private static int y;
    private boolean first; 
    private boolean firstFill;
    private boolean secondFill;
    private boolean endCheck;

    private Point imgSize = new Point(sizeX, sizeY);
    private Point point;
    private Font font;

    private static BufferedImage image;
    private ImgPlacement placement;
    private static Graphics graphics;
    
    public WordCloudCreator(Word[] words){

            //Big-O running time: O(n3)?
            //When combined with the function setLocation() within, creates a horrible loop inside a horrible loop
            //This loop creates the word cloud, it runs once per word and has an inner loop which runs checks for collision, it also has a do-while loop
            //this loop has the same problems as I have with ImgPlacement class, the img placement algorithm is not efficient
            //should be modified prior to submission if I can figure out how to place words in spiral
        this.first = true;
        this.firstFill = false;
        this.secondFill = false;
        this.endCheck = false;
        image= new BufferedImage(sizeX, sizeY, BufferedImage.TYPE_4BYTE_ABGR);
        placement = new ImgPlacement();
        graphics = image.getGraphics();
        Random rnd = new Random();
        //graphics.drawRect(-1, -1, sizeX+1, sizeY+1);
        graphics.setColor(new Color(0x2E382E));
        graphics.fillRect(-1, -1, sizeX+1, sizeY+1);

        for (int i = 0; i < words.length; i++){
            //graphics.setColor(colors[(int)words[i].getWeight()]);
            graphics.setColor(colors[rnd.nextInt(4)]);
            graphics.setFont(words[i].getFont());
            words[i].setFontMetrics(graphics.getFontMetrics());

            //test collision
            boolean collision = false;
            int fillCheck = 0;

            do{
                if ((this.firstFill)&&(fillCheck >= 50)){this.secondFill = true; } //fillCheck = 0; }
                if (fillCheck >= 50){ this.firstFill = true; fillCheck = 0;}
                words[i].startingPoint = setLocation(words[i], words[0]);
                collision = false;

                for(int j = 0; j < words.length; j++){
                    //System.out.println("I: " + i + ", J: " + j);
                    if (j >= i) break;
                    //System.out.println(words[i].collisionCheck(words[j]));  //should say true

                    if (words[i].collisionCheck(words[j])){
                        if(secondFill == false) fillCheck++;
                        collision = true;
                        //System.out.println("Reloop");
                        break;
                    }
                }
            } while (collision);

            graphics.drawString(words[i].getWord(), (int)words[i].startingPoint.getX(), (int)words[i].startingPoint.getY());//to be changed
            Word.setFactor(5);
        }
    }//constructor

    private Point setLocation(Word w, Word w2){
        if (first){ 
            this.point = placement.getStartLocation(sizeX, sizeY, w);
            this.first = false;
            return point;
        } else{
            this.point = placement.getLocation(sizeX, sizeY, w, w2, this.firstFill, this.secondFill);
            return this.point;
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
        graphics.dispose();
    }//saveImg()
}
