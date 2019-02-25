import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class WordCloudCreator {   
    static BufferedImage image= new BufferedImage(600, 300, BufferedImage.TYPE_4BYTE_ABGR);
    //Font font = new Font
    //G

    Font font;
    static Graphics graphics = image.getGraphics();
       
    //constructor
    WordCloudCreator(String word)
    {
        //below inputs are for test purposes, all of the text is currently on top of each other
        //unsure how to ensure the text is correctly positioned
        //how to handle the change of size and color?
        //if I know number of numbers to be displayed then it could be % based, would require a size to be set which can be done statically
        //or passed when a word is created
        font = new Font(Font.SANS_SERIF, Font.BOLD, 62);    //to be changed
        graphics.setColor(Color.red);                       //to be changed
		graphics.setFont(font);
        graphics.drawString(word, 0, 100);                  //to be changed
        
    }

    public static void dispose()
    {
   		graphics.dispose();     
    }

    public static void saveImg(String saveAs)
    {
        try {
            ImageIO.write(image, "png", new File(saveAs + ".png")); 
        } catch (IOException e) {
            //TODO: handle exception
            System.out.printf("Error saving file '%s'%n", saveAs + ".png");
        }
    }


/*
	public static void main(String args[]) throws Exception {


		font = new Font(Font.SANS_SERIF, Font.ITALIC, 42);
		graphics.setFont(font);
		graphics.setColor(Color.yellow);
		graphics.drawString("Software Development", 10, 150);
		font = new Font(Font.MONOSPACED, Font.PLAIN, 22);
		graphics.setFont(font);
		graphics.setColor(Color.blue);
		graphics.drawString("2012 Assignment", 40, 180);


    }
    */
}
