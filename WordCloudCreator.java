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
import java.io.*;
import java.util.Random;
import java.util.Queue;

public class WordCloudCreator {   
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
    
    public WordCloudCreator(Queue<Word> words){

            //Big-O running time: O(n3)?
            //When combined with the function setLocation() within, creates a horrible loop inside a horrible loop
            //This loop creates the word cloud, it runs once per word and has an inner loop which runs checks for collision, it also has a do-while loop
            //this loop has the same problems as I have with ImgPlacement class, the img placement algorithm is not efficient
            //could ignore collision checks and place words randomly for Big-O: O(n)
        int arraySize = words.size();
        this.first = true;
        this.firstFill = false;
        this.secondFill = false;
        this.endCheck = false;
        image= new BufferedImage(sizeX, sizeY, BufferedImage.TYPE_4BYTE_ABGR);
        placement = new ImgPlacement();
        graphics = image.getGraphics();
        Random rnd = new Random();
        graphics.setColor(new Color(0x2E382E));
        graphics.fillRect(-1, -1, sizeX+1, sizeY+1);
        Word currentWord;
        //copy words to array for collision check
        Word[] wordsArray = new Word[arraySize];

        for (int i = 0; i < arraySize; i++){
            //add to array as polled from queue
            wordsArray[i] = words.poll();

            wordsArray[i].calculateValue();
            wordsArray[i].createFont();
            graphics.setColor(colors[rnd.nextInt(4)]);
            graphics.setFont(wordsArray[i].getFont());
            wordsArray[i].setFontMetrics(graphics.getFontMetrics());

            //test collision
            boolean collision = false;
            int fillCheck = 0;
            boolean exit = false;
            int exitCheck = 0;

            //Big-O of following has been described within the used methods
            do{
                if(exit == true)
                    break;
                else if(exitCheck > 1000)
                    exit = true; 
                else if(this.secondFill == true)
                    exitCheck++;
                else if ((this.firstFill)&&(fillCheck >= 50))
                    this.secondFill = true; 
                else if (fillCheck >= 50){
                    this.firstFill = true;
                    fillCheck = 0;
                }
                wordsArray[i].startingPoint = setLocation(wordsArray[i], wordsArray[0]);
                collision = false;

                for(int j = 0; j < arraySize; j++){
                    if (j >= i) break;

                    if (wordsArray[i].collisionCheck(wordsArray[j])){
                        if(secondFill == false) fillCheck++;
                        collision = true;
                        break;
                    }
                }
            } while (collision);

            if(exit == true) break; //ensures program will run if words.length is too large
            graphics.drawString(wordsArray[i].getWord(), (int)wordsArray[i].startingPoint.getX(), (int)wordsArray[i].startingPoint.getY());//to be changed
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

    public static void dispose(){
   		graphics.dispose();     
    }//dispose()

    public static void saveImg(String saveAs){
        try {
            ImageIO.write(image, "png", new File(saveAs + ".png")); 
        } catch (IOException e) {
            System.out.printf("Error saving file '%s'%n", saveAs + ".png");
        }
        graphics.dispose();
    }//saveImg()
}
