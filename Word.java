/*
Author: Eoin Wilkie
Class information:
    Words are converted to Word Class objections before being placed on the word cloud, this class calculates a weight value for the word within the word cloud,
    which determine the size the word will display on the word cloud. It also creates a Font for the image, which is the image placed on the word cloud.
    Font metrics are also calculated which are used to determine the size ofthe image, this is used within the collisionCheck method ensure that words on the word cloud do not overlap.

    Note: factor function is used to determine the word sizes on the word cloud, this divides the words into a number between 1-5, 5 being the largest.
        Factor value is based on the highWordCount, if the change in factor from 1 word to the next is too large, the highWordCount will be assigned the value of the
        first of these 2 words for further calculations, this ensures there should be representation in each of the values and the words will gradually descend in value
        as opposed to having 1 large word and the rest of the words all being the minimun size available.
*/

import java.awt.*;

public class Word{
    //Big-O running time: O(1)
    //All functions here are manipulating one object, using the variables of that object in some capacity, there is no loops
    static int[] wordSizes = {12, 12, 24, 36, 48, 62};

    private String word;
    private int count;
    private float weight;
    private static int highWordCount = 0;
    private static int factor = 5;
    private Font font;
    private int fontWidth;
    private int fontHeight;
    Point startingPoint;
    private static String fontType = "Serif";

    public Word(String word, int wordCount){
        this.setWord(word);
        this.setCount(wordCount);
    }//constructor

    //===== Setters/Getters[START] =====//

        //All setter and getters, if not otherwise stated:
        //Big-O running time: O(1) 
        //There are no loops, elements are being accessed at known locations.

    public void setWord(String word){
        this.word = word;
    }//setWord()

    public String getWord(){
        return this.word;
    }//getWord()

    public void setCount(int count){
        this.count = count;
    }//setCount()

    public int getCount(){
        return this.count;
    }//getCount()

    public void setWeight(float weight){
        this.weight = weight;
    }//setWeight()

    public float getWeight(){
        return this.weight;
    }//getWeight()

    public static void setFactor(int factor){
        Word.factor = factor;
    }

    public static int getFactor(){
        return Word.factor;
    }

    public static void setHighWordCount(int highCount){
        Word.highWordCount = highCount;
    } 

    public static int getHighWordCount(){
        return Word.highWordCount; 
    }

    //===== Setters/Getters[END] =====//

    //===== FontMetrics[START] =====//

        //All FontMetrics methods, if not otherwise stated:
        //Big-O running time: O(1) 
        //There are no loops, elements are being accessed at known locations.

    public void setFontMetrics(FontMetrics fm){
        this.setImgWidth(fm.stringWidth(this.word));
        this.setImgHeight(fm.getMaxDescent()); 
    }//setFontMetrics()

    public void setImgWidth(int width){
        this.fontWidth = width;
    }//setImgWidth()

    public int getImgWidth(){
        return this.fontWidth;
    }//getImgWidth()

    public void setImgHeight(int height){
        this.fontHeight = height;
    }//setImgHeight()

    public int getImgHeight(){
        return this.fontHeight * 3;
    }//getImgHeight()

    //===== FontMetrics[END] =====/

    /*
        Calculate the value of the word based on word count
        if words are not ordered as they are created, this would need be called on each word after all are created
        Unnecessary due to how they are created in this program.
    */
    public void calculateValue(){
        //Big-O running time: O(1)
        //There are no loops, elements are being accessed at known locations.
        //if (Word.getFactor() <= 0) Word.setFactor(1);
        if (this.getCount() <= 5)   Word.setFactor(1);
        if (Word.getHighWordCount() == 0) setHighWordCount(this.count);

        float weight = this.getCount() / Word.getHighWordCount()* factor;

        this.setWeight(weight);
        if(weight < Word.getFactor() - 0.5){
            if(Word.getFactor() > 1){
                Word.setHighWordCount(this.getCount());
                Word.setFactor(Word.getFactor() -1);
                this.setWeight(factor);
            }
        }
    }//calculateValue();

    public Font getFont(){
        return this.font;
    }//getFont()
    
    /*
        Creates a Font for each word based on word.weight
    */
    public void createFont(){
        //Big-O running time: O(1)
        //There are no loops, elements are being accessed at known locations

        int weight = (int)Math.ceil(this.weight);

        switch (weight){
            case 5:
                font = new Font(fontType, Font.BOLD, wordSizes[weight]);
                break;
            case 4:
                font = new Font(fontType, Font.BOLD, wordSizes[weight]);
                break;
            case 3:
                font = new Font(fontType, Font.BOLD, wordSizes[weight]);
                break;
            case 2:
                font = new Font(fontType, Font.BOLD, wordSizes[weight]);
                break;
            case 1:
                font = new Font(fontType, Font.BOLD, wordSizes[weight]);
                break;
            default:
                //do not reach 
                font = new Font(fontType, Font.BOLD, wordSizes[0]);
                break;
        }
    }//createFont()

    /*
        Checks for collision between 2 words based on ImgWidth and ImgHeight values
    */
    public boolean collisionCheck(Word w2){
        //Big-O running time: O(1)
        //Checks the corners of the image, max 4 checks, min 2 checks.

        final Point position = this.startingPoint;
        final Point position2 = w2.startingPoint;
        
        if( (position2.getX() + w2.getImgWidth() < position.getX()) || (position2.getX() > (position.getX() + this.getImgWidth())))
            return false;
        if( (position2.getY() - w2.getImgHeight() > position.getY()) || (position2.getY() < (position.getY() - this.getImgHeight())))
            return false;
        return true;
    }//collisionCheck()
}