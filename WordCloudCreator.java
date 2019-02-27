import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

/*
plindenbaum.blogspot.com/2010/10/playing-with-wordle-algroithm-gat.html
    sort words by weight, decreasing
    for each word w:
        w.postion:= makeInitialPosition(w);
        while w intersects other words:
            updatePosition(w);

code invokes a java.awt.font.TextLayout class to get the shape of the text:
    and a java.awt.geom.Area to test is two shapes intersects.

http://github.com/lidenb/jsandbox/blob/master/src/sandbox/MyWordle.java
static.mrfeinberg.com/bv_ch03.pdf
*/
/*
I'm the creator of Wordle. Here's how Wordle actually works:

Count the words, throw away boring words, and sort by the count, descending.
Keep the top N words for some N. Assign each word a font size proportional to its count.
Generate a Java2D Shape for each word, using the Java2D API.

Each word "wants" to be somewhere, such as "at some random x position in the vertical center".
I decreasing order of frequency, do this for each word:

place the word where it wants to be
while it intersects any of the previously placed words
    move it one step along an ever-increasing spiral

That's it. The hard part is in doing the intersection-testing efficiently,
for which I use last-hit caching, hierarchical bounding boxes,
and a quadtree spatial index (all of which are things you can learn more about with some diligent googling).

Edit: As Reto Aebersold pointed out, there's now a book chapter, freely available,
that covers this same territory: Beautiful Visualization, Chapter 3: Wordle


*/
public class WordCloudCreator {   
    static int[] wordSizes = {12, 12, 27, 42, 67, 100};
    final static int sizeX = 600;
    final static int sizeY = 300;
    static int x = (sizeX - wordSizes[wordSizes.length-1]) / 2;   //indicates start location
    static int y = sizeY / 2;
    static BufferedImage image= new BufferedImage(sizeX, sizeY, BufferedImage.TYPE_4BYTE_ABGR);
    private static java.util.Random rnd = new java.util.Random();
    //must create a function to modify x and y
    //must also check for collision
    //must know the height of words

    Font font;
    static Graphics graphics = image.getGraphics();
    
       
    //constructor
    WordCloudCreator(String word, int wordValue)
    {
        //below inputs are for test purposes, all of the text is currently on top of each other
        //unsure how to ensure the text is correctly positioned
        //how to handle the change of size and color?
        //if I know number of numbers to be displayed then it could be % based, would require a size to be set which can be done statically
        //or passed when a word is created
        font = createFont(word, wordValue); 
		graphics.setFont(font);
        graphics.drawString(word, x, y);                  //to be changed
        //System.out.println(word + " x: " + x + " y: " + y);
        //create new function: drawFont: Calls drawString() and implements algorithm to position(ensure no overlapping)
        //y += 20;
    }

    private static void getWordLocation(int wordSize)
    {
        //should be called after createFont (within this function) - and use the previous words height/width to determine next location
        //following is simplistic, not correct
    
        x = rnd.nextInt(sizeX - (100 - wordSize));
        y = rnd.nextInt(sizeY - 50);
        y += 50;

    }
    
    private Font createFont(String word, int wordValue)
    {
        Font font = null;
        //System.out.println(wordValue);

        switch (wordValue){
            case 5:
                font = new Font("TimesRoman", Font.BOLD, wordSizes[wordValue]);
                graphics.setColor(Color.red);
                break;
            case 4:
                font = new Font("TimesRoman", Font.BOLD, wordSizes[wordValue]);
                graphics.setColor(Color.orange);
                break;
            case 3:
                font = new Font("TimesRoman", Font.BOLD, wordSizes[wordValue]);
                graphics.setColor(Color.blue);
                break;
            case 2:
                font = new Font("TimesRoman", Font.BOLD, wordSizes[wordValue]);
                graphics.setColor(Color.yellow);
                break;
            case 1:
                font = new Font("TimesRoman", Font.BOLD, wordSizes[wordValue]);
                graphics.setColor(Color.cyan);
                break;
            default:
                //do not reach 
                font = new Font("TimesRoman", Font.BOLD, wordSizes[0]);
                graphics.setColor(Color.green);
                break;
        }
        getWordLocation(wordSizes[wordValue]);
        return font;
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
