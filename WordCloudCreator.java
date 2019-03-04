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
    Color[] colors = {Color.white, Color.cyan, Color.green, Color.yellow, Color.orange, Color.red};  //can be moved to enum
    //static int[] wordSizes = {12, 12, 27, 42, 67, 100};
    //static int[] wordSizes = {10, 10, 10, 10, 10, 10};
    final static int sizeX = 600;
    final static int sizeY = 300;
    Point imgSize = new Point(sizeX, sizeY);
    static int x;   //indicates start location
    static int y;
    static BufferedImage image= new BufferedImage(sizeX, sizeY, BufferedImage.TYPE_4BYTE_ABGR);
    private static java.util.Random rnd = new java.util.Random();
    static boolean first = true;
    static boolean firstFill = false;
    //static Point point;
    //must create a function to modify x and y
    //must also check for collision
    //must know the height of words

    Font font;
    static Graphics graphics = image.getGraphics();
    //graphics.setColor(Color.blue);
    
       
    //constructor
    WordCloudCreator(Word[] words)
    {
        //below inputs are for test purposes, all of the text is currently on top of each other
        //unsure how to ensure the text is correctly positioned
        //how to handle the change of size and color?
        //if I know number of numbers to be displayed then it could be % based, would require a size to be set which can be done statically
        //or passed when a word is created
        
        //graphics.setColor(colors[(int)word.weight]);
        //graphics.setFont(word.getFont());
        
        //fontMetrics fm = graphics.getFontMetrics();
        //word.fontWidth = graphics.getFontMetrics().stringWidth(word.word);
        //word.fontHeight = graphics.getFontMetrics().getMaxDescent();

        //word.setFontMetrics(graphics.getFontMetrics());

        //word.startingPoint = setLocation(word);

        //System.out.println("DEBUG: " + graphics.getFontMetrics().stringWidth(word.word));
        //graphics.drawString(word.word, x, y);//to be changed
        //point = new Point(x, y);
        
        //graphics.drawString(word.word, (int)word.startingPoint.getX(), (int)word.startingPoint.getY());//to be changed
    
        //recreated constructor, takes array now.
        for (int i = 0; i < words.length; i++){
            //System.out.println("I: " + i);
            graphics.setColor(colors[(int)words[i].weight]);
            graphics.setFont(words[i].getFont());
            //graphics.setFont(new Font("TimesRoman", Font.BOLD, 20));
            
            words[i].setFontMetrics(graphics.getFontMetrics());

            //words[i].startingPoint = setLocation(words[i]);

            //test collision
            boolean collision = false;
            int firstFillCheck = 0;

            do{
                if (firstFillCheck == 50){ firstFill = true; firstFillCheck = 0;}
                words[i].startingPoint = setLocation(words[i], words[0]);
                collision = false;

                for(int j = 0; j < words.length; j++){
                    //System.out.println("I: " + i + ", J: " + j);
                    if (j >= i) break;
                    //System.out.println(words[i].collisionCheck(words[j]));  //should say true

                    if (words[i].collisionCheck(words[j])){
                        firstFillCheck++;
                        collision = true;
                        //System.out.println("Reloop");
                        break;
                    }
                }
            } while (collision);
            firstFill = false;      //may make code very inefficien - but will make word cloud tighter
                                    //at some point this must ot be set back to true, 
            //System.out.println(words[i].word + ": bounds Test: " + ImgPlacement.inBoundsCheck(imgSize, words[i].startingPoint, words[i]));
            //System.out.println(words[i].word + " collision: " + collision);
            //Rotation not working, the following seems to rotate the text at random or in relation to the width of the text
            //continue placememnt without rotation
            /*/rotation
            Graphics2D g2d = (Graphics2D) graphics;
            g2d.rotate(180);       //.setToRotation(Math.toRadians(180));
            graphics = g2d;
            //*/
            graphics.drawString(words[i].word, (int)words[i].startingPoint.getX(), (int)words[i].startingPoint.getY());//to be changed
        }
    }

    private Point setLocation(Word w, Word w2){
        //should be called after createFont (within this function) - and use the previous words height/width to determine next location
        //following is simplistic, not correct
        Point point;
        if (first){ //set first word in middle
            point = new ImgPlacement().getStartLocation(sizeX, sizeY, w);
            //x = (int)point.getX();
            //y = (int)point.getY();
            //point = new Point(500, 150);
            first = false;
            return point;
        } else{
            /*
            int x = rnd.nextInt(sizeX - w.getImgWidth());
            int y = rnd.nextInt(sizeY - w.getImgHeight());
            y += w.getImgHeight();
            */
            //int x = rnd.nextInt(sizeX - w.getImgWidth());
            int x, tempX;
            int y, tempY;
            if (!firstFill){
                if (w.getImgWidth() <= w2.getImgWidth())
                    tempX = rnd.nextInt(w2.getImgWidth() - w.getImgWidth());
                else tempX = (int)w2.startingPoint.getX();

                tempY = rnd.nextInt(w2.getImgHeight()*2);
                x = tempX + (int)w2.startingPoint.getX();
                y = (tempY >= w2.getImgHeight())? (int)(w2.startingPoint.getY() + (tempY - w2.getImgHeight())): (int)(w2.startingPoint.getY() - w2.getImgHeight() - tempY/2);//((int)w2.startingPoint.getY() - w2.getImgHeight()) : y = 0;
                
           // } else if (!Seconfill){}
           
            } else {
                x = rnd.nextInt(sizeX - w.getImgWidth());
                y = rnd.nextInt(sizeY - w.getImgHeight());
                y += w.getImgHeight();
            }

            point = new Point(x, y);
            return point;
        }
    }
            /*
            for (int r = 0; r < sizeX; r += 2) {
                for (int x = -r; x <= r; x++) {
                    if (start.x + x < 0) { continue; }
                    if (start.x + x >= sizeX) { continue; }

                    boolean placed = false;
                    word.getPosition().x = start.x + x;

                    // try positive root
                    final int y1 = (int) Math.sqrt(r * r - x * x);
                    if (start.y + y1 >= 0 && start.y + y1 < dimension.height) {
                        word.getPosition().y = start.y + y1;
                        placed = canPlace(word);
                    }
                    // try negative root
                    final int y2 = -y1;
                    if (!placed && start.y + y2 >= 0 && start.y + y2 < dimension.height) {
                        word.getPosition().y = start.y + y2;
                        placed = canPlace(word);
                    }
                    if (placed) {
                        collisionRaster.mask(word.getCollisionRaster(), word.getPosition());
                        graphics.drawImage(word.getBufferedImage(), word.getPosition().x, word.getPosition().y, null);
                        return true;
                    }

                }
            }
        }
    }*/
    

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
