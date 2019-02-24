package ie.gmit.sw;

import java.util.HashMap;
import java.util.Map;

//import java.util.*;
//import java.util.Scanner;

public class WordCloudGenerator {
	//main may be cleaned up, decide later
	public static void main(String[] args) {
		//what class should this be in, perhaps its own class. Should it be static?

		/*
		 * present the user with a suite of choices
		 * The name of the file or URL. Do not hardcode file names, paths, URL or any
		 * environmental variables into your application. Doing so is a cardinal sin in
		 * computer programming. Instead, ask the user to enter these variables as input
		 * parameters from the menu.
		 * The maximum number of words to display.
		 * The file name of the word-cloud image to save. 
		*/
		/*
		 * My understanding of this is, at the start of the program the user must enter the following 3 things:
		 * Name of file or URL
		 * Max number of words to display
		 * File name to save the image as
		 */	
		
		//command-line menu
		String fileIn;
		int maxWords;
		String fileOut;
		char fileInType;
		Map<String, Integer> frequencyTable = new HashMap<String, Integer>();
		
		java.util.Scanner sc = new java.util.Scanner(System.in);
		//Scanner sc = new Scanner(System.in);
		
		//this should be wrapped in options, filter to decide if it is a www., html. or file
		//anything not begining with http or www will be a file, could have 2 options for file
		//search literal location, if not there, search folder sample-text-file
		//would allow for short inputs 
		System.out.println("Command-Line Menu");
		System.out.print("Enter FILENAME (http://www.oracle.com/), (sample-text-file//DeBelloGallico.txt):");
		fileIn = sc.nextLine();
		//following is concerned with the word cloud output
		System.out.print("Enter MAXWORDS:");
		maxWords = sc.nextInt();	
		sc.nextLine();
		/*
		System.out.print("Enter SAVEAS: ");
		fileOut = sc.nextLine();
		*/
		
		//Dealing with fileIn info
		fileInType = Parser.fileType(fileIn);
		
		switch(fileInType) {
			case 'f':
			case 'w':
				Parser.read(frequencyTable, fileIn, fileInType);
				break;
			default:
				System.out.println("Could not determine file/url.");
				break;
		}
		/*/tests
		frequencyTable.forEach((k, v) -> {
			//if(v > 20)
			System.out.format("Word: %s\tCount: %d%n", k, v);
		});
		//*/			
		System.out.println("Word count: " + frequencyTable.size());
		
		/*
		 * Test mapCompatator
		 */
		TempFunctions tf = new TempFunctions();
		Map<String, Integer> ftReduced = tf.sortMap(frequencyTable, maxWords);
		
		//tf.sortMap(frequencyTable, maxWords);
		//tests
		frequencyTable.clear();
		System.out.println("Table size: " + frequencyTable.size());
		System.out.println("Table size: " + ftReduced.size());
		ftReduced.forEach((k, v) -> {
			//if(v > 20)
			System.out.format("Word: %s\tCount: %d%n", k, v);
		});
		
		//*/
		
		//System.out.printf("%nFILENAME: %s%nMAXWORDS: %d%nSAVEAS: %s%n", fileIn, maxWords, fileOut);



	}//main

}
