import java.awt.*;

//font size is the character size, overall fontsize must be hight = size, width = word.length * size
public class Word{
    String word;
    float weight;
    static float highWordCount = 0;  //set by constructor - used to assign weight
    static int factor = 5;
    Font font;
    int fontWidth;
    int fontHeight;
    Point startingPoint;
    //must store image here and assign variables to calculate img demsions
    
    //Point point;
    static int[] wordSizes = {12, 12, 27, 42, 67, 100};     //can be moved to enum
    //static int[] wordSizes = {10, 10, 10, 10, 10, 10};

    //move functions from wordloudGenerator
    //add collision detection and placement
    public Word(String word, int wordCount){
        this.word = word.toUpperCase();
        this.weight = 0;

        highWordCount = wordCount > highWordCount? wordCount: highWordCount;
        calculateValue(wordCount);
        createFont();
    }

    public void setFontMetrics(FontMetrics fm)
    {
        this.fontWidth = fm.stringWidth(this.word);
        System.out.println("d: " + fm.getMaxDescent() + " a: " + fm.getMaxAscent() + " h: " + fm.getHeight() + " l: " + fm.getLeading());
        this.fontHeight = fm.getMaxDescent(); //fm.getHeight() - fm.getDescent(); //fm.getMaxAscent(); // + fm.getAscent();// + fm.getMaxAscent();
    }

    public int getImgWidth(){
        //how to calc length of img pixals?
        //make word sizes a % of the imgSize?
        //Font myfont = jTextField1.getFont();      
        //FontMetrics myMetrics = getFontMetrics(this.word);
        //return SwingUtilities.computeStringWidth(myMetrics, jTextField1.getText()); 

        /*
        If you just want to use AWT, then use Graphics.getFontMetrics (optionally specifying the font, for a non-default one) to get a FontMetrics and then FontMetrics.stringWidth to find the width for the specified string.
        For example, if you have a Graphics variable called g, you'd use:
        int width = g.getFontMetrics().stringWidth(text);
        */
        //System.out.println("Width: " + this.fontWidth);
        return this.fontWidth;// * this.word.length();

    }
    public int getImgHeight(){
        //System.out.println("Height: " + this.fontHeight);
        return this.fontHeight * 3;
    }

    public String getWord(){
        return this.word;
    }

    //for now, called in constructor
    public void calculateValue(int wordCount){
        //value is based on other words values. Value numbers are 
            //word size should be in relation to available space on img
            this.weight = (wordCount / highWordCount) * (factor / 1);
            if (this.weight < factor){// - 0.5) { //seems to be a decent place to split it, can be changed.  Looks reasonable for 20, 100, 1000
                this.weight = --factor;
            }
    }
    public Font getFont(){
        return this.font;
    }
    
    private void createFont()
    {
        //System.out.println(wordValue);
        int weight = (int)this.weight;

        switch (weight){
            case 5:
                font = new Font("TimesRoman", Font.BOLD, wordSizes[weight]);
                break;
            case 4:
                font = new Font("TimesRoman", Font.BOLD, wordSizes[weight]);
                break;
            case 3:
                font = new Font("TimesRoman", Font.BOLD, wordSizes[weight]);
                break;
            case 2:
                font = new Font("TimesRoman", Font.BOLD, wordSizes[weight]);
                break;
            case 1:
                font = new Font("TimesRoman", Font.BOLD, wordSizes[weight]);
                break;
            default:
                //do not reach 
                font = new Font("TimesRoman", Font.BOLD, wordSizes[0]);
                break;
        }
    }

    
    public boolean collisionCheck(Word w2){

        final Point position = this.startingPoint;
        final Point position2 = w2.startingPoint;
        
        //System.out.println("w1: " + this.startingPoint.x + "," + this.startingPoint.y + "/w2: "+ w2.startingPoint.x + "," + w2.startingPoint.y);
        //boolean collision = true;

        /*if ((position.getX() + this.getImgWidth() < position2.getX())
                || (position2.getX() + w2.getImgWidth() < position.getX())) {*/
        if( (position2.getX() + w2.getImgWidth() < position.getX()) || (position2.getX() > (position.getX() + this.getImgWidth()))){
            //collision = false;
            return false;
        } /*else {
            System.out.println("X: w1: " + this.startingPoint.x + "," + (this.startingPoint.x + this.getImgWidth()) + "/w2: "+ w2.startingPoint.x + "," + (w2.startingPoint.x + w2.getImgWidth()));
        }*/
        /*if ((position.getY() + this.getImgHeight() < position2.getY())
                || (position2.y + w2.getImgHeight() < position.y)) {*/
        if( (position2.getY() - w2.getImgHeight() > position.getY()) || (position2.getY() < (position.getY() - this.getImgHeight()))){
            //collision = false;
            return false;
        } /*else {
            System.out.println("Y: w1: " + this.startingPoint.y + "," + (this.startingPoint.y + this.getImgHeight()) + "/w2: "+ w2.startingPoint.y + "," + (w2.startingPoint.y + w2.getImgHeight()));
        }*/
        return true;//collision;
    }
}