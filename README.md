# Y2S4DSA_WordCloudGenerator

Author: Eoin Wilkie

Project: A Word Cloud Generator

Description:
Console menu requires filename or webpage, aswell as a word cloud size and a save file name.
The file type must be .txt.
The console menu will loop until the user requests exit by inputting N/n.

Words are parsed from either a file or html, and are added to a HashMap. Ignorewords are stored in a TreeSet
Additional checks are made to eliminate non-words are made for both files and HTML, such as words contained with in < and > in html.
The HashMap is placed in a PriorityQueue as Word objects using WordComparator to order for useage in the Word Cloud.
This is done for quick ordering of the collection.

The WordCloudGenerator class is called with the PriorityQueue as an argument.
Words are placed on the image. First placement is always central, afterwards words are placed within sections of the wordCloudaround this first word, 
until the available space is filled and then the placeable space is increased.
This is done inefficiently with random placement used but it works with collision checking to ensure words are not overlapping.
Word image size is determined by seperating the words into 5 catagories. The first word starts as 5, if the next word has a similar word count,
it will also be 5 but if it is significantly less it will be 4 until a minimum of 1. 
Colouring does not indicate word weight, it is applied randomly.

Word objects are created with (word, count) arguments,
additional variables include weight, which determines the size of the word on the word image, as described previously.
There are also functions to determine the word image parameters, which are used for collision checking.
